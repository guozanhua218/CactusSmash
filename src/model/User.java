package model;

import java.awt.Point;

public class User extends Player {
	
	private int startingHealth = 100;
	
	private Skeletal skeletal;
	
	public User() {
		setHealth(startingHealth);
		setCoords(new Point(0, 0));
		this.setSkeletal(new Skeletal());
		this.getSkeletal().leftHandCoords = new Point(0, 0);
		this.getSkeletal().rightHandCoords = new Point(0, 0);
	}

	public Skeletal getSkeletal() {
		return skeletal;
	}

	public void setSkeletal(Skeletal skeletal) {
		this.skeletal = skeletal;
	}
}
