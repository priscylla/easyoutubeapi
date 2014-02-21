package com.tips.easyoutubeapi.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatistics;
import com.tips.easyoutubeapi.entity.SimpleVideo;
import com.tips.easyoutubeapi.entity.SimpleStatistics;
import com.tips.easyoutubeapi.repository.VideoRepository;

@Service
public class VideoRepositoryImpl extends AbstractYoutube implements VideoRepository {
	
	@Autowired
	public VideoRepositoryImpl(@Qualifier("youtubeProperties") Properties properties) {
		super(properties);
	}

	public List<SimpleVideo> findVideos(List<String> keywords, Long quantity) {
		String keywordsSeparatedBySpace = getKeywordsSeparatedBySpace(keywords);
		List<SearchResult> searchResultList = searchGeneralInformationsOnYouTubeByKeywords(keywordsSeparatedBySpace, quantity);
        
		List<SimpleVideo> simpleVideoListToReturn = new ArrayList<SimpleVideo>();

		for (SearchResult searchResult : searchResultList) {
			
        	ResourceId resourceId = searchResult.getId();
        	SearchResultSnippet snippet = searchResult.getSnippet();
        	Thumbnail thumbnail = snippet.getThumbnails().getDefault();
        	
        	if (isResourceOfTypeYouTubeVideo(resourceId)) {
        		SimpleVideo simpleVideo = new SimpleVideo();
        		simpleVideo.setId(resourceId.getVideoId());
        		simpleVideo.setTitle(snippet.getTitle());
        		simpleVideo.setDescription(snippet.getDescription());
        		simpleVideo.setPublishedAt(snippet.getPublishedAt());
        		simpleVideo.setChannelId(snippet.getChannelId());
        		simpleVideo.setChannelTitle(snippet.getChannelTitle());
        		simpleVideo.setThumbnail(thumbnail.getUrl());
        		
        		Video ytVideo = findYouTubeVideo(resourceId.getVideoId());
        		
        		simpleVideo.setDuration(ytVideo.getContentDetails().getDuration());
        		
        		SimpleStatistics statistics = new SimpleStatistics(ytVideo.getStatistics().getViewCount(), 
        				ytVideo.getStatistics().getLikeCount(), 
        				ytVideo.getStatistics().getDislikeCount(), 
        				ytVideo.getStatistics().getFavoriteCount(), 
        				ytVideo.getStatistics().getCommentCount());
				simpleVideo.setSimpleStatistics(statistics);
				
				simpleVideoListToReturn.add(simpleVideo);
        	}
		}
        
		return simpleVideoListToReturn;
	}

	private String getKeywordsSeparatedBySpace(List<String> keywords) {
		String keywordsAux = keywords.toString();
		keywordsAux = keywordsAux.replace("[", "");
		keywordsAux = keywordsAux.replace("]", "");
		keywordsAux = keywordsAux.replace(",", "");
		return keywordsAux;
	}

	public SimpleVideo findVideo(String videoId) {
		Video videoReturned = findYouTubeVideo(videoId);
		return convertVideoToSimpleVideo(videoReturned);
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
