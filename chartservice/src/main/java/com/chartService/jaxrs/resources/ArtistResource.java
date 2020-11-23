package com.chartService.jaxrs.resources;

import com.chartService.CustomSpotifyApi;
import com.chartService.interfaces.ArtistApi;
import com.chartService.jaxrs.exceptions.ClientRequestException;
import com.chartService.jaxrs.exceptions.RemoteApiException;
import com.chartService.jaxrs.exceptions.ResourceNotFoundException;
import com.chartService.models.ErrorMessage;
import com.chartService.models.Song;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.artists.GetArtistsTopTracksRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


// Beyonce: 6vWDO969PvNqNYHIOW5v0m

@Path("charts")
@Produces(MediaType.APPLICATION_JSON)
public class ArtistResource implements ArtistApi {

	
	@Override
	@GET
	@Path("{artist-id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Song> getTopTracks(@PathParam("artist-id") String artistId) {
		if (artistId == null) {
			throw new ClientRequestException(new ErrorMessage("Required path parameter is missing: artist-id"));
		}
		
		if (CustomSpotifyApi.getInstance().getArtist(artistId).build() == null) {
			throw new ResourceNotFoundException(new ErrorMessage(String.format("No matching artist found for query: %s", artistId)));
		}
		
		GetArtistsTopTracksRequest topTracksRequest = CustomSpotifyApi.getInstance().getArtistsTopTracks(artistId, CountryCode.DE).build();

		try {
			// get search results
			Track[] topTracks = topTracksRequest.execute();

			// no top tracks found - example: 1UBVtcHhzWfhOsajijAWGU
			if (topTracks != null && topTracks.length == 0) {
				throw new ResourceNotFoundException(new ErrorMessage(String.format("No top tracks found for artist id: %s", artistId)));
			}

			return Arrays.asList(topTracks)
					.stream()
					.map(this::toSong)
					.limit(5)
					.collect(Collectors.toList());
			
			
		} catch (SpotifyWebApiException | IOException e) {
			throw new RemoteApiException(new ErrorMessage(e.getMessage()));
		}
	}
	
	private Song toSong(Track track) {
		Song song = new Song();
		song.setTitle(track.getName());
		song.setArtist(track.getArtists());
		song.setDuration(new Double(track.getDurationMs()));
		return song;
	}
}
