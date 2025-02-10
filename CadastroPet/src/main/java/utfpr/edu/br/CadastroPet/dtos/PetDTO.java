package utfpr.edu.br.CadastroPet.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetDTO(
    @NotNull(message = "O nome do Pet não pode ser nulo")
    @NotBlank(message = "Obrigatório informar o Nome do Pet.")
    String nome,

    @NotNull(message = "A espécie do Pet não pode ser nula")
    @NotBlank(message = "Obrigatório informar a Espécie do Pet.")
    String especie,

    @NotNull(message = "A Nome do Tutor do Pet não pode ser nulo")
    @NotBlank(message = "Obrigatório informar o Nome do Tutor do Pet.")
    String nomeTutor,

    @NotNull(message = "O Principal do Tutor do Pet não pode ser nulo")
    @NotBlank(message = "Obrigatório informar o Principal do Tutor do Pet.")
    String contato1Tutor,

    @NotNull(message = "O Secundário do Tutor do Pet não pode ser nulo")
    String contato2Tutor,

    @NotNull(message = "O Endereço do Tutor do Pet não pode ser nulo")
    @NotBlank(message = "Obrigatório informar o Endereço do Tutor do Pet.")
    String enderecoTutor
) {}
