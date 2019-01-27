package Controladores;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;
import Interfaces.All_utili;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class FXMLadminController1 implements Initializable, All_utili {

    @FXML
    private JFXComboBox<String> Idioma_1;

    @FXML
    private JFXComboBox<String> Idioma_2;

    @FXML
    private AnchorPane Frame;

    @FXML
    private Label close;

    @FXML
    private ImageView img_ing;

    @FXML
    private ImageView img_ita;

    @FXML
    private ImageView img_esp;

    @FXML
    private Label mins;

    @FXML
    private void tutto(MouseEvent event) {

    }

    @FXML
    private void conexiones(MouseEvent e) {

    }

    @FXML
    private void palabras(MouseEvent e) {

    }

    @FXML
    private void teclado(KeyEvent event) {

    }

    @FXML
    private void add(MouseEvent event) {

    }

    @FXML
    private void elimina(MouseEvent event) {

    }

    @FXML
    private void add_conexion(MouseEvent e) {

    }

    @FXML
    private void numeros(KeyEvent e) {
        char verificar = e.getCharacter().charAt(0);
        if (((verificar < '0') || (verificar > '9')) && (verificar != '\b')) {
            e.consume(); // ignorar el evento de teclado
            Notifications.create().darkStyle().title("Informacion").text("no se puede introducir ni letras ni espacios").showInformation();
        }
    }

    @FXML
    private void letras(KeyEvent e) {
        char verificar = e.getCharacter().charAt(0);
        if (!((verificar < '0') || (verificar > '9')) || (verificar == ' ')) {
            e.consume(); // ignorar el evento de teclado
            Notifications.create().darkStyle().title("Informacion").text("No se puede ingresar numeros ni espacios").showInformation();
        }
    }

    @FXML
    private void letrastraductor(KeyEvent e) {
        char verificar = e.getCharacter().charAt(0);
        if (!((verificar < '0') || (verificar > '9'))) {
            e.consume(); // ignorar el evento de teclado
            Notifications.create().darkStyle().title("Informacion").text("No se puede ingresar numeros").showInformation();
        }
    }

    @FXML
    private void borrar(MouseEvent e) {

    }

    private void cancelar(JFXTextField text) {
        text.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.onDraggedScene(Frame);
        this.close(close);
        this.mins(Frame, mins);
        
        ObservableList<String> items = FXCollections.observableArrayList("Italiano", "English", "Español");
        Idioma_1.setItems(items);
        Idioma_1.getSelectionModel().select(0);
        Idioma_2.setItems(items);
        Idioma_2.getSelectionModel().select(1);
        Idioma_2.getSelectionModel().selectedItemProperty().addListener((v, old_v, new_v) -> {
            if (!Idioma_1.getValue().equals(new_v)) {
                Idioma_1.getSelectionModel().select(old_v);
            }
        });
        Idioma_1.getSelectionModel().selectedItemProperty().addListener((v, old_v, new_v) -> {
            if (Idioma_2.getValue().equals(new_v)) {
                Idioma_2.getSelectionModel().select(old_v);

            }
        });
    }

}
