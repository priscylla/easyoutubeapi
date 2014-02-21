package com.tips.easyoutubeapi.business.facade.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tips.easyoutubeapi.business.facade.VideoFacade;
import com.tips.easyoutubeapi.business.service.VideoService;
import com.tips.easyoutubeapi.dto.SimpleVideoDTO;
import com.tips.easyoutubeapi.entity.SimpleVideo;

@RunWith(JMock.class)
public class VideoFacadeImplTest {

	private Mockery context = new JUnit4Mockery();
	VideoFacade videoFacadeImpl;
	VideoService videoServiceMock;
	
	@Before
	public void initContext() {
		videoServiceMock = context.mock(VideoService.class);
		videoFacadeImpl = new VideoFacadeImpl(videoServiceMock);
	}
	
	@Test
	public void whenFindVideosShouldToDelegateToVideoServiceFindVideos() throws Exception {
		
		final List<String> keywords = new ArrayList<String>();
		final List<SimpleVideo> simpleVideoList = new ArrayList<SimpleVideo>();
		final long NUMBER_OF_SEARCHED_VIDEOS = 1;
		
		context.checking(new Expectations() {{
			oneOf(videoServiceMock).findVideos(with(same(keywords)), with(same(NUMBER_OF_SEARCHED_VIDEOS)));
			will(returnValue(simpleVideoList));
		}});
		
		videoFacadeImpl.findVideos(keywords, NUMBER_OF_SEARCHED_VIDEOS);
		
	}
	
	@Test
	public void whenFindVideosShouldReturnAnExpectedSimpleVideoDTOList() throws Exception {
		
		final List<String> keywords = new ArrayList<String>();
		final List<SimpleVideo> simpleVideoList = new ArrayList<SimpleVideo>();
		final long NUMBER_OF_SEARCHED_VIDEOS = 1;
		SimpleVideo simpleVideo1 = new SimpleVideo();
		simpleVideo1.setTitle("Titulo");
		simpleVideo1.setDescription("Descrição do Video");
		simpleVideoList.add(simpleVideo1);
		
		context.checking(new Expectations() {{
			oneOf(videoServiceMock).findVideos(with(same(keywords)), with(same(NUMBER_OF_SEARCHED_VIDEOS)));
			will(returnValue(simpleVideoList));
		}});
		
		List<SimpleVideoDTO> simpleVideoDTOList = videoFacadeImpl.findVideos(keywords, NUMBER_OF_SEARCHED_VIDEOS);

		assertNotNull("Não deveria ter retornado nulo.", simpleVideoDTOList);
		
		assertEquals("Deveria conter a mesma quantidade de elementos da lista de videos simples.", 
				simpleVideoList.size(), simpleVideoDTOList.size());
		
		assertEquals("Deveria ter retornado o titulo esperado.", 
				simpleVideo1.getTitle(),  simpleVideoDTOList.get(0).getTitle());
		
		assertEquals("Deveria ter a descrição esperada.", 
				simpleVideo1.getDescription(),  simpleVideoDTOList.get(0).getDescription());

	}
	
	@Test
	public void whenFindVideoShouldToDelegateToVideoServiceFindVideo() throws Exception {
		final String videoId = "wszv-asdsa";
		final SimpleVideo simpleVideo = new SimpleVideo();
		
		context.checking(new Expectations() {{
			oneOf(videoServiceMock).findVideo(with(same(videoId)));
			will(returnValue(simpleVideo));
		}});
		
		videoFacadeImpl.findVideo(videoId);		
	}
	
}
