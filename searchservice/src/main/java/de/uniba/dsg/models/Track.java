package de.uniba.dsg.models;

/**
 * This class is for stroring the Track information in the tracks/search service 
 * 
 */

public class Track {
	
	private String id;
	private String artist;
	private String song;
	

	
	public Track(String id, String artist, String song) {
		this.id = id;
		this.artist = artist;
		this.song = song;
	}

	public Track() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	@Override
	public String toString() {
		return "Track [id=" + id + ", artist=" + artist + ", song=" + song + "]";
	}
	
	
}