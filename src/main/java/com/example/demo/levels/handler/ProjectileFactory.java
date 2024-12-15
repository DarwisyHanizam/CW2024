package com.example.demo.levels.handler;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import com.example.demo.levels.LevelBuilder;

/**
 * Manages the creation and firing of projectiles in the game.
 * Interacts with the LevelBuilder to add projectiles to the game scene.
 */
public class ProjectileFactory {

	private LevelBuilder levelBuilder;

	/**
	 * Constructs a new ProjectileFactory instance.
	 * 
	 * @param levelBuilder the LevelBuilder instance to interact with the game level
	 */
	public ProjectileFactory(LevelBuilder levelBuilder){
		this.levelBuilder = levelBuilder;
	}

	/**
	 * Fires a projectile from the user's plane with no y-position offset.
	 */
	void fireProjectile() {
		fireProjectile(0);
	}

	/**
	 * Fires a projectile from the user's plane with a specified y-position offset.
	 * 
	 * @param yPositionOffset the Y position offset for the projectile
	 */
	void fireProjectile(double yPositionOffset) {
		ActiveActorDestructible projectile = levelBuilder.getUser().fireProjectile(yPositionOffset);
		levelBuilder.getRoot().getChildren().add(projectile);
		levelBuilder.getUserProjectiles().add(projectile);
	}

	/**
	 * Generates enemy fire by spawning projectiles from enemy units.
	 */
	void generateEnemyFire() {
		levelBuilder.getEnemyUnits().forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	/**
	 * Spawns an enemy projectile and adds it to the game scene.
	 * 
	 * @param projectile the enemy projectile to spawn
	 */
	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			levelBuilder.getRoot().getChildren().add(projectile);
			levelBuilder.getEnemyProjectiles().add(projectile);
		}
	}

}