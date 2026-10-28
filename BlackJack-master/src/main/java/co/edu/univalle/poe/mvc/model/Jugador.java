package co.edu.univalle.poe.mvc.model;

/**
 * Representa al jugador humano del Blackjack.
 * Administra su mano y su saldo de fichas, valida
 * las apuestas y permite descontar o acreditar fichas.
 *
 * @author Anderson Romero
 * @author Javier Patiño
 */

public class Jugador {

    private final Mano mano;
    private int saldo;

    public Jugador() {
        mano = new Mano();
        saldo = 1000;
    }

    public Mano getMano() {
        return mano;
    }

    public int getSaldo() {
        return saldo;
    }

    public boolean puedeApostar(int apuesta) {
        return apuesta >= 10
                && apuesta % 10 == 0
                && apuesta <= saldo;
    }

    public boolean descontarApuesta(int apuesta) {
        if (!puedeApostar(apuesta)) {
            return false;
        }

        saldo -= apuesta;
        return true;
    }

    public void acreditarFichas(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException(
                    "La cantidad no puede ser negativa."
            );
        }

        saldo += cantidad;
    }
}
