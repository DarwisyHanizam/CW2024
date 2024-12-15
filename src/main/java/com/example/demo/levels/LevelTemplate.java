package com.example.demo.levels;

import com.example.demo.levels.handler.LevelDisplay;

public abstract class LevelTemplate extends LevelBuilder {

	public LevelTemplate(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth){
		super(backgroundImageName, screenHeight, screenWidth, playerInitialHealth);
	}

	public abstract void checkIfGameOver();

	public abstract void initializeFriendlyUnits();

	public abstract void spawnEnemyUnits();

	public abstract LevelDisplay instantiateLevelDisplay();

}