package com.tips.easyoutubeapi.repository.impl;

import java.io.IOException;
import java.util.List;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.tips.easyoutubeapi.Auth;

public abstract class AbstractYoutube {

	private static final String PARAMS_ID_AND_GENERAL_INFORMATIONS = "id,snippet";
	private static final String APP_NAME = "EasYouTubeApi";
	private static final String API_KEY = "AIzaSyCoaNth4gZXiEz-bzVJaUkwnZguwwiisLg";
	private static final String TYPE_YOUTUBE_VIDEO = "youtube#video";
	private static final Long MAX_RESULTS_ONE = 1l;
	
	protected static YouTube youtube;
	protected YouTube.Search.List search;
	
	public AbstractYoutube() {
		youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName(APP_NAME).build();
		
		try {
			search = youtube.search().list(PARAMS_ID_AND_GENERAL_INFORMATIONS);
			search.setKey(API_KEY);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getApiKey() {
		return API_KEY;
	}

	
	
	protected boolean isResourceOfTypeYouTubeVideo(ResourceId resourceId) {
		return resourceId.getKind().equals(TYPE_YOUTUBE_VIDEO);
	}
	
	protected List<SearchResult> searchGeneralInformationsOnYouTubeByKeywords(String keywordsSeparatedBySpace, Long quantity) {
		search.setQ(keywordsSeparatedBySpace);
        search.setType("video");
        search.setFields("items(id/kind,id/videoId,snippet/title,snippet/description,snippet/channelTitle,snippet/channelId,snippet/publishedAt,snippet/thumbnails/default/url)");
        search.setMaxResults(quantity);
        SearchListResponse searchResponse = null;
        
		try {
			searchResponse = search.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        List<SearchResult> searchResultList = searchResponse.getItems();
		return searchResultList;
	}
	
	protected Video findYouTubeVideo(String videoId) {
		
		try {
			com.google.api.services.youtube.YouTube.Videos.List  youTubeVideoList = 
					youtube.videos().list("snippet,statistics,contentDetails")
					.setId(videoId)
					.setKey(getApiKey())
					.setMaxResults(MAX_RESULTS_ONE);
			
			VideoListResponse videoListResponse = youTubeVideoList.execute();
			
			if (!videoListResponse.isEmpty()) {
				List<Video> videoList = videoListResponse.getItems();
				return videoList.get(0);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return new Video();
		
	}
	
}
