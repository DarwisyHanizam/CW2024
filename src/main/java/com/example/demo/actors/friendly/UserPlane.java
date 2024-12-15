package com.example.demo.actors.friendly;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;

/**
 * Represents the user's plane in the game.
 * Extends the FighterPlane class and includes specific behaviors such as firing projectiles and movement controls.
 */
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

	/**
	 * Constructs a new UserPlane instance.
	 * 
	 * @param initialHealth the initial health of the user's plane
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		velocityMultiplierX = velocityMultiplierY = 0;
	}

	/**
	 * Updates the position of the user's plane.
	 * Moves the plane based on its velocity multipliers.
	 */
	@Override
	public void updatePosition() {
		if (isMovingX() && isMovingY()) {
			updatePositionX();
			updatePositionY();
		} else {
			if (isMovingX()) updatePositionX();
			if (isMovingY()) updatePositionY();
		}
	}

	/**
	 * Updates the horizontal position of the user's plane.
	 * Ensures the plane stays within the horizontal bounds of the game area.
	 */
	private void updatePositionX() {
		double initialTranslateX = getTranslateX();
		this.moveHorizontally(HORIZONTAL_VELOCITY * velocityMultiplierX);
		double newPositionX = getLayoutX() + getTranslateX();
		if (newPositionX < X_LEFT_BOUND || newPositionX > X_RIGHT_BOUND) {
			this.setTranslateX(initialTranslateX);
		}
	}

	/**
	 * Updates the vertical position of the user's plane.
	 * Ensures the plane stays within the vertical bounds of the game area.
	 */
	private void updatePositionY() {
		double initialTranslateY = getTranslateY();
		this.moveVertically(VERTICAL_VELOCITY * velocityMultiplierY);
		double newPositionY = getLayoutY() + getTranslateY();
		if (newPositionY < Y_UPPER_BOUND || newPositionY > Y_LOWER_BOUND) {
			this.setTranslateY(initialTranslateY);
		}
	}

	/**
	 * Updates the state of the user's plane.
	 * This includes updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Fires a projectile from the user's plane.
	 * 
	 * @return an ActiveActorDestructible representing the projectile
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return fireProjectile(0);
	}

	/**
	 * Fires a projectile from the user's plane with a specified y-position offset.
	 * 
	 * @param yPositionOffset the y-position offset for the projectile
	 * @return an ActiveActorDestructible representing the projectile
	 */
	public ActiveActorDestructible fireProjectile(double yPositionOffset) {
		return new UserProjectile(getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET + yPositionOffset));
	}

	/**
	 * Checks if the user's plane is moving horizontally.
	 * 
	 * @return true if the plane is moving horizontally, false otherwise
	 */
	public boolean isMovingX() {
		return isMovingLeft() || isMovingRight();
	}

	/**
	 * Checks if the user's plane is moving left.
	 * 
	 * @return true if the plane is moving left, false otherwise
	 */
	public boolean isMovingLeft() {
		return velocityMultiplierX < 0;
	}

	/**
	 * Checks if the user's plane is moving right.
	 * 
	 * @return true if the plane is moving right, false otherwise
	 */
	public boolean isMovingRight() {
		return velocityMultiplierX > 0;
	}

	/**
	 * Checks if the user's plane is moving vertically.
	 * 
	 * @return true if the plane is moving vertically, false otherwise
	 */
	public boolean isMovingY() {
		return isMovingUp() || isMovingDown();
	}

	/**
	 * Checks if the user's plane is moving up.
	 * 
	 * @return true if the plane is moving up, false otherwise
	 */
	public boolean isMovingUp() {
		return velocityMultiplierY < 0;
	}

	/**
	 * Checks if the user's plane is moving down.
	 * 
	 * @return true if the plane is moving down, false otherwise
	 */
	public boolean isMovingDown() {
		return velocityMultiplierY > 0;
	}

	/**
	 * Sets the plane to move up.
	 */
	public void moveUp() {
		velocityMultiplierY = -1;
	}

	/**
	 * Sets the plane to move down.
	 */
	public void moveDown() {
		velocityMultiplierY = 1;
	}

	/**
	 * Sets the plane to move left.
	 */
	public void moveLeft() {
		velocityMultiplierX = -1;
	}

	/**
	 * Sets the plane to move right.
	 */
	public void moveRight() {
		velocityMultiplierX = 1;
	}

	/**
	 * Stops the vertical movement of the plane.
	 */
	public void stopVertical() {
		velocityMultiplierY = 0;
	}

	public void stopHorizontal(){
		velocityMultiplierX = 0;
	}

	/**
	 * Gets the number of kills made by the user's plane.
	 * 
	 * @return the number of kills
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increments the kill count of the user's plane.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	} 

}