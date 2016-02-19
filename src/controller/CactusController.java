package controller;

import java.util.List;

import model.Attack;
import model.Cactus;
import model.Game;
import model.PictureType;
import model.State;
import model.Updateable;
import model.User;

public class CactusController implements Updateable {

	private Game game;
	private int waitTicker=0;
	private int attackTicker=0;
	private Cactus cactus;
	private User user;
	private List<Attack> attackSeries;
	private int currentAttackIndex;
	public PictureType pic=PictureType.Idle;
	

	public CactusController(Game game) {
		this.setGame(game);
		this.cactus = game.getCactus();
		this.user = game.getUser();
	}

	public void update() {
		
		// Movement
		if (user.getCoords().x - cactus.getCoords().x > cactus.getAttackRadius()) {
			// move towards player
			this.cactus.getCoords().translate(this.cactus.getMoveSpeed(), 0);
			pic=PictureType.Idle;
		} else if (cactus.getCoords().x > cactus.getWidth()/2 
				&& user.getCoords().x - cactus.getCoords().x < cactus.getMinDistance()) {
			// back up
			this.cactus.getCoords().translate(-this.cactus.getMoveSpeed(), 0);
			this.cactus.setHandHeight(this.user);
			pic=PictureType.Idle;
		} else if (user.getCoords().x - cactus.getCoords().x <= cactus.getAttackRadius()) {
			// Assault/Defend
			if (this.cactus.getState() == State.Attack) {
				this.runAttackSeries();
			} else if (waitTicker >= this.cactus.getAttackCooldown()) {
				int random = (int) (Math.random() * 10);
				if (random <= 1) {
					this.cactus.setState(State.Attack);
					this.runAttackSeries();
				}
			}
		}
		waitTicker ++;
	}

	private void runAttackSeries() {
		//intializes series if first time its been called
		if (this.attackSeries == null) {
			this.attackSeries = this.cactus.chooseAttackSeries();
			attackTicker=0;
			this.currentAttackIndex = 0;
		}

		if (currentAttackIndex >= this.attackSeries.size()) {
			// no more attacks: clears array list and resets timers
			attackSeries=null;
			//if cactus did body attack it blocks body else it blocks
			pic=PictureType.Idle;
			this.cactus.setState(State.Idle);
			//restarts waiting time
			waitTicker=0;
			return;
		}
		
		Attack currentAttack = this.attackSeries.get(this.currentAttackIndex++);

		if (attackTicker == currentAttack.getContactTime()) {
			currentAttack.execute(this.user);
		} else if (attackTicker != currentAttack.getDuration()) {
			//sets picture type
			if(currentAttack.getName().equalsIgnoreCase("bodyattack")){
				pic=PictureType.AttackHead;
			}
			else if(currentAttack.getName().equalsIgnoreCase("headattack")){
				pic=PictureType.AttackBody;
			}
			else{
				this.cactus.setState(State.Idle);
			}
		}

		attackTicker++;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
