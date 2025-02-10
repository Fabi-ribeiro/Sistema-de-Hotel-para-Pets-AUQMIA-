package utfpr.edu.br.GerenciamentoReserva.service;

import utfpr.edu.br.GerenciamentoReserva.models.Reserva;
import utfpr.edu.br.GerenciamentoReserva.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public void atualizarStatusReserva(String reservaId, String novoStatus) {
        try {
            // Tenta encontrar a reserva pelo ID
            Optional<Reserva> reservaOptional = reservaRepository.findById(Long.valueOf(reservaId));
            if (reservaOptional.isPresent()) {
                Reserva reserva = reservaOptional.get();
                reserva.setStatus(novoStatus);
                reservaRepository.save(reserva);
                System.out.println("Status da reserva atualizado com sucesso: " + reservaId + " para " + novoStatus);
            } else {
                System.out.println("Reserva não encontrada para o ID: " + reservaId);
            }
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter o ID da reserva para Long: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro ao atualizar o status da reserva: " + e.getMessage());
            e.printStackTrace();
            // Você pode optar por lançar a exceção novamente se necessário
            throw e; // Re-lançar a exceção se necessário
        }
    }
}