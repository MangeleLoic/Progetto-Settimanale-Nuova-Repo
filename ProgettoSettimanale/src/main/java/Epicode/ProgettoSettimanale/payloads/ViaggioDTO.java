package Epicode.ProgettoSettimanale.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ViaggioDTO(
        @NotEmpty(message = "La destinazione è obbligatoria!")
        String destinazione,

        @NotNull(message = "La data del viaggio è obbligatoria!")
        LocalDate dataViaggio,

        @NotEmpty(message = "Lo stato è obbligatorio!")
        @Size(min = 5, max = 10, message = "Lo stato deve essere compreso tra 5 e 10 caratteri!")
        String stato) {
}
