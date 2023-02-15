package dk.easv.presentation.controller;

import dk.easv.entities.*;
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
import java.util.*;

public class AppController implements Initializable {
    @FXML
    private Button btnMovies;
    @FXML
    private ListView<User> lvUsers;
    @FXML
    private ListView<Movie> lvTopForUser;
    @FXML
    private ListView<Movie> lvTopAvgNotSeen;
    @FXML
    private ListView<UserSimilarity> lvTopSimilarUsers;
    @FXML
    private ListView<TopMovie> lvTopFromSimilar;


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
        singleSelection();
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
        lvTopForUser.setItems(model.getObsTopMovieSeen());
        lvTopAvgNotSeen.setItems(model.getObsTopMovieNotSeen());
        lvTopSimilarUsers.setItems(model.getObsSimilarUsers());
        lvTopFromSimilar.setItems(model.getObsTopMoviesSimilarUsers());
    }

    @FXML
    private void handleMoviesBtn(ActionEvent actionEvent) {
        changeView();
    }

    @FXML
    private void handleTVSeriesBtn(ActionEvent actionEvent) {
        changeView();
    }

    private void changeView(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/presentation/view/SpecificView.fxml"));
            Parent root = loader.load();
            Stage currentStage = (Stage) btnMovies.getScene().getWindow();
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

    private void selectRecommended() {
        lvTopFromSimilar.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
                lvTopAvgNotSeen.getSelectionModel().clearSelection();
                lvTopForUser.getSelectionModel().clearSelection();

        }));
    }

    private void selectTrending() {
        lvTopAvgNotSeen.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
                lvTopFromSimilar.getSelectionModel().clearSelection();
                lvTopForUser.getSelectionModel().clearSelection();
        }));
    }

    private void selectFavorites(){
        lvTopForUser.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
                lvTopFromSimilar.getSelectionModel().clearSelection();
                lvTopAvgNotSeen.getSelectionModel().clearSelection();

        }));
    }

    /**
     * Deselect other lists when making a new selection
     */
    private void singleSelection() {
        selectRecommended();
        selectTrending();
        selectFavorites();
    }
}
