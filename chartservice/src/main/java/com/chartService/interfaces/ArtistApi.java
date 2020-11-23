package com.chartService.interfaces;
import com.chartService.models.Song;

import javax.jws.WebParam;
import javax.jws.WebResult;
import java.util.List;

public interface ArtistApi {
   
    /**
     * TODO:
     * Method should return a collection of songs modeled by the song model class
     * Method should be available at /artists/[artist-id]/top-tracks, e.g., /artists/4gzpq5DPGxSnKTe4SA8HAU/top-tracks
     * Parameter name must be 'artist-id'
     * Handle requests with unknown artist IDs correctly
     * Always return top songs for Germany (DE)
     * Size of the track list must be <= 5
     */
	@WebResult(name="top_tracks")
    List<Song> getTopTracks(@WebParam(name="artistId")String artistId);
}
