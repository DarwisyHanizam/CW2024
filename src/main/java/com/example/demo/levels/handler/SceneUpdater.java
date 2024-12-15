package com.example.demo.levels.handler;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.levels.LevelTemplate;
import com.example.demo.levels.LevelBuilder;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages the updates to the game scene, including actor movements, collision handling, and display updates.
 */
public class SceneUpdater {

	private LevelBuilder levelBuilder;
	private LevelTemplate levelTemplate;

	public SceneUpdater(LevelBuilder levelBuilder){
		this.levelBuilder = levelBuilder;
		levelTemplate = levelBuilder.getLevelTemplate();
	}

	/**
	 * Updates the game scene by spawning enemy units, updating actors, generating enemy fire, handling collisions, and updating the display.
	 */
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

	/**
	 * Updates the state of all actors in the game.
	 */
	private void updateActors() {
		levelBuilder.getFriendlyUnits().forEach(plane -> plane.updateActor());
		levelBuilder.getEnemyUnits().forEach(enemy -> enemy.updateActor());
		levelBuilder.getUserProjectiles().forEach(projectile -> projectile.updateActor());
		levelBuilder.getEnemyProjectiles().forEach(projectile -> projectile.updateActor());
	}

	/**
	 * Removes all actors from the game scene and stops the timeline.
	 */
	public void removeAllActors(){
		levelBuilder.getFriendlyUnits().clear();
		levelBuilder.getEnemyUnits().clear();
		levelBuilder.getUserProjectiles().clear();
		levelBuilder.getEnemyProjectiles().clear();
		levelBuilder.getTimeline().stop();
		levelBuilder.getRoot().getChildren().clear();
	}

	/**
	 * Removes all destroyed actors from the game scene.
	 * 
	 * @param actors the list of actors to check for destruction
	 */
	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream()
				.filter(actor -> actor.isDestroyed()).collect(Collectors.toList());
		levelBuilder.getRoot().getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	/**
	 * Removes all destroyed actors from all actor lists.
	 */
	private void removeAllDestroyedActors() {
		removeDestroyedActors(levelBuilder.getFriendlyUnits());
		removeDestroyedActors(levelBuilder.getEnemyUnits());
		removeDestroyedActors(levelBuilder.getUserProjectiles());
		removeDestroyedActors(levelBuilder.getEnemyProjectiles());
	}

	/**
	 * Updates the level display to reflect the current health of the user.
	 */
	private void updateLevelDisplay() {
		levelBuilder.getLevelDisplay().removeHearts(levelBuilder.getUser().getHealth());
	}

	/**
	 * Updates the kill count for the user based on the number of enemies destroyed.
	 */
	private void updateKillCount() {
		for (int i = 0; i < levelBuilder.getCurrentNumberOfEnemies() - levelBuilder.getEnemyUnits().size(); i++) {
			levelBuilder.getUser().incrementKillCount();
		}
	}

	/**
	 * Updates the current number of enemies in the game.
	 */
	private void updateNumberOfEnemies() {
		levelBuilder.setCurrentNumberOfEnemies(levelBuilder.getEnemyUnits().size());;
	}

}