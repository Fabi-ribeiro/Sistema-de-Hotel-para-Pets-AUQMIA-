package utfpr.edu.br.CadastroPet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utfpr.edu.br.CadastroPet.exceptionhadler.PetNotFoundException;
import utfpr.edu.br.CadastroPet.models.Pet;
import utfpr.edu.br.CadastroPet.repository.petRepository;

@Service
public class PetService {

    @Autowired
    private petRepository petRepository;

    public Pet buscarPorId(Long id) {
        return petRepository.findById(id)
            .orElseThrow(PetNotFoundException::new); // Lança a exceção se não encontrado
    }

    public Pet atualizarPet(Long id, Pet petAtualizado) {
        Pet pet = petRepository.findById(id)
            .orElseThrow(PetNotFoundException::new); // Lança a exceção se não encontrado
        // Atualiza os campos do pet
        pet.setNome(petAtualizado.getNome());
        pet.setGenero(petAtualizado.getGenero());
        pet.setCastrado(petAtualizado.getCastrado());
        pet.setIdade(petAtualizado.getIdade());
        pet.setEspecie(petAtualizado.getEspecie());
        pet.setNomeTutor(petAtualizado.getNomeTutor());
        pet.setContato1Tutor(petAtualizado.getContato1Tutor());
        pet.setContato2Tutor(petAtualizado.getContato2Tutor());
        pet.setEndereco(petAtualizado.getEndereco());
        pet.setOutros(petAtualizado.getOutros());

        return petRepository.save(pet); // Salva e retorna o pet atualizado
    }

    public void deletarPet(Long id) {
        if (!petRepository.existsById(id)) {
            throw new PetNotFoundException(); // Lança a exceção se o pet não existir
        }
        petRepository.deleteById(id); // Deleta o pet se encontrado
    }

    public String obterNomePetPorId(Long id) {
        Pet pet = petRepository.findById(id)
            .orElseThrow(PetNotFoundException::new); // Lança a exceção se o pet não for encontrado
        return pet.getNome();
    }
}