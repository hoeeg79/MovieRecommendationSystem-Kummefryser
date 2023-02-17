package dk.easv.presentation.controller;

import dk.easv.entities.*;
import dk.easv.presentation.model.AppModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.*;

public class AppController extends BaseController implements Initializable {
    @FXML
    private Button btnMovies;
    @FXML
    private ListView<User> lvUsers;
    @FXML
    private ListView<Movie> lvFavorites;
    @FXML
    private ListView<Movie> lvTrending;
    @FXML
    private ListView<UserSimilarity> lvTopSimilarUsers;
    @FXML
    private ListView<TopMovie> lvRecommended;


    private AppModel model;
    private long timerStartMillis = 0;
    private String timerMsg = "";

    private void startTimer(String message){
        timerStartMillis = System.currentTimeMillis();
        timerMsg = message;
    }

    private void stopTimer(){
        System.out.println(timerMsg + " took : " + (System.currentTimeMillis() - timerStartMillis) + "ms");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        openSelection();
    }

    public void setModelFirstLogin(AppModel model) {
        loadModelAndLists(model);

        startTimer("Load users");
        model.loadUsers();
        stopTimer();

        lvUsers.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldUser, selectedUser) -> {
                    startTimer("Loading all data for user: " + selectedUser);
                    model.loadData(selectedUser);
                });

        // Select the logged-in user in the listview, automagically trigger the listener above
        if(model.getObsLoggedInUser() != null) {
            lvUsers.getSelectionModel().select(model.getObsLoggedInUser());
        }
    }

    /**
     * What about second breakfast?!
     * @param model - Breakfast
     */
    public void setModelSecondTime(AppModel model){
        loadModelAndLists(model);
    }

    private void loadModelAndLists(AppModel model){
        this.model = model;
        lvUsers.setItems(model.getObsUsers());
        lvFavorites.setItems(model.getObsTopMovieSeen());
        lvTrending.setItems(model.getObsTopMovieNotSeen());
        lvTopSimilarUsers.setItems(model.getObsSimilarUsers());
        lvRecommended.setItems(model.getObsTopMoviesSimilarUsers());
    }

    @FXML
    private void handleMoviesBtn(ActionEvent actionEvent) {
        setSceneSpecificView(model,btnMovies);
    }

    @FXML
    private void handleTVSeriesBtn(ActionEvent actionEvent) {
        setSceneSpecificView(model,btnMovies);
    }

    private void openSelection(){
        lvFavorites.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            setSceneSelectMovie(model, btnMovies, newValue);
        }));
        lvTrending.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            setSceneSelectMovie(model, btnMovies, newValue);
        }));
    }

    @FXML
    private void handleRandomMovie(ActionEvent actionEvent) {
        trollBtn1();
    }

    @FXML
    private void handleRandomSeries(ActionEvent actionEvent) {
        trollBtn2();
    }
}
