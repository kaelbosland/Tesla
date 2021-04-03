package UI;

import Helpers.EndpointProvider;
import Helpers.SpotifyWrapper;
import Spotify.Device;
import Spotify.Playlist;
import Spotify.Track;
import com.gluonhq.charm.glisten.control.BottomNavigationButton;
import com.gluonhq.charm.glisten.control.CharmListView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main extends Application {

    private SpotifyWrapper spotifyWrapper;
    private EndpointProvider endpointProvider;

    @FXML
    ListView<Playlist> playlists;

    @FXML
    ListView<Track> tracks;

    @FXML
    Button back;
    @FXML
    Button playPause;
    @FXML
    Button next;

    private ObservableList<Playlist> playlistData = FXCollections.observableArrayList();
    private ObservableList<Track> trackData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        endpointProvider = new EndpointProvider();
        spotifyWrapper = new SpotifyWrapper(endpointProvider);

        FXMLLoader root = new FXMLLoader(getClass().getResource("sample.fxml"));
        root.setController(this);
        primaryStage.setTitle("Spotify 4 Kael");
        Parent parentRoot = root.load();
        primaryStage.setScene(new Scene(parentRoot));
        primaryStage.show();

        List<Playlist> allPlaylists = spotifyWrapper.getAllPlaylists();
        playlistData.setAll(allPlaylists);
        playlists.setItems(playlistData);
        playlists.setCellFactory(playlist -> new PlaylistListViewCell());

        playlists.setOnMouseClicked(func -> {
            populateTrackList(playlists.getSelectionModel().getSelectedItem().getId());
        });

        back.setOnAction(func -> {
            try {
                spotifyWrapper.back();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        next.setOnAction(func -> {
            try {
                spotifyWrapper.next();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void populateTrackList (String selectedId) {
        Playlist selected = (playlistData.stream().filter(playlist -> playlist.getId().equals(selectedId)).findFirst().get());
        List<Track> trackList = new ArrayList<>();
        for (String key : selected.getTracks().keySet()) {
            trackList.add(selected.getTracks().get(key));
        }
        trackData.setAll(trackList);
        tracks.setItems(trackData);
        tracks.setCellFactory(track -> new TrackListViewCell());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
