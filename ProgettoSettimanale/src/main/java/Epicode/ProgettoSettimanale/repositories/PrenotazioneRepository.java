package Epicode.ProgettoSettimanale.repositories;

import Epicode.ProgettoSettimanale.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    Optional<Prenotazione> findByDipendenteIdAndDataViaggio(Long dipendenteId, LocalDate dataPrenotazione);
}
