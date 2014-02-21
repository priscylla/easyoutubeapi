package com.tips.easyoutubeapi.repository;

import java.util.List;

import com.tips.easyoutubeapi.entity.SimpleVideo;

public interface VideoRepository {

	List<SimpleVideo> findVideos(List<String> keywords, Long quantity);
	
	SimpleVideo findVideo(String videoId);
	
}
