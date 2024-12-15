package com.example.demo.levels.handler;

import com.example.demo.viewImage.GameOverImage;
import com.example.demo.viewImage.HeartDisplay;
import com.example.demo.viewImage.ShieldImage;
import com.example.demo.viewImage.WinImage;
import javafx.scene.Group;

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

	public LevelDisplay(Group root, int heartsToDisplay) {
		this.root = root;
		heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		gameOverImage = new GameOverImage(LOSS_IMAGE_X_POSITION, LOSS_IMAGE_Y_POSISITION);
		shieldImage = new ShieldImage(SHIELD_IMAGE_X_POSITION, SHIELD_IMAGE_Y_POSITION);
	}

	void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	void showWinImage() {
		root.getChildren().add(winImage);
	}

	void showGameOverImage() {
		root.getChildren().add(gameOverImage);
	}

	public void showShieldImage() {
		root.getChildren().add(shieldImage);
		shieldImage.showShield();
	}

	void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

	public WinImage getWinImage() {
		return winImage;
	}

	public GameOverImage getGameOverImage() {
		return gameOverImage;
	}

	public HeartDisplay getHeartDisplay() {
		return heartDisplay;
	}

	public Group getRoot() {
		return root;
	}

	public ShieldImage getShieldImage() {
		return shieldImage;
	}
}