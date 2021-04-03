package Helpers;

import Spotify.Device;
import Spotify.Playlist;
import Spotify.Track;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import java.util.ArrayList;
import java.util.List;

public class JSONConverter {
    public static List<Device> convertToDevicesList (String devicesJson) {
        List<Device> devices = new ArrayList<>();
        JSONArray deviceArray = ((JSONArray) ((JSONObject) JSONValue.parse(devicesJson)).get("devices"));
        for (Object obj : deviceArray) {
             devices.add(new Device((JSONObject) obj));
        }
        return devices;
    }

    public static List<Playlist> convertAllPlaylists(String playlistJson) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(playlistJson);
        List<Playlist> playlists = new ArrayList<>();
        for (Object playlistID: jsonObject.keySet()) {
            playlists.add(new Playlist((JSONObject) jsonObject.get(playlistID)));
        }
        return playlists;
    }

    public static Playlist convertPlaylist (String playlistJson) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(playlistJson);
        return new Playlist(jsonObject);
    }

    public static Track convertTrack (String trackJson) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(trackJson);
        return new Track(jsonObject);
    }
}
