package com.example.demo.actors.friendly;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;

public class UserPlane extends FighterPlane {

	private static final String IMAGE_NAME = "userplane.png";
	private static final int IMAGE_HEIGHT = 30;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final double Y_UPPER_BOUND = 10;
	private static final double Y_LOWER_BOUND = 660.0;
	private static final double X_LEFT_BOUND = 5.0;
	private static final double X_RIGHT_BOUND = 1100.0;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HORIZONTAL_VELOCITY = 8;
	private static final int PROJECTILE_X_POSITION_OFFSET = 190;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 15;
	private int velocityMultiplierX;
	private int velocityMultiplierY;
	private int numberOfKills;

	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		velocityMultiplierX = velocityMultiplierY = 0;
	}

	@Override
	public void updatePosition() {
		if (isMovingX() && isMovingY()) {
			updatePositionX();
			updatePositionY();
		} else if (isMovingX()) {
			updatePositionX();
		} else if (isMovingY()) {
			updatePositionY();
		}
	}

	private void updatePositionX() {
		double initialTranslateX = getTranslateX();
		this.moveHorizontally(HORIZONTAL_VELOCITY * velocityMultiplierX);
		double newPositionX = getLayoutX() + getTranslateX();
		if (newPositionX < X_LEFT_BOUND || newPositionX > X_RIGHT_BOUND) {
			this.setTranslateX(initialTranslateX);
		}
	}

	private void updatePositionY() {
		double initialTranslateY = getTranslateY();
		this.moveVertically(VERTICAL_VELOCITY * velocityMultiplierY);
		double newPositionY = getLayoutY() + getTranslateY();
		if (newPositionY < Y_UPPER_BOUND || newPositionY > Y_LOWER_BOUND) {
			this.setTranslateY(initialTranslateY);
		}
	}

	@Override
	public void updateActor() {
		updatePosition();
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		return fireProjectile(0);
	}

	public ActiveActorDestructible fireProjectile(double yPositionOffset) {
		return new UserProjectile(getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET + yPositionOffset));
	}

	private boolean isMovingX() {
		return velocityMultiplierX != 0;
	}

	private boolean isMovingY() {
		return velocityMultiplierY != 0;
	}

	public void moveUp() {
		velocityMultiplierY = -1;
	}

	public void moveDown() {
		velocityMultiplierY = 1;
	}

	public void moveLeft() {
		velocityMultiplierX = -1;
	}

	public void moveRight() {
		velocityMultiplierX = 1;
	}

	public void stop(String directionAxis) {
		if (directionAxis == "Vertical") velocityMultiplierY = 0;
		if (directionAxis == "Horizontal") velocityMultiplierX = 0;
	}

	public int getNumberOfKills() {
		return numberOfKills;
	}

	public void incrementKillCount() {
		numberOfKills++;
	}

}