package UI;

import Helpers.EndpointProvider;
import Helpers.SpotifyWrapper;
import Spotify.Playlist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class PlaylistListViewCell extends ListCell<Playlist> {

    @FXML
    Label name;

    @FXML
    ImageView image;

    @FXML
    GridPane grid;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(Playlist playlist, boolean empty) {
        super.updateItem(playlist, empty);

        if (empty && playlist == null) {
            setText(null);
            setGraphic(null);
        } else {
            fxmlLoader = new FXMLLoader(getClass().getResource("playlistCell.fxml"));
            fxmlLoader.setController(this);
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            name.setText(playlist.getName());
            String imageSource = playlist.getImage();
            image.setImage(new Image(imageSource));

            setText(null);
            setGraphic(grid);
        }
    }
}
