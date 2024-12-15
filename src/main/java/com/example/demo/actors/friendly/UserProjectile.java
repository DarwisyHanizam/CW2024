package com.example.demo.actors.friendly;

import com.example.demo.actors.Projectile;

/**
 * Represents a projectile fired by the user's plane.
 * Extends the Projectile class and includes specific behaviors such as horizontal movement.
 */
public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "userfire.png";
	private static final int IMAGE_HEIGHT = 10;
	private static final int HORIZONTAL_VELOCITY = 15;

	/**
	 * Constructs a new UserProjectile instance.
	 * 
	 * @param initialXPos the initial x-position of the projectile
	 * @param initialYPos the initial y-position of the projectile
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the projectile.
	 * Moves the projectile horizontally based on its velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the projectile.
	 * This includes updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

}