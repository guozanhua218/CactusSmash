package controller;

import model.Game;

/**
 * @authors Ben Ackerman, Chris Carsey, Lorenzo Fusaro
 *
 * @param A game object that will be controlled by the controller
 * 
 * @Description  Used to run the updates on the cactus controller 
 */
public class GameController {

	private CactusController cactusController;

	public GameController(Game game) {
		cactusController = new CactusController(game);
	}

	public void runUpdates() {
		cactusController.update();
	}
	
	public CactusController getCactusController(){
		return cactusController;
	}

}
