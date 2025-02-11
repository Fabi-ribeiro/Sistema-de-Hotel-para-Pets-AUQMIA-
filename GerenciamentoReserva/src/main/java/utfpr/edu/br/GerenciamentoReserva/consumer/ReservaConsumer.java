package utfpr.edu.br.GerenciamentoReserva.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.Message;
import utfpr.edu.br.GerenciamentoReserva.service.ReservaService;
import utfpr.edu.br.GerenciamentoReserva.config.RabbitMQConfig;

@Service
public class ReservaConsumer {

    @Autowired
    private ReservaService reservaService; // Injetar o serviço de reserva

    @RabbitListener(queues = RabbitMQConfig.FUNCIONARIO_QUEUE)
    public void receberMensagem(Message message) {
        try {
            String corpo = new String(message.getBody());
            String status = (String) message.getMessageProperties().getHeaders().get("status");
            System.out.println("Mensagem recebida: " + corpo);

            if (status == null) {
                System.out.println("Status está nulo. Mensagem não será processada.");
                return; // Evita que o switch seja executado com status nulo
            }

            // Processar a mensagem com base no status
            switch (status) {
                case "sucesso":
                    processarMensagemDeSucesso(corpo);
                    break;
                case "erro":
                    processarMensagemDeErro(corpo);
                    break;
                case "invalido":
                    processarMensagemInvalida(corpo);
                    break;
                default:
                    System.out.println("Status desconhecido: " + status);
                    break;
            }
        } catch (Exception e) {
            // Log do erro
            System.err.println("Erro ao processar a mensagem: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processarMensagemDeSucesso(String mensagem) {
        System.out.println("[SUCESSO] Mensagem recebida: " + mensagem);
        // Atualizar status no banco
        try {
            reservaService.atualizarStatusReserva(mensagem, "CONFIRMADA - reserva confirmada");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar status de sucesso: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processarMensagemDeErro(String mensagem) {
        System.out.println("[ERRO] Mensagem recebida: " + mensagem);
        // Atualizar status da reserva no banco
        try {
            reservaService.atualizarStatusReserva(mensagem, "ERRO - reserva não confirmada");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar status de erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processarMensagemInvalida(String mensagem) {
        System.out.println("[INVÁLIDO] Mensagem recebida: " + mensagem);
        // Atualizar status da reserva no banco
        try {
            reservaService.atualizarStatusReserva(mensagem, "INVALIDA - reserva não confirmada");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar status de inválido: " + e.getMessage());
            e.printStackTrace();
        }
    }
}