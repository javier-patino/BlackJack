package co.edu.univalle.poe.mvc.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Optional;

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

    @FXML
    public void onIniciarRonda() {
        if (lblMensaje != null) lblMensaje.setText("¡Ronda iniciada! Pide carta o plántate.");
        if (btnPedir != null) btnPedir.setDisable(false);
        if (btnPlantarse != null) btnPlantarse.setDisable(false);
        if (btnIniciar != null) btnIniciar.setDisable(true);
    }

    @FXML
    public void onPedirCarta() {
        if (lblMensaje != null) lblMensaje.setText("Pediste una carta...");
    }

    @FXML
    public void onPlantarse() {
        if (lblMensaje != null) lblMensaje.setText("Te has plantado. Le toca a la banca.");
        if (btnPedir != null) btnPedir.setDisable(true);
        if (btnPlantarse != null) btnPlantarse.setDisable(true);
        if (btnIniciar != null) btnIniciar.setDisable(false);
    }

    // Ventana emergente de Reglas
    @FXML
    public void onVerReglas() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reglas del Juego");
        alert.setHeaderText("Instrucciones de 21 BlackJack");
        alert.setContentText(
                "1. El objetivo es sumar 21 o acercarse sin pasarte.\n" +
                        "2. Las cartas del 2 al 10 valen su número; J, Q y K valen 10. El As vale 11 o 1.\n" +
                        "3. La apuesta debe ser número entero, múltiplo de 10 y de mínimo 10 fichas.\n" +
                        "4. Jugada 'Veinte y Medio': Se logra con una Figura (J, Q, K) y un 4 en las dos primeras cartas.\n" +
                        "5. La Banca no recibe cartas al inicio; jugará al final y pedirá mientras sume menos de 17."
        );
        alert.showAndWait();
    }

    // Ventana emergente de Confirmación
    @FXML
    public void onCerrarJuego(Event event) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Confirmación de Cierre");
        alert.setHeaderText("¿Está seguro que quiere cerrar el juego?");
        alert.setContentText("Si confirmas, se cerrará la aplicación de BlackJack.");

        ButtonType btnNo = new ButtonType("No, continuar jugando", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType btnSi = new ButtonType("Sí, salir de BlackJack");
        alert.getButtonTypes().addAll(btnNo, btnSi);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == btnNo) {
                if (event != null) event.consume();
            } else {
                System.exit(0);
            }
        }
    }
}