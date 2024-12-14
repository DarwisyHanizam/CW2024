package com.example.demo.levels.handler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;

public class UserInput {
	private LevelAll levelAll;

    private boolean movingLeft = false, movingRight = false;
    private boolean movingUp = false, movingDown = false;
	private boolean singleShot = false, rapidShot = false, multiShot = false;
	private long timeLeftPress = 0, timeRightPress = 0;
    private long timeUpPress = 0, timeDownPress = 0;
	private static long DELAY = 100;

	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public UserInput(LevelAll levelAll) {
        this.levelAll = levelAll;
    }

	public void inputHandler() {
		levelAll.background.setOnKeyPressed(e -> {
			switch (e.getCode()) {
				case KeyCode.LEFT:
					setMovingLeft(true);
					setTimePressLeft();
					break;
				case KeyCode.RIGHT:
					setMovingRight(true);
					setTimePressRight();
					break;
				case KeyCode.UP:
					setMovingUp(true);
					setTimePressUp();
					break;
				case KeyCode.DOWN:
					setMovingDown(true);
					setTimePressDown();
					break;
				default:
					break;
			}
			handleKeyPress();
		});

		levelAll.background.setOnKeyReleased(e -> {
			switch (e.getCode()) {
				case KeyCode.LEFT:
					setMovingLeft(false);
					setTimePressLeft(DELAY);
					break;
				case KeyCode.RIGHT:
					setMovingRight(false);
					setTimePressRight(DELAY);
					break;
				case KeyCode.UP:
					setMovingUp(false);
					setTimePressUp(DELAY);
					break;
				case KeyCode.DOWN:
					setMovingDown(false);
					setTimePressDown(DELAY);
					break;
				case KeyCode.Z:
					setMultiShot(true);
					break;
				case KeyCode.X:
					setRapidShot(true);
					break;
				case KeyCode.C:
					setSingleShot(true);
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
		if (isMovingLeft() && isMovingRight()) {
			if (getTimePressLeft() > getTimePressRight()) {
				levelAll.user.moveLeft();
			} else {
				levelAll.user.moveRight();
			}
		} else {
			if (isMovingLeft()) levelAll.user.moveLeft();
			if (isMovingRight()) levelAll.user.moveRight();
		}

		if (isMovingUp() && isMovingDown()) {
			if (getTimePressUp() > getTimePressDown()) {
				levelAll.user.moveUp();
			} else {
				levelAll.user.moveDown();
			}
		} else {
			if (isMovingUp()) levelAll.user.moveUp();
			if (isMovingDown()) levelAll.user.moveDown();
		}

		if (!isMovingLeft() && !isMovingRight()) {
			levelAll.user.stop("Horizontal");
		}

		if (!isMovingUp() && !isMovingDown()) {
			levelAll.user.stop("Vertical");
		}
	}

    private void keyPressShot() {
        if (readySingleShot()) {
			setSingleShot(false);
			levelAll.levelMechanics.fireProjectile();
		}
		
		if (readyRapidShot()) {
			setRapidShot(false);
			for (int i = 0; i < 3; ++i) {
				scheduler.schedule(() -> {
					Platform.runLater(() -> {
						levelAll.levelMechanics.fireProjectile();
					});
				}, i * 100, TimeUnit.MILLISECONDS);
			}
		}

		if (readyMultiShot()) {
			setMultiShot(false);
			levelAll.levelMechanics.fireProjectile(-10);
			levelAll.levelMechanics.fireProjectile(10);
		}
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    private void setMovingLeft(boolean state) {
        movingLeft = state;
    }

	public long getTimePressLeft() {
		return timeLeftPress;
	}

	private void setTimePressLeft() {
		timeLeftPress = System.currentTimeMillis();
	}

	private void setTimePressLeft(long delay) {
		timeLeftPress = System.currentTimeMillis() + delay;
	}

    public boolean isMovingRight() {
        return movingRight;
    }

    private void setMovingRight(boolean state) {
        movingRight = state;
    }

	public long getTimePressRight() {
		return timeRightPress;
	}

	private void setTimePressRight() {
		timeRightPress = System.currentTimeMillis();
	}

	private void setTimePressRight(long delay) {
		timeRightPress = System.currentTimeMillis() + delay;
	}

    public boolean isMovingUp() {
        return movingUp;
    }

    private void setMovingUp(boolean state) {
        movingUp = state;
    }

	public long getTimePressUp() {
		return timeUpPress;
	}

	private void setTimePressUp() {
		timeUpPress = System.currentTimeMillis();
	}

	private void setTimePressUp(long delay) {
		timeUpPress = System.currentTimeMillis() + delay;
	}

    public boolean isMovingDown() {
        return movingDown;
    }

    private void setMovingDown(boolean state) {
        movingDown = state;
    }

	public long getTimePressDown() {
		return timeDownPress;
	}

	private void setTimePressDown() {
		timeDownPress = System.currentTimeMillis();
	}

	private void setTimePressDown(long delay) {
		timeDownPress = System.currentTimeMillis() + delay;
	}

    public boolean readySingleShot() {
        return singleShot;
    }

    private void setSingleShot(boolean state) {
        singleShot = state;
    }

    public boolean readyRapidShot() {
        return rapidShot;
    }

    private void setRapidShot(boolean state) {
        rapidShot = state;
    }

    public boolean readyMultiShot() {
        return multiShot;
    }

    private void setMultiShot(boolean state) {
        multiShot = state;
    }
}