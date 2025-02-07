package utfpr.edu.br.CadastroPet.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do Pet é obrigatório.")
    private String nome;

    @NotBlank(message = "O gênero do Pet é obrigatório.")
    private String genero;

    private String castrado;

    @NotNull(message = "A idade do Pet é obrigatória.")
    @Min(value = 0, message = "A idade do Pet não pode ser negativa.")
    @Max(value = 100, message = "Idade inválida para um pet.")
    private Integer idade;

    @NotBlank(message = "A espécie do Pet é obrigatória.")
    private String especie;

    private String restricaoComorbidade;

    @NotBlank(message = "O nome do Tutor é obrigatório.")
    private String nomeTutor;

    @NotBlank(message = "O contato Principal do Tutor é obrigatório.")
    private String contato1Tutor;

    @NotBlank(message = "O contato Secundário do Tutor é obrigatório.")
    private String contato2Tutor;

    @NotBlank(message = "O endereço do Tutor é obrigatório.")
    private String endereco;

    private String outros;

    // Getters e Setters
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getCastrado() {
        return castrado;
    }
    public void setCastrado(String castrado) {
        this.castrado = castrado;
    }
    public Integer getIdade() {
        return idade;
    }
    public void setIdade(Integer idade) {
        this.idade = idade;
    }
    public String getEspecie() {
        return especie;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    public String getRestricaoComorbidade() {
        return restricaoComorbidade;
    }
    public void setRestricaoComorbidade(String restricaoComorbidade) {
        this.restricaoComorbidade = restricaoComorbidade;
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
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getOutros() {
        return outros;
    }
    public void setOutros(String outros) {
        this.outros = outros;
    }

}
