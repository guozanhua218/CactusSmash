package model;

import KinectProjectorToolkit.KinectProjectorToolkit;
import SimpleOpenNI.SimpleOpenNI;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * @authors Ben Ackerman and Chris Carsey
 * 
 * @Description This class creates a Human fighter based on Kinect data
 * 
 * @Param: Takes in a PApplet object
 */
public class KinectFighter implements HumanFighter
{ 
	// Instance variables for kinect
	SimpleOpenNI kinect;
	KinectProjectorToolkit kpc;
	Skeletal skeletal;


	public KinectFighter(PApplet input) {
		// setup Kinect
		kinect = new SimpleOpenNI((PApplet) input); 
		kinect.enableDepth();
		kinect.enableUser();
		kinect.alternativeViewPointDepthToImage();

		// setup Kinect Projector Toolkit
		kpc = new KinectProjectorToolkit((PApplet) input, kinect.depthWidth(), kinect.depthHeight());
		kpc.loadCalibration("calibration.txt");
		
		// Create the Skeletal
		skeletal = new Skeletal();

	}

	public void update() {
		kinect.update();
		if(this.getImportantData() != null) System.out.println("Right Hand Location: (" + this.getImportantData()[0].x + ", " + this.getImportantData()[0].y + "), " + kinect.getNumberOfUsers()); 
		kpc.setDepthMapRealWorld(kinect.depthMapRealWorld());
		//System.out.println("Users: " + kinect.getUsers().length);
	}
	PVector getProjectedJoint(int userId, int jointIdx) {
		PVector jointKinectRealWorld = new PVector();
		PVector jointProjected = new PVector();
		kinect.getJointPositionSkeleton(userId, jointIdx, jointKinectRealWorld);
		jointProjected = kpc.convertKinectToProjector(jointKinectRealWorld);
		return jointProjected;
	}


	public PVector[] getImportantData()
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

	public Skeletal getSkeletal() {
		int user = 0;
		if(kinect.getNumberOfUsers() < 1)
		{
			System.out.println("ERROR: To many users present in KinectFighter.getAllData()");
			return null; 
		}
		else
		{
			user = kinect.getUsers()[kinect.getNumberOfUsers() - 1];
		}
		skeletal.setHead(getProjectedJoint(user, SimpleOpenNI.SKEL_HEAD));
		skeletal.setLeftShoulder(getProjectedJoint(user, SimpleOpenNI.SKEL_LEFT_SHOULDER));
		skeletal.setRightShoulder(getProjectedJoint(user, SimpleOpenNI.SKEL_RIGHT_SHOULDER));
		skeletal.setTorso(getProjectedJoint(user, SimpleOpenNI.SKEL_TORSO));
		skeletal.setLeftHip(getProjectedJoint(user, SimpleOpenNI.SKEL_LEFT_HIP));
		skeletal.setRightHip(getProjectedJoint(user, SimpleOpenNI.SKEL_RIGHT_HIP));
		skeletal.setNeck(getProjectedJoint(user, SimpleOpenNI.SKEL_NECK));
		skeletal.setRightElbow(getProjectedJoint(user, SimpleOpenNI.SKEL_RIGHT_ELBOW));
		skeletal.setLeftElbow(getProjectedJoint(user, SimpleOpenNI.SKEL_LEFT_ELBOW));
		skeletal.setLeftKnee(getProjectedJoint(user, SimpleOpenNI.SKEL_LEFT_KNEE));
		skeletal.setRightKnee(getProjectedJoint(user, SimpleOpenNI.SKEL_RIGHT_KNEE));
		skeletal.setLeftFoot(getProjectedJoint(user, SimpleOpenNI.SKEL_LEFT_FOOT));
		skeletal.setRightFoot(getProjectedJoint(user, SimpleOpenNI.SKEL_RIGHT_FOOT));
		return skeletal;
	}

}
