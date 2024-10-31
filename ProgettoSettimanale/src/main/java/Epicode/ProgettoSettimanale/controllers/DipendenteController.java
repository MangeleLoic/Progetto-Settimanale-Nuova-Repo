package Epicode.ProgettoSettimanale.controllers;

import Epicode.ProgettoSettimanale.servicies.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping("/dipendenti")
    public String dipendentiInDb() {
        dipendenteService.saveMany();
        return "dipendenti salvati con successo";
    }
}
