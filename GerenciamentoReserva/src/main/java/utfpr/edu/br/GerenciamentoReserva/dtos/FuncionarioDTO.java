package utfpr.edu.br.GerenciamentoReserva.dtos;

public class FuncionarioDTO {
    private Long id;
    private String nome;
    private String agenda;
    private String funcao;
    private String horariosDeTrabalho;
    private boolean disponivel;

    public FuncionarioDTO() {}

    public FuncionarioDTO(Long id, String nome, String agenda, String funcao, String horariosDeTrabalho, boolean disponivel) 
    {
        this.id = id;
        this.nome = nome;
        this.agenda = agenda;
        this.funcao = funcao;
        this.horariosDeTrabalho = horariosDeTrabalho;
        this.disponivel = disponivel;
    }

    // Getters e Setters
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