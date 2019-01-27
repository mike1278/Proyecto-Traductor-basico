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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import Interfaces.All_utili;

public class FXMLadminController implements Initializable, All_utili {

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
    private AnchorPane Frame;

    @FXML
    private ImageView ing;

    @FXML
    private JFXTextField palabra1;

    @FXML
    private JFXTextField palabra2;

    @FXML
    private ListView<String> ita_eng;

    @FXML
    private ListView<String> ita_esp;

    @FXML
    private ListView<String> eng_esp;

    @FXML
    private JFXTextField txt_add;

    @FXML
    private ListView<String> ita;

    @FXML
    private ListView<String> eng;

    @FXML
    private ListView<String> esp;

    @FXML
    private JFXTextField txt_eli;

    @FXML
    private JFXComboBox<String> C_add;

    @FXML
    private JFXComboBox<String> C_eli;

    @FXML
    private ToggleGroup Sin;

    @FXML
    private JFXTextField txt_1;

    @FXML
    private JFXTextField txt_2;

    @FXML
    private JFXComboBox<String> Idioma_1;

    @FXML
    private JFXComboBox<String> Idioma_2;

    @FXML
    private JFXComboBox<String> Conexion;

    @FXML
    private void conexiones(MouseEvent e) {
        try {
            ObservableList<String> a = FXCollections.observableArrayList();
            //conexion italiano y ingles
            i = 1;
            pst = len.prepareStatement("select idword,idparola from ita_eng;");
            resultado = pst.executeQuery();
            while (resultado.next()) {
                a.add("eng->" + resultado.getString("idword") + "->ita->" + resultado.getString("idparola"));
                i++;
            }
            ita_eng.setItems(a);
            a = FXCollections.observableArrayList();
            //conexion espanol y italiano
            i = 1;
            pst = len.prepareStatement("select idpalabra,idparola from ita_esp");
            resultado = pst.executeQuery();
            while (resultado.next()) {
                a.add("esp->" + resultado.getString("idpalabra") + "->ita->" + resultado.getString("idparola"));
                i++;
            }
            ita_esp.setItems(a);
            a = FXCollections.observableArrayList();
            //conexion espanol y ingles
            i = 1;
            pst = len.prepareStatement("select idword,idpalabra from esp_eng;");
            resultado = pst.executeQuery();
            while (resultado.next()) {
                a.add("eng->" + resultado.getString("idword") + "->esp->" + resultado.getString("idpalabra"));
                i++;
            }
            eng_esp.setItems(a);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void palabras(MouseEvent e) {
        ita.getItems().removeAll();
        esp.getItems().removeAll();
        eng.getItems().removeAll();
        try {
            ObservableList<String> b = FXCollections.observableArrayList();
            //palabras italianas
            pst = len.prepareStatement("select idparola,parola from italiano;");
            resultado = pst.executeQuery();
            while (resultado.next()) {
                b.add(resultado.getString("idparola") + "->" + resultado.getString("parola"));
            }
            ita.setItems(b);
            b = FXCollections.observableArrayList();
            //palabras espanol
            pst = len.prepareStatement("select idpalabra,palabras from espanol");
            resultado = pst.executeQuery();
            while (resultado.next()) {
                b.add(resultado.getString("idpalabra") + "->" + resultado.getString("palabras"));
            }
            esp.setItems(b);
            b = FXCollections.observableArrayList();
            //palabras ingles
            pst = len.prepareStatement("select idword,words from english;");
            resultado = pst.executeQuery();
            while (resultado.next()) {
                b.add(resultado.getString("idword") + "->" + resultado.getString("words"));
            }
            eng.setItems(b);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void tutto(MouseEvent event) {
        conexiones(event);
        palabras(event);
    }

    @FXML
    private void teclado(KeyEvent event) {
        if (txt_1.getText().equals("")) {
            txt_2.setText("");
        }
        String delimiter = " ";
        todo(txt_1.getText().split(delimiter));
    }

    private void todo(String[] palabras){
        String conjunto="",parametro="",parametro1="",parametro2="",parametro3="",parametro4="",parametro5="",parametro6="";
        if(!txt_1.getText().equals("")){
            switch(Idioma_2.getSelectionModel().getSelectedItem()){
                case "Italiano":
                    parametro="parola";
                    parametro1="italiano";
                    parametro2="idparola";
                    switch(Idioma_1.getSelectionModel().getSelectedItem()){
                        case "Español":
                            parametro3="ita_esp";
                            parametro4="idpalabra";
                            parametro5="espanol";
                            parametro6="palabras";
                            break;
                        case "English":
                            parametro3="ita_eng";
                            parametro4="idword";
                            parametro5="english";
                            parametro6="words";
                            break;
                    }
                    break;
                case "English":
                    parametro="words";
                    parametro1="english";
                    parametro2="idword";
                    switch(Idioma_1.getSelectionModel().getSelectedItem()){
                        case "Español":
                            parametro3="esp_eng";
                            parametro4="idpalabra";
                            parametro5="espanol";
                            parametro6="palabras";
                            break;
                        case "Italiano":
                            parametro3="ita_eng";
                            parametro4="idparola";
                            parametro5="italiano";
                            parametro6="parola";
                            break;
                    }
                    break;
                case "Español":
                    parametro="palabras";
                    parametro1="espanol";
                    parametro2="idpalabra";
                    switch(Idioma_1.getSelectionModel().getSelectedItem()){
                        case "English":
                            parametro3="esp_eng";
                            parametro4="idword";
                            parametro5="english";
                            parametro6="words";
                            break;
                        case "Italiano":
                            parametro3="ita_esp";
                            parametro4="idparola";
                            parametro5="italiano";
                            parametro6="parola";
                            break;
                    }
                    break;
            }
            for (String palbra : palabras) {
                try {
                    pst = len.prepareStatement("select " + parametro + " from "
                            + parametro1 + " where " + parametro2 + " in(select "
                            + parametro2 + " from " + parametro3 + " where "
                            + parametro4 + " in(select " + parametro4 + " from "
                            + parametro5 + " where " + parametro6 + " ='" + palbra + "'))");
                    resultado = pst.executeQuery();
                    if (resultado.next()) {
                        conjunto += resultado.getString(parametro) + " ";
                    } else {
                        conjunto += palbra + " ";
                    }
                }catch (SQLException e){
                    Notifications.create().text(e.toString()).title("Error con la Base de dato").darkStyle().showError();
                }
            }
            txt_2.setText(conjunto);
        }
    }

    @FXML
    private void add(MouseEvent event) {
        if ((!txt_add.getText().trim().equals(""))) {
            try {
                String idioma, var;
                switch (C_add.getSelectionModel().getSelectedItem()) {
                    case "Italiano":
                        idioma = "Italiano";
                        var = "parola";
                        break;
                    case "Español":
                        idioma = "Espanol";
                        var = "palabras";
                        break;
                    default:
                        idioma = "English";
                        var = "words";
                        break;
                }
                pst = len.prepareStatement("INSERT into " + idioma + "(" + var + ") values('" + txt_add.getText() + "')");
                pst.execute();
                txt_add.setText("");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } else {
            Notifications.create().darkStyle().title("Error").text("Los campos no pueden ser vacios").showError();
        }
        txt_add.setText("");
        palabras(event);
    }

    @FXML
    private void elimina(MouseEvent event) {
        if ((!txt_eli.getText().equals(""))) {
            try {
                String idioma="", var="";
                switch (C_eli.getSelectionModel().getSelectedItem()) {
                    case "Italiano":
                        idioma = "Italiano";
                        var = "parola";
                        break;
                    case "Español":
                        idioma = "Espanol";
                        var = "palabras";
                        break;
                    default:
                        idioma = "English";
                        var = "words";
                        break;
                }
                pst = len.prepareStatement("delete from " + idioma + " where " + var + " ='" + txt_add.getText() + "'");
                pst.execute();
                txt_add.setText("");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } else {
            Notifications.create().darkStyle().title("Error").text("Los campos no pueden ser vacios").showError();
        }
        txt_eli.setText("");
        palabras(event);
    }

    @FXML
    private void add_conexion(MouseEvent e) {
        PreparedStatement stat;
        ResultSet r;
        RadioButton radio = (RadioButton) Sin.getSelectedToggle();
        if (!(palabra1.getText().equals("") && palabra2.getText().equals(""))) {
            try {
                switch (Conexion.getSelectionModel().getSelectedItem()) {
                    case "Ita_Esp":
                        pst = len.prepareStatement("select parola from italiano where idparola=" + Integer.parseInt(palabra1.getText()));
                        stat = len.prepareStatement("select palabras from espanol where idpalabra=" + Integer.parseInt(palabra2.getText()));
                        resultado = pst.executeQuery();
                        r = stat.executeQuery();
                        resultado.next();
                        r.next();
                        if (!resultado.getString("parola").equals("") && !r.getString("palabras").equals("")) {
                            boolean sin=radio.getText().equals("Si")?true:false;
                            pst = len.prepareStatement("insert into ita_esp (idparola,idpalabra,sinonimos) values(" + Integer.parseInt(palabra1.getText()) + "," + Integer.parseInt(palabra2.getText()) + "," + sin + ")");
                            pst.execute();
                        } else {
                            throw new SQLException();
                        }
                        break;
                    case "Eng_Esp":
                        pst = len.prepareStatement("select palabras from espanol where idpalabra=" + Integer.parseInt(palabra2.getText()));
                        stat = len.prepareStatement("select words from english where idword=" + Integer.parseInt(palabra1.getText()));
                        resultado = pst.executeQuery();
                        r = stat.executeQuery();
                        resultado.next();
                        r.next();
                        if (!resultado.getString("palabras").equals("") && !r.getString("words").equals("")) {
                            boolean sin=radio.getText().equals("Si")?true:false;
                            pst = len.prepareStatement("insert into esp_eng(idpalabra,idword,sinonimos) values(" + Integer.parseInt(palabra2.getText()) + "," + Integer.parseInt(palabra1.getText()) + "," + sin + ")");
                            pst.execute();
                        } else {
                            throw new SQLException();
                        }
                        break;
                    case "Ita_Eng":
                        pst = len.prepareStatement("select parola from italiano where idparola=" + Integer.parseInt(palabra1.getText()));
                        stat = len.prepareStatement("select words from english where idword=" + Integer.parseInt(palabra2.getText()));
                        resultado = pst.executeQuery();
                        r = stat.executeQuery();
                        resultado.next();
                        r.next();
                        if (!resultado.getString("parola").equals("") && !r.getString("words").equals("")) {
                            boolean sin=radio.getText().equals("Si")?true:false;
                            pst = len.prepareStatement("insert into ita_eng (idword,idparola,sinonimos) values(" + Integer.parseInt(palabra2.getText()) + "," + Integer.parseInt(palabra1.getText()) + "," + sin + ")");
                            pst.execute();
                        } else {
                            throw new SQLException();
                        }
                        break;
                }
                Notifications.create().darkStyle().title("Informacion").text("Se ha creado a conexion con exito").showInformation();
            } catch (SQLException ex) {
                Notifications.create().darkStyle().title("Error").text("Error al crear la conexion entre " + palabra1.getText() + " y " + palabra2.getText()).showError();
            }
        }
        palabra1.setText("");
        palabra2.setText("");
        conexiones(e);
    }

    @FXML
    private void numeros(KeyEvent e) {
        char verificar = e.getCharacter().charAt(0);
        if (((verificar < '0') || (verificar > '9')) && (verificar != '\b')) {
            e.consume(); // ignorar el evento de teclado
            Notifications.create().darkStyle().title("Informacion").text("Solo se puede introducir numeros ni espacios").showInformation();
        }
    }

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
            Notifications.create().darkStyle().title("Informacion").text("No se puede ingresar numeros ni espacios").showInformation();
        }
    }

    @FXML
    private void borrar(MouseEvent e) {
        cancelar(txt_2);
    }

    private void cancelar(JFXTextField text) {
        text.setText("");
    }

    private void autocompletado() throws SQLException {
        Vector<String> palabras = new Vector<>(40, 1);
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
        C_add.setItems(items);
        C_add.getSelectionModel().select(0);
        C_eli.setItems(items);
        C_eli.getSelectionModel().select(0);
        ObservableList<String> item = FXCollections.observableArrayList("Ita_Esp", "Eng_Esp", "Ita_Eng");
        Conexion.setItems(item);
        Conexion.getSelectionModel().select(0);
        Idioma_2.getSelectionModel().selectedItemProperty().addListener((v,old_v,new_v)->{
            if(Idioma_1.getValue().equals(new_v)){
                Idioma_1.getSelectionModel().select(old_v);
            }
        });
        Idioma_1.getSelectionModel().selectedItemProperty().addListener((v,old_v,new_v)->{
            if(Idioma_2.getValue().equals(new_v)){
                Idioma_2.getSelectionModel().select(old_v);

            }
        });
        txt_add.addEventFilter(KeyEvent.KEY_TYPED,event -> {
            letras(event);
        });
        txt_eli.addEventFilter(KeyEvent.KEY_TYPED,event -> {
            letras(event);
        });
    }

}