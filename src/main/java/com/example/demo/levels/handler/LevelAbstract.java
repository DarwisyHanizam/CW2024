package com.example.demo.levels.handler;

import com.example.demo.levels.LevelView;

public abstract class LevelAbstract extends LevelAll{

	public LevelAbstract(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth){
		super(backgroundImageName, screenHeight, screenWidth, playerInitialHealth);
		initializeLevelAbstract(this);
	}

	protected abstract void checkIfGameOver();

	protected abstract void initializeFriendlyUnits();

	protected abstract void spawnEnemyUnits();

	protected abstract LevelView instantiateLevelView();

}