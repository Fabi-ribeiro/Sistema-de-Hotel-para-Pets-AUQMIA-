package utfpr.edu.br.CadastroPet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.edu.br.CadastroPet.models.Pet;

public interface petRepository extends JpaRepository<Pet, Long> {
}