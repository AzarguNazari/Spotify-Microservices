package com.searchService.jaxrs.resources;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import com.sun.research.ws.wadl.Response;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import com.wrapper.spotify.requests.data.albums.GetAlbumsTracksRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchArtistsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;

import com.searchService.CustomSpotifyApi;
import com.searchService.interfaces.TrackSearch;
import com.searchService.jaxrs.exceptions.ClientRequestException;
import com.searchService.jaxrs.exceptions.RemoteApiException;
import com.searchService.jaxrs.exceptions.ResourceNotFoundException;
import com.searchService.models.ErrorMessage;
import com.searchService.models.Interpreter;
import com.searchService.models.Track;

// Beyonce: 6vWDO969PvNqNYHIOW5v0m

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrackResource implements TrackSearch {

    @Override
    @GET
    @Path("tracks/search")
    public Track searchTrack(@QueryParam("title") String title,
                             @QueryParam("artist") String artist) {

        if (title == null || title.trim().length() == 0) {
            throw new ClientRequestException(new ErrorMessage("Required query parameters is missing: song title"));
        }

        if (artist == null || artist.trim().length() == 0) {
            throw new ClientRequestException(new ErrorMessage("Required query parameters is missing: artist name"));
        }

        String songQuery = "track: " + title + " artist: " + artist;

        try {

            SearchTracksRequest trackRequest = CustomSpotifyApi.getInstance().searchTracks(songQuery).build();

            Paging<com.wrapper.spotify.model_objects.specification.Track> paging = trackRequest.execute();

            if (paging.getItems().length == 0) {
                throw new ResourceNotFoundException(new ErrorMessage(String.format("No matching track found for artist: %s and song title: %s", artist, title)));
            }

            com.wrapper.spotify.model_objects.specification.Track track = paging.getItems()[0];

            Track p = new Track(track.getId(), track.getArtists()[0].getName(), track.getName());
            System.out.println(p);

            return p;


        } catch (SpotifyWebApiException | IOException e) {
            throw new RemoteApiException(new ErrorMessage("Error on the server"));
        }
    }

    @Override
    @GET
    @Path("artists/search")
    public Interpreter searchArtist(@QueryParam("artist") String artistName) {
        // TODO Auto-generated method stub
        if (artistName == null || artistName.trim().length() == 0) {
            throw new ClientRequestException(new ErrorMessage("Required query parameters is missing: artist name"));
        }

        SearchArtistsRequest artistRequest = CustomSpotifyApi.getInstance().searchArtists(artistName).build();



        try {

            for(Artist artist : artistRequest.execute().getItems()) {
                if(artist.getId().length() > 0) {

                    Interpreter artistObject = new Interpreter();
                    artistObject.setId(artist.getId());
                    artistObject.setName(artist.getName());
                    artistObject.setGenres(artist.getGenres());
                    artistObject.setPopularity(artistObject.getPopularity());
                    return artistObject;

                }
                else {
                    throw new ResourceNotFoundException(new ErrorMessage(String.format("No matching artist found for query: %s", artistName)));
                }
            }

            return null;
        } catch (SpotifyWebApiException | IOException e) {
            throw new RemoteApiException(new ErrorMessage("Error on the server"));
        }
    }
}
