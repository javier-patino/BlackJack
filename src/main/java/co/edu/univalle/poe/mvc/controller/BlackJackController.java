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

    // Ventana emergente de Reglas al estilo de la profesora
    @FXML
    public void onVerReglas() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reglas del Juego");
        alert.setHeaderText("Instrucciones de 21 BlackJack");
        alert.setContentText(
                "1. El objetivo es sumar 21 o acercarse sin pasarse.\n" +
                        "2. Las cartas del 2 al 10 valen su número.\n" +
                        "3. J, Q y K valen 10 puntos. El As vale 11 o 1.\n" +
                        "4. La Banca pide carta si tiene menos de 17 puntos."
        );
        alert.showAndWait();
    }

    // Ventana emergente de Confirmación de Cierre idéntica al ejemplo de la profesora
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