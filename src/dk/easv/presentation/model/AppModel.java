package dk.easv.presentation.model;

import dk.easv.entities.*;
import dk.easv.logic.LogicManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class AppModel {

    LogicManager logic = new LogicManager();
    // Models of the data in the view
    private final ObservableList<User>  obsUsers = FXCollections.observableArrayList();
    private ObservableList<Movie> obsTopMovieSeen = FXCollections.observableArrayList();
    private List<Movie> topMovieSeen;
    private ObservableList<Movie> obsTopMovieNotSeen = FXCollections.observableArrayList();
    private List<Movie> topMovieNotSeen;
    private final ObservableList<UserSimilarity>  obsSimilarUsers = FXCollections.observableArrayList();
    private final ObservableList<TopMovie> obsTopMoviesSimilarUsers = FXCollections.observableArrayList();
    private ObservableList<Movie> obsMoviesSimilarUsers = FXCollections.observableArrayList();
    private List<Movie> moviesSimilarUsers;

    private final SimpleObjectProperty<User> obsLoggedInUser = new SimpleObjectProperty<>();

    public void loadUsers(){
        obsUsers.clear();
        obsUsers.addAll(logic.getAllUsers());
    }

    public void loadData(User user) {
        obsTopMovieSeen.clear();
        obsTopMovieSeen.addAll(logic.getTopAverageRatedMovies(user));
        topMovieSeen = obsTopMovieSeen.subList(0, 50);
        obsTopMovieSeen = FXCollections.observableList(topMovieSeen);

        obsTopMovieNotSeen.clear();
        obsTopMovieNotSeen.addAll(logic.getTopAverageRatedMoviesUserDidNotSee(user));
        topMovieNotSeen = obsTopMovieNotSeen.subList(0, 50);
        obsTopMovieNotSeen = FXCollections.observableList(topMovieNotSeen);

        obsSimilarUsers.clear();
        obsSimilarUsers.addAll(logic.getTopSimilarUsers(user));


        obsTopMoviesSimilarUsers.clear();
        obsTopMoviesSimilarUsers.addAll(logic.getTopMoviesFromSimilarPeople(user));
        moviesSimilarUsers = obsTopMoviesSimilarUsers.stream().map(obsTopMoviesSimilarUsers -> obsTopMoviesSimilarUsers.getMovie()).collect(Collectors.toList());
        obsMoviesSimilarUsers = FXCollections.observableList(moviesSimilarUsers.subList(0, 50));
    }
    public ObservableList<Movie> getObsMoviesSimilarUsers() {
        return obsMoviesSimilarUsers;
    }

    public ObservableList<User> getObsUsers() {
        return obsUsers;
    }

    public ObservableList<Movie> getObsTopMovieSeen() {
        return obsTopMovieSeen;
    }

    public ObservableList<Movie> getObsTopMovieNotSeen() {
        return obsTopMovieNotSeen;
    }

    public ObservableList<UserSimilarity> getObsSimilarUsers() {
        return obsSimilarUsers;
    }

    public ObservableList<TopMovie> getObsTopMoviesSimilarUsers() {
        return obsTopMoviesSimilarUsers;
    }

    public User getObsLoggedInUser() {
        return obsLoggedInUser.get();
    }

    public SimpleObjectProperty<User> obsLoggedInUserProperty() {
        return obsLoggedInUser;
    }

    public void setObsLoggedInUser(User obsLoggedInUser) {
        this.obsLoggedInUser.set(obsLoggedInUser);
    }

    public boolean loginUserFromUsername(String userName) {
        User u = logic.getUser(userName);
        obsLoggedInUser.set(u);
        if (u==null)
            return false;
        else
            return true;
    }
}
