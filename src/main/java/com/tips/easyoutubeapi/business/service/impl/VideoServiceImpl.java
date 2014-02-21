package com.tips.easyoutubeapi.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tips.easyoutubeapi.business.service.VideoService;
import com.tips.easyoutubeapi.entity.SimpleVideo;
import com.tips.easyoutubeapi.repository.VideoRepository;

@Service
public class VideoServiceImpl implements VideoService {
	
	private VideoRepository videoRepository;

	@Autowired
	public VideoServiceImpl(VideoRepository videoRepository) {
		this.videoRepository = videoRepository;
	}

	//TODO: Alterar parametro do repositôrio para receber apenas uma string e serviço juntar keywords em uma string.
	public List<SimpleVideo> findVideos(List<String> keywords, long quantity) {
		return videoRepository.findVideos(keywords, quantity);
	}

	public SimpleVideo findVideo(String videoId) {
		return videoRepository.findVideo(videoId);
	}

}
