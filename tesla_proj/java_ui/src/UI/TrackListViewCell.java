package UI;

import Helpers.EndpointProvider;
import Helpers.SpotifyWrapper;
import Spotify.Track;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class TrackListViewCell extends ListCell<Track> {

    @FXML
    GridPane grid;
    @FXML
    AnchorPane anchor;
    @FXML
    Button play;
    @FXML
    Button queue;
    @FXML
    Label trackName;
    @FXML
    Label artist;
    @FXML
    Label album;

    private FXMLLoader fxmlLoader;
    private EndpointProvider endpointProvider;
    private SpotifyWrapper spotifyWrapper;

    @Override
    protected void updateItem(Track track, boolean empty) {
        super.updateItem(track, empty);
        endpointProvider = new EndpointProvider();
        spotifyWrapper = new SpotifyWrapper(endpointProvider);

        if (empty && track == null) {
            setText(null);
            setGraphic(null);
        } else {
            fxmlLoader = new FXMLLoader(getClass().getResource("trackCell.fxml"));
            fxmlLoader.setController(this);
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            trackName.setText(track.getName());
            artist.setText(track.getArtist());
            album.setText(track.getArtist());

            play.setOnAction(func -> {
                try {
                    spotifyWrapper.startTrackPlayback(track.getSource());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            queue.setOnAction(func -> {
                try {
                    spotifyWrapper.queueTrack(track.getSource());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            setText(null);
            setGraphic(anchor);
        }
    }
}
