package com.example.demo.actors;

public abstract class FighterPlane extends ActiveActorDestructible {

	private int health;
	private static final String planeFolder = "/com/example/demo/images/planes/";

	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(planeFolder + imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	public abstract ActiveActorDestructible fireProjectile();
	
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	private boolean healthAtZero() {
		return health == 0;
	}

	public int getHealth() {
		return health;
	}
		
}