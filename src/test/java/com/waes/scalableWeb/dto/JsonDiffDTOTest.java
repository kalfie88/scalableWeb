package com.waes.scalableWeb.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.waes.scalableWeb.common.ObjectMapperUtils;
import com.waes.scalableWeb.entity.JsonDiff;

/**
 * 
 * @author Katherine Alfaro
 * @version 1.0.0
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonDiffDTOTest {
	private JsonDiff jsonDiff;

	@Before
	public void setUp() throws Exception {
		jsonDiff = new JsonDiff();
		jsonDiff.setId(1004);
		jsonDiff.setDifference("{name: John, lastName: Smith}");

	}

	@Test
	public void dtoMappingTest() {
		JsonDiffDTO mapJsonDiff = ObjectMapperUtils.map(jsonDiff,
				JsonDiffDTO.class);
		mapJsonDiff.getId();

		assertEquals(mapJsonDiff.getId(), 1004);
		assertEquals(mapJsonDiff.getDifference(),
				"{name: John, lastName: Smith}");

	}

}
