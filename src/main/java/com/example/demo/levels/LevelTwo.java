package com.example.demo.levels;//

import com.example.demo.actors.Boss;
import com.example.demo.levels.handler.LevelAbstract;

public class LevelTwo extends LevelAbstract {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelViewLevelTwo levelViewTwo;

	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		setLevelAbstract(this);
		System.out.println("here first right?");
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
			levelControl.winGame();
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
		System.out.println("load level 2.");
		levelViewTwo = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelViewTwo;
	}

}
