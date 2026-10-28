package co.edu.univalle.poe.mvc.model;

/**
 * Representa una carta individual del juego Blackjack.
 *
 * @author Anderson Romero
 * @author Javier Patiño
 */

public class Carta {

    private final String palo;
    private final String valor;

    public Carta(String palo, String valor) {
        this.palo = palo;
        this.valor = valor;
    }

    public String getPalo() {
        return palo;
    }

    public String getNombreValor() {
        return valor;
    }


    public int obtenerValor() {
        switch (valor) {
            case "A":
                return 11;
            case "J":
            case "Q":
            case "K":
                return 10;
            default:
                return Integer.parseInt(valor);
        }
    }

    public boolean esAs() {
        return valor.equals("A");
    }

    @Override
    public String toString() {
        return valor + " de " + palo;
    }
}
