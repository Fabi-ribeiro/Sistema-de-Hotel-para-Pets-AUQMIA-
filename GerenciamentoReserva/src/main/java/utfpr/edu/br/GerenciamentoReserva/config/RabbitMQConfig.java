package utfpr.edu.br.GerenciamentoReserva.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
public class RabbitMQConfig {

    public static final String FUNCIONARIO_QUEUE = "funcionarioQueue";
    public static final String DISPONIVEL_QUEUE = "funcionarioDisponivelQueue";
    public static final String INDISPONIVEL_QUEUE = "funcionarioIndisponivelQueue";
    public static final String EXCHANGE = "funcionarioExchange";
    public static final String ROUTING_KEY = "funcionarioKey";
    public static final String DISPONIVEL_ROUTING_KEY = "funcionarioDisponivelKey";
    public static final String INDISPONIVEL_ROUTING_KEY = "funcionarioIndisponivelKey";

    @Bean
    public Queue funcionarioQueue() {
        return new Queue(FUNCIONARIO_QUEUE, true);
    }

    @Bean
    public Queue funcionarioDisponivelQueue() {
        return new Queue(DISPONIVEL_QUEUE, true);
    }

    @Bean
    public Queue funcionarioIndisponivelQueue() {
        return new Queue(INDISPONIVEL_QUEUE, true);
    }

    @Bean
    public DirectExchange funcionarioExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding bindingFuncionario(@Qualifier("funcionarioQueue") Queue funcionarioQueue, 
                                      @Qualifier("funcionarioExchange") DirectExchange funcionarioExchange) {
        return BindingBuilder.bind(funcionarioQueue).to(funcionarioExchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding bindingDisponivel(@Qualifier("funcionarioDisponivelQueue") Queue funcionarioDisponivelQueue, 
                                     @Qualifier("funcionarioExchange") DirectExchange funcionarioExchange) {
        return BindingBuilder.bind(funcionarioDisponivelQueue()).to(funcionarioExchange).with(DISPONIVEL_ROUTING_KEY);
    }

    @Bean
    public Binding bindingIndisponivel(@Qualifier("funcionarioIndisponivelQueue") Queue funcionarioIndisponivelQueue, 
                                       @Qualifier("funcionarioExchange") DirectExchange funcionarioExchange) {
        return BindingBuilder.bind(funcionarioIndisponivelQueue).to(funcionarioExchange).with(INDISPONIVEL_ROUTING_KEY);
    }
}
