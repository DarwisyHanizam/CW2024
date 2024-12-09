package com.example.demo.levels;

import com.example.demo.actors.enemy.Boss;
import com.example.demo.levels.handler.LevelAbstract;

public class LevelTwo extends LevelAbstract {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.levels.LevelThree";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelViewLevelTwo levelViewTwo;

	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss(levelViewTwo);
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			levelControl.loseGame();
		}
		else if (boss.isDestroyed()) {
			levelControl.goToNextLevel(NEXT_LEVEL);
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		levelViewTwo = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelViewTwo;
	}

}