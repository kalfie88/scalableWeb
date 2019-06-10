package com.waes.scalableWeb.enums;

/**
 * 
 * @author Katherine Alfaro
 * @version 1.0.0
 *
 */
public enum Sides {

	LEFT("left"), RIGHT("right");

	private String side;

	Sides(String side) {
		this.side = side;
	}

	public String getSide() {
		return this.side;
	}

}