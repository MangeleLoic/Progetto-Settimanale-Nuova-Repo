package Epicode.ProgettoSettimanale.servicies;

import Epicode.ProgettoSettimanale.entities.Prenotazione;
import Epicode.ProgettoSettimanale.exceptions.DipendenteNotFoundException;
import Epicode.ProgettoSettimanale.exceptions.PrenotazioneGiaPresente;
import Epicode.ProgettoSettimanale.exceptions.ViaggioNotFoundException;
import Epicode.ProgettoSettimanale.repositories.DipendenteRepository;
import Epicode.ProgettoSettimanale.repositories.PrenotazioneRepository;
import Epicode.ProgettoSettimanale.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PrenotazioneService {
    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private ViaggioRepository viaggioRepository;

    @Transactional
    public Prenotazione nuovaPrenotazione(Long dipendenteId, Long viaggioId, LocalDate dataPrenotazione, String note) throws Exception {
        Optional<Prenotazione> prenotazineGiaPresente = prenotazioneRepository.findByDipendenteIdAndDataPrenotazione(dipendenteId, dataPrenotazione);

        if (prenotazineGiaPresente.isPresent()) {
            throw new PrenotazioneGiaPresente();
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDipendente(dipendenteRepository.findById(dipendenteId).orElseThrow(() -> new DipendenteNotFoundException()));
        prenotazione.setViaggio(viaggioRepository.findById(viaggioId).orElseThrow(() -> new ViaggioNotFoundException()));
        prenotazione.setDataPrenotazione(dataPrenotazione);
        prenotazione.setNote(note);
        return prenotazioneRepository.save(prenotazione);

    }
}
