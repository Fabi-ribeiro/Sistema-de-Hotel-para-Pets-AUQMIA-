package utfpr.edu.br.CadastroPet.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

public class RabbitMQConfig {

   @Bean
   public Queue verificarDisponibilidadeQueue(){
    return new Queue("verificarDisponibilidade", true);
   }

   @Bean
   public Queue reservaSucessoQueue(){
    return new Queue("reservaSucessoQueue", true);
   }

    @Bean
    public Queue reservaFalhaQueue(){
     return new Queue("reservaFalhaQueue", true);
    }

}
