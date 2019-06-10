package com.waes.scalableWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waes.scalableWeb.common.JsonManipulationManager;
import com.waes.scalableWeb.common.ObjectMapperUtils;
import com.waes.scalableWeb.dto.JsonDiffDTO;
import com.waes.scalableWeb.repository.ScalableWebRepository;

/**
 * 
 * @author Katherine Alfaro
 * @version 1.0.0
 *
 */
@Service
public class ScalableWebService {

	@Autowired
	private JsonManipulationManager jsonManager;

	@Autowired
	private ScalableWebRepository scalableRepo;

	private static final String SUCCESS = "Operation Successful";
	private static final String SUCCESSFUL_SAVE = "Json File was successfully saved";
	private static final String SUCCESSFUL_DELETE = "Json Diff File was successfully deleted";

	/**
	 * 
	 * @param body
	 * @param side
	 * @return
	 */
	public String processJson(String body, String side) {
		jsonManager.saveJson(body, side);

		return jsonManager.getJsonStyleResponse(SUCCESS, SUCCESSFUL_SAVE);
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String compareJson() throws Exception {

		return jsonManager.compareJson();
	}

	// -------------------Persistence logic-------------------------
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdate(int id) throws Exception {
		ObjectMapperUtils.map(scalableRepo.save(jsonManager.persistJason(id)),
				JsonDiffDTO.class);

		return jsonManager.getJsonStyleResponse(SUCCESS, SUCCESSFUL_SAVE);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public String findDiffString(Integer id) {
		String diff = (scalableRepo.findById(id).get()).getDifference();

		return diff;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public String deleteById(int id) {
		scalableRepo.deleteById(id);

		return jsonManager.getJsonStyleResponse(SUCCESS, SUCCESSFUL_DELETE);
	}

}
