package com.tips.easyoutubeapi.entity;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Languages {
	
	EN("English"),
	PT("Portuguese"),
	ES("Spanish"),
	FR("French");
	
	private String name;
	
	private static final Map<String,Languages> map = new HashMap<String, Languages>();
	
	static {
		for (Languages e : EnumSet.allOf(Languages.class)) {
			map.put(e.getName(), e);
		}
	}
	
	private Languages(String name) {
		this.name = name;
	}
	
	public static Languages getType(String name) {
		return map.get(name);
	}
	
	public String getName() {
		return this.name;
	}

}
