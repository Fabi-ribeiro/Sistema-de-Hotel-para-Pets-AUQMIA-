package utfpr.edu.br.Cadastro_cuidador.funcionario.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Envia a mensagem para a fila reservaQueue
    public void enviarMensagem(String menssagem) {
        rabbitTemplate.convertAndSend("reservaQueue", menssagem);
    }
    
    // Envia a mensagem para a fila reservaSucessoQueue
    public void EnviarMensagemParaSucesso(String menssagem) {
        rabbitTemplate.convertAndSend("reservaSucessoQueue", menssagem);
    }

    // Envia a mensagem para a fila reservaNaoAprovadaQueue
    public void enviarMensagemParaNaoAprovada(String mensagem) {
        rabbitTemplate.convertAndSend("reservaNaoAprovadaQueue", mensagem);
    }
}
