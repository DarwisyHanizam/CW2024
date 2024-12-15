package com.example.demo.actors;

/**
 * Represents an active actor in the game that can be destroyed.
 * Extends the ActiveActor class and includes methods for updating position, taking damage, and checking if the actor is destroyed.
 */
public abstract class ActiveActorDestructible extends ActiveActor {

	private boolean isDestroyed;

	/**
	 * Constructs a new ActiveActorDestructible instance.
	 * 
	 * @param imageName the name of the image file for the actor
	 * @param imageHeight the height of the image
	 * @param initialXPos the initial x-position of the actor
	 * @param initialYPos the initial y-position of the actor
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	/**
	 * Updates the position of the actor.
	 * This method should be implemented by subclasses to define specific behavior.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Updates the state of the actor.
	 * This method should be implemented by subclasses to define specific behavior.
	 */
	public abstract void updateActor();

	/**
	 * Takes damage and decreases the health of the actor.
	 * This method should be implemented by subclasses to define specific behavior.
	 */
	public abstract void takeDamage();

	/**
	 * Destroys the actor by setting its destroyed status to true.
	 */
	public void destroy() {
		setDestroyed(true);
	}

	/**
	 * Sets the destroyed status of the actor.
	 * 
	 * @param isDestroyed the destroyed status to set
	 */
	private void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * Checks if the actor is destroyed.
	 * 
	 * @return true if the actor is destroyed, false otherwise
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}

}