package Epicode.ProgettoSettimanale.exceptions;

public class PrenotazioneGiaPresente extends RuntimeException {
    public PrenotazioneGiaPresente() {
        super("La prenotazione è già presente.");
    }
}
