package Epicode.ProgettoSettimanale.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PrenotazioneDTO(
        @NotNull(message = "L'ID del dipendente è obbligatorio!")
        Long dipendenteId,
        @NotNull(message = "L'ID del viaggio è obbligatorio!")
        Long viaggioId,
        LocalDate dataPrenotazione,
        @Size(max = 100, message = "Le note non possono superare i 100 caratteri!")
        String note
) {
}
