package Interfaces;

import java.util.concurrent.atomic.AtomicReference;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public interface All_utili {
    
    default void FadeIn(Parent root){
        FadeTransition a=new FadeTransition(Duration.seconds(1), root);
        a.setFromValue(0);
        a.setToValue(1);
        a.setCycleCount(1);
        a.play();
    }
    
    default void onDraggedScene(AnchorPane panelFather) {

        AtomicReference<Double> xOffset = new AtomicReference<>((double) 0);
        AtomicReference<Double> yOffset = new AtomicReference<>((double) 0);

        panelFather.setOnMousePressed(e -> {
            Stage stage = (Stage) panelFather.getScene().getWindow();
            xOffset.set(stage.getX() - e.getScreenX());
            yOffset.set(stage.getY() - e.getScreenY());

        });

        panelFather.setOnMouseDragged(e -> {
            Stage stage = (Stage) panelFather.getScene().getWindow();
            stage.setX(e.getScreenX() + xOffset.get());
            stage.setY(e.getScreenY() + yOffset.get());
            stage.setOpacity(0.8f);
            panelFather.setStyle("-fx-cursor: CLOSED_HAND;");
        });

        panelFather.setOnDragDone((event) -> {
            Stage stage = (Stage) panelFather.getScene().getWindow();
            stage.setOpacity(1.0f);
        });

        panelFather.setOnMouseReleased(e -> {
            Stage stage = (Stage) panelFather.getScene().getWindow();
            panelFather.setStyle("-fx-cursor: DEFAULT;");
            stage.setOpacity(1.0f);
        });
    }
    
    default void close(Label close){
        close.setOnMouseClicked(e->{
            Platform.exit();
        });
    }
    
    default void mins(AnchorPane panelFather,Label min){
        min.setOnMouseClicked(e->{
            Stage stage = (Stage) panelFather.getScene().getWindow();
            stage.setIconified(true);
        });
    }
    
}
