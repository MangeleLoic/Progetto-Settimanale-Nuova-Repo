package Epicode.ProgettoSettimanale.controllers;

import Epicode.ProgettoSettimanale.entities.Prenotazione;
import Epicode.ProgettoSettimanale.servicies.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping
    public Prenotazione inserisciPrenotazione(
            @RequestParam Long dipendenteId,
            @RequestParam Long viaggioId,
            @RequestParam LocalDate dataViaggio,
            @RequestParam(required = false) String note

    ) throws Exception {
        return prenotazioneService.nuovaPrenotazione(dipendenteId, viaggioId, dataViaggio, note);
    }

}
