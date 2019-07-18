package de.uniba.dsg.jaxrs.resources;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.artists.GetArtistsTopTracksRequest;

import de.uniba.dsg.CustomSpotifyApi;
import de.uniba.dsg.interfaces.ArtistApi;
import de.uniba.dsg.jaxrs.exceptions.ClientRequestException;
import de.uniba.dsg.jaxrs.exceptions.RemoteApiException;
import de.uniba.dsg.jaxrs.exceptions.ResourceNotFoundException;
import de.uniba.dsg.models.ErrorMessage;
import de.uniba.dsg.models.Song;


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
