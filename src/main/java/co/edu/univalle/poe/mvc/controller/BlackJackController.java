package co.edu.univalle.poe.mvc.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

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

    @FXML
    public void onVerReglas() {
        if (lblMensaje != null) lblMensaje.setText("Reglas: Suma 21 o acércate sin pasarte.");
    }

    @FXML
    public void onCerrarJuego() {
        System.exit(0);
    }
}