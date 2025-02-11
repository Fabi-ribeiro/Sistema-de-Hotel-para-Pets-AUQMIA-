package utfpr.edu.br.GerenciamentoReserva.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Data de início é obrigatória.")
    @Column(nullable = false)
    private LocalDateTime dataInicio;
    
    @NotNull(message = "Data de fim é obrigatória.")
    @Column(nullable = false)
    private LocalDateTime dataFim;

    @Column(nullable = false)
    private boolean cancelada = false;

    @NotNull(message = "O ID do Pet é obrigatório.")
    @Column(name = "pet_id", nullable = false)
    private Long petId;

    @NotNull(message = "O ID do Cuidador é obrigatório.")
    @Column(name = "cuidador_id", nullable = false)
    private Long cuidadorId;

    @Column(nullable = false)
    private String status;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public Long getCuidadorId() {
        return cuidadorId;
    }

    public void setCuidadorId(Long cuidadorId) {
        this.cuidadorId = cuidadorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}