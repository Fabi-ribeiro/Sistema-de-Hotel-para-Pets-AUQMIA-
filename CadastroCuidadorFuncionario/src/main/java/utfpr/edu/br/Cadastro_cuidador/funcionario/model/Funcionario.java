package utfpr.edu.br.Cadastro_cuidador.funcionario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name ="funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "O Nome é obrigatório")
    @Size(min = 3, max = 100, message = "O Nome deve ter entre 3 e 100 caracteres")
    private String nome;
    
    @NotNull(message = "A agenda é obrigatória")
    private String agenda;

    @NotNull(message = "A função é obrigatória")
    @Size(min = 3, max = 100, message = "A descricão da função deve ter entre 3 e 100 caracteres")
    private String funcao;

    private String horariosDeTrabalho;

    @NotNull(message = "A disponibilidade é obrigatória")
    private boolean disponivel; 

    public Funcionario(){}
    
    // getters e setters
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