package dk.easv.presentation.controller;

import dk.easv.presentation.model.AppModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class SpecificViewController {
    @FXML
    private ListView LView;
    private AppModel model;
    @FXML
    private Button btnLogo;



    public void setModel(AppModel model) {
        this.model = model;
        LView.setItems(model.getObsTopMovieNotSeen());
        System.out.println(model.getObsLoggedInUser());
    }

    @FXML
    private void handleReturnBtn(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/presentation/view/App.fxml"));
            Parent root = loader.load();
            Stage currentStage = (Stage) btnLogo.getScene().getWindow();
            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Movie Recommendation System 0.01 Beta");
            currentStage.show();
            AppController controller = loader.getController();

            controller.setModelSecondTime(model);
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load App.fxml");
            alert.showAndWait();
        }
    }
}
