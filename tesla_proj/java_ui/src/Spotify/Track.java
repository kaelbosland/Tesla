package Spotify;

import org.json.simple.JSONObject;

public class Track {
    private String source;
    private String name;
    private String artist;
    private String album;
    private String albumCover;

    public Track(JSONObject trackJson) {
        this.source = trackJson.get("source").toString();
        this.name = trackJson.get("name").toString();
        this.artist = trackJson.get("artist").toString();
        this.album = trackJson.get("album").toString();
        this.albumCover = trackJson.get("album_cover").toString();
    }

    @Override
    public String toString() {
        return "Name: " + this.name +
                "\nArtist: " + this.artist +
                "\nAlbum: " + this.album +
                "\nSource: " + this.source;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAlbumCover() {
        return this.albumCover;
    }

    public void setAlbumCover(String albumCover) {
        this.albumCover = albumCover;
    }
}
