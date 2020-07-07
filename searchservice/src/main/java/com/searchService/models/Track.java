package com.searchService.models;

import lombok.Data;

/**
 * This class is for stroring the Track information in the tracks/search service 
 * 
 */

@Data
public class Track {
	
	private String id;
	private String artist;
	private String song;

	public Track(String id, String artist, String song) {
		this.id = id;
		this.artist = artist;
		this.song = song;
	}
}