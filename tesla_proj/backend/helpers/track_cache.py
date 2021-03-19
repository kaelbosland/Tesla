from classes.track import Track
MAX_PAGE = 50

class TrackCache():
    def __init__ (self, sp):
        self.sp = sp
        self.cache = {}

    def cache_all_tracks(self):
        all_track_data = []
        offset = 0
        curr = self.sp.current_user_saved_tracks(MAX_PAGE, 0)
        while len(curr["items"]) != 0:
            offset = offset + MAX_PAGE
            curr = (self.sp.current_user_saved_tracks(MAX_PAGE, offset))
            all_track_data = all_track_data + [curr["items"]]
        
        for sublist in all_track_data:
            for t in sublist:
                track = Track(t["track"])    
                self.cache[track.get()["source"]] = track

    def get(self, track):
        return self.cache[track.get()["source"]]

    def uri_get(self, track_uri):
        return self.cache[track_uri]

    def play(self, device, track):
        self.sp.start_playback(device, track.get()["source"])
