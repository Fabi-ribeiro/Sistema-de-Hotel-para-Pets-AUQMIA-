package utfpr.edu.br.GerenciamentoReserva.dtos;

public class PetDTO {
    private Long id;
    private String nome;
    private String especie;
    private String nomeTutor;
    private String contato1Tutor;
    private String contato2Tutor;
    
    public PetDTO() {}
    
    public PetDTO(Long id, String nome, String especie, String nomeTutor, 
                  String contato1Tutor, String contato2Tutor) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.nomeTutor = nomeTutor;
        this.contato1Tutor = contato1Tutor;
        this.contato2Tutor = contato2Tutor;
    }
    
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
    
    public String getEspecie() {
        return especie;
    }
    
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    
    public String getNomeTutor() {
        return nomeTutor;
    }
    
    public void setNomeTutor(String nomeTutor) {
        this.nomeTutor = nomeTutor;
    }
    
    public String getContato1Tutor() {
        return contato1Tutor;
    }
    
    public void setContato1Tutor(String contato1Tutor) {
        this.contato1Tutor = contato1Tutor;
    }
    
    public String getContato2Tutor() {
        return contato2Tutor;
    }
    
    public void setContato2Tutor(String contato2Tutor) {
        this.contato2Tutor = contato2Tutor;
    }
}