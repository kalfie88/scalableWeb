package com.waes.scalableWeb.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.waes.scalableWeb.entity.JsonDiff;

/**
 * 
 * @author Katherine Alfaro
 * @version 1.0.0
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ScalableWebRepositoryTest {

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	ScalableWebRepository scalableRepo;

	@Test
	public void findDiffTest() {
		JsonDiff diff = new JsonDiff();
		diff.setId(1);
		diff.setDifference("{nickName: Petite}");
		entityManager.persist(diff);

		Optional<JsonDiff> foundDiff = scalableRepo.findById(1);

		assertThat(
				foundDiff.get().getDifference().equals(diff.getDifference()));
	}

	@Test
	public void deleteDiffTest() {
		JsonDiff diff = new JsonDiff();
		diff.setId(1);
		diff.setDifference("{nickName: Petite}");
		entityManager.persist(diff);

		scalableRepo.deleteById(1);

		Optional<JsonDiff> foundDiff = scalableRepo.findById(1);

		assertThat(!foundDiff.isPresent());
	}

}
