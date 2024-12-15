package com.example.demo.actors;

public abstract class Projectile extends ActiveActorDestructible {

	private static final String projectileFolder = "/com/example/demo/images/projectiles/";

	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(projectileFolder + imageName, imageHeight, initialXPos, initialYPos);
	}

	@Override
	public void takeDamage() {
		this.destroy();
	}

	@Override
	public abstract void updatePosition();

}