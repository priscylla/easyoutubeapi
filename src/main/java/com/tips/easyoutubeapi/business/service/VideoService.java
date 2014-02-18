package com.tips.easyoutubeapi.business.service;

import java.util.List;

import com.tips.easyoutubeapi.entity.SimpleVideo;

public interface VideoService {

	List<SimpleVideo> findVideos(List<String> keywords, long quantity);

}
