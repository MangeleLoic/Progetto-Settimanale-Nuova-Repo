package Epicode.ProgettoSettimanale.exceptions;

public class PrenotazioneNotFoundException extends RuntimeException {
    public PrenotazioneNotFoundException() {
        super("Prenotazione non trovata");
    }
}
