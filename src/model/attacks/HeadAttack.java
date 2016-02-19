package model.attacks;

import model.Attack;
import model.Difficulty;
import model.User;

public class HeadAttack extends Attack {

	public HeadAttack(Difficulty difficulty) {
		setName("headattack");
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
	
	public void execute(User user){
		int leftY=user.getSkeletal().leftHandCoords.y;
		int rightY=user.getSkeletal().rightHandCoords.y;
		int headY=user.getSkeletal().headPoint.y;
		int nearestShoulderY=user.getSkeletal().nearestShoulder.y;
		
		
		//if left hand blocks punch (above shoulders and below head
		if(leftY < headY && leftY > nearestShoulderY){
			//do nothing
		}
		//if right hand blockd punch
		else if(rightY < headY && rightY > nearestShoulderY){
			//do nothing
		}
		//if punch was not blocked
		else{
			user.setHealth(user.getHealth()-getDamage());
		}
	}

}
