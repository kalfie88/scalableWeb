package com.waes.scalableWeb;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Katherine Alfaro
 * @version 1.0.0
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ScalableWebApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private static final int ID = 1;
	private static final String LEFT_JSON = "{name:John, lastName:Doe}";
	private static final String RIGHT_JSON = "{name:John, lastName:Doe, nickName: Petite}";

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	// Post request methods
	@Test
	public void statusOkForLeft() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/v1/diff/{id}/left", ID)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(asJsonString(LEFT_JSON))).andExpect(status().isOk());

	}

	@Test
	public void statusOkForRight() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/v1/diff/{id}/right", ID)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(asJsonString(RIGHT_JSON))).andExpect(status().isOk());
	}

	// Get request tests
	@Test
	public void statusOkForFind() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/v1/diff/{id}/findDiff", ID)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andExpect(content()
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	// Delete request methods
	@Test
	public void statusOkForDelete() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.delete("/v1/diff/{id}/deleteDiff", ID)
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk());
	}

	// Helper methods
	private String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
