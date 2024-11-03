package Epicode.ProgettoSettimanale.controllers;

import Epicode.ProgettoSettimanale.entities.Dipendente;
import Epicode.ProgettoSettimanale.exceptions.BadRequestException;
import Epicode.ProgettoSettimanale.payloads.DipendenteDTO;
import Epicode.ProgettoSettimanale.servicies.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;


    @GetMapping("/genera")
    public String generaDipendenti() {
        dipendenteService.saveMany();
        return "Dipendenti generati con successo";
    }

    @GetMapping
    public Page<Dipendente> findAllDipendenti(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(defaultValue = "id") String sortBy) {

        return this.dipendenteService.findAllDipendenti(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente save(@RequestBody @Validated DipendenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }
        return this.dipendenteService.saveDipendente(body);
    }

    @GetMapping("/{dipendenteId}")
    public Dipendente findById(@PathVariable Long dipendenteId) {
        return dipendenteService.findById(dipendenteId);
    }

    @PutMapping("/{dipendenteId}")
    public Dipendente findByIdAndUpdate(@PathVariable Long dipendenteId, @RequestBody DipendenteDTO body) {
        return this.dipendenteService.findByIdAndUpdate(dipendenteId, body);
    }

    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long dipendenteId) {
        this.dipendenteService.findByIdAndDelete(dipendenteId);
    }

    @DeleteMapping("/deleteAll")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllDipendenti() {
        dipendenteService.deleteAllDipendenti();
    }

    @PostMapping("/{id}/upload-avatar")
    public String uploadAvatar(@PathVariable Long id, @RequestParam("img") MultipartFile file) {
        return dipendenteService.uploadAvatar(id, file);
    }
    //questa è solo una prova

}
