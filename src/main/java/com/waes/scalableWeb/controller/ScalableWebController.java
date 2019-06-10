package com.waes.scalableWeb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waes.scalableWeb.enums.Sides;
import com.waes.scalableWeb.service.ScalableWebService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Katherine Alfaro
 * @version 1.0.0 API Rest Controller
 *
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "ScalableWebController", authorizations = {
		@Authorization(value = "noAuth")})
public class ScalableWebController {

	@Autowired
	private ScalableWebService scalableWebService;

	/**
	 * Endpoint to receive left Json input
	 * 
	 * @param id
	 * @param body
	 * @return
	 */
	@PostMapping(path = "/v1/diff/{id}/left")
	@ApiOperation(value = "Saves leftJson file", 
	notes = "This method will set a flag to determine we're saving the leftJson file", 
	response = Map.class)
	public ResponseEntity<String> processJsonLeft(
			@ApiParam(value = "Id of operation", required = true) @PathVariable("id") Long id,
			@ApiParam(value = "Left Json to check", required = true) @RequestBody String body) {

		return ResponseEntity
				.ok(scalableWebService.processJson(body, Sides.LEFT.getSide()));
	}

	/**
	 * Endpoint to receive right Json input
	 * 
	 * @param id
	 * @param body
	 * @return
	 */
	@PostMapping(path = "/v1/diff/{id}/right")
	@ApiOperation(value = "Saves rightJson file", 
	notes = "This method will set a flag to determine we're saving the rightJson file", 
	response = Map.class)
	public ResponseEntity<String> processJsonRight(
			@ApiParam(value = "Id of operation", required = true) @PathVariable("id") Long id,
			@ApiParam(value = "Right Json to check", required = true) @RequestBody String body) {

		return ResponseEntity.ok(
				scalableWebService.processJson(body, Sides.RIGHT.getSide()));
	}

	/**
	 * Endpoint to return the differences between the two Json files
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = "/v1/diff/{id}")
	@ApiOperation(value = "Retrieves diff file", 
	notes = "This method retrieves the result of the comparison of the Json files", 
	response = String.class)
	public ResponseEntity<String> getDiff(
			@ApiParam(value = "Id of operation", required = true) @PathVariable("id") int id)
			throws Exception {

		return ResponseEntity.ok(scalableWebService.compareJson());
	}

	// -------------------Persistence endpoints-------------------------

	/**
	 * Endpoint to persist the differences to the H2 schema
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/diff/{id}/saveDiff")
	@ApiOperation(value = "Persistance: Saves diff file into H2", 
	notes = "This method saves the result of the comparison of the Json files into the local DB H2", 
	response = String.class)
	private ResponseEntity<String> saveDiff(
			@ApiParam(value = "Id of operation", required = true) @PathVariable("id") int id)
			throws Exception {

		return ResponseEntity.ok(scalableWebService.saveOrUpdate(id));
	}

	/**
	 * Endpoint to retrieve the diff file from the H2 schema
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = "/v1/diff/{id}/findDiff")
	@ApiOperation(value = "Persistance: Retrieves the diff file", 
	notes = "Retrieves the diff file from H2 by id", 
	response = List.class)
	public ResponseEntity<String> findDiffFromDB(
			@ApiParam(value = "Id of operation", required = true) @PathVariable("id") int id)
			throws Exception {

		return ResponseEntity.ok(scalableWebService.findDiffString(id));
	}

	/**
	 * Endpoint to delete the diff file saved on the H2 schema
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/v1/diff/{id}/deleteDiff")
	@ApiOperation(value = "Persistance: Deletes the diff file", 
	notes = "Deletes the diff file from H2 by id")
	private ResponseEntity<String> deleteDiffRecordById(
			@ApiParam(value = "Id of operation", required = true) @PathVariable("id") int id) {

		return ResponseEntity.ok(scalableWebService.deleteById(id));
	}

}
