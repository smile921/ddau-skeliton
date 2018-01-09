/**
 * 
 */
package com.ddau.backend.pojo;

/**
 * @author frere
 *
 */
public class Pet {

	private int id;
	private String name;
	private float weight;

	public Pet() {

	}

	public Pet(String name, int id, float weight) {
		this.id = id;
		this.weight = weight;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

}
