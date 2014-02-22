package com.tips.easyoutubeapi.infraestructure.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tips.easyoutubeapi.entity.SimpleVideo;
import com.tips.easyoutubeapi.infraestructure.repository.VideoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
public class VideoRepositoryImplTest {

	private static final String MSG_THE_ATTRIBUTE_THUMBNAIL_NOT_SHOULD_BE_NULL = "The attribute thumbnail not should be null.";
	private static final String MSG_THE_ATTRIBUTE_STATISTICS_VIEW_COUNT_NOT_SHOULD_BE_NULL = "The attribute statistics view count not should be null.";
	private static final String MSG_THE_ATTRIBUTE_STATISTICS_LIKE_COUNT_NOT_SHOULD_BE_NULL = "The attribute statistics like count not should be null.";
	private static final String MSG_THE_ATTRIBUTE_STATISTICS_FAVORITE_COUNT_NOT_SHOULD_BE_NULL = "The attribute statistics favorite count not should be null.";
	private static final String MSG_THE_ATTRIBUTE_STATISTICS_DISLIKE_COUNT_NOT_SHOULD_BE_NULL = "The attribute statistics dislike count not should be null.";
	private static final String MSG_THE_ATTRIBUTE_STATISTICS_COMMENT_COUNT_NOT_SHOULD_BE_NULL = "The attribute statistics comment count not should be null.";
	private static final String MSG_THE_ATTRIBUTE_STATISTICS_NOT_SHOULD_BE_NULL = "The attribute statistics not should be null.";
	private static final String MSG_THE_ATTRIBUTE_TITLE_NOT_SHOULD_BE_NULL = "The attribute title not should be null.";
	private static final String MSG_THE_ATTRIBUTE_CAPTION_NOT_SHOULD_BE_NULL = "The attribute caption not should be null.";
	private static final String MSG_THE_ATTRIBUTE_CHANNEL_ID_NOT_SHOULD_BE_NULL = "The attribute channel id not should be null.";
	private static final String MSG_THE_ATTRIBUTE_CHANNEL_TITLE_NOT_SHOULD_BE_NULL = "The attribute channel title not should be null.";
	private static final String MSG_THE_ATTRIBUTE_DESCRIPTION_NOT_SHOULD_BE_NULL = "The attribute description not should be null.";
	private static final String MSG_THE_ATTRIBUTE_PUBLISHED_AT_NOT_SHOULD_BE_NULL = "The attribute published at not should be null.";
	private static final String MSG_THE_ATTRIBUTE_DURATION_NOT_SHOULD_BE_NULL = "The attribute duration not should be null.";
	private static final String MSG_THE_ATTRIBUTE_ID_NOT_SHOULD_BE_NULL = "The attribute id not should be null.";
	private static final String MSG_THE_SIPMLE_VIDEO_LIST_SHOULD_HAVE_THE_SAME_QUANTITY_OF_ELEMENTS = "The SipmleVideoList should have the same quantity of elements.";
	private static final String MSG_THE_SIPMLE_VIDEO_LIST_DON_T_BE_EMPTY = "The SipmleVideoList don't be empty.";
	private static final String MSG_THE_SIPMLE_VIDEO_LIST_DON_T_BE_NULL = "The SipmleVideoList don't be null.";
	private static final String MSG_THE_VIDEO_RETURNED_NOT_SHOULD_BE_NULL = "The video returned not should be null";

	private static final String KEYWORDS_OPERACOES_MATRIZES_MATEMATICA_MULTIPLICACAO = "operações, matrizes, matemática, multiplicação";
	private static final long MAX_RESULTS_OF_VIDEOS_10L = 10l;
	private static final String VIDEO_ID_QBWO_V_GIJ_UBY = "QbwoVGijUBY";

	@Autowired
	private VideoRepository videoRepositoryImpl;
	
	private String keywords;
	Long quantity;
	
	String videoId;
	
	@Before
	public void initialize() throws Exception {
		keywords = KEYWORDS_OPERACOES_MATRIZES_MATEMATICA_MULTIPLICACAO;
		quantity = MAX_RESULTS_OF_VIDEOS_10L;
		
		videoId = VIDEO_ID_QBWO_V_GIJ_UBY;
	}
	
	@Test
	public void whenFindVideosShouldReturnAnExpectedQuantityOfSimpleVideoList() throws Exception {
		List<SimpleVideo> simpleVideoList = findVideos();
		
		assertNotNull(MSG_THE_SIPMLE_VIDEO_LIST_DON_T_BE_NULL, simpleVideoList);
		assertFalse(MSG_THE_SIPMLE_VIDEO_LIST_DON_T_BE_EMPTY, simpleVideoList.isEmpty());
		assertEquals(MSG_THE_SIPMLE_VIDEO_LIST_SHOULD_HAVE_THE_SAME_QUANTITY_OF_ELEMENTS, quantity, new Long(simpleVideoList.size()));
	}
	
	@Test
	public void whenFindVideosShouldReturnSimpleVideoListWithNotNullAttributes() throws Exception {
		List<SimpleVideo> simpleVideoList = findVideos();
		
		assertNotNullSimpleVideoListId(simpleVideoList);
		assertNotNullSimpleVideoListChannelId(simpleVideoList);
		assertNotNullSimpleVideoListChannelTitle(simpleVideoList);
		assertNotNullSimpleVideoListDescription(simpleVideoList);
		assertNotNullSimpleVideoListDuration(simpleVideoList);
		assertNotNullSimpleVideoListPublishedAt(simpleVideoList);
		assertNotNullSimpleVideoListSimpleStatistics(simpleVideoList);
		assertNotNullSimpleVideoListTitle(simpleVideoList);
		assertNotNullSimpleVideoListThumbnail(simpleVideoList);
	}
	
	@Test
	public void whenFindVideosShouldReturnSimpleVideoListWithNotNullAttributeCaption() throws Exception {
		List<SimpleVideo> simpleVideoList = findVideos();
		
		assertNotNullSimpleVideoListCaption(simpleVideoList);
	}
	
	@Test
	public void whenFindVideoShouldToReturnNotNullSimpleVideo() throws Exception {
		SimpleVideo videoReturned = findVideo(videoId);
		
		assertNotNull(MSG_THE_VIDEO_RETURNED_NOT_SHOULD_BE_NULL, videoReturned);
	}

	@Test
	public void whenFindVideoShouldToReturnSimpleVideoWithNotNullAttributes() throws Exception {
		SimpleVideo videoReturned = findVideo(videoId);
		
		assertNotNull(MSG_THE_ATTRIBUTE_ID_NOT_SHOULD_BE_NULL, videoReturned.getId());
		assertNotNull(MSG_THE_ATTRIBUTE_DURATION_NOT_SHOULD_BE_NULL, videoReturned.getDuration());
		assertNotNull(MSG_THE_ATTRIBUTE_CHANNEL_ID_NOT_SHOULD_BE_NULL, videoReturned.getChannelId());
		assertNotNull(MSG_THE_ATTRIBUTE_CHANNEL_TITLE_NOT_SHOULD_BE_NULL, videoReturned.getChannelTitle());
		assertNotNull(MSG_THE_ATTRIBUTE_DESCRIPTION_NOT_SHOULD_BE_NULL, videoReturned.getDescription());
		assertNotNull(MSG_THE_ATTRIBUTE_PUBLISHED_AT_NOT_SHOULD_BE_NULL, videoReturned.getPublishedAt());
		assertNotNull(MSG_THE_ATTRIBUTE_STATISTICS_NOT_SHOULD_BE_NULL, videoReturned.getSimpleStatistics());
		assertNotNull(MSG_THE_ATTRIBUTE_STATISTICS_COMMENT_COUNT_NOT_SHOULD_BE_NULL, videoReturned.getSimpleStatistics().getCommentCount());
		assertNotNull(MSG_THE_ATTRIBUTE_STATISTICS_DISLIKE_COUNT_NOT_SHOULD_BE_NULL, videoReturned.getSimpleStatistics().getDislikeCount());
		assertNotNull(MSG_THE_ATTRIBUTE_STATISTICS_FAVORITE_COUNT_NOT_SHOULD_BE_NULL, videoReturned.getSimpleStatistics().getFavoriteCount());
		assertNotNull(MSG_THE_ATTRIBUTE_STATISTICS_LIKE_COUNT_NOT_SHOULD_BE_NULL, videoReturned.getSimpleStatistics().getLikeCount());
		assertNotNull(MSG_THE_ATTRIBUTE_STATISTICS_VIEW_COUNT_NOT_SHOULD_BE_NULL, videoReturned.getSimpleStatistics().getViewCount());
		assertNotNull(MSG_THE_ATTRIBUTE_THUMBNAIL_NOT_SHOULD_BE_NULL, videoReturned.getThumbnail());
		assertNotNull(MSG_THE_ATTRIBUTE_TITLE_NOT_SHOULD_BE_NULL, videoReturned.getTitle());
	}
	
	@Test
	public void whenFindVideoShouldToReturnSimpleVideoWithNotNullAttributeCaption() throws Exception {
		SimpleVideo videoReturned = findVideo(videoId);
		
		assertNotNull(MSG_THE_ATTRIBUTE_TITLE_NOT_SHOULD_BE_NULL, videoReturned.getCaption());
	}
	
	private List<SimpleVideo> findVideos() {
		List<SimpleVideo> simpleVideoList = videoRepositoryImpl.findVideos(keywords, quantity);
		return simpleVideoList;
	}
	
	private SimpleVideo findVideo(String videoId) {
		return videoRepositoryImpl.findVideo(videoId);
	}

	private void assertNotNullSimpleVideoListId(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull(MSG_THE_ATTRIBUTE_ID_NOT_SHOULD_BE_NULL, simpleVideo.getId());
		}
	}

	private void assertNotNullSimpleVideoListCaption(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull(MSG_THE_ATTRIBUTE_CAPTION_NOT_SHOULD_BE_NULL, simpleVideo.getCaption());
		}
	}
	
	private void assertNotNullSimpleVideoListChannelId(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull(MSG_THE_ATTRIBUTE_CHANNEL_ID_NOT_SHOULD_BE_NULL, simpleVideo.getChannelId());
		}
	}

	private void assertNotNullSimpleVideoListChannelTitle(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull(MSG_THE_ATTRIBUTE_CHANNEL_TITLE_NOT_SHOULD_BE_NULL, simpleVideo.getChannelTitle());
		}
	}

	private void assertNotNullSimpleVideoListDescription(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull(MSG_THE_ATTRIBUTE_DESCRIPTION_NOT_SHOULD_BE_NULL, simpleVideo.getDescription());
		}
	}

	private void assertNotNullSimpleVideoListDuration(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull(MSG_THE_ATTRIBUTE_DURATION_NOT_SHOULD_BE_NULL, simpleVideo.getDuration());
		}
	}

	private void assertNotNullSimpleVideoListPublishedAt(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull(MSG_THE_ATTRIBUTE_PUBLISHED_AT_NOT_SHOULD_BE_NULL, simpleVideo.getPublishedAt());
		}
	}

	private void assertNotNullSimpleVideoListSimpleStatistics(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull(MSG_THE_ATTRIBUTE_STATISTICS_NOT_SHOULD_BE_NULL, simpleVideo.getSimpleStatistics());
			assertNotNull(MSG_THE_ATTRIBUTE_STATISTICS_COMMENT_COUNT_NOT_SHOULD_BE_NULL, simpleVideo.getSimpleStatistics().getCommentCount());
			assertNotNull(MSG_THE_ATTRIBUTE_STATISTICS_DISLIKE_COUNT_NOT_SHOULD_BE_NULL, simpleVideo.getSimpleStatistics().getDislikeCount());
			assertNotNull(MSG_THE_ATTRIBUTE_STATISTICS_FAVORITE_COUNT_NOT_SHOULD_BE_NULL, simpleVideo.getSimpleStatistics().getFavoriteCount());
			assertNotNull(MSG_THE_ATTRIBUTE_STATISTICS_LIKE_COUNT_NOT_SHOULD_BE_NULL, simpleVideo.getSimpleStatistics().getLikeCount());
			assertNotNull(MSG_THE_ATTRIBUTE_STATISTICS_VIEW_COUNT_NOT_SHOULD_BE_NULL, simpleVideo.getSimpleStatistics().getViewCount());
		}
	}

	private void assertNotNullSimpleVideoListThumbnail(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull(MSG_THE_ATTRIBUTE_THUMBNAIL_NOT_SHOULD_BE_NULL, simpleVideo.getThumbnail());
		}
	}

	private void assertNotNullSimpleVideoListTitle(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull(MSG_THE_ATTRIBUTE_TITLE_NOT_SHOULD_BE_NULL, simpleVideo.getTitle());
		}
	}
	
}
