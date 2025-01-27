package dk.easv.presentation.controller;

import dk.easv.entities.Movie;
import dk.easv.presentation.model.AppModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class SelectMovieViewController extends BaseController{

    @FXML
    private Label lblTitle;
    private AppModel model;
    @FXML
    private Button btnMovie;
    private Movie movie;

    public void setModel(AppModel model, Movie movie) {
        this.model = model;
        this.movie = movie;
        lblTitle.textProperty().set(movie.getTitle() + " (" + movie.getYear() + ")");
    }

    @FXML
    private void handleMovieBtn(ActionEvent actionEvent) {
        setSceneSpecificView(model, btnMovie);
    }

    @FXML
    private void handleReturn(ActionEvent actionEvent) {
        setSceneApp(model, btnMovie);
    }

    @FXML
    private void handleSeriesBtn(ActionEvent actionEvent) {
        setSceneSpecificView(model, btnMovie);
    }

    @FXML
    private void handleRandomSeries(ActionEvent actionEvent) {
        trollBtn1();
    }

    @FXML
    private void handleRandomMovie(ActionEvent actionEvent) {
        Random r = new Random();
        int boundForRandom = model.getObsTopMovieNotSeen().size();
        Movie rMovie = model.getObsTopMovieNotSeen().get(r.nextInt(boundForRandom) - 1);

        setSceneSelectMovie(model,btnMovie,rMovie);
    }

    @FXML
    private void handlePlay(ActionEvent actionEvent) {
        trollBtn2();
    }
}
