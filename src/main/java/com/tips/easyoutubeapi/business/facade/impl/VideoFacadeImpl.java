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
import com.tips.easyoutubeapi.dto.CaptionDTO;
import com.tips.easyoutubeapi.dto.SimpleVideoDTO;
import com.tips.easyoutubeapi.dto.StatisticsDTO;
import com.tips.easyoutubeapi.dto.SubtitleDTO;
import com.tips.easyoutubeapi.entity.Caption;
import com.tips.easyoutubeapi.entity.SimpleVideo;
import com.tips.easyoutubeapi.entity.Statistics;
import com.tips.easyoutubeapi.entity.Subtitle;

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
			simpleVideoDTOList.add(simpleVideoToSimpleVideoDTO(simpleVideo));
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
		simpleVideoDTO.setCaption(captionToCaptionDTO(simpleVideo.getCaption()));
		simpleVideoDTO.setPublishedAt(DateTimeToDate(simpleVideo.getPublishedAt()));
		simpleVideoDTO.setStatistics(StatisticsToStatisticsDTO(simpleVideo.getStatistics()));
		simpleVideoDTO.setThumbnail(simpleVideo.getThumbnail());
		
		return simpleVideoDTO;
	}

	private CaptionDTO captionToCaptionDTO(Caption caption) {
		CaptionDTO captionDTO;
		if(caption==null)
			return null;
		else
			captionDTO = new CaptionDTO();
			captionDTO.setSubtitles(subtitleListToSubtitleDTOList(caption.getSubtitles()));
			return captionDTO;
	}

	private List<SubtitleDTO> subtitleListToSubtitleDTOList(List<Subtitle> subtitles) {
		List<SubtitleDTO> subtitleDTOList = new ArrayList<SubtitleDTO>();
		for (Subtitle subtitle : subtitles) {
			subtitleDTOList.add(subtitleToSubtitleDTO(subtitle));
		}
		return subtitleDTOList;
	}

	private SubtitleDTO subtitleToSubtitleDTO(Subtitle subtitle) {
		SubtitleDTO subtitleDTO = new SubtitleDTO();
		subtitleDTO.setText(subtitle.getText());
		subtitleDTO.setLanguage(subtitle.getLanguage().getName());
		return subtitleDTO;
	}

	private StatisticsDTO StatisticsToStatisticsDTO(Statistics statistics) {
		StatisticsDTO statisticsDTO = new StatisticsDTO();
		statisticsDTO.setCommentCount(statistics.getCommentCount());
		statisticsDTO.setDislikeCount(statistics.getDislikeCount());
		statisticsDTO.setFavoriteCount(statistics.getFavoriteCount());
		statisticsDTO.setLikeCount(statistics.getLikeCount());
		statisticsDTO.setViewCount(statistics.getViewCount());
		return statisticsDTO;
	}

	private Date DateTimeToDate(DateTime originalTime) {
		if(originalTime==null)
			return null;
		return new java.util.Date(originalTime.getValue());
	}

}
