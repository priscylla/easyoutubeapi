package com.tips.easyoutubeapi.entity;

import java.util.List;

public class Caption {
	
	private List<Subtitle> subtitles;

	/**
	 * @return the subtitles
	 */
	public List<Subtitle> getSubtitles() {
		return subtitles;
	}

	/**
	 * @param subtitle the subtitles to set
	 */
	public void setSubtitles(List<Subtitle> subtitle) {
		this.subtitles = subtitle;
	}
	
	public boolean isEmpty(){
		return this.subtitles.isEmpty();
	}

}
