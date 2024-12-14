package utfpr.edu.br.Cadastro_cuidador.funcionario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
          .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    public Funcionario salvarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void deletarFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }

    public Funcionario atualizarFuncionario(Long id, Funcionario funcionarioAtualizado) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        // Atualizando os campos
        funcionarioExistente.setNome(funcionarioAtualizado.getNome());
        funcionarioExistente.setAgenda(funcionarioAtualizado.getAgenda());
        funcionarioExistente.setFuncao(funcionarioAtualizado.getFuncao());
        funcionarioExistente.setHorariosDeTrabalho(funcionarioAtualizado.getHorariosDeTrabalho());

        // Salvando no banco de dados
        return funcionarioRepository.save(funcionarioExistente);
    }
}
