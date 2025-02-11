package utfpr.edu.br.Cadastro_cuidador.funcionario.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

@Service
public class FuncionarioProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Envia a mensagem para a fila funcionarioQueue
    public void enviarMensagem(String menssagem) {
        enviarComStatus("funcionarioQueue", menssagem, "default");
    }
    
    // Envia a mensagem para a fila funcionarioDisponivelQueue
    public void enviarMensagemParaDisponivel(String menssagem) {
        enviarComStatus("funcionarioDisponivelQueue", menssagem, "success");
    }

    // Envia a mensagem para a fila funcionarioIndisponivelQueue
    public void enviarMensagemParaIndisponivel(String mensagem) {
        enviarComStatus("funcionarioIndisponivelQueue", mensagem, "failed");
    }

    // Método genérico para enviar mensagens com o status correto
    private void enviarComStatus(String queue, String mensagem, String status) {
        MessageProperties properties = new MessageProperties();
        properties.setHeader("status", status);  // Define o header status
        Message message = new Message(mensagem.getBytes(), properties);

        rabbitTemplate.send(queue, message);
    }
}
