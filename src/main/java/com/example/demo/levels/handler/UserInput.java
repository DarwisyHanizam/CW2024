package com.example.demo.levels.handler;

import com.example.demo.actors.friendly.UserPlane;
import com.example.demo.levels.LevelBuilder;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javafx.scene.input.KeyCode;
import javafx.scene.image.ImageView;
import javafx.application.Platform;

public class UserInput {

	private static final long DELAY = 100;
	private static boolean movingLeft = false, movingRight = false;
	private static boolean movingUp = false, movingDown = false;
	private static boolean singleShot = false, rapidShot = false, multiShot = false;
	private static long timeLeftPress = 0, timeRightPress = 0;
	private static long timeUpPress = 0, timeDownPress = 0;
	private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private LevelBuilder levelBuilder;

	public UserInput(LevelBuilder levelBuilder) {
		this.levelBuilder = levelBuilder;
	}

	void inputHandler() {
		ImageView background = levelBuilder.getBackground();

		background.setOnKeyPressed(e -> {
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
				case KeyCode.R:
					Progression progression;
					progression = levelBuilder.getProgression();
					progression.goToNextLevel("com.example.demo.levels.types.LevelOne");
					break;
				case KeyCode.ESCAPE:
					Platform.exit();
					break;
				default:
					break;
			}
			handleKeyPress();
		});

		background.setOnKeyReleased(e -> {
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
		UserPlane user = levelBuilder.getUser();

		if (isMovingLeft() && isMovingRight()) {
			if (getTimePressLeft() > getTimePressRight()) {
				user.moveLeft();
			} else {
				user.moveRight();
			}
		} else {
			if (isMovingLeft()) user.moveLeft();
			if (isMovingRight()) user.moveRight();
		}

		if (isMovingUp() && isMovingDown()) {
			if (getTimePressUp() > getTimePressDown()) {
				user.moveUp();
			} else {
				user.moveDown();
			}
		} else {
			if (isMovingUp()) user.moveUp();
			if (isMovingDown()) user.moveDown();
		}

		if (!isMovingLeft() && !isMovingRight()) {
			user.stopHorizontal();
		}

		if (!isMovingUp() && !isMovingDown()) {
			user.stopVertical();
		}
	}

	private void keyPressShot() {
		ProjectileFactory projectileFactory = levelBuilder.getProjectileFactory();

		if (readySingleShot()) {
			setSingleShot(false);
			projectileFactory.fireProjectile();
		}
		
		if (readyRapidShot()) {
			setRapidShot(false);
			for (int i = 0; i < 3; ++i) {
				scheduler.schedule(() -> {
					Platform.runLater(() -> {
						projectileFactory.fireProjectile();
					});
				}, i * 100, TimeUnit.MILLISECONDS);
			}
		}

		if (readyMultiShot()) {
			setMultiShot(false);
			projectileFactory.fireProjectile(-10);
			projectileFactory.fireProjectile(10);
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