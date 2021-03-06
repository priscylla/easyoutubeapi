package com.tips.easyoutubeapi.entity;

import com.google.api.client.util.DateTime;

public class SimpleVideo {
	
	private String id;
	private String title;
	private String description;
	private DateTime publishedAt;
	private String channelId;
	private String thumbnail;
	private String channelTitle;
	private String duration;
	private SimpleStatistics simpleStatistics;
	private Caption caption;

	public SimpleVideo() {
	}

	public SimpleVideo(String id, String title, String description,
			DateTime publishedAt, String channelId, String thumbnail,
			String channelTitle, String duration, SimpleStatistics simpleStatistics,
			Caption caption) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.publishedAt = publishedAt;
		this.channelId = channelId;
		this.thumbnail = thumbnail;
		this.channelTitle = channelTitle;
		this.duration = duration;
		this.simpleStatistics = simpleStatistics;
		this.caption = caption;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DateTime getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(DateTime publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getChannelTitle() {
		return channelTitle;
	}

	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public SimpleStatistics getSimpleStatistics() {
		return simpleStatistics;
	}

	public void setSimpleStatistics(SimpleStatistics simpleStatistics) {
		this.simpleStatistics = simpleStatistics;
	}

	public Caption getCaption() {
		return caption;
	}

	public void setCaption(Caption caption) {
		this.caption = caption;
	}
	
}
