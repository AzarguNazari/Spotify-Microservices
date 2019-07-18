package de.uniba.dsg.interfaces;
import javax.ws.rs.QueryParam;

import de.uniba.dsg.models.Interpreter;
import de.uniba.dsg.models.Track;

public interface TrackSearch {
    /**
     * Method does return an track information modeled by the Track model class
     * Method is available at tracks/search and takes a mandatory query parameter title=titl_of_the_song & artist=name_of_the_artist_to_look_for
     * Throws an error if the query parameter is not provided by the request
     * Throws an error if no artist can be found
     * Throws an error if the connection or the remote server reports an internal error
     */
     
     // Should throw exception
     
	Track searchTrack(String title, String artist);
	
	Interpreter searchArtist(String artist);
}
