package proyecto_idiomas_mejorados;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Interfaces.All_utili;

public class Proyecto_idiomas_mejorados extends Application implements All_utili{
    
    public static Stage stage=null;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLadmin.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("Style/Css_admin.css");  
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);        
        stage.show();
        Proyecto_idiomas_mejorados.stage=stage;
        FadeIn(root);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
