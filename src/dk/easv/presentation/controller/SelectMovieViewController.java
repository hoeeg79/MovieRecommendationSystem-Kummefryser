package dk.easv.presentation.controller;

import dk.easv.presentation.model.AppModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectMovieViewController {

    private AppModel model;
    @FXML
    private Button btnMovie;

    public void setModel(AppModel model) {
        this.model = model;
    }


    public void handleMovieBtn(ActionEvent actionEvent) {
        returnToMainView();
    }

    public void handleReturn(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/presentation/view/App.fxml"));
            Parent root = loader.load();
            Stage currentStage = (Stage) btnMovie.getScene().getWindow();
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

    public void handleSeriesBtn(ActionEvent actionEvent) {
        returnToMainView();
    }

    private void returnToMainView(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/presentation/view/SpecificView.fxml"));
            Parent root = loader.load();
            Stage currentStage = (Stage) btnMovie.getScene().getWindow();
            currentStage.setScene(new Scene(root));
            currentStage.show();
            SpecificViewController controller = loader.getController();

            controller.setModel(model);

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load App.fxml");
            alert.showAndWait();
        }
    }
}
