package Epicode.ProgettoSettimanale.repositories;

import Epicode.ProgettoSettimanale.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
}
