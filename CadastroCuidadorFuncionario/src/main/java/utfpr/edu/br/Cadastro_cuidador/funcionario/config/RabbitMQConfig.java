package utfpr.edu.br.Cadastro_cuidador.funcionario.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

public class RabbitMQConfig {

   @Bean
   public Queue funcionarioQueue(){
    return new Queue("funcionarioQueue", true);
   }

   @Bean
   public Queue funcionarioDisponivelQueue(){
    return new Queue("funcionarioDisponivelQueue", true);
   }
   
    @Bean
    public Queue funcionarioIndisponivelQueue(){
     return new Queue("funcionarioIndisponivelQueue", true);
    }
}
