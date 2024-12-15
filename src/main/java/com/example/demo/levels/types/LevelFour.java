package com.example.demo.levels.types;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.enemy.Boss;
import com.example.demo.actors.enemy.EnemyPlaneTwo;
import com.example.demo.levels.LevelTemplate;
import com.example.demo.levels.handler.LevelDisplay;

/**
 * Represents the fourth level of the game.
 */
public class LevelFour extends LevelTemplate{

	private static final String BACKGROUND_IMAGE_NAME = "background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private static final double ENEMY_SPAWN_PROBABILITY = .05;
	private static final int TOTAL_ENEMIES = 3;
	private final Boss boss;
	private LevelDisplay levelDisplay;

	/**
	 * Constructs a new LevelFour instance.
	 *
	 * @param screenHeight the height of the screen
	 * @param screenWidth the width of the screen
	 */
	public LevelFour(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss(levelDisplay);
	}
	
	/**
	 * Initializes the friendly units in the level.
	 */
	@Override
	public void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Checks if the game is over.
	 */
	@Override
	public void checkIfGameOver() {
		if (userIsDestroyed()) {
			getProgression().loseGame();
		}
		else if (boss.isDestroyed() && getCurrentNumberOfEnemies() == 0) {
			getProgression().winGame();
		}
	}

	/**
	 * Spawns enemy units in the level.
	 */
	@Override
	public void spawnEnemyUnits() {
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

	/**
	 * Instantiates the level display.
	 *
	 * @return the level display
	 */
	@Override
	public LevelDisplay instantiateLevelDisplay() {
		levelDisplay = new LevelDisplay(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelDisplay;
	}

}