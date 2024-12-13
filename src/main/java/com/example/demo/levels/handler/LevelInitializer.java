package com.example.demo.levels.handler;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LevelInitializer{

	private LevelAll levelAll;
	private LevelAbstract levelAbstract;

	private boolean movingLeft, movingRight, movingUp, movingDown;
	private boolean singleShot = true, rapidShot = true, multiShot = true;
	private long timeLeftPress, timeRightPress, timeUpPress, timeDownPress = 0;
	private static long DELAY = 100;
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public LevelInitializer(LevelAll levelAll, LevelAbstract levelAbstract){
		this.levelAll = levelAll;
		this.levelAbstract = levelAbstract;
	}

	public Scene initializeScene() {
		initializeBackground();
		levelAbstract.initializeFriendlyUnits();
		levelAll.levelView.showHeartDisplay();
		return levelAll.scene;
	}

	void initializeTimeline() {
		levelAll.timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(levelAll.getDelay()), e -> levelAll.levelScene.updateScene());
		levelAll.timeline.getKeyFrames().add(gameLoop);
	}

	private void initializeBackground() {
		levelAll.background.setFocusTraversable(true);
		levelAll.background.setFitHeight(levelAll.screenHeight);
		levelAll.background.setFitWidth(levelAll.screenWidth);
		updateUserInput();
		levelAll.root.getChildren().add(levelAll.background);
	}

	private void updateUserInput() {
		levelAll.background.setOnKeyPressed(e -> {
			switch (e.getCode()) {
				case KeyCode.LEFT:
					movingLeft = true;
					timeLeftPress = System.currentTimeMillis();
					break;
				case KeyCode.RIGHT:
					movingRight = true;
					timeRightPress = System.currentTimeMillis();
					break;
				case KeyCode.UP:
					movingUp = true;
					timeUpPress = System.currentTimeMillis();
					break;
				case KeyCode.DOWN:
					movingDown = true;
					timeDownPress = System.currentTimeMillis();
					break;
				default:
					break;
			}
			updateMovement();
		});

		levelAll.background.setOnKeyReleased(e -> {
			switch (e.getCode()) {
				case KeyCode.LEFT:
					movingLeft = false;
					timeLeftPress = System.currentTimeMillis() + DELAY;
					break;
				case KeyCode.RIGHT:
					movingRight = false;
					timeRightPress = System.currentTimeMillis() + DELAY;;
					break;
				case KeyCode.UP:
					movingUp = false;
					timeUpPress = System.currentTimeMillis() + DELAY;;
					break;
				case KeyCode.DOWN:
					movingDown = false;
					timeDownPress = System.currentTimeMillis() + DELAY;;
					break;
				case KeyCode.Z:
					multiShot = false;
					break;
				case KeyCode.X:
					rapidShot = false;
					break;
				case KeyCode.C:
					singleShot = false;
					break;
				default:
					break;
			}
			updateMovement();
		});
	}

	private void updateMovement(){
		if (movingLeft && movingRight) {
			if (timeLeftPress > timeRightPress) {
				levelAll.user.moveLeft();
			} else {
				levelAll.user.moveRight();
			}
		} else {
			if (movingLeft) levelAll.user.moveLeft();
			if (movingRight) levelAll.user.moveRight();
		}

		if (movingUp && movingDown) {
			if (timeUpPress > timeDownPress) {
				levelAll.user.moveUp();
			} else {
				levelAll.user.moveDown();
			}
		} else {
			if (movingUp) levelAll.user.moveUp();
			if (movingDown) levelAll.user.moveDown();
		}

		if (!movingLeft && !movingRight) {
			levelAll.user.stop("Horizontal");
		}

		if (!movingUp && !movingDown) {
			levelAll.user.stop("Vertical");
		}

		if (!singleShot) {
			singleShot = true;
			levelAll.levelMechanics.fireProjectile();
		}
		
		if (!rapidShot) {
			rapidShot = true;
			for (int i = 0; i < 3; ++i) {
				scheduler.schedule(() -> {
					Platform.runLater(() -> {
						levelAll.levelMechanics.fireProjectile();
					});
				}, i * 100, TimeUnit.MILLISECONDS);
			}
		}

		if (!multiShot) {
			multiShot = true;
			levelAll.levelMechanics.fireProjectile(-10);
			levelAll.levelMechanics.fireProjectile(10);
		}
	}
}