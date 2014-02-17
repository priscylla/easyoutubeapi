package com.tips.easyoutubeapi.business.facade.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.util.DateTime;
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
	
	private SimpleVideoDTO simpleVideoToSimpleVideoDTO(SimpleVideo simpleVideo){
		SimpleVideoDTO simpleVideoDTO = new SimpleVideoDTO();
		
		simpleVideoDTO.setTitle(simpleVideo.getTitle());
		simpleVideoDTO.setDescription(simpleVideo.getDescription());
		simpleVideoDTO.setChannelId(simpleVideo.getChannelId());
		simpleVideoDTO.setChannelTitle(simpleVideo.getChannelTitle());
		simpleVideoDTO.setDuration(simpleVideo.getDuration());
		simpleVideoDTO.setCaption(caption);
		simpleVideoDTO.setPublishedAt(DateTimeToDate(simpleVideo.getPublishedAt()));
		simpleVideoDTO.setStatistics(simpleVideo.getStatistics());
		simpleVideoDTO.setThumbnail(simpleVideo.getThumbnail());
		
		return simpleVideoDTO;
	}

	private Date DateTimeToDate(DateTime originalTime) {
		return new java.util.Date(originalTime.getValue());
	}

}
