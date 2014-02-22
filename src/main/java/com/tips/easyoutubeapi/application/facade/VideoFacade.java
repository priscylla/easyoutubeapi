package com.tips.easyoutubeapi.application.facade;

import java.util.List;

import com.tips.easyoutubeapi.dto.SimpleVideoDTO;

public interface VideoFacade {

	public List<SimpleVideoDTO> findVideos(List<String> keywords, Long quantity);
	
	public SimpleVideoDTO findVideo(String videoId);
	
}
