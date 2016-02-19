package model;

public abstract class Attack {
	private int damage;
	private int duration;
	private int contactTime;
	private String name;
	
	// Set constants
	public static final int EASY_DAMAGE = 5;
	public static final int MEDIUM_DAMAGE = 10;
	public static final int HARD_DAMAGE = 15;
	public static final int EASY_DURATION = 50;
	public static final int MEDIUM_DURATION = 50;
	public static final int HARD_DURATION = 50;
	public static final int EASY_CONTACTTIME = 25;
	public static final int MEDIUM_CONTACTTIME = 25;
	public static final int HARD_CONTACTTIME = 25;
	
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getContactTime() {
		return contactTime;
	}
	public void setContactTime(int contactTime) {
		this.contactTime = contactTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public abstract void execute(User user);
}
