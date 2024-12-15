package com.example.demo.actors;

/**
 * Represents a projectile in the game.
 * Extends the ActiveActorDestructible class and includes specific behaviors such as taking damage and updating position.
 */
public abstract class Projectile extends ActiveActorDestructible {

	private static final String projectileFolder = "/com/example/demo/images/projectiles/";

	/**
	 * Constructs a new Projectile instance.
	 * 
	 * @param imageName the name of the image file for the projectile
	 * @param imageHeight the height of the image
	 * @param initialXPos the initial x-position of the projectile
	 * @param initialYPos the initial y-position of the projectile
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(projectileFolder + imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
	 * Takes damage and destroys the projectile.
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
	 * Updates the position of the projectile.
	 * This method should be implemented by subclasses to define specific behavior.
	 */
	@Override
	public abstract void updatePosition();

}