package utfpr.edu.br.GerenciamentoReserva.service;

import utfpr.edu.br.GerenciamentoReserva.models.Reserva;
import utfpr.edu.br.GerenciamentoReserva.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    private static final Logger logger = LoggerFactory.getLogger(ReservaService.class);

    public void atualizarStatusReserva(String reservaId, String novoStatus) {
        if (reservaId == null || reservaId.trim().isEmpty()) {
            logger.error("ID da reserva não pode ser nulo ou vazio.");
            return; 
        }
        try {
            // Tenta encontrar a reserva pelo ID
            Optional<Reserva> reservaOptional = reservaRepository.findById(Long.valueOf(reservaId));
            if (reservaOptional.isPresent()) {
                Reserva reserva = reservaOptional.get();
                reserva.setStatus(novoStatus);
                reservaRepository.save(reserva);
                logger.info("Status da reserva atualizado com sucesso: {} para {}", reservaId, novoStatus);
            } else {
                logger.warn("Reserva não encontrada para o ID: {}", reservaId);
            }
        } catch (NumberFormatException e) {
            logger.error("Erro ao converter o ID da reserva para Long: {}", e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("Erro ao atualizar o status da reserva: {}", e.getMessage());
            e.printStackTrace();
            throw e; // Re-lançar a exceção se necessário
        }
    }
}