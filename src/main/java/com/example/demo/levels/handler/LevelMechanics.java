package com.example.demo.levels.handler;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;

public class LevelMechanics {
	private LevelAll levelAll;

	public LevelMechanics(LevelAll levelAll){
		this.levelAll = levelAll;
	}

	void fireProjectile() {
		ActiveActorDestructible projectile = levelAll.user.fireProjectile();
		levelAll.root.getChildren().add(projectile);
		levelAll.userProjectiles.add(projectile);
	}

	void generateEnemyFire() {
		levelAll.enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			levelAll.root.getChildren().add(projectile);
			levelAll.enemyProjectiles.add(projectile);
		}
	}
}