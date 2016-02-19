package model;

import java.awt.Point;

/**
 * @authors Ben Ackerman, Chris Carsey, Lorenzo Fusaro
 * 
 * @Description An abstract class used to represent different states of the player
 */
public abstract class Player {

	// instance vars
	private int health;
	
	private Point coords;
	private int width;
	private int height;

	// getters and setters
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Point getCoords() {
		return coords;
	}

	public void setCoords(Point centerCoords) {
		this.coords = centerCoords;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
