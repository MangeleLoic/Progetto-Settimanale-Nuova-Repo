package Epicode.ProgettoSettimanale.exceptions;

public class DipendenteNotFoundException extends RuntimeException {
    public DipendenteNotFoundException() {
        super("Dipendente non trovato");
    }
}
