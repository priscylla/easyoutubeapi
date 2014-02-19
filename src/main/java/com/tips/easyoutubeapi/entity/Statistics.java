package com.tips.easyoutubeapi.entity;

import java.math.BigInteger;

public class Statistics {
	
	private BigInteger viewCount;
	private BigInteger likeCount;
	private BigInteger dislikeCount;
	private BigInteger favoriteCount;
	private BigInteger commentCount;
	
	public Statistics() {
	}
	
	public Statistics(BigInteger viewCount, BigInteger likeCount,
			BigInteger dislikeCount, BigInteger favoriteCount,
			BigInteger commentCount) {
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
		this.favoriteCount = favoriteCount;
		this.commentCount = commentCount;
	}

	public BigInteger getViewCount() {
		return viewCount;
	}

	public void setViewCount(BigInteger viewCount) {
		this.viewCount = viewCount;
	}

	public BigInteger getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(BigInteger likeCount) {
		this.likeCount = likeCount;
	}

	public BigInteger getDislikeCount() {
		return dislikeCount;
	}

	public void setDislikeCount(BigInteger dislikeCount) {
		this.dislikeCount = dislikeCount;
	}

	public BigInteger getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(BigInteger favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public BigInteger getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(BigInteger commentCount) {
		this.commentCount = commentCount;
	}
	
}
