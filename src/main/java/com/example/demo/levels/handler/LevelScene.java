package com.example.demo.levels.handler;

import com.example.demo.actors.ActiveActorDestructible;

import java.util.List;
import java.util.stream.Collectors;

public class LevelScene {
	private LevelAll levelAll;
	private LevelAbstract levelAbstract;

	public LevelScene(LevelAll levelAll, LevelAbstract levelAbstract){
		this.levelAll = levelAll;
		this.levelAbstract = levelAbstract;
	}

	void updateScene() {
		levelAbstract.spawnEnemyUnits();
		updateActors();
		levelAll.levelMechanics.generateEnemyFire();
		updateNumberOfEnemies();
		levelAll.levelCollision.handleEnemyPenetration();
		levelAll.levelCollision.handleUserProjectileCollisions();
		levelAll.levelCollision.handleEnemyProjectileCollisions();
		levelAll.levelCollision.handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		levelAbstract.checkIfGameOver();
	}

	private void updateActors() {
		levelAll.friendlyUnits.forEach(plane -> plane.updateActor());
		levelAll.enemyUnits.forEach(enemy -> enemy.updateActor());
		levelAll.userProjectiles.forEach(projectile -> projectile.updateActor());
		levelAll.enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

	public void removeAllActors(){
		levelAll.friendlyUnits.clear();
		levelAll.enemyUnits.clear();
		levelAll.userProjectiles.clear();
		levelAll.enemyProjectiles.clear();
		levelAll.timeline.stop();
		levelAll.root.getChildren().clear();
	}

	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());
		levelAll.root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	private void removeAllDestroyedActors() {
		removeDestroyedActors(levelAll.friendlyUnits);
		removeDestroyedActors(levelAll.enemyUnits);
		removeDestroyedActors(levelAll.userProjectiles);
		removeDestroyedActors(levelAll.enemyProjectiles);
	}

	private void updateLevelView() {
		levelAll.levelView.removeHearts(levelAll.user.getHealth());
	}

	private void updateKillCount() {
		for (int i = 0; i < levelAll.currentNumberOfEnemies - levelAll.enemyUnits.size(); i++) {
			levelAll.user.incrementKillCount();
		}
	}

	private void updateNumberOfEnemies() {
		levelAll.currentNumberOfEnemies = levelAll.enemyUnits.size();
	}
}