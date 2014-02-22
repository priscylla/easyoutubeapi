package com.tips.easyoutubeapi.entity.builder;


import com.google.api.client.util.DateTime;
import com.tips.easyoutubeapi.entity.Caption;
import com.tips.easyoutubeapi.entity.SimpleStatistics;
import com.tips.easyoutubeapi.entity.SimpleVideo;


public class SimpleVideoBuilder extends AbstractEntityBuilder<SimpleVideo> {

	@Override
	protected void initializeDefaultData() {
		entity = new SimpleVideo();
	}
	
	public SimpleVideoBuilder withId(String id) {
		if (id != null) {
			entity.setId(id);
		}
		return this;
	}
	
	public SimpleVideoBuilder withDescription(String description) {
		if (description != null) {
			entity.setDescription(description);
		}
		return this;
	}
	
	public SimpleVideoBuilder withPublishedAt(DateTime publishedAt) {
		if (publishedAt != null) {
			entity.setPublishedAt(publishedAt);
		}
		return this;
	}
	
	public SimpleVideoBuilder withChannelId(String channelId) {
		if (channelId != null) {
			entity.setChannelId(channelId);
		}
		return this;
	}
	
	public SimpleVideoBuilder withThumbnail(String thumbnail) {
		if (thumbnail != null) {
			entity.setThumbnail(thumbnail);
		}
		return this;
	}
	
	public SimpleVideoBuilder withChannelTitle(String channelTitle) {
		if (channelTitle != null) {
			entity.setChannelTitle(channelTitle);
		}
		return this;
	}
	
	public SimpleVideoBuilder withDuration(String duration) {
		if (duration != null) {
			entity.setDuration(duration);
		}
		return this;
	}
	
	public SimpleVideoBuilder withStatistics(SimpleStatistics simpleStatistics) {
		if (simpleStatistics != null) {
			entity.setSimpleStatistics(simpleStatistics);
		}
		return this;
	}
	
	public SimpleVideoBuilder withCaption(Caption caption) {
		if (caption != null) {
			entity.setCaption(caption);
		}
		return this;
	}

}
