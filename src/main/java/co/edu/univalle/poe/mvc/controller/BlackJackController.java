package co.edu.univalle.poe.mvc.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.util.Optional;
import javafx.application.Platform;

import co.edu.univalle.poe.mvc.model.Carta;
import co.edu.univalle.poe.mvc.model.Mano;
import co.edu.univalle.poe.mvc.model.Partida;

/**
 * Controla las acciones de la interfaz gráfica del Blackjack.
 * Conecta los botones y componentes visuales con el modelo
 * de la partida, actualizando cartas, puntajes, saldo y mensajes.
 *
 * @author Anderson Romero
 * @author Javier Patiño
 */

public class BlackJackController {

    @FXML private Label lblPuntajeBanca;
    @FXML private HBox contenedorCartasBanca;

    @FXML private Label lblPuntajeJugador;
    @FXML private HBox contenedorCartasJugador;

    @FXML private Label lblMensaje;
    @FXML private Label lblSaldo;
    @FXML private TextField txtApuesta;

    @FXML private Button btnIniciar;
    @FXML private Button btnPedir;
    @FXML private Button btnPlantarse;
    @FXML private Button btnReglas;
    @FXML private Button btnCerrar;

    private final Partida partida = new Partida();

    @FXML
    public void initialize() {
        actualizarVista();
        actualizarBotones();
    }
    @FXML
    public void onIniciarRonda() {
        String textoApuesta = txtApuesta.getText().trim();

        try {
            int apuesta = Integer.parseInt(textoApuesta);

            if (!partida.iniciarRonda(apuesta)) {
                lblMensaje.setText(
                        "Apuesta inválida o ya existe una ronda activa."
                );
            } else {
                lblMensaje.setText(
                        partida.getResultado().isEmpty()
                                ? "¡Ronda iniciada!"
                                : partida.getResultado()
                );
            }

        } catch (NumberFormatException e) {
            lblMensaje.setText("Ingresa una apuesta válida.");
        }

        actualizarVista();
        actualizarBotones();
    }

    @FXML
    public void onPedirCarta() {
        if (partida.pedirCartaJugador()) {
            lblMensaje.setText(
                    partida.getResultado().isEmpty()
                            ? "Has pedido una carta."
                            : partida.getResultado()
            );
        } else {
            lblMensaje.setText("No puedes pedir carta en este momento.");
        }

        actualizarVista();
        actualizarBotones();
    }

    @FXML
    public void onPlantarse() {
        if (partida.plantarseJugador()) {
            lblMensaje.setText(partida.getResultado());
        } else {
            lblMensaje.setText("No puedes plantarte en este momento.");
        }

        actualizarVista();
        actualizarBotones();
    }

    private void actualizarVista() {
        lblSaldo.setText("Saldo: $" + partida.getJugador().getSaldo());

        Mano manoJugador = partida.getJugador().getMano();
        Mano manoBanca = partida.getComputador().getMano();

        lblPuntajeJugador.setText(
                "Jugador: " + manoJugador.calcularPuntaje() + " pts"
        );

        contenedorCartasJugador.getChildren().clear();
        mostrarCartas(manoJugador, contenedorCartasJugador);

        boolean revelarBanca =
                partida.isTurnoComputador()
                        || (!partida.isPartidaEnCurso() && !manoBanca.estaVacia());

        contenedorCartasBanca.getChildren().clear();

        if (revelarBanca) {
            lblPuntajeBanca.setText(
                    "Banca: " + manoBanca.calcularPuntaje() + " pts"
            );
            mostrarCartas(manoBanca, contenedorCartasBanca);
        } else if (partida.isPartidaEnCurso()) {
            lblPuntajeBanca.setText("Banca: oculto");
        } else {
            lblPuntajeBanca.setText("Banca: 0 pts");
        }
    }

    private void mostrarCartas(Mano mano, HBox contenedor) {
        for (Carta carta : mano.getCartas()) {
            Label etiquetaCarta = new Label(carta.toString());

            etiquetaCarta.setStyle(
                    "-fx-background-color: white;"
                            + "-fx-border-color: #c9c9c9;"
                            + "-fx-border-radius: 8;"
                            + "-fx-background-radius: 8;"
                            + "-fx-padding: 12;"
                            + "-fx-font-size: 13px;"
            );

            contenedor.getChildren().add(etiquetaCarta);
        }
    }

    private void actualizarBotones() {
        boolean enCurso = partida.isPartidaEnCurso();

        btnIniciar.setDisable(enCurso);
        txtApuesta.setDisable(enCurso);
        btnPedir.setDisable(!enCurso || partida.isTurnoComputador());
        btnPlantarse.setDisable(!enCurso || partida.isTurnoComputador());
    }

    //Muestra las instrucciones del juego Blackjack.//
    @FXML
    public void onVerReglas() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Reglas del juego");
        alert.setHeaderText("Instrucciones de 21 BlackJack");

        alert.setContentText(
                "1. El jugador comienza con 1000 fichas.\n\n"
                        + "2. La apuesta mínima es de 10 fichas y debe ser "
                        + "múltiplo de 10.\n\n"
                        + "3. El objetivo es acercarse a 21 sin superarlo.\n\n"
                        + "4. Las cartas del 2 al 10 valen su número; "
                        + "J, Q y K valen 10. El As vale 1 u 11.\n\n"
                        + "5. La banca pide cartas mientras tenga menos de 17.\n\n"
                        + "6. Blackjack es un As y una carta de valor 10 "
                        + "en las dos cartas iniciales."
        );

        alert.showAndWait();
    }

    /**
     * Solicita confirmación antes de cerrar la aplicación.
     *
     * @param event evento de cierre generado por la interfaz.
     * @author Anderson Romero
     * @author Javier Patiño
     */
    @FXML
    public void onCerrarJuego(Event event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirmación de cierre");
        alert.setHeaderText("¿Deseas cerrar el juego?");
        alert.setContentText("Puedes cancelar y continuar jugando.");

        ButtonType btnNo = new ButtonType(
                "No, continuar jugando",
                ButtonBar.ButtonData.CANCEL_CLOSE
        );

        ButtonType btnSi = new ButtonType("Sí, salir");

        alert.getButtonTypes().setAll(btnNo, btnSi);

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.isPresent() && resultado.get() == btnSi) {
            Platform.exit();
        } else if (event != null) {
            event.consume();
        }
    }
}