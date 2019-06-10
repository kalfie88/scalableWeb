package com.waes.scalableWeb.common;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.waes.scalableWeb.common.JsonManipulationManager;
import com.waes.scalableWeb.entity.JsonEntity;

/**
 * 
 * @author Katherine Alfaro
 * @version 1.0.0
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonManipulationManagerTest {

	@Autowired
	private JsonManipulationManager jsonManager;

	@Autowired
	private JsonEntity expectedJsonEntity;

	private static final String LEFT_JSON = "{name: John, lastName: Doe}";
	private static final String RIGTH_JSON = "{name: John, lastName: Doe, nickName: Petite}";
	private static final String DIFF = "{nickName: Petite}";

	@Before
	public void setUp() {
		expectedJsonEntity.setLeftJson(LEFT_JSON);
		expectedJsonEntity.setRightJson(RIGTH_JSON);
		expectedJsonEntity.setDiffResult(DIFF);
	}

	@Test
	public void saveJsonTest() {
		jsonManager.saveJson(LEFT_JSON, RIGTH_JSON);

		assertEquals(jsonManager.getJsonEntity().getLeftJson(),
				expectedJsonEntity.getLeftJson());
		assertEquals(jsonManager.getJsonEntity().getRightJson(),
				expectedJsonEntity.getRightJson());
	}

	@Test
	public void compareJsonTest() throws Exception {
		jsonManager.saveJson(LEFT_JSON, RIGTH_JSON);
		jsonManager.compareJson();

		assertEquals(jsonManager.getJsonEntity().getDiffResult(),
				expectedJsonEntity.getDiffResult());
	}

}
