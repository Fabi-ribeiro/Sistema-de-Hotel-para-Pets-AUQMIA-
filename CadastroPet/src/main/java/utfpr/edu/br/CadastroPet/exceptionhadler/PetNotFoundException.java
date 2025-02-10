package utfpr.edu.br.CadastroPet.exceptionhadler;

public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException() {
        super("Pet não encontrado!"); // Mensagem padrão
    }
}