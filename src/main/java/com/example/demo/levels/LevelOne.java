package com.example.demo.levels;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.enemy.EnemyPlane;
import com.example.demo.levels.handler.LevelAbstract;

public class LevelOne extends LevelAbstract {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.levels.LevelTwo";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			levelControl.loseGame();
		}
		else if (userHasReachedKillTarget() && getCurrentNumberOfEnemies() == 0)
			levelControl.goToNextLevel(NEXT_LEVEL);
	}

	@Override
	protected void initializeFriendlyUnits() {
		System.out.println("Test here twoo");
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void spawnEnemyUnits() {
		if (!userHasReachedKillTarget()){
			int currentNumberOfEnemies = getCurrentNumberOfEnemies();
			for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
				if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
					double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
					ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
					addEnemyUnit(newEnemy);
				}
			}
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		System.out.println("when does this occur?");
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

}