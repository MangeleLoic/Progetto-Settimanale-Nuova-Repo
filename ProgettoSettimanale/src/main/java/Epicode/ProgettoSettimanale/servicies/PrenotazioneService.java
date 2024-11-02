package Epicode.ProgettoSettimanale.servicies;

import Epicode.ProgettoSettimanale.entities.Prenotazione;
import Epicode.ProgettoSettimanale.entities.Viaggio;
import Epicode.ProgettoSettimanale.exceptions.DipendenteNotFoundException;
import Epicode.ProgettoSettimanale.exceptions.PrenotazioneGiaPresente;
import Epicode.ProgettoSettimanale.exceptions.PrenotazioneNotFoundException;
import Epicode.ProgettoSettimanale.exceptions.ViaggioNotFoundException;
import Epicode.ProgettoSettimanale.payloads.PrenotazioneDTO;
import Epicode.ProgettoSettimanale.repositories.DipendenteRepository;
import Epicode.ProgettoSettimanale.repositories.PrenotazioneRepository;
import Epicode.ProgettoSettimanale.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        Viaggio viaggio = viaggioRepository.findById(viaggioId)
                .orElseThrow(() -> new ViaggioNotFoundException());

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDipendente(dipendenteRepository.findById(dipendenteId).orElseThrow(() -> new DipendenteNotFoundException()));
        prenotazione.setViaggio(viaggioRepository.findById(viaggioId).orElseThrow(() -> new ViaggioNotFoundException()));
        prenotazione.setDataPrenotazione(dataPrenotazione);
        prenotazione.setNote(note);
        return prenotazioneRepository.save(prenotazione);

    }

    public Optional<Prenotazione> findPrenotazioneById(Long prenotazioneId) {
        return prenotazioneRepository.findById(prenotazioneId);
    }

    public Page<Prenotazione> findAllPrenotazioni(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.prenotazioneRepository.findAll(pageable);
    }

    public Prenotazione findPrenotazioneByIdAndUpdate(Long prenotazioneId, PrenotazioneDTO body) {
        return prenotazioneRepository.findById(prenotazioneId).map(prenotazione -> {
            prenotazione.setDataPrenotazione(body.dataPrenotazione());
            prenotazione.setNote(body.note());
            return prenotazioneRepository.save(prenotazione);
        }).orElseThrow(() -> new PrenotazioneNotFoundException());
    }

    public void findPrenotazioneByIdAndDelete(Long prenotazioneId) {
        if (!prenotazioneRepository.existsById(prenotazioneId)) {
            throw new PrenotazioneNotFoundException();
        }
        prenotazioneRepository.deleteById(prenotazioneId);
    }

    public void deleteAllPrenotazioni() {
        prenotazioneRepository.deleteAll();
    }
}
