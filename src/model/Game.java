package model;

/**
 * @authors Ben Ackerman, Chris Carsey, Lorenzo Fusaro
 * 
 * @Description  Used to keep track of the user and cactus 
 */
public class Game {
	private Cactus cactus;
	private User user;
	
	
	public Game() {
		setCactus(new Cactus());
		setUser(new User());
	}

	public Cactus getCactus() {
		return cactus;
	}

	public void setCactus(Cactus cactus) {
		this.cactus = cactus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
