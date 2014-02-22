package com.tips.easyoutubeapi.domain.service;

import java.util.List;

import com.tips.easyoutubeapi.entity.SimpleVideo;

public interface VideoService {

	List<SimpleVideo> findVideos(List<String> keywords, Long quantity);

	SimpleVideo findVideo(String videoId);

}
