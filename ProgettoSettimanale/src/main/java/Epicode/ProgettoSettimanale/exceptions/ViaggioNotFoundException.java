package Epicode.ProgettoSettimanale.exceptions;

public class ViaggioNotFoundException extends RuntimeException {
    public ViaggioNotFoundException() {
        super("Viaggio non trovato");
    }
}
