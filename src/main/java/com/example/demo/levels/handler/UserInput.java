package com.example.demo.levels.handler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;

public class UserInput {
	private LevelAll levelAll;

    private boolean movingLeft, movingRight, movingUp, movingDown;
	private boolean singleShot = true, rapidShot = true, multiShot = true;
	private long timeLeftPress, timeRightPress, timeUpPress, timeDownPress = 0;
	private static long DELAY = 100;

	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public UserInput(LevelAll levelAll) {
        this.levelAll = levelAll;
    }

	public void inputHandler() {
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
			handleKeyPress();
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
			handleKeyPress();
		});
	}

    private void handleKeyPress(){
        keyPressMovement();
        keyPressShot();
    }

	private void keyPressMovement(){
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
	}

    private void keyPressShot() {
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
