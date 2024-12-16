package utfpr.edu.br.GerenciamentoReserva.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utfpr.edu.br.GerenciamentoReserva.models.Reserva;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    
    @Query("SELECT r FROM Reserva r WHERE r.dataInicio <= :fim AND r.dataFim >= :inicio")
    List<Reserva> findByDataInicioLessThanEqualAndDataFimGreaterThanEqual(
            @Param("fim") LocalDateTime fim,
            @Param("inicio") LocalDateTime inicio
    );
}