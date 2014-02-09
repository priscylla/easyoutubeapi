package com.tips.easyoutubeapi.business.facade;

import java.util.List;

import com.tips.easyoutubeapi.dto.VideoDTO;

public interface VideoFacade {

	public List<VideoDTO> findVideo(List<String> keywords);
	
	public VideoDTO findVideo(String videoId);
	
	public String getCaption(String videoId);
	
}
