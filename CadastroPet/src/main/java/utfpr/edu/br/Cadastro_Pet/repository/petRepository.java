package utfpr.edu.br.Cadastro_Pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.edu.br.Cadastro_Pet.models.Pet;

public interface petRepository extends JpaRepository<Pet, Long> {
}