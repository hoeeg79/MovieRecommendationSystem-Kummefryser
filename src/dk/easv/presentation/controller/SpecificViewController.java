package dk.easv.presentation.controller;

import dk.easv.entities.User;
import dk.easv.presentation.model.AppModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class SpecificViewController {
    @FXML
    private ListView LView;
    private AppModel model;

    public void setModel(AppModel model) {
        this.model = model;
        //lvUsers.setItems(model.getObsUsers());
        LView.setItems(model.getObsTopMovieNotSeen());
        //lvTopAvgNotSeen.setItems(model.getObsTopMovieNotSeen());
        //lvTopSimilarUsers.setItems(model.getObsSimilarUsers());
        //lvTopFromSimilar.setItems(model.getObsTopMoviesSimilarUsers());

        //startTimer("Load users");
        //model.loadUsers();
        //stopTimer();

        /*
        lvUsers.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldUser, selectedUser) -> {
                    startTimer("Loading all data for user: " + selectedUser);
                    model.loadData(selectedUser);
                });
         */

        // Select the logged-in user in the listview, automagically trigger the listener above
        //lvUsers.getSelectionModel().select(model.getObsLoggedInUser());
    }
}
