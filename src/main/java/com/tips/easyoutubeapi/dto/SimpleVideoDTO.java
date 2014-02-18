package com.tips.easyoutubeapi.dto;

import java.util.Date;

public class SimpleVideoDTO {

	private String id;
	private String title;
	private String description;
	private Date publishedAt;
	private String channelId;
	private String thumbnail;
	private String channelTitle;
	private String duration;
	private StatisticsDTO statistics;
	private CaptionDTO caption;
	
	public SimpleVideoDTO() {}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the publishedAt
	 */
	public Date getPublishedAt() {
		return publishedAt;
	}

	/**
	 * @param publishedAt the publishedAt to set
	 */
	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	/**
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId the channelId to set
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/**
	 * @return the thumbnail
	 */
	public String getThumbnail() {
		return thumbnail;
	}

	/**
	 * @param thumbnail the thumbnail to set
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	/**
	 * @return the channelTitle
	 */
	public String getChannelTitle() {
		return channelTitle;
	}

	/**
	 * @param channelTitle the channelTitle to set
	 */
	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the statistics
	 */
	public StatisticsDTO getStatistics() {
		return statistics;
	}

	/**
	 * @param statistics the statistics to set
	 */
	public void setStatistics(StatisticsDTO statistics) {
		this.statistics = statistics;
	}

	/**
	 * @return the caption
	 */
	public CaptionDTO getCaption() {
		return caption;
	}

	/**
	 * @param caption the caption to set
	 */
	public void setCaption(CaptionDTO caption) {
		this.caption = caption;
	}

	
}
