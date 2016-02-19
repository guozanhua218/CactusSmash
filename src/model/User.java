package model;

import java.awt.Point;

/**
 * @authors Ben Ackerman, Chris Carsey, Lorenzo Fusaro
 * 
 * @Description Used to represent the most important aspects of a player such as key body positions. 
 */
public class User extends Player {
	
	private int startingHealth = 100;
	
	private PartialSkeletal partialSkeletal;
	
	public User() {
		setHealth(startingHealth);
		setCoords(new Point(0, 0));
		this.setSkeletal(new PartialSkeletal());
		this.getSkeletal().leftHandCoords = new Point(0, 0);
		this.getSkeletal().rightHandCoords = new Point(0, 0);
	}

	public PartialSkeletal getSkeletal() {
		return partialSkeletal;
	}

	public void setSkeletal(PartialSkeletal partialSkeletal) {
		this.partialSkeletal = partialSkeletal;
	}
}
