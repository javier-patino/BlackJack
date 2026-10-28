package co.edu.univalle.poe.mvc.model;

/**
 * Coordina una partida de Blackjack entre el jugador
 * humano y el computador.
 *
 * @author Anderson Romero
 * @author Javier Patiño
 */

public class Partida {

    private final Jugador jugador;
    private final Computador computador;
    private Mazo mazo;

    private int apuestaActual;
    private boolean partidaEnCurso;
    private boolean turnoComputador;
    private String resultado;

    public Partida() {
        jugador = new Jugador();
        computador = new Computador();
        mazo = new Mazo();

        apuestaActual = 0;
        partidaEnCurso = false;
        turnoComputador = false;
        resultado = "";
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Computador getComputador() {
        return computador;
    }

    public int getApuestaActual() {
        return apuestaActual;
    }

    public boolean isPartidaEnCurso() {
        return partidaEnCurso;
    }

    public boolean isTurnoComputador() {
        return turnoComputador;
    }

    public String getResultado() {
        return resultado;
    }

    public boolean iniciarRonda(int apuesta) {
        if (partidaEnCurso || !jugador.puedeApostar(apuesta)) {
            return false;
        }

        if (!jugador.descontarApuesta(apuesta)) {
            return false;
        }

        apuestaActual = apuesta;
        jugador.getMano().limpiar();
        computador.limpiarMano();

        mazo = new Mazo();
        partidaEnCurso = true;
        turnoComputador = false;
        resultado = "";

        jugador.getMano().agregarCarta(mazo.entregarCarta());
        jugador.getMano().agregarCarta(mazo.entregarCarta());

        if (jugador.getMano().tieneBlackjackInicial()
                || jugador.getMano().sePaso()) {
            jugarTurnoComputador();
        }

        return true;
    }

    public boolean pedirCartaJugador() {
        if (!partidaEnCurso || turnoComputador) {
            return false;
        }

        jugador.getMano().agregarCarta(mazo.entregarCarta());

        if (jugador.getMano().sePaso()
                || jugador.getMano().tieneVeintiuno()) {
            jugarTurnoComputador();
        }

        return true;
    }

    public boolean plantarseJugador() {
        if (!partidaEnCurso || turnoComputador) {
            return false;
        }

        jugarTurnoComputador();
        return true;
    }

    private void jugarTurnoComputador() {
        turnoComputador = true;

        if (jugador.getMano().sePaso()) {
            finalizarRonda();
            return;
        }

        computador.recibirCarta(mazo.entregarCarta());
        computador.recibirCarta(mazo.entregarCarta());

        while (computador.debePedirCarta()) {
            computador.recibirCarta(mazo.entregarCarta());
        }

        finalizarRonda();
    }


    private void finalizarRonda() {
        Mano manoJugador = jugador.getMano();
        Mano manoComputador = computador.getMano();

        int puntosJugador = manoJugador.calcularPuntaje();
        int puntosComputador = manoComputador.calcularPuntaje();

        boolean blackjackJugador = manoJugador.tieneBlackjackInicial();
        boolean blackjackComputador = manoComputador.tieneBlackjackInicial();

        boolean veinteYMedioJugador =
                manoJugador.tieneVeinteYMedioInicial();

        boolean veinteYMedioComputador =
                manoComputador.tieneVeinteYMedioInicial();

        if (manoJugador.sePaso()) {
            resultado = "Perdiste: superaste los 21 puntos.";

        } else if (blackjackJugador && blackjackComputador) {
            resultado = "Empate: ambos tienen Blackjack.";
            jugador.acreditarFichas(apuestaActual);

        } else if (blackjackJugador) {
            resultado = "¡Blackjack! Ganaste la ronda.";
            jugador.acreditarFichas(apuestaActual * 5 / 2);

        } else if (blackjackComputador) {
            resultado = "El computador tiene Blackjack. Perdiste.";

        } else if (puntosJugador == 21 && puntosComputador == 21) {
            resultado = "Empate: ambos tienen 21 puntos.";
            jugador.acreditarFichas(apuestaActual);

        } else if (puntosJugador == 21) {
            resultado = "¡Ganaste con 21 puntos!";
            jugador.acreditarFichas(apuestaActual * 2);

        } else if (puntosComputador == 21) {
            resultado = "El computador tiene 21 puntos. Perdiste.";

        } else if (veinteYMedioJugador && veinteYMedioComputador) {
            resultado = "Empate: ambos tienen Veinte y medio.";
            jugador.acreditarFichas(apuestaActual);

        } else if (veinteYMedioJugador) {
            resultado = "¡Ganaste con Veinte y medio!";
            jugador.acreditarFichas(apuestaActual * 2);

        } else if (veinteYMedioComputador) {
            resultado = "El computador tiene Veinte y medio. Perdiste.";

        } else if (computador.sePaso()) {
            resultado = "¡Ganaste! El computador superó los 21 puntos.";
            jugador.acreditarFichas(apuestaActual * 2);

        } else if (puntosJugador > puntosComputador) {
            resultado = "¡Ganaste la ronda!";
            jugador.acreditarFichas(apuestaActual * 2);

        } else if (puntosJugador < puntosComputador) {
            resultado = "Perdiste la ronda.";

        } else {
            resultado = "Empate: ambos tienen los mismos puntos.";
            jugador.acreditarFichas(apuestaActual);
        }

        partidaEnCurso = false;
    }

}
