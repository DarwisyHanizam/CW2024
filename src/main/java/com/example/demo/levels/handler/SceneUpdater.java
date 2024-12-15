package com.example.demo.levels.handler;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.levels.LevelTemplate;
import com.example.demo.levels.LevelBuilder;
import java.util.List;
import java.util.stream.Collectors;

public class SceneUpdater {
	private LevelBuilder levelBuilder;
	private LevelTemplate levelTemplate;

	public SceneUpdater(LevelBuilder levelBuilder){
		this.levelBuilder = levelBuilder;
		levelTemplate = levelBuilder.getLevelTemplate();
	}

	void updateScene() {
		CollisionProcessor collisionHandler = levelBuilder.getCollisionProcessor();

		levelTemplate.spawnEnemyUnits();
		updateActors();
		levelBuilder.getProjectileFactory().generateEnemyFire();
		updateNumberOfEnemies();
		collisionHandler.handleEnemyPenetration();
		collisionHandler.handleUserProjectileCollisions();
		collisionHandler.handleEnemyProjectileCollisions();
		collisionHandler.handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelDisplay();
		levelTemplate.checkIfGameOver();
	}

	private void updateActors() {
		levelBuilder.getFriendlyUnits().forEach(plane -> plane.updateActor());
		levelBuilder.getEnemyUnits().forEach(enemy -> enemy.updateActor());
		levelBuilder.getUserProjectiles().forEach(projectile -> projectile.updateActor());
		levelBuilder.getEnemyProjectiles().forEach(projectile -> projectile.updateActor());
	}

	public void removeAllActors(){
		levelBuilder.getFriendlyUnits().clear();
		levelBuilder.getEnemyUnits().clear();
		levelBuilder.getUserProjectiles().clear();
		levelBuilder.getEnemyProjectiles().clear();
		levelBuilder.getTimeline().stop();
		levelBuilder.getRoot().getChildren().clear();
	}

	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream()
				.filter(actor -> actor.isDestroyed()).collect(Collectors.toList());
		levelBuilder.getRoot().getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	private void removeAllDestroyedActors() {
		removeDestroyedActors(levelBuilder.getFriendlyUnits());
		removeDestroyedActors(levelBuilder.getEnemyUnits());
		removeDestroyedActors(levelBuilder.getUserProjectiles());
		removeDestroyedActors(levelBuilder.getEnemyProjectiles());
	}

	private void updateLevelDisplay() {
		levelBuilder.getLevelDisplay().removeHearts(levelBuilder.getUser().getHealth());
	}

	private void updateKillCount() {
		for (int i = 0; i < levelBuilder.getCurrentNumberOfEnemies() - levelBuilder.getEnemyUnits().size(); i++) {
			levelBuilder.getUser().incrementKillCount();
		}
	}

	private void updateNumberOfEnemies() {
		levelBuilder.setCurrentNumberOfEnemies(levelBuilder.getEnemyUnits().size());;
	}
}