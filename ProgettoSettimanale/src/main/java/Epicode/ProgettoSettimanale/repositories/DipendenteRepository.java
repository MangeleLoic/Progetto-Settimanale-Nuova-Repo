package Epicode.ProgettoSettimanale.repositories;

import Epicode.ProgettoSettimanale.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    Optional<Dipendente> findByEmail(String email);
}
