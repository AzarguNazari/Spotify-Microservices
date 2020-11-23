package com.chartService.models;


import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO:
 * Song attributes should be
 * - title:String
 * - artist:String (possibly multiple artists concatenated with ", ")
 * - duration:double (in seconds)
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Song {
	private String title = "";
	private String artist = "";
	private Double duration = 0.0;

	public void setArtist(ArtistSimplified[] artists) {
		for(int i = 0; i < artists.length; i++) {
			if(i == artists.length - 1) {
				this.artist += artists[i].getName();
			}
			else {
				this.artist += artists[i].getName() + ", ";
			}
		}
		
	}
}