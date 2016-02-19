package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.attacks.BodyAttack;
import model.attacks.HeadAttack;
import model.attacks.IdleAttack;

/**
 * @authors Ben Ackerman, Chris Carsey, Lorenzo Fusaro
 * 
 * @Description  Used to represent the different states of the cactus fighter 
 */
public class Cactus extends Player {

	private int startingHealth = 100;

	private int attackRadius = 300;
	private int minDistance = 40;

	private int moveSpeed = 10;

	private int attackCooldown = 5;
	private int defendCooldown = 2;

	private int handHeight;

	private State state;
	
	//points for hit zone
	

	public Cactus() {
		setHealth(startingHealth);
		setCoords(new Point(0, 0));
		setState(State.Idle);
	}

	public int getAttackCooldown() {
		return attackCooldown;
	}

	public void setAttackCooldown(int cooldown) {
		this.attackCooldown = cooldown;
	}

	//I dont think the defense methods are needed now
	public int getDefendCooldown() {
		return defendCooldown;
	}

	public void setDefendCooldown(int defendCooldown) {
		this.defendCooldown = defendCooldown;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getAttackRadius() {
		return attackRadius;
	}

	public void setAttackRadius(int attackRadius) {
		this.attackRadius = attackRadius;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public int getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(int minDistance) {
		this.minDistance = minDistance;
	}

	public List<Attack> chooseAttackSeries() {
		Difficulty difficulty = Difficulty.Easy;
		ArrayList<Attack> series = new ArrayList<Attack>();
		int numberOfAttacks = (int) (Math.random() * 10);
		for (int i = 0; i < numberOfAttacks; i++) {
			int attack = (int) (Math.random() * 3);
			switch (attack) {
			case 0:
				series.add(new IdleAttack(difficulty));
				break;
			case 1:
				series.add(new HeadAttack(difficulty));
				break;
			case 2:
				series.add(new BodyAttack(difficulty));
				break;
			}
		}
		return series;
	}

	public int getHandHeight(){
		return handHeight;
	}

	public void setHandHeight(User user){
		int leftY = user.getSkeletal().leftHandCoords.y;
		int rightY = user.getSkeletal().rightHandCoords.y;
		int target = leftY+rightY/2;
		if(handHeight < target){
			handHeight += moveSpeed;
		}
		else{
			handHeight -= moveSpeed;
		}
	}
}
