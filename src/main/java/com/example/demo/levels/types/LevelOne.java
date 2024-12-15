package com.example.demo.levels.types;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.enemy.EnemyPlaneOne;
import com.example.demo.levels.LevelTemplate;
import com.example.demo.levels.handler.LevelDisplay;

/**
 * Represents the first level of the game.
 */
public class LevelOne extends LevelTemplate {

	private static final String BACKGROUND_IMAGE_NAME = "background1.jpg";
	private static final String NEXT_LEVEL = "LevelTwo";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * Constructs a new LevelOne instance.
	 *
	 * @param screenHeight the height of the screen
	 * @param screenWidth the width of the screen
	 */
	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the game is over.
	 */
	@Override
	public void checkIfGameOver() {
		if (userIsDestroyed()) {
			getProgression().loseGame();
		}
		else if (userHasReachedKillTarget() && getCurrentNumberOfEnemies() == 0) {
			getProgression().goToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Initializes the friendly units in the level.
	 */
	@Override
	public void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Spawns enemy units in the level.
	 */
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

	/**
	 * Instantiates the level display.
	 *
	 * @return the level display
	 */
	@Override
	public LevelDisplay instantiateLevelDisplay() {
		return new LevelDisplay(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the user has reached the kill target.
	 *
	 * @return true if the user has reached the kill target, false otherwise
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

}