package model;

import processing.core.PVector;

/**
 * @authors Ben Ackerman
 * 
 * @Description  Used to keep track of all the body point positions;
 */
public class Skeletal {
	private PVector head;
	private PVector neck;
	private PVector rightShoulder;
	private PVector leftShoulder;
	private PVector torso;
	private PVector leftHip;
	private PVector rightHip;
	private PVector rightHand;
	private PVector leftHand;
	private PVector rightElbow;
	private PVector leftElbow;
	private PVector rightKnee;
	private PVector leftKnee;
	private PVector rightFoot;
	private PVector leftFoot;
	
	public PVector getHead() {
		return head;
	}
	
	public void setHead(PVector head) {
		this.head = head;
	}

	public PVector getNeck() {
		return neck;
	}

	public void setNeck(PVector neck) {
		this.neck = neck;
	}

	public PVector getRightShoulder() {
		return rightShoulder;
	}

	public void setRightShoulder(PVector rightShoulder) {
		this.rightShoulder = rightShoulder;
	}

	public PVector getLeftShoulder() {
		return leftShoulder;
	}

	public void setLeftShoulder(PVector leftShoulder) {
		this.leftShoulder = leftShoulder;
	}

	public PVector getTorso() {
		return torso;
	}

	public void setTorso(PVector torso) {
		this.torso = torso;
	}

	public PVector getLeftHip() {
		return leftHip;
	}

	public void setLeftHip(PVector leftHip) {
		this.leftHip = leftHip;
	}

	public PVector getRightHip() {
		return rightHip;
	}

	public void setRightHip(PVector rightHip) {
		this.rightHip = rightHip;
	}

	public PVector getRightHand() {
		return rightHand;
	}

	public void setRightHand(PVector rightHand) {
		this.rightHand = rightHand;
	}

	public PVector getLeftHand() {
		return leftHand;
	}

	public void setLeftHand(PVector leftHand) {
		this.leftHand = leftHand;
	}

	public PVector getRightElbow() {
		return rightElbow;
	}

	public void setRightElbow(PVector rightElbow) {
		this.rightElbow = rightElbow;
	}

	public PVector getLeftElbow() {
		return leftElbow;
	}

	public void setLeftElbow(PVector leftElbow) {
		this.leftElbow = leftElbow;
	}

	public PVector getRightKnee() {
		return rightKnee;
	}

	public void setRightKnee(PVector rightKnee) {
		this.rightKnee = rightKnee;
	}

	public PVector getLeftKnee() {
		return leftKnee;
	}

	public void setLeftKnee(PVector leftKnee) {
		this.leftKnee = leftKnee;
	}

	public PVector getRightFoot() {
		return rightFoot;
	}

	public void setRightFoot(PVector rightFoot) {
		this.rightFoot = rightFoot;
	}

	public PVector getLeftFoot() {
		return leftFoot;
	}

	public void setLeftFoot(PVector leftFoot) {
		this.leftFoot = leftFoot;
	}
}
