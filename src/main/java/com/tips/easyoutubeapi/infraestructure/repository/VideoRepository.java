package com.tips.easyoutubeapi.infraestructure.repository;

import java.util.List;

import com.tips.easyoutubeapi.entity.SimpleVideo;

public interface VideoRepository {

	List<SimpleVideo> findVideos(String keywordsSeparatedBySpace, Long quantity);
	
	SimpleVideo findVideo(String videoId);
	
}
