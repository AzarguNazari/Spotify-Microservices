package de.uniba.dsg.models;


import com.wrapper.spotify.model_objects.specification.ArtistSimplified;

/**
 * TODO:
 * Song attributes should be
 * - title:String
 * - artist:String (possibly multiple artists concatenated with ", ")
 * - duration:double (in seconds)
 */

public class Song {
	private String title = "";
	private String artist = "";
	private Double duration = 0.0;
	

	
	public Song(String title, String artist, Double duration) {
		this.title = title;
		this.artist = artist;
		this.duration = duration;
	}

	public Song() {}


	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getArtist() {
		return artist;
	}



	public void setArtist(String artist) {
		this.artist = artist;
	}



	public Double getDuration() {
		return duration;
	}



	public void setDuration(Double duration) {
		this.duration = duration;
	}

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