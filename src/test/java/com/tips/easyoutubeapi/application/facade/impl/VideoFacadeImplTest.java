package com.tips.easyoutubeapi.application.facade.impl;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.api.client.util.DateTime;
import com.tips.easyoutubeapi.application.facade.VideoFacade;
import com.tips.easyoutubeapi.application.facade.impl.VideoFacadeImpl;
import com.tips.easyoutubeapi.domain.service.VideoService;
import com.tips.easyoutubeapi.dto.SimpleVideoDTO;
import com.tips.easyoutubeapi.entity.Caption;
import com.tips.easyoutubeapi.entity.Languages;
import com.tips.easyoutubeapi.entity.SimpleStatistics;
import com.tips.easyoutubeapi.entity.SimpleVideo;
import com.tips.easyoutubeapi.entity.Subtitle;
import com.tips.easyoutubeapi.entity.builder.CaptionBuilder;
import com.tips.easyoutubeapi.entity.builder.SimpleStatisticsBuilder;
import com.tips.easyoutubeapi.entity.builder.SimpleVideoBuilder;

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
		final Long NUMBER_OF_SEARCHED_VIDEOS = 1L;
		
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

		simpleVideoList.add(getASimpleVideo());
		
		context.checking(new Expectations() {{
			oneOf(videoServiceMock).findVideos(with(same(keywords)), with(same(NUMBER_OF_SEARCHED_VIDEOS)));
			will(returnValue(simpleVideoList));
		}});
		
		List<SimpleVideoDTO> simpleVideoDTOList = videoFacadeImpl.findVideos(keywords, NUMBER_OF_SEARCHED_VIDEOS);

		assertNotNull("Não deveria ter retornado nulo.", simpleVideoDTOList);
		
		assertEquals("Deveria conter a mesma quantidade de elementos da lista de videos simples.", 
				simpleVideoList.size(), simpleVideoDTOList.size());
		
		//TODO: Testar DTO equals Entity

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
	
	//TODO: Criar um Builder para Subtitle.
	private SimpleVideo getASimpleVideo() {
		List<Subtitle> subtitles = new ArrayList<Subtitle>();
		Subtitle subtitle = new Subtitle();
		subtitle.setLanguage(Languages.PT);
		subtitle.setText("Text Subtitle");
		subtitles.add(subtitle);
		Caption caption = new CaptionBuilder().withSubtitleList(subtitles).build();
		DateTime publishedAtDate = new DateTime(new Date());
		
		SimpleStatistics simpleStatistics = new  SimpleStatisticsBuilder()
													.withCommentCount(new BigInteger("1"))
													.withDislikeCount(new BigInteger("2"))
													.withFavoriteCount(new BigInteger("3"))
													.withLikeCount(new BigInteger("4"))
													.withViewCount(new BigInteger("5"))
													.build();
		return new SimpleVideoBuilder()
										.withCaption(caption)
										.withChannelId("Channel Id")
										.withChannelTitle("Tittle")
										.withDescription("Description")
										.withDuration("Duration")
										.withId("Id")
										.withPublishedAt(publishedAtDate)
										.withStatistics(simpleStatistics)
										.withThumbnail("Thumbnail").build();
	}
}
