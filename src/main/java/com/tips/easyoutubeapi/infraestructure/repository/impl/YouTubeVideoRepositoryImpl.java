package com.tips.easyoutubeapi.infraestructure.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatistics;
import com.tips.easyoutubeapi.entity.SimpleStatistics;
import com.tips.easyoutubeapi.entity.SimpleVideo;
import com.tips.easyoutubeapi.infraestructure.repository.VideoRepository;

@Service
public class YouTubeVideoRepositoryImpl extends YouTubeVideoGenericRepository implements VideoRepository {
	
	@Autowired
	public YouTubeVideoRepositoryImpl(@Qualifier("youtubeProperties") Properties properties) {
		super(properties);
	}

	public List<SimpleVideo> findVideos(String keywordsSeparatedBySpace, Long quantity) {
		List<SearchResult> searchResultList = searchGeneralInformationsOnYouTubeByKeywords(keywordsSeparatedBySpace, quantity);
		List<String> videoIdListReturned = getVideoIdListFromSearchResultList(searchResultList);
		return findVideosByVideoIdList(videoIdListReturned);
	}

	public SimpleVideo findVideo(String videoId) {
		Video videoReturned = findYouTubeVideo(videoId);
		return convertVideoToSimpleVideo(videoReturned);
	}
	
	private List<SimpleVideo> findVideosByVideoIdList(List<String> videoIdList) {
		List<SimpleVideo> simpleVideoListToReturn = new ArrayList<SimpleVideo>();
		for (String videoId : videoIdList) {
			SimpleVideo simpleVideoReturned = findVideo(videoId);
			simpleVideoListToReturn.add(simpleVideoReturned);
		}
		return simpleVideoListToReturn;
	}
	
	private List<String> getVideoIdListFromSearchResultList(List<SearchResult> searchResultList) {
		List<String> videoIdList = new ArrayList<String>();
		for (SearchResult searchResult : searchResultList) {
        	ResourceId resourceId = searchResult.getId();
        	if (isResourceOfTypeYouTubeVideo(resourceId)) {
        		videoIdList.add(resourceId.getVideoId());
        	}
		}
		return videoIdList;
	}

	private SimpleVideo convertVideoToSimpleVideo(Video video) {
		SimpleVideo simpleVideo = new SimpleVideo();
		
		VideoStatistics videoStatistics = video.getStatistics();
		VideoSnippet videoSnippet = video.getSnippet();
		Thumbnail thumbnail = videoSnippet.getThumbnails().getDefault();
		
		simpleVideo.setId(video.getId());
		simpleVideo.setDuration(video.getContentDetails().getDuration());
		simpleVideo.setSimpleStatistics(convertVideoStatisticsToSimpleStatistics(videoStatistics));
		simpleVideo.setTitle(videoSnippet.getTitle());
		simpleVideo.setDescription(videoSnippet.getDescription());
		simpleVideo.setPublishedAt(videoSnippet.getPublishedAt());
		simpleVideo.setChannelId(videoSnippet.getChannelId());
		simpleVideo.setChannelTitle(videoSnippet.getChannelTitle());
		simpleVideo.setThumbnail(thumbnail.getUrl());
		
		return simpleVideo;
	}

	private SimpleStatistics convertVideoStatisticsToSimpleStatistics(VideoStatistics videoStatistics) {
		return new SimpleStatistics(videoStatistics.getViewCount(), 
				videoStatistics.getLikeCount(), 
				videoStatistics.getDislikeCount(), 
				videoStatistics.getFavoriteCount(), 
				videoStatistics.getCommentCount());
	}

}
