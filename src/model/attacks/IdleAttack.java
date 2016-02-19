package model.attacks;

import model.Attack;
import model.Difficulty;
import model.User;


// Takes in a difficulty and set the idle time accordingly.
public class IdleAttack extends Attack {
	public IdleAttack(Difficulty difficulty) {
		setName("idle");
		switch (difficulty) {
		case Easy:
			setDuration(EASY_DURATION);
			break;
		case Medium:
			setDuration(MEDIUM_DURATION);
			break;
		case Hard:
			setDuration(HARD_DURATION);
			break;
		}
	}
	
	//attack does nothing
	public void execute(User user){
		return;
	}
}
