package com.example.demo.levels.types;

import com.example.demo.actors.enemy.Boss;
import com.example.demo.levels.LevelTemplate;
import com.example.demo.levels.handler.LevelDisplay;

public class LevelTwo extends LevelTemplate {

	private static final String BACKGROUND_IMAGE_NAME = "background2.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.levels.types.LevelThree";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelDisplay levelDisplay;

	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss(levelDisplay);
	}

	@Override
	public void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	public void checkIfGameOver() {
		if (userIsDestroyed()) {
			getProgression().loseGame();
		}
		else if (boss.isDestroyed()) {
			getProgression().goToNextLevel(NEXT_LEVEL);
		}
	}

	@Override
	public void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	@Override
	public LevelDisplay instantiateLevelDisplay() {
		levelDisplay = new LevelDisplay(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelDisplay;
	}

}