package dk.easv.presentation.controller;

import dk.easv.entities.Movie;
import dk.easv.presentation.model.AppModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class BaseController {

    protected void setSceneApp(AppModel model, Button btn){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/presentation/view/App.fxml"));
            Parent root = loader.load();
            Stage currentStage = (Stage) btn.getScene().getWindow();
            currentStage.setScene(new Scene(root));
            currentStage.show();
            AppController controller = loader.getController();

            controller.setModelSecondTime(model);
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load App.fxml");
            alert.showAndWait();
        }
    }

    protected void setSceneSpecificView(AppModel model, Button btn) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/presentation/view/SpecificView.fxml"));
            Parent root = loader.load();
            Stage currentStage = (Stage) btn.getScene().getWindow();
            currentStage.setScene(new Scene(root));
            currentStage.show();
            SpecificViewController controller = loader.getController();

            controller.setModel(model);

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load SpecificView.fxml");
            alert.showAndWait();
        }
    }

    protected void setSceneSelectMovie(AppModel model, Button btn, Movie movie){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/presentation/view/SelectMovieView.fxml"));
            Parent root = loader.load();
            Stage currentStage = (Stage) btn.getScene().getWindow();
            currentStage.setScene(new Scene(root));
            currentStage.show();
            SelectMovieViewController controller = loader.getController();

            controller.setModel(model, movie);

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load SpecificView.fxml");
            alert.showAndWait();
        }
    }

    protected void trollBtn1(){
        try{
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI("https://www.youtube.com/watch?v=ZZ5LpwO-An4"));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "boooo");
            alert.showAndWait();
        }
    }

    protected void trollBtn2(){
        try{
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI("https://www.youtube.com/watch?v=mghhLqu31cQ"));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "boooo");
            alert.showAndWait();
        }
    }
}
