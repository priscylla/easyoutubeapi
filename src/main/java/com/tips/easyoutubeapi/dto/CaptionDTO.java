package com.tips.easyoutubeapi.dto;

import java.util.List;

public class CaptionDTO {
	
	private List<SubtitleDTO> subtitles;

	/**
	 * @return the subtitles
	 */
	public List<SubtitleDTO> getSubtitles() {
		return subtitles;
	}

	/**
	 * @param subtitle the subtitles to set
	 */
	public void setSubtitles(List<SubtitleDTO> subtitle) {
		this.subtitles = subtitle;
	}
	
	public boolean isEmpty(){
		return this.subtitles.isEmpty();
	}

}
