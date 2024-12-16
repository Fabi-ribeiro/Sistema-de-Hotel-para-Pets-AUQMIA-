package main.java.utfpr.edu.br.GerenciamentoReserva.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataInicio;

    @Column(nullable = false)
    private LocalDateTime dataFim;

    @Column(nullable = false)
    private boolean cancelada = false;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private CadastroPet pet;

    @ManyToOne
    @JoinColumn(name = "cuidador_id", nullable = false)
    private CadastroCuidador cuidador;

    @Column(nullable = false)
    private String status; // Ex.: "CONFIRMADA", "PENDENTE", "CANCELADA"

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

    public CadastroPet getPet() {
        return pet;
    }

    public void setPet(CadastroPet pet) {
        this.pet = pet;
    }

    public CadastroCuidador getCuidador() {
        return cuidador;
    }

    public void setCuidador(CadastroCuidador cuidador) {
        this.cuidador = cuidador;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
