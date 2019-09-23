package com.websystique.springmvc.model;

public class CardioChallenge {

	private long id;

	private String name;
	private String level;

	public CardioChallenge() {
		id = 0;
	}

	public CardioChallenge(long id, String name, String level) {
		this.id = id;
		this.name = name;
		this.level = level;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
