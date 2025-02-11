package utfpr.edu.br.GerenciamentoReserva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import utfpr.edu.br.GerenciamentoReserva.dtos.FuncionarioDTO;
import utfpr.edu.br.GerenciamentoReserva.dtos.PetDTO;
import utfpr.edu.br.GerenciamentoReserva.models.Reserva;
import utfpr.edu.br.GerenciamentoReserva.repository.ReservaRepository;
import javax.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String FUNCIONARIO_SERVICE_URL = "http://localhost:8081/api/funcionarios";
    private static final String PET_SERVICE_URL = "http://localhost:8080/pets";

    @PostMapping
    public ResponseEntity<?> criarReserva(@Valid @RequestBody Reserva reserva) {
        boolean disponivel = verificarDisponibilidadeDatas(reserva.getDataInicio(), reserva.getDataFim());
        if (!disponivel) {
            return ResponseEntity.badRequest().body("Datas não estão disponíveis para reserva.");
        }
          
        try {
            ResponseEntity<PetDTO> petResponse = restTemplate.getForEntity(
                PET_SERVICE_URL + "/" + reserva.getPetId(), 
                PetDTO.class
            );
            if (!petResponse.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.badRequest().body("Pet não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao verificar pet: " + e.getMessage());
        }

        try {
            Long funcionarioId = reserva.getCuidadorId();
            ResponseEntity<FuncionarioDTO> funcResponse = restTemplate.getForEntity(
                FUNCIONARIO_SERVICE_URL + "/" + funcionarioId, 
                FuncionarioDTO.class
            );
            if (funcResponse.getStatusCode().is2xxSuccessful() && funcResponse.getBody() != null) {
                FuncionarioDTO funcionario = funcResponse.getBody();
                if (funcionario != null && funcionario.isDisponivel()) {
                    reserva.setCuidadorId(funcionario.getId());
                } else {
                    return ResponseEntity.badRequest().body("Funcionário não está disponível!");
                }
            } else {
                return ResponseEntity.badRequest().body("Funcionário não foi encontrado!");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao alocar cuidador: " + e.getMessage());
        }

        reserva.setStatus("PENDENTE");
        Reserva novaReserva = reservaRepository.save(reserva);
        return ResponseEntity.ok(novaReserva);
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas() {
        return ResponseEntity.ok(reservaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarReserva(@PathVariable Long id) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);
        
        if (reservaOptional.isPresent()) {
            return ResponseEntity.ok(reservaOptional.get());
        } else {
            String mensagemErro = "A Reserva " + id + " não foi encontrada!";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagemErro);
        }
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarReserva(@PathVariable Long id, @RequestBody Reserva reservaAtualizada) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);

        if (reservaOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();
            reserva.setDataInicio(reservaAtualizada.getDataInicio());
            reserva.setDataFim(reservaAtualizada.getDataFim());
            reserva.setPetId(reservaAtualizada.getPetId());
            reserva.setCuidadorId(reservaAtualizada.getCuidadorId());
            reserva.setStatus(reservaAtualizada.getStatus());
            reserva.setCancelada(reservaAtualizada.isCancelada());

            return ResponseEntity.ok(reservaRepository.save(reserva));
        } else {
            String mensagemErro = "A Reserva " + id + " não foi encontrada!";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagemErro);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long id) {
        return reservaRepository.findById(id)
            .map(reserva -> {
                reservaRepository.delete(reserva);
                return ResponseEntity.ok().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }

    private boolean verificarDisponibilidadeDatas(LocalDateTime inicio, LocalDateTime fim) {
        List<Reserva> reservasConflitantes = reservaRepository
            .findByDataInicioLessThanEqualAndDataFimGreaterThanEqual(fim, inicio);
        return reservasConflitantes.isEmpty();
    }
}