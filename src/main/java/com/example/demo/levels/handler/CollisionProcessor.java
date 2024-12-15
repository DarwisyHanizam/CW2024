package com.example.demo.levels.handler;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.levels.LevelBuilder;
import java.util.List;

/**
 * Handles collision detection and processing for the game.
 * Manages interactions between different types of actors and projectiles.
 */
public class CollisionProcessor {

	private LevelBuilder levelBuilder;

	/**
	 * Constructs a new CollisionProcessor instance.
	 * 
	 * @param levelBuilder the LevelBuilder instance associated with this CollisionProcessor
	 */
	public CollisionProcessor(LevelBuilder levelBuilder){
		this.levelBuilder = levelBuilder;
	}

	/**
	 * Handles collisions between two lists of actors.
	 * 
	 * @param actors1 the first list of actors
	 * @param actors2 the second list of actors
	 */
	private void handleCollisions(List<ActiveActorDestructible> actors1, 
			List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor : actors2) {
			for (ActiveActorDestructible otherActor : actors1) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					actor.takeDamage();
					otherActor.takeDamage();
				}
			}
		}
	}

	/**
	 * Checks if an enemy has penetrated the defenses.
	 * 
	 * @param enemy the enemy actor to check
	 * @return true if the enemy has penetrated the defenses, false otherwise
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > levelBuilder.getScreenWidth();
	}

	/**
	 * Handles collisions between friendly and enemy planes.
	 */
	void handlePlaneCollisions() {
		handleCollisions(levelBuilder.getFriendlyUnits(), levelBuilder.getEnemyUnits());
	}

	/**
	 * Handles collisions between user projectiles and enemy units.
	 */
	void handleUserProjectileCollisions() {
		handleCollisions(levelBuilder.getUserProjectiles(), levelBuilder.getEnemyUnits());
	}

	/**
	 * Handles collisions between enemy projectiles and friendly units.
	 */
	void handleEnemyProjectileCollisions() {
		handleCollisions(levelBuilder.getEnemyProjectiles(), levelBuilder.getFriendlyUnits());
	}

	/**
	 * Handles enemy penetration of defenses.
	 * If an enemy penetrates the defenses, the user takes damage and the enemy is destroyed.
	 */
	void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : levelBuilder.getEnemyUnits()) {
			if (enemyHasPenetratedDefenses(enemy)) {
				levelBuilder.getUser().takeDamage();
				enemy.destroy();
			}
		}
	}

}