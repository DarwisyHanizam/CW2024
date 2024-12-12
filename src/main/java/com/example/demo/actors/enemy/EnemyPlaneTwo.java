package com.example.demo.actors.enemy;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;

public class EnemyPlaneTwo extends FighterPlane {

	private static final String IMAGE_NAME = "enemyplane2.png";
	private static final int IMAGE_HEIGHT = 40;
	private static final int HORIZONTAL_VELOCITY = -10;
	private static final int INITIAL_HEALTH = 1;

	public EnemyPlaneTwo(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
        return null;
	}

	@Override
	public void updateActor() {
		updatePosition();
	}

}