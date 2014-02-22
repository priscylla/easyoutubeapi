package com.tips.easyoutubeapi.entity.builder;

import java.util.List;

import com.tips.easyoutubeapi.entity.Caption;
import com.tips.easyoutubeapi.entity.Subtitle;

public class CaptionBuilder extends AbstractEntityBuilder<Caption> {

	@Override
	protected void initializeDefaultData() {
		entity = new Caption();
	}
	
	public CaptionBuilder withSubtitleList(List<Subtitle> subtitles) {
		if (subtitles != null) {
			entity.setSubtitles(subtitles);
		}
		return this;
	}
	
}
