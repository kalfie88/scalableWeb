package com.waes.scalableWeb.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.waes.scalableWeb.entity.JsonDiff;

/**
 * 
 * @author Katherine Alfaro
 * @version 1.0.0
 *
 */
@Repository
public interface ScalableWebRepository extends CrudRepository<JsonDiff, Integer> {
	@Modifying
	@Transactional
	Optional<JsonDiff> findById(Integer id);

	@Modifying
	@Transactional
	void deleteById(int id);
}
