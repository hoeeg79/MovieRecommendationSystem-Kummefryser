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

public class SelectMovieViewController extends BaseController{

    private AppModel model;
    @FXML
    private Button btnMovie;

    public void setModel(AppModel model) {
        this.model = model;
    }

    public void handleMovieBtn(ActionEvent actionEvent) {
        setSceneSelectMovie(model, btnMovie);
    }

    public void handleReturn(ActionEvent actionEvent) {
        setSceneApp(model, btnMovie);
    }

    public void handleSeriesBtn(ActionEvent actionEvent) {
        setSceneSpecificView(model, btnMovie);
    }
}
