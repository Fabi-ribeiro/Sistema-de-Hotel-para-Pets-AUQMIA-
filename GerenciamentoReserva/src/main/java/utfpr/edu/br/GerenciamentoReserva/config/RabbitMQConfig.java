package utfpr.edu.br.GerenciamentoReserva.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
public class RabbitMQConfig {

    public static final String RESERVA_QUEUE = "reservaQueue";
    public static final String SUCESSO_QUEUE = "reservaSucessoQueue";
    public static final String NAO_APROVADA_QUEUE = "reservaNaoAprovadaQueue";
    public static final String EXCHANGE = "reservaExchange";
    public static final String ROUTING_KEY = "reservaKey";
    public static final String SUCESSO_ROUTING_KEY = "reservaSucessoKey";
    public static final String NAO_APROVADA_ROUTING_KEY = "reservaNaoAprovadaKey";

    @Bean
    public Queue reservaQueue() {
        return new Queue(RESERVA_QUEUE, true);
    }

    @Bean
    public Queue reservaSucessoQueue() {
        return new Queue(SUCESSO_QUEUE, true);
    }

    @Bean
    public Queue reservaNaoAprovadaQueue() {
        return new Queue(NAO_APROVADA_QUEUE, true);
    }

    @Bean
    public DirectExchange reservaExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding bindingReserva(@Qualifier("reservaQueue") Queue reservaQueue, 
                                  @Qualifier("reservaExchange") DirectExchange reservaExchange) {
        return BindingBuilder.bind(reservaQueue).to(reservaExchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding bindingSucesso(@Qualifier("reservaSucessoQueue") Queue reservaSucessoQueue, 
                                  @Qualifier("reservaExchange") DirectExchange reservaExchange) {
        return BindingBuilder.bind(reservaSucessoQueue).to(reservaExchange).with(SUCESSO_ROUTING_KEY);
    }

    @Bean
    public Binding bindingNaoAprovada(@Qualifier("reservaNaoAprovadaQueue") Queue reservaNaoAprovadaQueue, 
                                       @Qualifier("reservaExchange") DirectExchange reservaExchange) {
        return BindingBuilder.bind(reservaNaoAprovadaQueue).to(reservaExchange).with(NAO_APROVADA_ROUTING_KEY);
    }
}
