package com.tips.easyoutubeapi.repository.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tips.easyoutubeapi.entity.SimpleVideo;
import com.tips.easyoutubeapi.repository.VideoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
public class VideoRepositoryImplTest {

	private static final String MSG_THE_VIDEO_RETURNED_NOT_SHOULD_BE_NULL = "The video returned not should be null";

	@Autowired
	private VideoRepository videoRepositoryImpl;
	
	private List<String> keywords;
	Long quantity;
	
	String videoId;
	
	@Before
	public void initialize() throws Exception {
		keywords = new ArrayList<String>();
		keywords.add("operações");
		keywords.add("matrizes");
		keywords.add("matemática");
		keywords.add("multiplicação");
		quantity = 10l;
		
		videoId = "QbwoVGijUBY";
	}
	
	@Test
	public void whenFindVideosShouldReturnAnExpectedQuantityOfSimpleVideoList() throws Exception {
		List<SimpleVideo> simpleVideoList = findVideos();
		
		assertNotNull("The SipmleVideoList don't be null.", simpleVideoList);
		assertFalse("The SipmleVideoList don't be empty.", simpleVideoList.isEmpty());
		assertEquals("The SipmleVideoList should have the same quantity of elements.", quantity, new Long(simpleVideoList.size()));
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
		assertNotNull("The attribute id not should be null.", videoReturned.getId());
		assertNotNull("The attribute duration not should be null.", videoReturned.getDuration());
		assertNotNull("The attribute channel id not should be null.", videoReturned.getChannelId());
		assertNotNull("The attribute channel title not should be null.", videoReturned.getChannelTitle());
		assertNotNull("The attribute description not should be null.", videoReturned.getDescription());
		assertNotNull("The attribute published at not should be null.", videoReturned.getPublishedAt());
		assertNotNull("The attribute statistics not should be null.", videoReturned.getSimpleStatistics());
		assertNotNull("The attribute statistics comment count not should be null.", videoReturned.getSimpleStatistics().getCommentCount());
		assertNotNull("The attribute statistics dislike count not should be null.", videoReturned.getSimpleStatistics().getDislikeCount());
		assertNotNull("The attribute statistics favorite count not should be null.", videoReturned.getSimpleStatistics().getFavoriteCount());
		assertNotNull("The attribute statistics like count not should be null.", videoReturned.getSimpleStatistics().getLikeCount());
		assertNotNull("The attribute statistics view count not should be null.", videoReturned.getSimpleStatistics().getViewCount());
		assertNotNull("The attribute thumbnail not should be null.", videoReturned.getThumbnail());
		assertNotNull("The attribute title not should be null.", videoReturned.getTitle());
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
			assertNotNull("The attribute id not should be null.", simpleVideo.getId());
		}
	}

	private void assertNotNullSimpleVideoListCaption(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull("The attribute caption not should be null.", simpleVideo.getCaption());
		}
	}
	
	private void assertNotNullSimpleVideoListChannelId(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull("The attribute channel id not should be null.", simpleVideo.getChannelId());
		}
	}

	private void assertNotNullSimpleVideoListChannelTitle(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull("The attribute channel title not should be null.", simpleVideo.getChannelTitle());
		}
	}

	private void assertNotNullSimpleVideoListDescription(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull("The attribute description not should be null.", simpleVideo.getDescription());
		}
	}

	private void assertNotNullSimpleVideoListDuration(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull("The attribute duration not should be null.", simpleVideo.getDuration());
		}
	}

	private void assertNotNullSimpleVideoListPublishedAt(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull("The attribute published at not should be null.", simpleVideo.getPublishedAt());
		}
	}

	private void assertNotNullSimpleVideoListSimpleStatistics(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull("The attribute statistics not should be null.", simpleVideo.getSimpleStatistics());
			assertNotNull("The attribute statistics comment count not should be null.", simpleVideo.getSimpleStatistics().getCommentCount());
			assertNotNull("The attribute statistics dislike count not should be null.", simpleVideo.getSimpleStatistics().getDislikeCount());
			assertNotNull("The attribute statistics favorite count not should be null.", simpleVideo.getSimpleStatistics().getFavoriteCount());
			assertNotNull("The attribute statistics like count not should be null.", simpleVideo.getSimpleStatistics().getLikeCount());
			assertNotNull("The attribute statistics view count not should be null.", simpleVideo.getSimpleStatistics().getViewCount());
		}
	}

	private void assertNotNullSimpleVideoListThumbnail(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull("The attribute thumbnail not should be null.", simpleVideo.getThumbnail());
		}
	}

	private void assertNotNullSimpleVideoListTitle(
			List<SimpleVideo> simpleVideoList) {
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull("The attribute title not should be null.", simpleVideo.getTitle());
		}
	}
	
}
