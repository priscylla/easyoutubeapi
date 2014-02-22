package com.tips.easyoutubeapi.domain.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tips.easyoutubeapi.domain.service.VideoService;
import com.tips.easyoutubeapi.domain.service.impl.VideoServiceImpl;
import com.tips.easyoutubeapi.entity.SimpleVideo;
import com.tips.easyoutubeapi.infraestructure.repository.VideoRepository;

@RunWith(JMock.class)
public class VideoServiceImplTest {
	
	private static final Long MAX_RESULTS_5 = 5L;

	private static final String KEYWORD_BANANA = "banana";
	private static final String KEYWORD_PEAR = "pear";
	private static final String KEYWORD_GRAPE = "grape";
	private static final String KEYWORD_APPLE = "apple";
	
	private static final String KEYWORD_SENT_APPLE_GRAPE_PEAR_BANANA = "apple grape pear banana";
	
	private static final String VIDEO_ID_VVIIDDEEOOIIDD = "vviiddeeooiidd";
	
	private static final String MSG_THE_SIMPLE_VIDEO_RETURNED_DON_T_EQUALS_SIMPLE_VIDEO_EXPECTED = "The simple video returned don't equals simple video expected.";
	private static final String MSG_THE_KEYWORDS_SENDDED_TO_REPOSITORY_DON_T_EQUALS_EXPECTED = "The keywords sendded to repository don't equals expected";
	private static final String MSG_THE_SIMPLE_VIDEO_LIST_EXPECTED_DON_T_BE_EQUALS_SIMPLE_VIDEO_LIST_RETURNED = "The simple video list expected don't be equals simple video list returned.";

	private Mockery context = new JUnit4Mockery();
	VideoService videoServiceImpl;
	VideoRepository videoRepositoryMock;
	
	String videoId;
	SimpleVideo simpleVideoExpected;
	List<SimpleVideo> simpleVideoListExpected;
	
	List<String> keywords;
	Long quantity;

	@Before
	public void initContext() {
		videoRepositoryMock = context.mock(VideoRepository.class);
		videoServiceImpl = new VideoServiceImpl(videoRepositoryMock);
		
		videoId = VIDEO_ID_VVIIDDEEOOIIDD;
		simpleVideoExpected = new SimpleVideo();
		
		keywords = new ArrayList<String>();
		keywords.add(KEYWORD_APPLE);
		keywords.add(KEYWORD_GRAPE);
		keywords.add(KEYWORD_PEAR);
		keywords.add(KEYWORD_BANANA);
		
		quantity = MAX_RESULTS_5;
	}
	
	@Test
	public void whenFindVideosShouldToDelegateToVideoRepositoryFindVideos() throws Exception {
		final Matcher<String> matcherKeywordsSeparatedBySpace = getMatcherKeywordsSeparatedBySpace();
		
		context.checking(new Expectations() {{
			oneOf(videoRepositoryMock).findVideos(with(matcherKeywordsSeparatedBySpace), with(same(quantity)));
			will(returnValue(simpleVideoListExpected));
		}});
		
		videoServiceImpl.findVideos(keywords, quantity);
	}
	
	@Test
	public void whenFindVideosShouldReturnAnExpectedSimpleVideoList() throws Exception {
		context.checking(new Expectations() {{
			ignoring(videoRepositoryMock).findVideos(with(any(String.class)), with(any(Long.class)));
			will(returnValue(simpleVideoListExpected));
		}});
		List<SimpleVideo> simpleVideoListReturned = videoServiceImpl.findVideos(keywords, quantity);
		assertEquals(MSG_THE_SIMPLE_VIDEO_LIST_EXPECTED_DON_T_BE_EQUALS_SIMPLE_VIDEO_LIST_RETURNED, simpleVideoListExpected, simpleVideoListReturned);
	}

	@Test
	public void whenFindVideoShouldToDelegateToVideoRepositoryFindVideo() throws Exception {
		context.checking(new Expectations() {{
			oneOf(videoRepositoryMock).findVideo(with(same(videoId)));
			will(returnValue(simpleVideoExpected));
		}});
		
		videoServiceImplFindVideo(videoId);
	}
	
	@Test
	public void whenFindVideoShouldReturnAnExpectedSimpleVideo() throws Exception {		
		context.checking(new Expectations() {{
			ignoring(videoRepositoryMock).findVideo(with(same(videoId)));
			will(returnValue(simpleVideoExpected));
		}});
		
		SimpleVideo simpleVideoReturned = videoServiceImplFindVideo(videoId);
		assertEquals(MSG_THE_SIMPLE_VIDEO_RETURNED_DON_T_EQUALS_SIMPLE_VIDEO_EXPECTED, simpleVideoExpected, simpleVideoReturned);
	}
	
	private SimpleVideo videoServiceImplFindVideo(String videoId) {
		return videoServiceImpl.findVideo(videoId);
	}
	
	private TypeSafeMatcher<String> getMatcherKeywordsSeparatedBySpace() {
		return new TypeSafeMatcher<String>() {

			public void describeTo(Description desc) {
				desc.appendText(MSG_THE_KEYWORDS_SENDDED_TO_REPOSITORY_DON_T_EQUALS_EXPECTED);
			}

			@Override
			public boolean matchesSafely(String keywordSent) {
				if (KEYWORD_SENT_APPLE_GRAPE_PEAR_BANANA.equals(keywordSent)) {
					return true;
				}
				return false;
			}
		};
	}

}
