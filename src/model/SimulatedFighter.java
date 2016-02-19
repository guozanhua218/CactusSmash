package model;

import processing.core.PVector;
/**
 * @authors Ben Ackerman and Chris Carsey
 * 
 * @Description This class creates a SimulatedFighter that represents a testable
 * location of the user
 */
public class SimulatedFighter implements HumanFighter {

	Skeletal body; 
	
	public SimulatedFighter() {
		body = new Skeletal();
		
		//TODO set all body parts
	}
	public PVector[] getImportantData() {
		// TODO Auto-generated method stub
		return null;
	}

	public Skeletal getSkeletal() {
		// TODO Auto-generated method stub
		return null;
	}

}
