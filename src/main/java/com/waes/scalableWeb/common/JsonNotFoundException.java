package com.waes.scalableWeb.common;

/**
 * 
 * @author Katherine Alfaro
 * @version 1.0.0
 *
 */
public class JsonNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Custom Exception to specify to the user that it needs to provide two Json
	 * inputs for the app to work properly
	 * 
	 * @param errorMessage
	 */
	public JsonNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
