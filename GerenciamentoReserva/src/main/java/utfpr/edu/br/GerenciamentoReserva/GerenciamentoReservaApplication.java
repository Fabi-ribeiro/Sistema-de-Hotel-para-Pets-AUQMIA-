package utfpr.edu.br.GerenciamentoReserva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GerenciamentoReservaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GerenciamentoReservaApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}