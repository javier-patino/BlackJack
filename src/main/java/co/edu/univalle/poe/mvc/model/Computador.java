package co.edu.univalle.poe.mvc.model;

/**
 * Representa al computador que participa como rival
 * en el juego Blackjack.
 * Administra su mano y aplica la estrategia de pedir
 * cartas mientras su puntaje sea menor que 17.
 *
 * @author Anderson Romero
 * @author Javier Patiño
 */

public class Computador {

    private final Mano mano;

    public Computador() {
        mano = new Mano();
    }

    public Mano getMano() {
        return mano;
    }

    public void recibirCarta(Carta carta) {
        mano.agregarCarta(carta);
    }

    public boolean debePedirCarta() {
        return mano.calcularPuntaje() < 17;
    }

    public boolean tieneBlackjackInicial() {
        return mano.tieneBlackjackInicial();
    }

    public boolean sePaso() {
        return mano.sePaso();
    }

    public void limpiarMano() {
        mano.limpiar();
    }
}

