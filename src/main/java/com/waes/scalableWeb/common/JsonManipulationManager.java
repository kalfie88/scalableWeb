package com.waes.scalableWeb.common;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.waes.scalableWeb.entity.JsonDiff;
import com.waes.scalableWeb.entity.JsonEntity;
import com.waes.scalableWeb.entity.JsonResponse;
import com.waes.scalableWeb.enums.Sides;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

/**
 * 
 * @author Katherine Alfaro
 * @version 1.0.0 JsonManipulationManager class is in charge of all the
 *          processing of the Json files Save, Comparing, generating the diff
 *          file, persist the Json and create a response of type Json for the
 *          controller
 *
 */

@Component
@Data
@Log4j2
public class JsonManipulationManager {

	@Autowired
	private JsonEntity jsonEntity;

	@Autowired
	private JsonResponse response;

	private static final String ERROR_MESSAGE = "Can't complete operation: Input is incomplete, for comparing we need two Jsons";

	/**
	 * Saves the input Jsons in memory (not in H2)
	 * 
	 * @param json
	 * @param side
	 */
	public void saveJson(String json, String side) {

		if (side.equals(Sides.LEFT.getSide())) {
			jsonEntity.setLeftJson(json);

		} else {
			jsonEntity.setRightJson(json);

		}
	}

	/**
	 * Compares and creates the diff file for the input Jsons
	 * 
	 * @return
	 * @throws Exception
	 */
	public String compareJson() throws Exception {
		if (jsonEntity == null
				|| !Optional.ofNullable(jsonEntity.getLeftJson()).isPresent()
				|| !Optional.ofNullable(jsonEntity.getRightJson())
						.isPresent()) {

			throw new JsonNotFoundException(ERROR_MESSAGE);

		} else {
			MapDifference<String, Object> difference = Maps.difference(
					convertJson(jsonEntity.getLeftJson()),
					convertJson(jsonEntity.getRightJson()));

			jsonEntity.setDiffResult(constructDiff(difference));

			return jsonEntity.getDiffResult();
		}
	}

	/**
	 * Creates the entity with the diff file created on compareJson() so it can
	 * be persisted by the repo in the service It throws a custom Exception to
	 * specify that you need 2 Json inputs for the comparator to work
	 * 
	 * @param id
	 * @return
	 * @throws JsonNotFoundException
	 */
	public JsonDiff persistJason(int id) throws JsonNotFoundException {
		JsonDiff entityToSave = new JsonDiff();
		entityToSave.setId(id);

		if (jsonEntity == null || !Optional
				.ofNullable(jsonEntity.getDiffResult()).isPresent()) {
			throw new JsonNotFoundException(ERROR_MESSAGE);

		} else {
			entityToSave.setDifference(jsonEntity.getDiffResult());

		}

		return entityToSave;
	}

	/**
	 * Process the response string to return it in Json format
	 * 
	 * @param message
	 * @param description
	 * @return
	 */
	public String getJsonStyleResponse(String message, String description) {
		Gson gson = new Gson();
		response.setMessage(message);
		response.setDescription(description);

		return gson.toJson(response);
	}

	/**
	 * Converts the input String into a map for processing
	 * 
	 * @param json
	 * @return
	 */
	private Map<String, Object> convertJson(String json) {
		Map<String, Object> jsonMap = null;

		try {
			Gson gson = new Gson();
			Type type = new TypeToken<Map<String, Object>>() {
			}.getType();

			jsonMap = gson.fromJson(json, type);

		} catch (Exception e) {
			log.error(e);
		}

		return jsonMap;
	}

	/**
	 * Creates the response for the diff file, it specifies the entries that are
	 * only on the right side, in the left side and the ones that differed in
	 * general
	 * 
	 * @param difference
	 * @return
	 */
	private String constructDiff(MapDifference<String, Object> difference) {
		StringBuilder result = new StringBuilder(
				"Entries only on the left\n--------------------------\n");
		difference.entriesOnlyOnLeft().forEach((key, value) -> result
				.append(key).append(": ").append(value).append("\n"));

		result.append(
				"\n\nEntries only on the right\n--------------------------\n");
		difference.entriesOnlyOnRight().forEach((key, value) -> result
				.append(key).append(": ").append(value).append("\n"));

		result.append("\n\nEntries differing\n--------------------------\n");
		difference.entriesDiffering().forEach((key, value) -> result.append(key)
				.append(": ").append(value).append("\n"));

		return result.toString();
	}

}
