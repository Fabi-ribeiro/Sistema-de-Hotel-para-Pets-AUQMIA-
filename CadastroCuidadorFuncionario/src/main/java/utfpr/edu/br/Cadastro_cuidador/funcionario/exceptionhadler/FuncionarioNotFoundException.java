package utfpr.edu.br.Cadastro_cuidador.funcionario.exceptionhadler;

public class FuncionarioNotFoundException extends RuntimeException {
    public FuncionarioNotFoundException() {
        super("Funcionario não encontrado!");
    }

    public FuncionarioNotFoundException(String message) {
        super(message);
    }  
}
