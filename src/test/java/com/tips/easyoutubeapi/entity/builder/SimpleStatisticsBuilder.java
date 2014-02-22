package com.tips.easyoutubeapi.entity.builder;

import java.math.BigInteger;

import com.tips.easyoutubeapi.entity.SimpleStatistics;

public class SimpleStatisticsBuilder extends AbstractEntityBuilder<SimpleStatistics> {

	@Override
	protected void initializeDefaultData() {
		entity = new SimpleStatistics();
	}
	
	public SimpleStatisticsBuilder withViewCount(BigInteger viewCount) {
		if (viewCount != null) {
			entity.setViewCount(viewCount);
		}
		return this;
	}
	
	public SimpleStatisticsBuilder withLikeCount(BigInteger likeCount) {
		if (likeCount != null) {
			entity.setLikeCount(likeCount);
		}
		return this;
	}
	
	public SimpleStatisticsBuilder withDislikeCount(BigInteger dislikeCount) {
		if (dislikeCount != null) {
			entity.setDislikeCount(dislikeCount);
		}
		return this;
	}
	
	public SimpleStatisticsBuilder withFavoriteCount(BigInteger favoriteCount) {
		if (favoriteCount != null) {
			entity.setDislikeCount(favoriteCount);
		}
		return this;
	}
	
	public SimpleStatisticsBuilder withCommentCount(BigInteger commentCount) {
		if (commentCount != null) {
			entity.setDislikeCount(commentCount);
		}
		return this;
	}
	
}
