package com.example.demo.actors.enemy;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;

/**
 * Represents the second type of enemy plane in the game.
 * Extends the FighterPlane class and includes specific behaviors such as horizontal movement.
 */
public class EnemyPlaneTwo extends FighterPlane {

	private static final String IMAGE_NAME = "enemyplane2.png";
	private static final int IMAGE_HEIGHT = 40;
	private static final int HORIZONTAL_VELOCITY = -10;
	private static final int INITIAL_HEALTH = 1;

	/**
	 * Constructs a new EnemyPlaneTwo instance.
	 * 
	 * @param initialXPos the initial x-position of the enemy plane
	 * @param initialYPos the initial y-position of the enemy plane
	 */
	public EnemyPlaneTwo(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Updates the position of the enemy plane.
	 * Moves the plane horizontally based on its velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Fires a projectile from the enemy plane.
	 * 
	 * @return null as this enemy plane does not fire projectiles
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return null;
	}

	/**
	 * Updates the state of the enemy plane.
	 * This includes updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

}