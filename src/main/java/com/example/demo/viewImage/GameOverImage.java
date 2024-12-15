package com.example.demo.viewImage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameOverImage extends ImageView {
	
	private static final String IMAGE_NAME = "/com/example/demo/images/display/gameover.png";
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;

	public GameOverImage(double xPosition, double yPosition) {
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()) );
		setFitHeight(HEIGHT);
		setFitWidth(WIDTH);
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}

}