package com.example.demo.levels;

import com.example.demo.levels.handler.LevelDisplay;

/**
 * Represents a template for a level in the game.
 */
public abstract class LevelTemplate extends LevelBuilder {

	/**
	 * Constructs a new LevelTemplate instance.
	 * 
	 * @param backgroundImageName the name of the background image
	 * @param screenHeight the height of the screen
	 * @param screenWidth the width of the screen
	 * @param playerInitialHealth the initial health of the player
	 */
	public LevelTemplate(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth){
		super(backgroundImageName, screenHeight, screenWidth, playerInitialHealth);
	}

	/**
	 * Checks if the game is over.
	 */
	public abstract void checkIfGameOver();

	/**
	 * Initializes the friendly units in the level.
	 */
	public abstract void initializeFriendlyUnits();

	/**
	 * Spawns enemy units in the level.
	 */
	public abstract void spawnEnemyUnits();

	/**
	 * Instantiates the level display.
	 *
	 * @return the level display
	 */
	public abstract LevelDisplay instantiateLevelDisplay();

}