package com.tips.easyoutubeapi.business.facade.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.util.DateTime;
import com.tips.easyoutubeapi.business.facade.VideoFacade;
import com.tips.easyoutubeapi.business.service.VideoService;
import com.tips.easyoutubeapi.dto.CaptionDTO;
import com.tips.easyoutubeapi.dto.SimpleVideoDTO;
import com.tips.easyoutubeapi.dto.StatisticsDTO;
import com.tips.easyoutubeapi.dto.SubtitleDTO;
import com.tips.easyoutubeapi.entity.Caption;
import com.tips.easyoutubeapi.entity.SimpleVideo;
import com.tips.easyoutubeapi.entity.SimpleStatistics;
import com.tips.easyoutubeapi.entity.Subtitle;

@Service
public class VideoFacadeImpl implements VideoFacade {

	private VideoService videoService;
	
	@Autowired
	public VideoFacadeImpl(VideoService videoService) {
		this.videoService = videoService;
	}
	
	public List<SimpleVideoDTO> findVideos(List<String> keywords, long quantity) {
		List<SimpleVideo> simpleVideoList = videoService.findVideos(keywords, quantity);
		
		List<SimpleVideoDTO> simpleVideoDTOList = convertSimpleVideoListToSimpleVideoDTOList(simpleVideoList);
		
		return simpleVideoDTOList;
	}

	private List<SimpleVideoDTO> convertSimpleVideoListToSimpleVideoDTOList(List<SimpleVideo> simpleVideoList) {
		List<SimpleVideoDTO> simpleVideoDTOList = new ArrayList<SimpleVideoDTO>();
		for (SimpleVideo simpleVideo : simpleVideoList) {
			simpleVideoDTOList.add(convertSimpleVideoToSimpleVideoDTO(simpleVideo));
		}
		return simpleVideoDTOList;
	}

	public SimpleVideoDTO findVideo(String videoId) {
		SimpleVideo simpleVideoReturned = videoService.findVideo(videoId);
		SimpleVideoDTO simpleVideoDTOConverted = convertSimpleVideoToSimpleVideoDTO(simpleVideoReturned);
		return simpleVideoDTOConverted;
	}
	
	private SimpleVideoDTO convertSimpleVideoToSimpleVideoDTO(SimpleVideo simpleVideo){
		SimpleVideoDTO simpleVideoDTO = new SimpleVideoDTO();
		
		simpleVideoDTO.setId(simpleVideo.getId());
		simpleVideoDTO.setTitle(simpleVideo.getTitle());
		simpleVideoDTO.setDescription(simpleVideo.getDescription());
		simpleVideoDTO.setChannelId(simpleVideo.getChannelId());
		simpleVideoDTO.setChannelTitle(simpleVideo.getChannelTitle());
		simpleVideoDTO.setDuration(simpleVideo.getDuration());
		simpleVideoDTO.setCaption(captionToCaptionDTO(simpleVideo.getCaption()));
		simpleVideoDTO.setPublishedAt(DateTimeToDate(simpleVideo.getPublishedAt()));
		simpleVideoDTO.setStatistics(StatisticsToStatisticsDTO(simpleVideo.getSimpleStatistics()));
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

	private StatisticsDTO StatisticsToStatisticsDTO(SimpleStatistics statistics) {
		StatisticsDTO statisticsDTO;
		if(statistics == null)
			return null;
		statisticsDTO = new StatisticsDTO();
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
