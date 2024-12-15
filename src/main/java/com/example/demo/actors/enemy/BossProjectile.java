package com.example.demo.actors.enemy;

import com.example.demo.actors.Projectile;

/**
 * Represents a projectile fired by the boss enemy.
 * Extends the Projectile class and includes specific behaviors such as horizontal movement.
 */
public class BossProjectile extends Projectile {

	private static final String IMAGE_NAME = "fireball.png";
	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Constructs a new BossProjectile instance.
	 * 
	 * @param initialYPos the initial y-position of the projectile
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
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