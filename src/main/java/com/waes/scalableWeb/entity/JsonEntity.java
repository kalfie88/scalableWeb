package com.waes.scalableWeb.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.Data;

/**
 * 
 * @author Katherine Alfaro
 * @version 1.0.0
 *
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class JsonEntity {

	private String leftJson;
	private String rightJson;
	private String diffResult;

}
