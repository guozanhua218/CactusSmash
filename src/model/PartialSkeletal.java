package model;

import java.awt.Point;

/**
 * @authors Ben Ackerman, Chris Carsey, Lorenzo Fusaro
 * 
 * @Description  Used to represent the five most key body points 
 */
public class PartialSkeletal {
	//TODO update to prive variables and make getters and setters
	public Point leftHandCoords = new Point(0, 0);
	public Point rightHandCoords = new Point(0, 0);
	public Point headPoint = new Point(0, 0);
	public Point nearestShoulder = new Point(0, 0);
	public Point nearestHip = new Point(0, 0);
}
