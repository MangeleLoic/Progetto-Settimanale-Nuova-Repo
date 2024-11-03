package Epicode.ProgettoSettimanale.controllers;

import Epicode.ProgettoSettimanale.entities.Prenotazione;
import Epicode.ProgettoSettimanale.payloads.PrenotazioneDTO;
import Epicode.ProgettoSettimanale.servicies.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    public Page<Prenotazione> findAllPrenotazioni(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(defaultValue = "id") String sortBy) {

        return this.prenotazioneService.findAllPrenotazioni(page, size, sortBy);
    }

    @GetMapping("/{prenotazioneId}")
    public Optional<Prenotazione> findById(@PathVariable Long prenotazioneId) {
        return prenotazioneService.findPrenotazioneById(prenotazioneId);
    }

    @PostMapping
    public Prenotazione inserisciPrenotazione(
            @RequestParam Long dipendenteId,
            @RequestParam Long viaggioId,
            @RequestParam LocalDate dataPrenotazione,
            @RequestParam(required = false) String note

    ) throws Exception {
        return prenotazioneService.nuovaPrenotazione(dipendenteId, viaggioId, dataPrenotazione, note);
    }

    @PutMapping("/{prenotazioneId}")
    public Prenotazione findPrenotazioneByIdAndUpdate(@PathVariable Long prenotazioneId, @RequestBody PrenotazioneDTO body) {
        return this.prenotazioneService.findPrenotazioneByIdAndUpdate(prenotazioneId, body);
    }

    @DeleteMapping("/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long prenotazioneId) {
        prenotazioneService.findPrenotazioneByIdAndDelete(prenotazioneId);
    }


    @DeleteMapping("/deleteAll")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllPrenotazioni() {
        prenotazioneService.deleteAllPrenotazioni();
    }


}
