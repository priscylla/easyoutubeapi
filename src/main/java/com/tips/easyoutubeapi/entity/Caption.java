package com.tips.easyoutubeapi.entity;

import java.util.List;

public class Caption {
	
	private List<Subtitle> subtitle;

	/**
	 * @return the subtitles
	 */
	public List<Subtitle> getSubtitles() {
		return subtitle;
	}

	/**
	 * @param subtitle the subtitles to set
	 */
	public void setSubtitles(List<Subtitle> subtitle) {
		this.subtitle = subtitle;
	}
	
	public boolean isEmpty(){
		return this.subtitle.isEmpty();
	}

}
