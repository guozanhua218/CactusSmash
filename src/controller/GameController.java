package controller;

import model.Game;

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
