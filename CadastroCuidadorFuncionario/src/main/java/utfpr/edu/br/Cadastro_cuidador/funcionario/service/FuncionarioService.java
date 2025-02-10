package utfpr.edu.br.Cadastro_cuidador.funcionario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utfpr.edu.br.Cadastro_cuidador.funcionario.exceptionhadler.FuncionarioNotFoundException;
import utfpr.edu.br.Cadastro_cuidador.funcionario.model.Funcionario;
import utfpr.edu.br.Cadastro_cuidador.funcionario.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listarTodos() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        System.out.println("Funcionários encontrados: " + funcionarios);
        return funcionarios;
    }

    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
        .orElseThrow(FuncionarioNotFoundException::new);
    }   


    public Funcionario salvarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void deletarFuncionario(Long id) {
        if (!funcionarioRepository.existsById(id)) {
           throw new FuncionarioNotFoundException(); // Lança a exceção se o funcionário não existir
    }
    funcionarioRepository.deleteById(id);
}

    public Funcionario atualizarFuncionario(Long id, Funcionario funcionarioAtualizado) {
        Funcionario funcionario = funcionarioRepository.findById(id)
        .orElseThrow(FuncionarioNotFoundException::new);  

        // Atualizando os campos
        funcionario.setNome(funcionarioAtualizado.getNome());
        funcionario.setAgenda(funcionarioAtualizado.getAgenda());
        funcionario.setFuncao(funcionarioAtualizado.getFuncao());
        funcionario.setHorariosDeTrabalho(funcionarioAtualizado.getHorariosDeTrabalho());

        // Salvando no banco de dados
        return funcionarioRepository.save(funcionario);
    }

}