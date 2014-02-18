package com.tips.easyoutubeapi.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;
import com.tips.easyoutubeapi.entity.SimpleVideo;
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
        	
        	if (isResourceOfTypeYouTubeVideo(resourceId)) {
        		SimpleVideo simpleVideo = new SimpleVideo();
        		
        		simpleVideo.setId("");
        		
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
