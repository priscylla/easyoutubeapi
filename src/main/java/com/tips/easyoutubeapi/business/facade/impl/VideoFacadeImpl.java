package com.tips.easyoutubeapi.business.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tips.easyoutubeapi.business.facade.VideoFacade;
import com.tips.easyoutubeapi.business.service.VideoService;
import com.tips.easyoutubeapi.dto.SimpleVideoDTO;
import com.tips.easyoutubeapi.entity.SimpleVideo;

@Service
@Transactional
public class VideoFacadeImpl implements VideoFacade {

	private VideoService videoService;
	
	@Autowired
	public VideoFacadeImpl(VideoService videoService) {
		this.videoService = videoService;
	}
	
	public List<SimpleVideoDTO> findVideos(List<String> keywords) {
		
		List<SimpleVideo> simpleVideoList = videoService.findVideos(keywords);
		
		List<SimpleVideoDTO> simpleVideoDTOList = new ArrayList<SimpleVideoDTO>();
		
		for (SimpleVideo simpleVideo : simpleVideoList) {
			SimpleVideoDTO simpleVideoDTO = new SimpleVideoDTO();
			simpleVideoDTO.setTitle(simpleVideo.getTitle());
			simpleVideoDTOList.add(simpleVideoDTO);
		}
		
		return simpleVideoDTOList;
	}

	public SimpleVideoDTO findVideo(String videoId) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCaption(String videoId) {
		// TODO Auto-generated method stub
		return null;
	}

}
