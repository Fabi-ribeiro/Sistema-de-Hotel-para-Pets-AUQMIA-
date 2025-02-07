package utfpr.edu.br.Cadastro_cuidador.funcionario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name ="funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do Funcionario é obrigatório")
    private String nome;

    private String agenda;

    @NotBlank(message = "A função do Funcionario é obrigatória")
    private String funcao;
    
    private String horariosDeTrabalho;
    
    private boolean disponivel; 

    public Funcionario(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getHorariosDeTrabalho() {
        return horariosDeTrabalho;
    }

    public void setHorariosDeTrabalho(String horariosDeTrabalho) {
        this.horariosDeTrabalho = horariosDeTrabalho;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}