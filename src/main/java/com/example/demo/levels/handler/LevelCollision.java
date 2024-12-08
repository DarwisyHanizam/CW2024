package com.example.demo.levels.handler;

import com.example.demo.actors.ActiveActorDestructible;
import java.util.List;

public class LevelCollision {
	private LevelAll levelAll;

	public LevelCollision(LevelAll levelAll){
		this.levelAll = levelAll;
	}

	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > levelAll.screenWidth;
	}

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

	void handlePlaneCollisions() {
		handleCollisions(levelAll.friendlyUnits, levelAll.enemyUnits);
	}

	void handleUserProjectileCollisions() {
		handleCollisions(levelAll.userProjectiles, levelAll.enemyUnits);
	}

	void handleEnemyProjectileCollisions() {
		handleCollisions(levelAll.enemyProjectiles, levelAll.friendlyUnits);
	}

	void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : levelAll.enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				levelAll.user.takeDamage();
				enemy.destroy();
			}
		}
	}
}