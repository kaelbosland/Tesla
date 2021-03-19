class Track:
    def __init__ (self, track_data):
        self.set_track_data(track_data)
        
    def set_track_data(self, track_data):
        self.source = track_data["uri"]
        self.name = track_data["name"]
        self.artist = track_data["album"]["artists"][0]["name"]
        self.album = track_data["album"]["name"]
        try:
            self.album_cover = track_data["album"]["images"][1]["url"]
        except IndexError:
            self.album_cover = "default.jpg"

    def get (self):
        return {
            "source": self.source,
            "name": self.name,
            "artist": self.artist,
            "album": self.album,
            "album_cover": self.album_cover
        }

    def __str__(self):
        return str(self.get())