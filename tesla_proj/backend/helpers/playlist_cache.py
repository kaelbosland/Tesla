import json

MAX_PAGE = 100

class PlaylistCache:
    def __init__(self, sp, track_cache):
        self.sp = sp
        self.track_cache = track_cache
        self.playlist_cache = {}

    def update_tracks(self, newtracks):
        self.track_cache = newtracks

    def add (self, playlist):
        self.playlist_cache[playlist.get()["id"]] = playlist

    def get (self, playlist):
        try:
            return self.playlist_cache[playlist.get()["id"]]
        except KeyError:
            return None

    def get_by_name(self, name):
        for key in self.playlist_cache.keys():
            if self.playlist_cache[key].get()["name"] == name:
                return self.playlist_cache[key]

    def populate_tracks_for_playlist(self, playlist):
        playlist_id = playlist.get()["id"]
        offset = 0
        track_data = []
        tracks = {}
        skipped_tracks = []
        curr = self.sp.playlist_items(playlist_id, 'items.track.uri,items.track.name', MAX_PAGE, offset)["items"]
        while len(curr) != 0:
            track_data = track_data + [curr]
            offset = offset + MAX_PAGE
            curr = self.sp.playlist_items(playlist_id, 'items.track.uri,items.track.name', MAX_PAGE, offset)["items"]

        for sublist in track_data:
            for t in sublist:
                try:
                    tracks[t["track"]["uri"]] = self.track_cache.uri_get(t["track"]["uri"])
                except KeyError:

                    skipped_tracks = skipped_tracks + [t["track"]["name"] + " - " + t["track"]["uri"]]
                except TypeError:
                    print (t)

        print (skipped_tracks)

        self.playlist_cache[playlist_id].set_tracks(tracks)
        return self.playlist_cache[playlist_id]

    def jsonify_cache(self):
        cache = {}

        for key in self.playlist_cache.keys():
            playlist = self.playlist_cache[key]
            jsonified_tracks = {}
            playlist_tracks = self.playlist_cache[key].get()["tracks"]
            for track in playlist_tracks:
                jsonified_tracks[track] = playlist_tracks[track].get()
            cache[key] = {
                "id": playlist.id,
                "source": playlist.source,
                "name": playlist.name,
                "image": playlist.image,
                "tracks": jsonified_tracks
            }

        return cache