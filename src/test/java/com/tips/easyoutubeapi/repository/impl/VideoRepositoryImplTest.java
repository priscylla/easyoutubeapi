package com.tips.easyoutubeapi.repository.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
	
	@Test
	public void whenFindVideosShouldReturnAnExpectedQuantityOfSimpleVideos() throws Exception {
		List<String> keywords = new ArrayList<String>();
		
		keywords.add("operações");
		keywords.add("matrizes");
		keywords.add("matemática");
		keywords.add("multiplicação");
		
		Long quantity = 10l;
		
		List<SimpleVideo> simpleVideoList = videoRepositoryImpl.findVideos(keywords, quantity);
		
		assertNotNull("The SipmleVideoList don't be null.", simpleVideoList);
		assertFalse("The SipmleVideoList don't be empty.", simpleVideoList.isEmpty());
		assertEquals("The SipmleVideoList should have the same quantity of elements.", quantity, new Long(simpleVideoList.size()));
	}
	
}
