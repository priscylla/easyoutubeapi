package com.tips.easyoutubeapi.infraestructure.repository.impl;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

public abstract class YouTubeVideoGenericRepository {

	private static final String PARAMS_ID_AND_GENERAL_INFORMATIONS = "id,snippet";
	private static final String APP_NAME = "EasYouTubeApi";
	private static final String TYPE_YOUTUBE_VIDEO = "youtube#video";
	private static final Long MAX_RESULTS_ONE = 1l;
	
	protected static YouTube youtube;
	protected YouTube.Search.List search;
	
	private Properties properties;
	
	@Autowired
	public YouTubeVideoGenericRepository(Properties properties) {
		this.properties = properties;
		
		youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName(APP_NAME).build();
		
		try {
			search = youtube.search().list(PARAMS_ID_AND_GENERAL_INFORMATIONS);
			search.setKey(getApiKey());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getApiKey() {
		return properties.getProperty("youtube.apikey");
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
