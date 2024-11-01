package Epicode.ProgettoSettimanale.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record DipendenteDTO(@NotEmpty(message = "Il nome è obbligatorio!")
                            @Size(min = 3, max = 10, message = "Il nome  deve essere compreso tra 3 e 10 caratteri!")
                            String nome,
                            @NotEmpty(message = "Il cognome è obbligatorio!")
                            @Size(min = 3, max = 20, message = "Il cognome proprio deve essere compreso tra 3 e 20 caratteri!") String cognome,
                            @NotEmpty(message = "Lo username è obbligatorio!") String username,
                            @NotEmpty(message = "L'email è un campo obbligatorio!")
                            @Email(message = "L'email inserita non è un'email valida")
                            String email) {
}
