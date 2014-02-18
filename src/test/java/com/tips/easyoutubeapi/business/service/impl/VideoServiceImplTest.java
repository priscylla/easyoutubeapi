package com.tips.easyoutubeapi.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tips.easyoutubeapi.business.service.VideoService;
import com.tips.easyoutubeapi.entity.SimpleVideo;
import com.tips.easyoutubeapi.repository.VideoRepository;

@RunWith(JMock.class)
public class VideoServiceImplTest {
	
	private Mockery context = new JUnit4Mockery();
	VideoService videoServiceImpl;
	VideoRepository videoRepositoryMock;
	
	@Before
	public void initContext() {
		videoRepositoryMock = context.mock(VideoRepository.class);
		videoServiceImpl = new VideoServiceImpl(videoRepositoryMock);
	}

	@Test
	public void whenFindVideosShouldToDelegateToVideoRepositoryFindVideos() throws Exception {
		
		final List<String> keywords = new ArrayList<String>();
		final List<SimpleVideo> simpleVideoList = new ArrayList<SimpleVideo>();
		final long NUMBER_OF_SEARCHED_VIDEOS = 1;
		
		context.checking(new Expectations() {{
			oneOf(videoRepositoryMock).findVideos(keywords, NUMBER_OF_SEARCHED_VIDEOS);
			will(returnValue(simpleVideoList));
		}});
		
		videoServiceImpl.findVideos(keywords, NUMBER_OF_SEARCHED_VIDEOS);
		
	}
	
	@Test
	public void whenFindVideosShouldReturnAQuantityExpectedSimpleVideos() throws Exception {
		
		final List<String> keywords = new ArrayList<String>();
		final List<SimpleVideo> simpleVideoListReturnedFromVideoRepository = new ArrayList<SimpleVideo>();
		final long NUMBER_OF_SEARCHED_VIDEOS = 1;
		SimpleVideo simpleVideo1 = new SimpleVideo();
		simpleVideo1.setTitle("Titulo");
		simpleVideo1.setDescription("Descrição do Video");
		simpleVideoListReturnedFromVideoRepository.add(simpleVideo1);
		
		context.checking(new Expectations() {{
			oneOf(videoRepositoryMock).findVideos(keywords, NUMBER_OF_SEARCHED_VIDEOS);
			will(returnValue(simpleVideoListReturnedFromVideoRepository));
		}});
		
		List<SimpleVideo> simpleVideoListReturnedFromVideoService = videoServiceImpl.findVideos(keywords, NUMBER_OF_SEARCHED_VIDEOS);

		assertNotNull("Não deveria ter retornado nulo.", simpleVideoListReturnedFromVideoService);
		
		assertEquals("Deveria conter a mesma quantidade de elementos da lista de videos simples.", 
				NUMBER_OF_SEARCHED_VIDEOS, simpleVideoListReturnedFromVideoService.size());
		


	}

}
