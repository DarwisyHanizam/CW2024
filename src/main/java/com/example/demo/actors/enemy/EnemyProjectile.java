package com.example.demo.actors.enemy;

import com.example.demo.actors.Projectile;

/**
 * Represents a projectile fired by an enemy plane.
 * Extends the Projectile class and includes specific behaviors such as horizontal movement.
 */
public class EnemyProjectile extends Projectile {

	private static final String IMAGE_NAME = "enemyFire.png";
	private static final int IMAGE_HEIGHT = 25;
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructs a new EnemyProjectile instance.
	 * 
	 * @param initialXPos the initial x-position of the projectile
	 * @param initialYPos the initial y-position of the projectile
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
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