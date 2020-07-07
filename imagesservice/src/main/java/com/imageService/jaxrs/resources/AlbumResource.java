package com.imageService.jaxrs.resources;

import com.imageService.CustomSpotifyApi;
import com.imageService.interfaces.SearchImage;
import com.imageService.jaxrs.exceptions.ClientRequestException;
import com.imageService.jaxrs.exceptions.RemoteApiException;
import com.imageService.models.Cover;
import com.imageService.models.ErrorMessage;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Image;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;


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

		GetTrackRequest trackRequest= CustomSpotifyApi.getInstance().getTrack(trackID).build();

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
