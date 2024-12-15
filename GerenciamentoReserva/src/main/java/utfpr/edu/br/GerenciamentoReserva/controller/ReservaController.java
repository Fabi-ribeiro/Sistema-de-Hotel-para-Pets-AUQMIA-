package main.java.utfpr.edu.br.GerenciamentoReserva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utfpr.edu.br.GerenciamentoReserva.models.Reserva;
import utfpr.edu.br.GerenciamentoReserva.models.ReservaRepository;
import utfpr.edu.br.Cadastro_cuidador.funcionario.repository.FuncionarioRepository;
import utfpr.edu.br.Cadastro_Pet.repository.petRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private petRepository petRepository;

    @PostMapping
    public ResponseEntity<?> criarReserva(@RequestBody Reserva reserva) {
        boolean disponivel = verificarDisponibilidadeDatas(reserva.getDataInicio(), reserva.getDataFim());
        if (!disponivel) {
            return ResponseEntity.badRequest().body("Datas não estão disponíveis para reserva.");
        }

        Optional<Long> cuidadorId = alocarCuidador(reserva.getDataInicio(), reserva.getDataFim());
        if (!cuidadorId.isPresent()) {
            return ResponseEntity.badRequest().body("Não há cuidadores disponíveis para a quantidade de pets.");
        }

        reserva.setCuidador(funcionarioRepository.findById(cuidadorId.get()).orElse(null));

        Reserva novaReserva = reservaRepository.save(reserva);
        return ResponseEntity.ok(novaReserva);
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/disponibilidade")
    public ResponseEntity<Boolean> verificarDisponibilidade(
            @RequestParam LocalDateTime dataInicio,
            @RequestParam LocalDateTime dataFim) {
        List<Reserva> reservasConflitantes = reservaRepository
                .findByDataInicioLessThanEqualAndDataFimGreaterThanEqual(dataFim, dataInicio);
        return ResponseEntity.ok(reservasConflitantes.isEmpty());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarReserva(@PathVariable Long id, @RequestBody Reserva reservaAtualizada) {
        Optional<Reserva> reservaExistente = reservaRepository.findById(id);

        if (reservaExistente.isPresent()) {
            Reserva reserva = reservaExistente.get();

            boolean disponivel = verificarDisponibilidadeDatas(reservaAtualizada.getDataInicio(), reservaAtualizada.getDataFim());
            if (!disponivel) {
                return ResponseEntity.badRequest().body("Datas atualizadas não estão disponíveis para reserva.");
            }

            if (!reserva.getDataInicio().equals(reservaAtualizada.getDataInicio()) ||
                !reserva.getDataFim().equals(reservaAtualizada.getDataFim())) {
                Optional<Long> cuidadorId = alocarCuidador(reservaAtualizada.getDataInicio(), reservaAtualizada.getDataFim());
                if (!cuidadorId.isPresent()) {
                    return ResponseEntity.badRequest().body("Não há cuidadores disponíveis para a quantidade de pets.");
                }
                reserva.setCuidador(funcionarioRepository.findById(cuidadorId.get()).orElse(null));
            }

            reserva.setDataInicio(reservaAtualizada.getDataInicio());
            reserva.setDataFim(reservaAtualizada.getDataFim());
            reserva.setPet(reservaAtualizada.getPet());
            reserva.setStatus(reservaAtualizada.getStatus());
            reserva.setCancelada(reservaAtualizada.isCancelada());

            Reserva reservaSalva = reservaRepository.save(reserva);
            return ResponseEntity.ok(reservaSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private boolean verificarDisponibilidadeDatas(LocalDateTime inicio, LocalDateTime fim) {
        List<Reserva> reservasConflitantes = reservaRepository
                .findByDataInicioLessThanEqualAndDataFimGreaterThanEqual(fim, inicio);
        return reservasConflitantes.isEmpty();
    }

    private Optional<Long> alocarCuidador(LocalDateTime inicio, LocalDateTime fim) {
        List<Reserva> reservasConflitantes = reservaRepository
                .findByDataInicioLessThanEqualAndDataFimGreaterThanEqual(fim, inicio);
        int totalPets = reservasConflitantes.size();

        int cuidadoresNecessarios = (int) Math.ceil(totalPets / 5.0);

        List<Long> cuidadoresDisponiveis = funcionarioRepository.findAvailableCuidadores(inicio, fim);

        if (cuidadoresDisponiveis.size() >= cuidadoresNecessarios) {
            return Optional.of(cuidadoresDisponiveis.get(0));
        } else {
            return Optional.empty();
        }
    }
}