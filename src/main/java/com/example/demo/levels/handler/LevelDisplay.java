package com.example.demo.levels.handler;

import com.example.demo.viewImage.GameOverImage;
import com.example.demo.viewImage.HeartDisplay;
import com.example.demo.viewImage.ShieldImage;
import com.example.demo.viewImage.WinImage;
import javafx.scene.Group;

/**
 * Manages the display elements of the game level, including heart display, win image, game over image, and shield image.
 */
public class LevelDisplay {

	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final int LOSS_IMAGE_X_POSITION = 305;
	private static final int LOSS_IMAGE_Y_POSISITION = 125;
	private static final int SHIELD_IMAGE_X_POSITION = 355;
	private static final int SHIELD_IMAGE_Y_POSITION = 165;
	private final Group root;
	private final HeartDisplay heartDisplay;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final ShieldImage shieldImage;

	/**
	 * Constructs a new LevelDisplay instance.
	 * 
	 * @param root the root group to which display elements are added
	 * @param heartsToDisplay the number of hearts to display
	 */
	public LevelDisplay(Group root, int heartsToDisplay) {
		this.root = root;
		heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		gameOverImage = new GameOverImage(LOSS_IMAGE_X_POSITION, LOSS_IMAGE_Y_POSISITION);
		shieldImage = new ShieldImage(SHIELD_IMAGE_X_POSITION, SHIELD_IMAGE_Y_POSITION);
	}

	/**
	 * Shows the heart display on the screen.
	 */
	void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Shows the win image on the screen.
	 */
	void showWinImage() {
		root.getChildren().add(winImage);
	}

	/**
	 * Shows the game over image on the screen.
	 */
	void showGameOverImage() {
		root.getChildren().add(gameOverImage);
	}

	/**
	 * Shows the shield image on the screen and activates the shield.
	 */
	public void showShieldImage() {
		root.getChildren().add(shieldImage);
		shieldImage.showShield();
	}

	/**
	 * Removes hearts from the heart display based on the number of hearts remaining.
	 * 
	 * @param heartsRemaining the number of hearts remaining
	 */
	void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

	/**
	 * Gets the win image.
	 * 
	 * @return the win image
	 */
	public WinImage getWinImage() {
		return winImage;
	}

	/**
	 * Gets the game over image.
	 * 
	 * @return the game over image
	 */
	public GameOverImage getGameOverImage() {
		return gameOverImage;
	}

	/**
	 * Gets the heart display.
	 * 
	 * @return the heart display
	 */
	public HeartDisplay getHeartDisplay() {
		return heartDisplay;
	}

	/**
	 * Gets the root group.
	 * 
	 * @return the root group
	 */
	public Group getRoot() {
		return root;
	}

	/**
	 * Gets the shield image.
	 * 
	 * @return the shield image
	 */
	public ShieldImage getShieldImage() {
		return shieldImage;
	}

}