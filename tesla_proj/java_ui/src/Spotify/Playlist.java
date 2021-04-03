package Spotify;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Playlist {

    private String source;
    private String name;
    private String id;
    private String image;
    private Map<String, Track> tracks;

    public Playlist() {

    }

    public Playlist(JSONObject playlistJson) {
        this.id = playlistJson.get("id").toString();
        this.name = playlistJson.get("name").toString();
        this.source = playlistJson.get("source").toString();
        this.image = playlistJson.get("image").toString();
        Map<String, Track> tracks = new HashMap<>();
        JSONObject tracksJson = (JSONObject) playlistJson.get("tracks");
        for (Object trackKey : tracksJson.keySet()) {
            tracks.put(trackKey.toString(), new Track((JSONObject) tracksJson.get(trackKey)));
        }
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "ID: " + this.id +
                "\nName: " + this.name +
                "\nSource: " + this.source +
                "\nImage: " + this.image +
                "\nTracks: " + this.tracks.keySet().toArray().length;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Map<String, Track> getTracks() {
        return tracks;
    }

    public void setTracks(Map<String, Track> tracks) {
        this.tracks = tracks;
    }
}
