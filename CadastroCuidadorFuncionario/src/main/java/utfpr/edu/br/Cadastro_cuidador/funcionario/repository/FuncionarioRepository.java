package utfpr.edu.br.Cadastro_cuidador.funcionario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.edu.br.Cadastro_cuidador.funcionario.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}