package co.edu.univalle.poe.mvc.model;

import java.util.Random;

/**
 * Crea y administra un mazo de 52 cartas sin comodines.
 *
 * @author Anderson Romero
 * @author Javier Patiño
 */

public class Mazo {

    private final Carta[] cartas;
    private int siguienteCarta;

    public Mazo() {
        cartas = new Carta[52];
        siguienteCarta = 0;

        crearMazo();
        mezclar();
    }

    private void crearMazo() {
        String[] palos = {"Corazones", "Diamantes", "Tréboles", "Picas"};

        String[] valores = {
                "A", "2", "3", "4", "5", "6", "7",
                "8", "9", "10", "J", "Q", "K"
        };

        int posicion = 0;

        for (String palo : palos) {
            for (String valor : valores) {
                cartas[posicion] = new Carta(palo, valor);
                posicion++;
            }
        }
    }

    public void mezclar() {
        Random random = new Random();

        for (int i = cartas.length - 1; i > 0; i--) {
            int posicionAleatoria = random.nextInt(i + 1);

            Carta temporal = cartas[i];
            cartas[i] = cartas[posicionAleatoria];
            cartas[posicionAleatoria] = temporal;
        }

        siguienteCarta = 0;
    }

    // Entrega una carta y avanza la posición. Por eso una misma carta no puede salir dos veces.//
    public Carta entregarCarta() {
        if (estaVacio()) {
            throw new IllegalStateException("No quedan cartas en el mazo.");
        }

        Carta cartaEntregada = cartas[siguienteCarta];
        siguienteCarta++;

        return cartaEntregada;
    }

    public boolean estaVacio() {
        return siguienteCarta >= cartas.length;
    }

    public int getCartasRestantes() {
        return cartas.length - siguienteCarta;
    }

    public void reiniciarMazo() {
        crearMazo();
        mezclar();
    }
}