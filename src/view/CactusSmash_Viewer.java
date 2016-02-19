package view;

import java.awt.Point;
import java.util.HashMap;

import model.Game;
import processing.core.*;
import ddf.minim.*;
import SimpleOpenNI.*;
import controller.GameController;
import KinectProjectorToolkit.*;


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
	SimpleOpenNI kinect;
	KinectProjectorToolkit kpc;
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
		backgroundImage = loadImage("Resources/1024Background.jpg");
		setButtons();
		frameRate(12);
		start = false;

		// Set up sound objects
		Minim minim; //audio context
		minim = new Minim(this);
		hitSound = minim.loadFile("Resources/Punch.mp3");

		// Placing user and cactus
		cactusHit = false;
		userHit = false;
		game.getUser().setCoords(new Point(4*width/5, height/2));
		game.getCactus().setCoords(new Point(0, (int) ypos));

		// Loads animation for cactus
		loadSprites();

		// setup Kinect
		kinect = new SimpleOpenNI(this); 
		kinect.enableDepth();
		kinect.enableUser();
		kinect.alternativeViewPointDepthToImage();

		// setup Kinect Projector Toolkit
		kpc = new KinectProjectorToolkit(this, kinect.depthWidth(), kinect.depthHeight());
		kpc.loadCalibration("calibration.txt");
		human = new HumanFighter();

	}

	private void loadSprites() {
		sprites = new HashMap<String, CactusSmash_Viewer.Animation>();
		sprites.put("view/sprites/punchright_", new Animation("view/sprites/punchright_", 10));
		for (int i = 0; i < 10; i++) {
			String file = "view/sprites/block_level_0" + i + "_";
			sprites.put(file, new Animation(file, 2));
		}
	}

	public void draw() {
		// will be Ben's image
		background(backgroundImage);

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

		kinect.update();
		if(human.getData() != null) System.out.println("Right Hand Location: (" + human.getData()[0].x + ", " + human.getData()[0].y + "), " + kinect.getNumberOfUsers()); 
		kpc.setDepthMapRealWorld(kinect.depthMapRealWorld());
		drawProjectedSkeletons();
		//System.out.println("Users: " + kinect.getUsers().length);
	}

	void drawProjectedSkeletons() {
		int[] userList = kinect.getUsers();
		for(int i=0; i<userList.length; i++) {
			if(kinect.isTrackingSkeleton(userList[i])) {
				PVector pHead = getProjectedJoint(userList[i], SimpleOpenNI.SKEL_HEAD);
				PVector pNeck = getProjectedJoint(userList[i], SimpleOpenNI.SKEL_NECK);
				PVector pTorso = getProjectedJoint(userList[i], SimpleOpenNI.SKEL_TORSO);
				PVector pLeftShoulder = getProjectedJoint(userList[i], SimpleOpenNI.SKEL_LEFT_SHOULDER);
				PVector pRightShoulder = getProjectedJoint(userList[i], SimpleOpenNI.SKEL_RIGHT_SHOULDER);
				PVector pLeftElbow = getProjectedJoint(userList[i], SimpleOpenNI.SKEL_LEFT_ELBOW);
				PVector pRightElbow = getProjectedJoint(userList[i], SimpleOpenNI.SKEL_RIGHT_ELBOW);
				PVector pLeftHand = getProjectedJoint(userList[i], SimpleOpenNI.SKEL_LEFT_HAND);
				PVector pRightHand = getProjectedJoint(userList[i], SimpleOpenNI.SKEL_RIGHT_HAND);      
				PVector pLeftHip = getProjectedJoint(userList[i], SimpleOpenNI.SKEL_LEFT_HIP);
				PVector pRightHip = getProjectedJoint(userList[i], SimpleOpenNI.SKEL_RIGHT_HIP);
				PVector pLeftKnee = getProjectedJoint(userList[i], SimpleOpenNI.SKEL_LEFT_KNEE);
				PVector pRightKnee = getProjectedJoint(userList[i], SimpleOpenNI.SKEL_RIGHT_KNEE);
				PVector pLeftFoot = getProjectedJoint(userList[i], SimpleOpenNI.SKEL_LEFT_FOOT);
				PVector pRightFoot = getProjectedJoint(userList[i], SimpleOpenNI.SKEL_RIGHT_FOOT);

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
		}    
	}

	PVector getProjectedJoint(int userId, int jointIdx) {
		PVector jointKinectRealWorld = new PVector();
		PVector jointProjected = new PVector();
		kinect.getJointPositionSkeleton(userId, jointIdx, jointKinectRealWorld);
		jointProjected = kpc.convertKinectToProjector(jointKinectRealWorld);
		return jointProjected;
	}


	// -----------------------------------------------------------------
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
	}

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

	public void mousePressed() {
		if (overStart(startButtonX, startButtonY, startButtonWidth, startButtonLength)) {
			start = true;
		}
	}

	boolean overStart(float x, float y, float width, float height)  {
		if (mouseX >= x && mouseX <= x+width && 
				mouseY >= y && mouseY <= y+height) {
			return true;
		} else {
			return false;
		}
	}

	private void drawCactus() {
		Animation sprite = null;
		Point coords = game.getCactus().getCoords();

		// Display the sprite at the position xpos, ypos
		switch (game.getCactus().getState()) {
		case Attack:
			sprite = sprites.get("view/sprites/punchright_");
			break;
		case Idle:
			sprite = sprites.get("view/sprites/block_level_05_");
			break;

		}
		sprite.display(coords.x - sprite.getWidth()/2, ypos);
	}

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
	
	public class HumanFighter
	{ 
	    public HumanFighter() {}
	    
	    public PVector[] getData()
	    {
	        int user = 0;
	        if(kinect.getNumberOfUsers() < 1)
	        {
	          return null; 
	        }
	        else
	        {
	           user = kinect.getUsers()[kinect.getNumberOfUsers() - 1];
	        }
	        PVector[] points = new PVector[6];
	        points[0] = getProjectedJoint(user, SimpleOpenNI.SKEL_HEAD);
	        points[1] = getProjectedJoint(user, SimpleOpenNI.SKEL_LEFT_SHOULDER);
	        points[2] = getProjectedJoint(user, SimpleOpenNI.SKEL_RIGHT_SHOULDER);
	        points[3] = getProjectedJoint(user, SimpleOpenNI.SKEL_TORSO);
	        points[4] = getProjectedJoint(user, SimpleOpenNI.SKEL_LEFT_HIP);
	        points[5] = getProjectedJoint(user, SimpleOpenNI.SKEL_RIGHT_HIP);
	        return points;
	    }
	}
}

