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

public class SpecificViewController extends BaseController{
    @FXML
    private ListView LView;
    private AppModel model;
    @FXML
    private Button btnLogo;



    public void setModel(AppModel model) {
        this.model = model;
        LView.setItems(model.getObsTopMovieNotSeen());
    }

    @FXML
    private void handleReturnBtn(ActionEvent actionEvent) {
        setSceneApp(model, btnLogo);
    }
}
