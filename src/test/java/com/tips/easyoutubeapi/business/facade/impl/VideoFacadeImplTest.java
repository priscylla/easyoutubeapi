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
		
		context.checking(new Expectations() {{
			oneOf(videoServiceMock).findVideos(with(same(keywords)));
			will(returnValue(simpleVideoList));
		}});
		
		videoFacadeImpl.findVideos(keywords);
		
	}
	
	@Test
	public void whenFindVideosShouldReturnAnExpectedSimpleVideoDTOList() throws Exception {
		
		final List<String> keywords = new ArrayList<String>();
		final List<SimpleVideo> simpleVideoList = new ArrayList<SimpleVideo>();
		SimpleVideo simpleVideo1 = new SimpleVideo();
		simpleVideo1.setTitle("Titulo");
		simpleVideoList.add(simpleVideo1);
		
		context.checking(new Expectations() {{
			oneOf(videoServiceMock).findVideos(with(same(keywords)));
			will(returnValue(simpleVideoList));
		}});
		
		List<SimpleVideoDTO> simpleVideoDTOList = videoFacadeImpl.findVideos(keywords);

		assertNotNull("Não deveria ter retornado nulo.", simpleVideoDTOList);
		
		assertEquals("Deveria conter a mesma quantidade de elementos da lista de videos simples.", 
				simpleVideoList.size(), simpleVideoDTOList.size());
		
		assertEquals("Deveria ter retornado o titulo esperado.", 
				simpleVideo1.getTitle(),  simpleVideoDTOList.get(0).getTitle());

	}
	
}
