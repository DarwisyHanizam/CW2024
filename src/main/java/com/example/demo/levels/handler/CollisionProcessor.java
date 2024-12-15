package com.example.demo.levels.handler;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.levels.LevelBuilder;
import java.util.List;

public class CollisionProcessor {
	private LevelBuilder levelBuilder;

	public CollisionProcessor(LevelBuilder levelBuilder){
		this.levelBuilder = levelBuilder;
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

	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > levelBuilder.getScreenWidth();
	}

	void handlePlaneCollisions() {
		handleCollisions(levelBuilder.getFriendlyUnits(), levelBuilder.getEnemyUnits());
	}

	void handleUserProjectileCollisions() {
		handleCollisions(levelBuilder.getUserProjectiles(), levelBuilder.getEnemyUnits());
	}

	void handleEnemyProjectileCollisions() {
		handleCollisions(levelBuilder.getEnemyProjectiles(), levelBuilder.getFriendlyUnits());
	}

	void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : levelBuilder.getEnemyUnits()) {
			if (enemyHasPenetratedDefenses(enemy)) {
				levelBuilder.getUser().takeDamage();
				enemy.destroy();
			}
		}
	}
}