package com.tips.easyoutubeapi.business.facade;

import java.util.List;

import com.tips.easyoutubeapi.dto.SimpleVideoDTO;

public interface VideoFacade {

	public List<SimpleVideoDTO> findVideos(List<String> keywords, long quantity);
	
	public SimpleVideoDTO findVideo(String videoId);
	
}
