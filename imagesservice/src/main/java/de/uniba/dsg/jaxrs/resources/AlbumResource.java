package de.uniba.dsg.jaxrs.resources;

import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistCoverImageRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;

import de.uniba.dsg.CustomSpotifyApi;
import de.uniba.dsg.interfaces.SearchImage;
import de.uniba.dsg.jaxrs.exceptions.ClientRequestException;
import de.uniba.dsg.jaxrs.exceptions.RemoteApiException;
import de.uniba.dsg.jaxrs.exceptions.ResourceNotFoundException;
import de.uniba.dsg.models.ErrorMessage;
import de.uniba.dsg.models.Cover;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.Image;


// Beyonce: 6vWDO969PvNqNYHIOW5v0m

@Path("album")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlbumResource implements SearchImage {

	@Override
	@GET
	@Path("{trackID}/image")
	public Cover getImage(@PathParam("trackID") String trackID) {

		if (trackID == null) {
			throw new ClientRequestException(new ErrorMessage("Required query parameter is missing: trackID"));
		}

		GetTrackRequest trackRequest=CustomSpotifyApi.getInstance().getTrack(trackID).build();

		try {
			Track track = trackRequest.execute();
			Image[] images = track.getAlbum().getImages();

			if(images.length == 0)
			{
				new ClientRequestException(new ErrorMessage("No images found for the given track ID"));

			}

			Cover cover = new Cover();
			cover.setUrl(images[0].getUrl());
			return cover;

		} catch (SpotifyWebApiException | IOException e) {
			// TODO Auto-generated catch block
			throw new RemoteApiException(new ErrorMessage(e.getMessage()));
		}
		
	}

}
