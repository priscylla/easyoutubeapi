package com.tips.easyoutubeapi.entity;

public class Subtitle {
	
	private String text;
	private Languages language;
	
	
	/**
	 * 
	 */
	public Subtitle() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the language
	 */
	public Languages getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(Languages language) {
		this.language = language;
	}

}
