package view;

import java.awt.Point;
import java.util.HashMap;

import model.Game;
import model.HumanFighter;
import model.KinectFighter;
import model.SimulatedFighter;
import model.Skeletal;
import processing.core.*;
import ddf.minim.*;
import controller.GameController;



public class CactusSmash_Viewer extends PApplet {


	private static final long serialVersionUID = 2418496950920397145L;

	// Instance variables
	private Game game;
	AudioPlayer hitSound;
	private GameController gameController;
	float xpos;
	float ypos = 0;
	float drag = (float) 30.0;
	boolean cactusHit;  // Determines if the cactus was hit
	boolean userHit;    // Determines if the player was hit
	boolean start;      // Determines if game has stated
	HashMap<String, Animation> sprites;
	PImage backgroundImage; // Holds the background image
	HumanFighter human;

	// Buttons
	float startButtonX, startButtonY, startButtonWidth, startButtonLength;     
	float healthBarCactusX, healthBarCactusY, healthBarCactusWidth, healthBarCactusLenght;
	float healthBarUserX, healthBarUserY, healthBarUserWidth, healthBarUserLenght;



	public static void main(String args[]) {
		new CactusSmash_Viewer();
		PApplet.main(new String[] { "--present", "controller.CactusSmash" });
	}

	public CactusSmash_Viewer() {
		game = new Game();
		gameController = new GameController(game);
	}

	public void setup() {
		// Set up background
		size(1024, 768, P2D);   // will need to uncomment
		background(153, 153, 0);
		backgroundImage = loadImage("./Resources/1024Background.jpg");
		setButtons();
		frameRate(12);
		start = false;

		// Set up sound objects
		Minim minim; //audio context
		minim = new Minim(this);
		hitSound = minim.loadFile("./Resources/Punch.mp3");

		// Placing user and cactus
		cactusHit = false;
		userHit = false;
		game.getUser().setCoords(new Point(4*width/5, height/2));
		game.getCactus().setCoords(new Point(0, (int) ypos));

		// Loads animation for cactus
		loadSprites();

		//human = new KinectFighter(this);
		human = new SimulatedFighter();

	}

	// Load the sprite animations
	private void loadSprites() {
		sprites = new HashMap<String, CactusSmash_Viewer.Animation>();
		sprites.put("view/sprites/punchright_", new Animation("sprites_large/punchright_", 10));
		for (int i = 0; i < 10; i++) {
			String file = "sprites_large/block_level_0" + i + "_";
			sprites.put(file, new Animation(file, 2));
		}
	}

	public void draw() {
		background(backgroundImage);
		drawSkeleton();
		drawButtons();
		if(start) {
			gameController.runUpdates();
			this.drawCactus();
			rect(game.getUser().getCoords().x-50, game.getUser().getCoords().y-50, 100, 100);

			if(cactusHit) {
				hitSound.play();
				cactusHit = false;
			}

			if(userHit) {
				hitSound.play();
				background(244, 0, 0);
				cactusHit = false;	
			}
		}

	}

	// Draws the Skeleton on the screen
	void drawSkeleton() {
		Skeletal body = human.getSkeletal();
		PVector pHead = body.getHead();
		PVector pNeck = body.getNeck();
		PVector pTorso = body.getTorso();
		PVector pLeftShoulder = body.getLeftShoulder();
		PVector pRightShoulder = body.getRightShoulder();
		PVector pLeftElbow = body.getLeftElbow();
		PVector pRightElbow = body.getRightElbow();
		PVector pLeftHand = body.getLeftHand();
		PVector pRightHand = body.getRightHand();    
		PVector pLeftHip = body.getLeftHip();
		PVector pRightHip = body.getRightHip();
		PVector pLeftKnee = body.getLeftKnee();
		PVector pRightKnee = body.getRightKnee();
		PVector pLeftFoot = body.getLeftFoot();
		PVector pRightFoot = body.getRightFoot();

		stroke(0, 0, 255);
		strokeWeight(16);
		line(pHead.x, pHead.y, pNeck.x, pNeck.y);
		line(pNeck.x, pNeck.y, pTorso.x, pTorso.y);
		line(pNeck.x, pNeck.y, pLeftShoulder.x, pLeftShoulder.y);
		line(pLeftShoulder.x, pLeftShoulder.y, pLeftElbow.x, pLeftElbow.y);
		line(pLeftElbow.x, pLeftElbow.y, pLeftHand.x, pLeftHand.y);
		line(pNeck.x, pNeck.y, pRightShoulder.x, pRightShoulder.y);
		line(pRightShoulder.x, pRightShoulder.y, pRightElbow.x, pRightElbow.y);
		line(pRightElbow.x, pRightElbow.y, pRightHand.x, pRightHand.y);
		line(pTorso.x, pTorso.y, pLeftHip.x, pLeftHip.y);
		line(pLeftHip.x, pLeftHip.y, pLeftKnee.x, pLeftKnee.y);
		line(pLeftKnee.x, pLeftKnee.y, pLeftFoot.x, pLeftFoot.y);
		line(pTorso.x, pTorso.y, pRightHip.x, pRightHip.y);
		line(pRightHip.x, pRightHip.y, pRightKnee.x, pRightKnee.y);
		line(pRightKnee.x, pRightKnee.y, pRightFoot.x, pRightFoot.y);   

		fill(255, 0, 0);
		noStroke();
		ellipse(pHead.x, pHead.y, 20, 20);
		ellipse(pNeck.x, pNeck.y, 20, 20);
		ellipse(pTorso.x, pTorso.y, 20, 20);
		ellipse(pLeftShoulder.x, pLeftShoulder.y, 20, 20);
		ellipse(pRightShoulder.x, pRightShoulder.y, 20, 20);
		ellipse(pLeftElbow.x, pLeftElbow.y, 20, 20);
		ellipse(pRightElbow.x, pRightElbow.y, 20, 20);
		ellipse(pLeftHand.x, pLeftHand.y, 20, 20);
		ellipse(pRightHand.x, pRightHand.y, 20, 20);
		ellipse(pLeftHip.x, pLeftHip.y, 20, 20);
		ellipse(pRightHip.x, pRightHip.y, 20, 20);
		ellipse(pLeftKnee.x, pLeftKnee.y, 20, 20);
		ellipse(pRightKnee.x, pRightKnee.y, 20, 20);
		ellipse(pLeftFoot.x, pLeftFoot.y, 20, 20);
		ellipse(pRightFoot.x, pRightFoot.y, 20, 20);
	}

	// Set the location and status of all the buttons
	public void setButtons() {
		startButtonX = (width/2 - 40);
		startButtonY = 5;
		startButtonWidth = 80;
		startButtonLength = 40;
		healthBarCactusX = 5;
		healthBarCactusY = 5;
		healthBarCactusWidth = 300;
		healthBarCactusLenght = 30;
		healthBarUserX = (width - 305);
		healthBarUserY = 5;
		healthBarUserWidth = 300;
		healthBarUserLenght = 30;
	}
	
	// Calling this method draws buttons on the screen
	public void drawButtons() {
		rect(startButtonX, startButtonY, startButtonWidth, startButtonLength);
		fill(244, 0, 0);
		rect(healthBarUserX, healthBarUserY, (float) (healthBarUserWidth* (.01) * game.getUser().getHealth()), healthBarUserLenght);
		rect(healthBarCactusX, healthBarCactusY, (float) (healthBarCactusWidth * (.01) * game.getCactus().getHealth()), healthBarCactusLenght);
		fill(0);
		textSize(32);
		text("Start", width /2 - 37, 33);
		fill(244);
		text("Player", width - 303, 31);;
		text("Mr. Cactus", 7, 31);
	}

	// Sets the start Variable to true if the start button is pushed
	public void mousePressed() {
		if (overButton(startButtonX, startButtonY, startButtonWidth, startButtonLength)) {
			start = true;
		}
	}
	
	/**
	 * @param x is the left.x coordinate of the button
	 * @param y is the top.y coordinate of the button
	 * @param width is the height of the button
	 * @param height is the width of the button
	 * @return True if the mouse is over the button else otherwise
	 */
	boolean overButton(float x, float y, float width, float height)  {
		if (mouseX >= x && mouseX <= x+width && 
				mouseY >= y && mouseY <= y+height) {
			return true;
		} else {
			return false;
		}
	}

	// Draws the cactus opponent
	private void drawCactus() {
		Animation sprite = null;
		Point coords = game.getCactus().getCoords();

		// Display the sprite at the position xpos, ypos
		switch (game.getCactus().getState()) {
		case Attack:
			sprite = sprites.get("sprites_large/punchright_");
			break;
		case Idle:
			sprite = sprites.get("sprites_large/block_level_05_");
			break;

		}
		sprite.display(coords.x - sprite.getWidth()/2, ypos);
	}

	/**
	 * @authors Ben Ackerman and Chris Carsey
	 * @param imagePrefix is the prefix string for the sprite image
	 * @param  count is the number of images
	 * 
	 * This class is used to create an image animation for the cactus
	 */
	class Animation {
		PImage[] images;
		int imageCount;
		int frame;

		Animation(String imagePrefix, int count) {
			imageCount = count;
			images = new PImage[imageCount];

			for (int i = 0; i < imageCount; i++) {
				// Use nf() to number format 'i' into four digits
				String filename = imagePrefix + nf(i, 2) + ".png";
				images[i] = requestImage(filename);
			}
		}

		void display(float xpos, float ypos) {
			frame = (frame+1) % imageCount;
			image(images[frame], xpos, ypos);
		}

		int getWidth() {
			return images[0].width;
		}
	}
	
	//Not sure if this is needed until testing
	/*	// -----------------------------------------------------------------
			// SimpleOpenNI events -- do not need to modify these...

			void onNewUser(SimpleOpenNI curContext, int userId) {
				println("onNewUser - userId: " + userId);
				curContext.startTrackingSkeleton(userId);
			}

			void onLostUser(SimpleOpenNI curContext, int userId) {
				println("onLostUser - userId: " + userId);
			}

			void onVisibleUser(SimpleOpenNI curContext, int userId) {
				println("onVisibleUser - userId: " + userId);
			}*/

}

