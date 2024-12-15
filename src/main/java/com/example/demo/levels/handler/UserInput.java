package com.example.demo.levels.handler;

import com.example.demo.actors.friendly.UserPlane;
import com.example.demo.levels.LevelBuilder;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javafx.scene.input.KeyCode;
import javafx.scene.image.ImageView;
import javafx.application.Platform;

/**
 * Handles user input for controlling the game.
 */
public class UserInput {

	private static final long DELAY = 100;
	private static boolean movingLeft = false, movingRight = false;
	private static boolean movingUp = false, movingDown = false;
	private static boolean singleShot = false, rapidShot = false, multiShot = false;
	private static long timeLeftPress = 0, timeRightPress = 0;
	private static long timeUpPress = 0, timeDownPress = 0;
	private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private LevelBuilder levelBuilder;

	/**
	 * Constructs a UserInput handler with the specified LevelBuilder.
	 *
	 * @param levelBuilder the LevelBuilder to be used
	 */
	public UserInput(LevelBuilder levelBuilder) {
		this.levelBuilder = levelBuilder;
	}

	/**
	 * Initializes the input handler for the game.
	 */
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
					progression.goToNextLevel("LevelOne");
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

	/**
	 * Handles key press events for movement and shooting.
	 */
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

	/**
	 * Handles key press events for shooting.
	 */
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

	/**
	 * Checks if the user is moving left.
	 *
	 * @return true if the user is moving left, false otherwise
	 */
	public boolean isMovingLeft() {
		return movingLeft;
	}

	/**
	 * Sets the moving left state.
	 *
	 * @param state the new state
	 */
	private void setMovingLeft(boolean state) {
		movingLeft = state;
	}

	/**
	 * Gets the time the left key was pressed.
	 *
	 * @return the time the left key was pressed
	 */
	public long getTimePressLeft() {
		return timeLeftPress;
	}

	/**
	 * Sets the time the left key was pressed.
	 */
	private void setTimePressLeft() {
		timeLeftPress = System.currentTimeMillis();
	}

	/**
	 * Sets the time the left key was pressed with a delay.
	 *
	 * @param delay the delay in milliseconds
	 */
	private void setTimePressLeft(long delay) {
		timeLeftPress = System.currentTimeMillis() + delay;
	}

	/**
	 * Checks if the user is moving right.
	 *
	 * @return true if the user is moving right, false otherwise
	 */
	public boolean isMovingRight() {
		return movingRight;
	}

	/**
	 * Sets the moving right state.
	 *
	 * @param state the new state
	 */
	private void setMovingRight(boolean state) {
		movingRight = state;
	}

	/**
	 * Gets the time the right key was pressed.
	 *
	 * @return the time the right key was pressed
	 */
	public long getTimePressRight() {
		return timeRightPress;
	}

	/**
	 * Sets the time the right key was pressed.
	 */
	private void setTimePressRight() {
		timeRightPress = System.currentTimeMillis();
	}

	/**
	 * Sets the time the right key was pressed with a delay.
	 *
	 * @param delay the delay in milliseconds
	 */
	private void setTimePressRight(long delay) {
		timeRightPress = System.currentTimeMillis() + delay;
	}

	/**
	 * Checks if the user is moving up.
	 *
	 * @return true if the user is moving up, false otherwise
	 */
	public boolean isMovingUp() {
		return movingUp;
	}

	/**
	 * Sets the moving up state.
	 *
	 * @param state the new state
	 */
	private void setMovingUp(boolean state) {
		movingUp = state;
	}

	/**
	 * Gets the time the up key was pressed.
	 *
	 * @return the time the up key was pressed
	 */
	public long getTimePressUp() {
		return timeUpPress;
	}

	/**
	 * Sets the time the up key was pressed.
	 */
	private void setTimePressUp() {
		timeUpPress = System.currentTimeMillis();
	}

	/**
	 * Sets the time when the up key was pressed with a delay.
	 *
	 * @param delay the delay in milliseconds
	 */
	private void setTimePressUp(long delay) {
		timeUpPress = System.currentTimeMillis() + delay;
	}

	/**
	 * Checks if the down key is being pressed.
	 *
	 * @return true if the down key is being pressed, false otherwise
	 */
	public boolean isMovingDown() {
		return movingDown;
	}

	/**
	 * Sets the state of the down key press.
	 *
	 * @param state the state of the down key press
	 */
	private void setMovingDown(boolean state) {
		movingDown = state;
	}

	/**
	 * Gets the time when the down key was pressed.
	 *
	 * @return the time when the down key was pressed
	 */
	public long getTimePressDown() {
		return timeDownPress;
	}

	/**
	 * Sets the time when the down key was pressed.
	 */
	private void setTimePressDown() {
		timeDownPress = System.currentTimeMillis();
	}

	/**
	 * Sets the time when the down key was pressed with a delay.
	 *
	 * @param delay the delay in milliseconds
	 */
	private void setTimePressDown(long delay) {
		timeDownPress = System.currentTimeMillis() + delay;
	}

	/**
	 * Checks if the single shot is ready to be fired.
	 *
	 * @return true if the single shot is ready, false otherwise
	 */
	public boolean readySingleShot() {
		return singleShot;
	}

	/**
	 * Sets the state of the single shot.
	 *
	 * @param state the state of the single shot
	 */
	private void setSingleShot(boolean state) {
		singleShot = state;
	}

	/**
	 * Checks if the rapid shot is ready to be fired.
	 *
	 * @return true if the rapid shot is ready, false otherwise
	 */
	public boolean readyRapidShot() {
		return rapidShot;
	}

	/**
	 * Sets the state of the rapid shot.
	 *
	 * @param state the state of the rapid shot
	 */
	private void setRapidShot(boolean state) {
		rapidShot = state;
	}

	/**
	 * Checks if the multi shot is ready to be fired.
	 *
	 * @return true if the multi shot is ready, false otherwise
	 */
	public boolean readyMultiShot() {
		return multiShot;
	}

	/**
	 * Sets the state of the multi shot.
	 *
	 * @param state the state of the multi shot
	 */
	private void setMultiShot(boolean state) {
		multiShot = state;
	}

}