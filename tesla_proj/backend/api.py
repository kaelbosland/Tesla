import random
from flask import (
    Flask,
    jsonify,
    url_for,
    redirect,
    make_response
)
import json
import os
import requests
import string

import spotipy
from spotipy.oauth2 import SpotifyOAuth
from pprint import pprint
from time import sleep

from helpers.track_cache import TrackCache
from helpers.playlist_cache import PlaylistCache
from helpers.logger import Logger
from classes.track import Track
from classes.playlist import Playlist

app = Flask(__name__)

DEVICE = "e14fc50b1466fb4e9400f80c7ae34158a3e07f6a"
SCOPE = 'user-read-playback-state,user-modify-playback-state,playlist-read-private,user-library-read'
LOG_FILE_PATH = 'logs'
MAX_PAGE = 50
ME = "Kael Bosland"

log = Logger(LOG_FILE_PATH)
SP = spotipy.Spotify(client_credentials_manager=SpotifyOAuth(scope=SCOPE))
current_user = SP.current_user()
track_cache = TrackCache(SP)
playlist_cache = PlaylistCache(SP, track_cache)

def created_by_me(playlist):
    return playlist["owner"]["display_name"] == ME

def cache_playlists():
    all_playlists = SP.current_user_playlists()["items"]
    return cache_all_playlists(all_playlists)

def cache_all_playlists(all_playlists):
    for playlist in all_playlists:
        playlist_obj = Playlist(playlist)
        if created_by_me(playlist) and playlist_cache.get(playlist_obj) == None:
            playlist_cache.add(playlist_obj)
            playlist_cache.populate_tracks_for_playlist(playlist_obj)

    return playlist_cache.jsonify_cache()

def get_playlist(name):
    log.log('Getting playlist with name: ' + name)
    playlist = playlist_cache.get_by_name(name)
    return playlist

def locate_playlist(playlists, name):
    for i in range (0, len(playlists)):
        if ((playlists[i]['name']) == name):
            return playlists[i]
    return {}

track_cache.cache_all_tracks()
cache_playlists()
playlist_cache.update_tracks(track_cache)

@app.route('/init')
def initialize():
    track_cache.cache_all_tracks()
    cache_playlists()
    playlist_cache.update_tracks(track_cache)
    return '200 OK'

@app.route('/current_playback')
def playback():
    log.log('Recieved call to get current playback')
    current = SP.current_playback()
    log.log('Current playback: ' + str(current))
    return (Track(current["item"]).get())
    
@app.route('/devices')
def devices():
    log.log('Recieved call to get current devices')
    res = SP.devices()
    log.log('Current devices :' + str(res))
    log.log('--------------------------------')
    return(jsonify(res))

@app.route('/play_playlist/<source>')
def play_playlist(source):
    log.log('Recieved call to play playlist from source:' + source)
    try:
        SP.start_playback(DEVICE, get_playlist(source).get()["uri"])
        log.log('Successful')
    except ConnectionError:
        log.log('[ERROR] Recieved connection error')
        log.log('--------------------------------')
        return ("500")
    log.log('--------------------------------')
    return ("200 OK")

@app.route('/next')
def next():
    log.log('Recieved call to play next track')
    try:
        SP.next_track(DEVICE)
        log.log('Successful')
    except ConnectionError:
        log.log('[ERROR] Recieved connection error')
        return ("500")

    log.log('--------------------------------')
    return ("200 OK")

@app.route('/back')
def back():
    log.log('Recieved call to play previous track')
    try:
        SP.previous_track(DEVICE)
        log.log('Successful')
    except ConnectionError:
        log.log('[ERROR] Recieved connection error')
        return ("500")

    log.log('--------------------------------')
    return ("200 OK")

@app.route('/rn')
def track():
    return track_cache.get((Track(SP.current_playback()["item"]))).get()

@app.route('/playlist/<name>')
def playlist(name):
    return playlist_cache.get_by_name(name).jsonify()

@app.route('/json_playlist')
def jsonify_playlists():
    return playlist_cache.jsonify_cache()

@app.route('/playtrack/<source>')
def playtrack(source):
    SP.start_playback(DEVICE, uris=source)
    return '200 OK'

@app.route('/queueup/<source>')
def queueup(source):
    SP.add_to_queue(source, '463176e913b1e197439140049c31f87d938e5e71')
    return '200 OK'

@app.route('/likes/<uri>')
def likes(uri):
    return str(SP.current_user_saved_tracks_contains([uri]))