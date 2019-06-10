package com.waes.scalableWeb.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Katherine Alfaro
 * @version 1.0.0
 *
 */

@Data
@Entity
@NoArgsConstructor
public class JsonDiff {

	@Id
	private int id;
	private String difference;

}
