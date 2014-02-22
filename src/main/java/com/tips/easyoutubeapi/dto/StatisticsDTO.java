package com.tips.easyoutubeapi.dto;

import java.math.BigInteger;

public class StatisticsDTO {
	
	private BigInteger viewCount;
	private BigInteger likeCount;
	private BigInteger dislikeCount;
	private BigInteger favoriteCount;
	private BigInteger commentCount;
	
	
	
	/**
	 * 
	 */
	public StatisticsDTO() {}
	/**
	 * @return the viewCount
	 */
	public BigInteger getViewCount() {
		return viewCount;
	}
	/**
	 * @param viewCount the viewCount to set
	 */
	public void setViewCount(BigInteger viewCount) {
		this.viewCount = viewCount;
	}
	/**
	 * @return the likeCount
	 */
	public BigInteger getLikeCount() {
		return likeCount;
	}
	/**
	 * @param likeCount the likeCount to set
	 */
	public void setLikeCount(BigInteger likeCount) {
		this.likeCount = likeCount;
	}
	/**
	 * @return the dislikeCount
	 */
	public BigInteger getDislikeCount() {
		return dislikeCount;
	}
	/**
	 * @param dislikeCount the dislikeCount to set
	 */
	public void setDislikeCount(BigInteger dislikeCount) {
		this.dislikeCount = dislikeCount;
	}
	/**
	 * @return the favoriteCount
	 */
	public BigInteger getFavoriteCount() {
		return favoriteCount;
	}
	/**
	 * @param favoriteCount the favoriteCount to set
	 */
	public void setFavoriteCount(BigInteger favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	/**
	 * @return the commentCount
	 */
	public BigInteger getCommentCount() {
		return commentCount;
	}
	/**
	 * @param commentCount the commentCount to set
	 */
	public void setCommentCount(BigInteger commentCount) {
		this.commentCount = commentCount;
	}

}
