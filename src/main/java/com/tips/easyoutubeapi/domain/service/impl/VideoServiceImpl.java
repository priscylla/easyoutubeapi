package com.tips.easyoutubeapi.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tips.easyoutubeapi.domain.service.VideoService;
import com.tips.easyoutubeapi.entity.SimpleVideo;
import com.tips.easyoutubeapi.infraestructure.repository.VideoRepository;

@Service
public class VideoServiceImpl implements VideoService {
	
	private VideoRepository videoRepository;

	@Autowired
	public VideoServiceImpl(VideoRepository videoRepository) {
		this.videoRepository = videoRepository;
	}

	public List<SimpleVideo> findVideos(List<String> keywords, Long quantity) {
		String keywordsSeparatedBySpace = joinKeywordsSeparatingByComma(keywords);
		return videoRepository.findVideos(keywordsSeparatedBySpace, quantity);
	}

	public SimpleVideo findVideo(String videoId) {
		return videoRepository.findVideo(videoId);
	}

	private String joinKeywordsSeparatingByComma(List<String> keywords) {
		String keywordsAux = keywords.toString();
		keywordsAux = keywordsAux.replace("[", "");
		keywordsAux = keywordsAux.replace("]", "");
		keywordsAux = keywordsAux.replace(",", "");
		return keywordsAux;
	}

}
