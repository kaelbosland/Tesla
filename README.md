# Tesla

This project is about using a Rasperry Pi 3 and a 7 inch display to create a UI that I can mount in my car.

For the backend, thinking of using Python and Flask. For the frontend, the best way to create a UI on a raspberry pi seems to be by going with a web app solution. For this, I decided to use React. My project has a few components.

## Spotify

In the backend, this is my main option:

1) Use pyspotify to stream music from my personal spotify account. The issue with this is connectivity. We may need to use the cellular data on my phone to be able to use spotify in the car. Could be draining allll my data trying to run this.
2) Possibly check out raspotify. See if there is a way to configure communication from my phone to the raspberry pi through bluetooth. Right now, I am able to cast music to the raspberry pi when we are on the same network

## Google Maps

Not sure yet how we would implement this. TODO

## Car Statistics

Implementing a spedometer and a compass would be easy, just need an attachment to the raspberry pi. The rest could prove to be more complicated. Would be aweosome to show engine temp, car service light when we plug in a reader, etc.
