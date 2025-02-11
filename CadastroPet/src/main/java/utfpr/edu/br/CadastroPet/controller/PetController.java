package utfpr.edu.br.CadastroPet.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import utfpr.edu.br.CadastroPet.models.Pet;
import utfpr.edu.br.CadastroPet.repository.petRepository;
import utfpr.edu.br.CadastroPet.service.PetService;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private petRepository petRepository;

    @Autowired
    private PetService petService;
    
    @PostMapping
    public ResponseEntity<Pet> salvarPet(@Valid @RequestBody Pet pet) {
    try {
        Pet savedPet = petRepository.save(pet);
        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);
    } catch (Exception e) {
        // Log de exception
        Logger logger = LoggerFactory.getLogger(PetController.class);
        logger.error("Erro ao salvar pet", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @GetMapping// Endpoint para listar todos os pets
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> buscarPet(@PathVariable Long id) {
        Pet pet = petService.buscarPorId(id); // Tenta buscar o pet
        return ResponseEntity.ok(pet); // Retorna o pet encontrado
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> atualizarPet(@PathVariable Long id, @RequestBody Pet petAtualizado) {
        Pet pet = petService.atualizarPet(id, petAtualizado); // Tenta atualizar o pet
        return ResponseEntity.ok(pet); // Retorna o pet atualizado
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPet(@PathVariable Long id) {
        String nomePet = petService.obterNomePetPorId(id); // Obter o nome do pet antes de deletar
        petService.deletarPet(id); // Tenta deletar o pet
        String mensagem = "O Pet " + nomePet + " foi excluído com sucesso.";
        return ResponseEntity.ok(mensagem); // Retorna a mensagem de sucesso
    }
}