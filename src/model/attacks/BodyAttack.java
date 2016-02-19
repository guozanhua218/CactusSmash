package model.attacks;

import model.Attack;
import model.Difficulty;
import model.User;

public class BodyAttack extends Attack {

	// Takes in difficulty and sets the attack damage and time accordingly
	public BodyAttack(Difficulty difficulty) {
		setName("bodyattack");
		switch (difficulty) {
		case Easy:
			setDamage(EASY_DAMAGE);
			setDuration(EASY_DURATION);
			setContactTime(EASY_CONTACTTIME);
			break;
		case Medium:
			setDamage(MEDIUM_DAMAGE);
			setDuration(MEDIUM_DURATION);
			setContactTime(MEDIUM_CONTACTTIME);
			break;
		case Hard:
			setDamage(HARD_DAMAGE);
			setDuration(HARD_DURATION);
			setContactTime(HARD_CONTACTTIME);
			break;
		}
	}
	
	public void execute(User user) {
		int leftY=user.getSkeletal().leftHandCoords.y;
		int rightY=user.getSkeletal().rightHandCoords.y;
		int nearestHipY=user.getSkeletal().nearestHip.y;
		int nearestShoulderY=user.getSkeletal().nearestShoulder.y;
		
		
		//if left hand blocks punch (above shoulders and below head
		if(leftY > nearestHipY && leftY < nearestShoulderY){
			//do nothing
		}
		//if right hand blockd punch
		else if(rightY > nearestHipY && rightY < nearestShoulderY){
			//do nothing
		}
		//if punch was not blocked
		else{
			user.setHealth(user.getHealth()-getDamage());
		}
	}

}
