package com.example.demo.viewImage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the shield image displayed in the game.
 */
public class ShieldImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/display/shield.png";
	private static final int WIDTH = 600;
	private static final int HEIGHT = 500;

	/**
	 * Constructs a new ShieldImage instance.
	 *
	 * @param xPosition the x-position of the image
	 * @param yPosition the y-position of the image
	 */
	public ShieldImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.setFitWidth(WIDTH);
		this.setFitHeight(HEIGHT);
	}

	/**
	 * Shows the shield image.
	 */
	public void showShield() {
		this.setVisible(true);
	}

	/**
	 * Hides the shield image.
	 */
	public void hideShield() {
		this.setVisible(false);
	}

}