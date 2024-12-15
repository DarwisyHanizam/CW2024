package com.example.demo.viewImage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the win image displayed in the game.
 */
public class WinImage extends ImageView {
	
	private static final String IMAGE_NAME = "/com/example/demo/images/display/youwin.png";
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;

	/**
	 * Constructs a new WinImage instance.
	 *
	 * @param xPosition the x-position of the image
	 * @param yPosition the y-position of the image
	 */
	public WinImage(double xPosition, double yPosition) {
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		setFitHeight(HEIGHT);
		setFitWidth(WIDTH);
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}

}