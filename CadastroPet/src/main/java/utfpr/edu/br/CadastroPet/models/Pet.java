package utfpr.edu.br.CadastroPet.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String genero;
    private String castrado;
    private Integer idade;
    private String especie;
    private String restricaoComorbidade;
    private String nomeTutor;
    private String contato1Tutor;
    private String contato2Tutor;
    private String endereco;
    private String outros;

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
