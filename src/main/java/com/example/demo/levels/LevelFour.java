package com.example.demo.levels;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.enemy.Boss;
import com.example.demo.actors.enemy.EnemyPlaneTwo;
import com.example.demo.levels.handler.LevelAbstract;

public class LevelFour extends LevelAbstract{

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private static final double ENEMY_SPAWN_PROBABILITY = .05;
	private static final int TOTAL_ENEMIES = 3;
	private final Boss boss;
	private LevelView levelView;

	public LevelFour(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss(levelView);
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
		else if (boss.isDestroyed() && getCurrentNumberOfEnemies() == 0) {
			levelControl.winGame();
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
		if (!boss.isDestroyed()) {
			if (TOTAL_ENEMIES > getCurrentNumberOfEnemies() && Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlaneTwo(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}
}