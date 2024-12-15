package com.example.demo.levels.types;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.enemy.EnemyPlaneOne;
import com.example.demo.levels.LevelTemplate;
import com.example.demo.levels.handler.LevelDisplay;

public class LevelOne extends LevelTemplate {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.levels.types.LevelTwo";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	@Override
	public void checkIfGameOver() {
		if (userIsDestroyed()) {
			getProgression().loseGame();
		}
		else if (userHasReachedKillTarget() && getCurrentNumberOfEnemies() == 0) {
			getProgression().goToNextLevel(NEXT_LEVEL);
		}
	}

	@Override
	public void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	public void spawnEnemyUnits() {
		if (!userHasReachedKillTarget()){
			int currentNumberOfEnemies = getCurrentNumberOfEnemies();
			for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
				if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
					double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
					ActiveActorDestructible newEnemy = new EnemyPlaneOne(getScreenWidth(), newEnemyInitialYPosition);
					addEnemyUnit(newEnemy);
				}
			}
		}
	}

	@Override
	public LevelDisplay instantiateLevelDisplay() {
		return new LevelDisplay(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

}