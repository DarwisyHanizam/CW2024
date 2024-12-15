package com.example.demo.actors;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * Represents an active actor in the game.
 * Extends the ImageView class and includes methods for updating position and moving horizontally or vertically.
 */
public abstract class ActiveActor extends ImageView {

	/**
	 * Constructs a new ActiveActor instance.
	 * 
	 * @param imageName the name of the image file for the actor
	 * @param imageHeight the height of the image
	 * @param initialXPos the initial x-position of the actor
	 * @param initialYPos the initial y-position of the actor
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		this.setImage(new Image(getClass().getResource(imageName).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Updates the position of the actor.
	 * This method should be implemented by subclasses to define specific behavior.
	 */
	public abstract void updatePosition();

	/**
	 * Moves the actor horizontally.
	 * 
	 * @param horizontalMove the distance to move horizontally
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves the actor vertically.
	 * 
	 * @param verticalMove the distance to move vertically
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}

}