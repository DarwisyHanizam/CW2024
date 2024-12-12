package com.example.demo.levels;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.enemy.EnemyPlaneOne;
import com.example.demo.actors.enemy.EnemyPlaneTwo;
import com.example.demo.levels.handler.LevelAbstract;

public class LevelThree extends LevelAbstract {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
	private static final int TOTAL_ENEMIES = 4;
	private static final int KILLS_TO_ADVANCE = 16;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
    private static final double ENEMY_TWO_SPAWN_PROBABILITY = .60;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	public LevelThree(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			levelControl.loseGame();
		}
		else if (userHasReachedKillTarget() && getCurrentNumberOfEnemies() == 0)
			levelControl.winGame();
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void spawnEnemyUnits() {
		if (!userHasReachedKillTarget()){
			int currentNumberOfEnemies = getCurrentNumberOfEnemies();
			for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
				if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                    double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                    ActiveActorDestructible newEnemy;
                    if (Math.random() < ENEMY_TWO_SPAWN_PROBABILITY) {
                        newEnemy = new EnemyPlaneTwo(getScreenWidth(), newEnemyInitialYPosition);
                    } else {
                        newEnemy = new EnemyPlaneOne(getScreenWidth(), newEnemyInitialYPosition);
                    }
					addEnemyUnit(newEnemy);
				}
			}
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

}