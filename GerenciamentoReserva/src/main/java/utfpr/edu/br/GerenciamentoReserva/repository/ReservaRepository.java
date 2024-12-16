package main.java.utfpr.edu.br.GerenciamentoReserva.models;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.edu.br.GerenciamentoReserva.models.Reserva;

public interface ReservaRepository extends JpaRepository<Pet, Long> {
}