package UI;

import Helpers.EndpointProvider;
import Helpers.SpotifyWrapper;
import Helpers.JSONConverter;
import Spotify.Device;
import Spotify.Track;
import com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.util.List;

public class Main extends Application {

    private final static int WIDTH = 800;
    private final static int HEIGHT = 480;

    private Button button;
    private SpotifyWrapper spotifyWrapper;
    private EndpointProvider endpointProvider;

    @Override
    public void start(Stage primaryStage) throws Exception{
        endpointProvider = new EndpointProvider();
        spotifyWrapper = new SpotifyWrapper(endpointProvider);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();

        button = new Button("List Devices");
        button.setOnAction(func -> {
            try {
                List<Device> devices = spotifyWrapper.getAllDevices();
                for (Device device : devices) {
                    System.out.println(device);
                }
                System.out.println("------------------------------------");
                System.out.println(spotifyWrapper.getCurrentPlayback());
                System.out.println("------------------------------------");
                System.out.println(spotifyWrapper.getPlaylist("studying"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
