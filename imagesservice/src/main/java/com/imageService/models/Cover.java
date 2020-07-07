package com.imageService.models;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"url"})
public class Cover {
	
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}