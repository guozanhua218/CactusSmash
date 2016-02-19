package model;

/**
 * @authors Ben Ackerman, Chris Carsey, Lorenzo Fusaro
 * 
 * @Description  Used to represent the necessary methods the human fighter whether real
 *  or simulated must implement
 */
import processing.core.PVector;

public interface HumanFighter {
	
	// Used to get the player location
	public PVector[] getImportantData();
	
	public Skeletal getSkeletal();
	
}
