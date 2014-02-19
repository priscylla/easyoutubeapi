package com.tips.easyoutubeapi.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.Video;
import com.tips.easyoutubeapi.entity.SimpleVideo;
import com.tips.easyoutubeapi.entity.Statistics;
import com.tips.easyoutubeapi.repository.VideoRepository;

@Service
public class VideoRepositoryImpl extends AbstractYoutube implements VideoRepository {

	

	public VideoRepositoryImpl() {
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
        		
        		Statistics statistics = new Statistics(ytVideo.getStatistics().getViewCount(), 
        				ytVideo.getStatistics().getLikeCount(), 
        				ytVideo.getStatistics().getDislikeCount(), 
        				ytVideo.getStatistics().getFavoriteCount(), 
        				ytVideo.getStatistics().getCommentCount());
				simpleVideo.setStatistics(statistics);
				
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

}
