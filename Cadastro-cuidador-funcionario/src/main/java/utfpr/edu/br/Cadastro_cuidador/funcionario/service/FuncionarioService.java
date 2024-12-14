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

    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
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
}
