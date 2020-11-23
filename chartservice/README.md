Import the Gradle project via `build.gradle` inside your preferred IDE.  

The `com.searchService.SpotifyApi` class is the main entry point for using the Spotify Api wrapper functionality provided by `se.michaelthelin.spotify:spotify-web-api-java`. 
To be able to authenticate your requests, set your Spotify API credentials in `src/main/resources/config.properties`.

All service interfaces are located inside `com.searchService.interfaces`.
Main entry point for the JAX-RS web service is the `com.searchService.jaxrs.MusicApi` class.
For JAX-WS take a look at the `com.searchService.jaxws.MusicApi` interface and the `com.searchService.jaxws.MusicApiImpl` class.
Resource implementations are located inside `com.searchService.jaxws.resources` and `com.searchService.jaxrs.resources`.
The shared model and entity classes for both web services are located inside `com.searchService.models`. 

Start the JAXRS server via `com.searchService.jaxrs.JaxRsServer.java`.  
Start the JAXWS server via `com.searchService.jaxws.JaxWsServer.java`.
  
Please feel free to report any errors and possible improvements for the assignment via an issue in this repository!
