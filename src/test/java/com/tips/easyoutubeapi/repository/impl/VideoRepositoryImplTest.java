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

	@Autowired
	private VideoRepository videoRepositoryImpl;
	
	private List<String> keywords;
	Long quantity;
	
	@Before
	public void initialize() throws Exception {
		keywords = new ArrayList<String>();
		keywords.add("operações");
		keywords.add("matrizes");
		keywords.add("matemática");
		keywords.add("multiplicação");
		quantity = 10l;
	}
	
	private List<SimpleVideo> findVideos() {
		List<SimpleVideo> simpleVideoList = videoRepositoryImpl.findVideos(keywords, quantity);
		return simpleVideoList;
	}
	
	@Test
	public void whenFindVideosShouldReturnAnExpectedQuantityOfSimpleVideoList() throws Exception {
		List<SimpleVideo> simpleVideoList = findVideos();
		
		assertNotNull("The SipmleVideoList don't be null.", simpleVideoList);
		assertFalse("The SipmleVideoList don't be empty.", simpleVideoList.isEmpty());
		assertEquals("The SipmleVideoList should have the same quantity of elements.", quantity, new Long(simpleVideoList.size()));
	}

	@Test
	public void whenFindVideosShouldReturnSimpleVideoListWithAttributeCaptionNotNullOfObjects() throws Exception {
		List<SimpleVideo> simpleVideoList = findVideos();
		
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull("The attribute caption not should be null.", simpleVideo.getCaption());
		}
		
	}
	
	@Test
	public void whenFindVideosShouldReturnSimpleVideoListWithAttributeChannelIdNotNullOfObjects() throws Exception {
		List<SimpleVideo> simpleVideoList = findVideos();
		
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull("The attribute channel id not should be null.", simpleVideo.getChannelId());
		}
		
	}
	
	@Test
	public void whenFindVideosShouldReturnSimpleVideoListWithAttributeChannelTitleNotNullOfObjects() throws Exception {
		List<SimpleVideo> simpleVideoList = findVideos();
		
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull("The attribute channel title not should be null.", simpleVideo.getChannelTitle());
		}
		
	}
	
	@Test
	public void whenFindVideosShouldReturnSimpleVideoListWithAttributeDescriptionNotNullOfObjects() throws Exception {
		List<SimpleVideo> simpleVideoList = findVideos();
		
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull("The attribute description not should be null.", simpleVideo.getDescription());
		}
		
	}
	
	@Test
	public void whenFindVideosShouldReturnSimpleVideoListWithAttributeDurationNotNullOfObjects() throws Exception {
		List<SimpleVideo> simpleVideoList = findVideos();
		
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull("The attribute duration not should be null.", simpleVideo.getDuration());
		}
		
	}
	
	@Test
	public void whenFindVideosShouldReturnSimpleVideoListWithAttributePublishedAtNotNullOfObjects() throws Exception {
		List<SimpleVideo> simpleVideoList = findVideos();
		
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull("The attribute published at not should be null.", simpleVideo.getPublishedAt());
		}
		
	}
	
	@Test
	public void whenFindVideosShouldReturnSimpleVideoListWithAttributeStatisticsNotNullOfObjects() throws Exception {
		List<SimpleVideo> simpleVideoList = findVideos();
		
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull("The attribute statistics not should be null.", simpleVideo.getStatistics());
		}
		
	}
	
	@Test
	public void whenFindVideosShouldReturnSimpleVideoListWithAttributeThumbnailNotNullOfObjects() throws Exception {
		List<SimpleVideo> simpleVideoList = findVideos();
		
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull("The attribute thumbnail not should be null.", simpleVideo.getThumbnail());
		}
		
	}
	
	@Test
	public void whenFindVideosShouldReturnSimpleVideoListWithAttributeTitleNotNullOfObjects() throws Exception {
		List<SimpleVideo> simpleVideoList = findVideos();
		
		for (SimpleVideo simpleVideo : simpleVideoList) {
			assertNotNull("The attribute title not should be null.", simpleVideo.getTitle());
		}
		
	}
	
}
