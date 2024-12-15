package com.example.demo.actors;

/**
 * Represents a fighter plane in the game.
 * Extends the ActiveActorDestructible class and includes specific behaviors such as firing projectiles and taking damage.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	private int health;
	private static final String planeFolder = "/com/example/demo/images/planes/";

	/**
	 * Constructs a new FighterPlane instance.
	 * 
	 * @param imageName the name of the image file for the plane
	 * @param imageHeight the height of the image
	 * @param initialXPos the initial x-position of the plane
	 * @param initialYPos the initial y-position of the plane
	 * @param health the initial health of the plane
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(planeFolder + imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Fires a projectile from the fighter plane.
	 * 
	 * @return an ActiveActorDestructible representing the projectile
	 */
	public abstract ActiveActorDestructible fireProjectile();
	
	/**
	 * Takes damage and decreases the health of the plane.
	 * If the health reaches zero, the plane is destroyed.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	/**
	 * Gets the x-position for the projectile fired by the plane.
	 * 
	 * @param xPositionOffset the x-position offset for the projectile
	 * @return the x-position for the projectile
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Gets the y-position for the projectile fired by the plane.
	 * 
	 * @param yPositionOffset the y-position offset for the projectile
	 * @return the y-position for the projectile
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks if the health of the plane is zero.
	 * 
	 * @return true if the health is zero, false otherwise
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Gets the current health of the plane.
	 * 
	 * @return the current health
	 */
	public int getHealth() {
		return health;
	}
		
}