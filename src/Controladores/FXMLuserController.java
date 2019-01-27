package Controladores;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import Conexion.conexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import Interfaces.All_utili;

public class FXMLuserController implements Initializable, All_utili {

    int i = 0;
    ResultSet resultado;
    PreparedStatement pst;
    conexion c = new conexion();
    Connection len = c.conexion();

    @FXML
    private Label close;
    
    @FXML
    private Label mins;
    
    @FXML
    private JFXTextField txt_1;

    @FXML
    private JFXTextField txt_2;

    @FXML
    private AnchorPane Frame;

    @FXML
    private JFXComboBox<String> Idioma_1;

    @FXML
    private JFXComboBox<String> Idioma_2;

    private void autocompletado() throws SQLException {
        Vector<String> palabras = new Vector<>(50, 1);
        pst = len.prepareStatement("select palabras from espanol");
        resultado = pst.executeQuery();
        while (resultado.next()) {
            palabras.add(resultado.getString("palabras"));
        }
        pst = len.prepareStatement("select parola from italiano");
        resultado = pst.executeQuery();
        while (resultado.next()) {
            palabras.add(resultado.getString("parola"));
        }
        pst = len.prepareStatement("select words from english");
        resultado = pst.executeQuery();
        while (resultado.next()) {
            palabras.add(resultado.getString("words"));
        }
        TextFields.bindAutoCompletion(txt_1, palabras);
    }

    @FXML
    private void letrastraductor(KeyEvent e) {
        char verificar = e.getCharacter().charAt(0);
        if (!((verificar < '0') || (verificar > '9'))) {
            e.consume(); // ignorar el evento de teclado
            Notifications.create().darkStyle().title("Informacion").text("No se puede ingresar numeros ni espacios").showInformation();
        }
    }

    @FXML
    private ListView<String> ita;

    @FXML
    private ListView<String> eng;

    @FXML
    private ListView<String> esp;

    @FXML
    private void borrar(MouseEvent e) {
        cancelar(txt_2);
    }

    private void cancelar(JFXTextField text) {
        text.setText("");
    }

    @FXML
    private void numeros(KeyEvent e) {
        char verificar = e.getCharacter().charAt(0);
        if (((verificar < '0') || (verificar > '9')) && (verificar != '\b')) {
            e.consume(); // ignorar el evento de teclado
            Notifications.create().darkStyle().title("Informacion").text("Solo se puede introducir numeros ni espacios").showInformation();
        }
    }

    @FXML
    private void teclado(KeyEvent event) {
        if (txt_1.getText().equals("")) {
            txt_2.setText("");
        }
        String delimiter = " ";
        todo(txt_1.getText().split(delimiter));
    }

    private void todo(String[] palabras) {
        String conjunto = "";
        if (!Idioma_2.getSelectionModel().isEmpty()) {
            if (!txt_1.getText().equals("")) {
                try {
                    switch (Idioma_1.getSelectionModel().getSelectedItem()) {
                        case "Italiano":
                            switch (Idioma_2.getSelectionModel().getSelectedItem()) {
                                case "Español":
                                    for (String palbra : palabras) {
                                        pst = len.prepareStatement("select palabras from espanol where idpalabra "
                                                + "in(select idpalabra from ita_esp where idparola "
                                                + "in(select idparola from italiano where parola ='" + palbra + "'))");
                                        resultado = pst.executeQuery();
                                        if (resultado.next()) {
                                            conjunto += resultado.getString("palabras") + " ";
                                        } else {
                                            conjunto += palbra + " ";
                                        }
                                    }
                                    txt_2.setText(conjunto);
                                    break;
                                case "English":
                                    for (String palbra : palabras) {
                                        pst = len.prepareStatement("select words from english where idword "
                                                + "in(select idword from ita_eng where idparola "
                                                + "in(select idparola from italiano where parola ='" + palbra + "' ))");
                                        resultado = pst.executeQuery();
                                        if (resultado.next()) {
                                            conjunto += (resultado.getString("words")) + " ";
                                        } else {
                                            conjunto += palbra + " ";
                                        }
                                    }
                                    txt_2.setText(conjunto);
                                    break;
                                default:
                                    txt_2.setText(txt_1.getText());
                                    break;
                            }
                            break;
                        case "Español":
                            switch (Idioma_2.getSelectionModel().getSelectedItem()) {
                                case "Italiano":
                                    for (String palbra : palabras) {
                                        pst = len.prepareStatement("select parola from italiano where idparola "
                                                + "in(select idparola from ita_esp where idpalabra "
                                                + "in(select idpalabra from espanol where palabras ='" + palbra + "' ))");
                                        resultado = pst.executeQuery();
                                        if (resultado.next()) {
                                            conjunto += resultado.getString("parola") + " ";
                                        } else {
                                            conjunto += palbra + " ";
                                        }
                                    }
                                    txt_2.setText(conjunto);
                                    break;
                                case "English":
                                    for (String palbra : palabras) {
                                        pst = len.prepareStatement("select words from english where idword "
                                                + "in(select idword from esp_eng where idpalabra "
                                                + "in(select idpalabra from espanol where palabras ='" + palbra + "' ))");
                                        resultado = pst.executeQuery();
                                        if (resultado.next()) {
                                            conjunto += (resultado.getString("words")) + " ";
                                        } else {
                                            conjunto += palbra + " ";
                                        }
                                    }
                                    txt_2.setText(conjunto);
                                    break;
                                default:
                                    txt_2.setText(txt_1.getText());
                                    break;
                            }
                            break;
                        case "English":
                            switch (Idioma_2.getSelectionModel().getSelectedItem()) {
                                case "Italiano":
                                    for (String palbra : palabras) {
                                        pst = len.prepareStatement("select parola from italiano where idparola "
                                                + "in(select idparola from ita_eng where idword "
                                                + "in(select idword from english where words ='" + palbra + "' ))");
                                        resultado = pst.executeQuery();
                                        if (resultado.next()) {
                                            conjunto += (resultado.getString("parola")) + " ";
                                        } else {
                                            conjunto += palbra + " ";
                                        }
                                    }
                                    txt_2.setText(conjunto);
                                    break;
                                case "Español":
                                    for (String palbra : palabras) {
                                        pst = len.prepareStatement("select palabras from espanol where idpalabra "
                                                + "in(select idpalabra from esp_eng where idword "
                                                + "in(select idword from english where words ='" + palbra + "' ))");
                                        resultado = pst.executeQuery();
                                        if (resultado.next()) {
                                            conjunto += (resultado.getString("palabras")) + " ";
                                        } else {
                                            conjunto += palbra + " ";
                                        }
                                    }
                                    txt_2.setText(conjunto);
                                    break;
                                default:
                                    txt_2.setText(txt_1.getText());
                                    break;
                            }
                            break;
                    }

                } catch (SQLException ex) {
                    Notifications.create().text(ex.toString()).title("Error con la Base de dato").darkStyle().showError();
                }
            }
        }
    }

    @FXML
    private void palabras(MouseEvent e) {
        try {
            ObservableList<String> b = FXCollections.observableArrayList();
            //palabras italianas
            i = 1;
            pst = len.prepareStatement("select idparola,parola from italiano;");
            resultado = pst.executeQuery();
            while (resultado.next()) {
                b.add(resultado.getString("idparola") + "->" + resultado.getString("parola"));
                i++;
            }
            ita.setItems(b);
            b = FXCollections.observableArrayList();
            //palabras espanol
            i = 1;
            pst = len.prepareStatement("select idpalabra,palabras from espanol");
            resultado = pst.executeQuery();
            while (resultado.next()) {
                b.add(resultado.getString("idpalabra") + "->" + resultado.getString("palabras"));
                i++;
            }
            esp.setItems(b);
            b = FXCollections.observableArrayList();
            //palabras ingles
            i = 1;
            pst = len.prepareStatement("select idword,words from english;");
            resultado = pst.executeQuery();
            while (resultado.next()) {
                b.add(resultado.getString("idword") + "->" + resultado.getString("words"));
                i++;
            }
            eng.setItems(b);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            autocompletado();
        } catch (SQLException ex) {
            Notifications.create().text(ex.toString()).title("Base de datos").darkStyle().showInformation();
        }
        this.onDraggedScene(Frame);
        this.close(close);
        this.mins(Frame, mins);
        ObservableList<String> items = FXCollections.observableArrayList("Italiano", "Español", "English");
        Idioma_1.setItems(items);
        Idioma_1.getSelectionModel().select(0);
        Idioma_2.setItems(items);
        Idioma_2.getSelectionModel().select(1);
    }

}
