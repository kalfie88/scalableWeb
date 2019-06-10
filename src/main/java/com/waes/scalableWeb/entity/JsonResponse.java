package com.waes.scalableWeb.entity;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 
 * @author Katherine Alfaro
 * @version 1.0.0
 *
 */
@Component
@Data
public class JsonResponse {

	private String message;
	private String description;
}
