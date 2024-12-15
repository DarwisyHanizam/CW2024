package com.example.demo.levels.handler;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import com.example.demo.levels.LevelBuilder;

public class ProjectileFactory {
	private LevelBuilder levelBuilder;

	public ProjectileFactory(LevelBuilder levelBuilder){
		this.levelBuilder = levelBuilder;
	}

	void fireProjectile() {
		fireProjectile(0);
	}

	void fireProjectile(double yPositionOffset) {
		ActiveActorDestructible projectile = levelBuilder.getUser().fireProjectile(yPositionOffset);
		levelBuilder.getRoot().getChildren().add(projectile);
		levelBuilder.getUserProjectiles().add(projectile);
	}

	void generateEnemyFire() {
		levelBuilder.getEnemyUnits().forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			levelBuilder.getRoot().getChildren().add(projectile);
			levelBuilder.getEnemyProjectiles().add(projectile);
		}
	}
}