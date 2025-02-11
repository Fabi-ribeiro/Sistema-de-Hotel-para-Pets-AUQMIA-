package utfpr.edu.br.Cadastro_cuidador.funcionario.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FuncionarioDTO(
        @NotNull(message = "O nome não pode ser nulo")
        @NotBlank(message = "Obrigatório informar o Nome.")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,

        @NotBlank(message = "Obrigatório informar a Função.")
        String funcao,

        @NotBlank(message="Obrigatório informar a disponibilidade")
        @NotNull(message="Obrigatório informar a disponibilidade")
        String disponivel
) {}