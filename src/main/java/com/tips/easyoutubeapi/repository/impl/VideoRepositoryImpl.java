package com.tips.easyoutubeapi.repository.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.tips.easyoutubeapi.Auth;
import com.tips.easyoutubeapi.entity.SimpleVideo;
import com.tips.easyoutubeapi.repository.VideoRepository;

@Service
public class VideoRepositoryImpl implements VideoRepository {

	private static YouTube youtube;
	private YouTube.Search.List search;
	
	public VideoRepositoryImpl() {
		youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName("EasYouTubeApi").build();
		
		try {
			search = youtube.search().list("id,snippet");
			search.setKey("AIzaSyCoaNth4gZXiEz-bzVJaUkwnZguwwiisLg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<SimpleVideo> findVideos(List<String> keywords, Long quantity) {
		String keywordsSeparatedBySpace = keywords.toString();
		keywordsSeparatedBySpace = keywordsSeparatedBySpace.replace("[", "");
		keywordsSeparatedBySpace = keywordsSeparatedBySpace.replace("]", "");
		keywordsSeparatedBySpace = keywordsSeparatedBySpace.replace(",", "");
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
        
        
        List<SimpleVideo> simpleVideoList = new ArrayList<SimpleVideo>();
        
        for (SearchResult searchResult : searchResultList) {

        	ResourceId rId = searchResult.getId();
        	
        	if (rId.getKind().equals("youtube#video")) {
        		SimpleVideo simpleVideo = new SimpleVideo();
        		simpleVideo.setTitle("Video de ID: " + rId.getVideoId());
				simpleVideoList.add(simpleVideo);
        	}
		}
        
		return simpleVideoList;
	}

}
