package com.example.demo.actors.enemy;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;

/**
 * Represents an enemy plane in the game.
 * Extends the FighterPlane class and includes specific behaviors such as firing projectiles and horizontal movement.
 */
public class EnemyPlaneOne extends FighterPlane {

	private static final String IMAGE_NAME = "enemyplane1.png";
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -80.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 10.0;
	private static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = .01;

	/**
	 * Constructs a new EnemyPlaneOne instance.
	 * 
	 * @param initialXPos the initial x-position of the enemy plane
	 * @param initialYPos the initial y-position of the enemy plane
	 */
	public EnemyPlaneOne(double initialXPos, double initialYPos) {
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
	 * @return an ActiveActorDestructible representing the projectile, or null if no projectile is fired
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPostion);
		}
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