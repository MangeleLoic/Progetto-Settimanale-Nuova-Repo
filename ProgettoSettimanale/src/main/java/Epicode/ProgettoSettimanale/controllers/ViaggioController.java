package Epicode.ProgettoSettimanale.controllers;

import Epicode.ProgettoSettimanale.entities.Viaggio;
import Epicode.ProgettoSettimanale.exceptions.BadRequestException;
import Epicode.ProgettoSettimanale.payloads.ViaggioDTO;
import Epicode.ProgettoSettimanale.servicies.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {
    @Autowired
    private ViaggioService viaggioService;

    @GetMapping
    public Page<Viaggio> findAllViaggi(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sortBy) {

        return this.viaggioService.findAllviaggi(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio save(@RequestBody @Validated ViaggioDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }
        return this.viaggioService.creaViaggio(body);
    }

    @GetMapping("/{viaggioId}")
    public Optional<Viaggio> findById(@PathVariable Long viaggioId) {
        return viaggioService.findViaggioById(viaggioId);
    }

    @PutMapping("/{viaggioId}")
    public Viaggio findViaggioByIdAndUpdate(@PathVariable Long viaggioId, @RequestBody ViaggioDTO body) {
        return this.viaggioService.findViaggioByIdAndUpdate(viaggioId, body);
    }

    @DeleteMapping("/{viaggioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long viaggioId) {
        viaggioService.findViaggioByIdAndDelete(viaggioId);
    }

    @DeleteMapping("/deleteAll")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllViaggi() {
        viaggioService.deleteAllViaggi();
    }

}
