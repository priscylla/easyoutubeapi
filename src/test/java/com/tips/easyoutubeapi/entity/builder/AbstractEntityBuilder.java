package com.tips.easyoutubeapi.entity.builder;

public abstract class AbstractEntityBuilder<ENTITY> {

protected ENTITY entity;
	
	public AbstractEntityBuilder() {
		this.initializeDefaultData();
	}
	
	protected abstract void initializeDefaultData();
	
	public final ENTITY build() {
		return entity;
	}
	
}
