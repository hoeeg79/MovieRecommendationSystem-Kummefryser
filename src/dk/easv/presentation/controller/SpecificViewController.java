package dk.easv.presentation.controller;

import dk.easv.entities.Movie;
import dk.easv.presentation.model.AppModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class SpecificViewController extends BaseController implements Initializable {
    @FXML
    private ListView<Movie> LView;
    private AppModel model;
    @FXML
    private Button btnLogo;



    public void setModel(AppModel model) {
        this.model = model;
        LView.setItems(model.getObsTopMovieNotSeen());
        deselect();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        openSelected();
    }

    @FXML
    private void handleReturnBtn(ActionEvent actionEvent) {
        setSceneApp(model, btnLogo);
    }

    @FXML
    private void handleRandomMovie(ActionEvent actionEvent) {
        Random r = new Random();
        int boundForRandom = model.getObsTopMovieNotSeen().size();
        Movie rMovie = model.getObsTopMovieNotSeen().get(r.nextInt(boundForRandom) - 1);

        setSceneSelectMovie(model,btnLogo,rMovie);
    }

    @FXML
    private void handleRandomSeries(ActionEvent actionEvent) {
        trollBtn2();
    }

    private void openSelected(){
        LView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setSceneSelectMovie(model, btnLogo, newValue);
        });
    }

    private void deselect(){
        LView.getSelectionModel().clearSelection();
    }

}
