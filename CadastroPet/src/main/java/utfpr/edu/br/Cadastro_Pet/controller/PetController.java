package utfpr.edu.br.Cadastro_Pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utfpr.edu.br.Cadastro_Pet.models.Pet;
import utfpr.edu.br.Cadastro_Pet.repository.petRepository;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private petRepository petRepository;

    // Endpoint para criar um pet
    @PostMapping
    public Pet createPet(@RequestBody Pet pet) {
        return petRepository.save(pet);
    }

    // Endpoint para listar todos os pets
    @GetMapping
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    // Endpoint para buscar um pet por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        return petRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para atualizar um pet
    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody Pet petDetails) {
        return petRepository.findById(id).map(pet -> {
            pet.setNome(petDetails.getNome());
            pet.setGenero(petDetails.getGenero());
            pet.setCastrado(petDetails.getCastrado());
            pet.setIdade(petDetails.getIdade());
            pet.setEspecie(petDetails.getEspecie());
            pet.setRestricaoComorbidade(petDetails.getRestricaoComorbidade());
            pet.setNomeTutor(petDetails.getNomeTutor());
            pet.setContato1Tutor(petDetails.getContato1Tutor());
            pet.setContato2Tutor(petDetails.getContato2Tutor());
            pet.setEndereco(petDetails.getEndereco());
            pet.setOutros(petDetails.getOutros());
            return ResponseEntity.ok(petRepository.save(pet));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para deletar um pet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        return petRepository.findById(id).map(pet -> {
            petRepository.delete(pet);
            return ResponseEntity.noContent().<Void>build(); // Especifica explicitamente o tipo Void
        }).orElse(ResponseEntity.notFound().build());
    }

}
