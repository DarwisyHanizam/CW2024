package com.example.demo.levels.handler;
import com.example.demo.actors.friendly.UserPlane;
import com.example.demo.controller.Main;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.image.ImageView;
import javafx.event.EventType;
import javafx.stage.Stage;

public class UserInputTest extends ApplicationTest {

	private KeyEvent leftPress, rightPress, upPress, downPress;
	private KeyEvent leftRelease, rightRelease, upRelease, downRelease;
	private KeyEvent zTap, xTap, cTap;

	private ImageView background;
	private UserInput userInput;
	private LevelAll levelAll;
	private UserPlane user;
	private Main main = new Main();

	@Override
	public void start(Stage stage) throws Exception {
		main.start(stage);
		levelAll = main.getController().getCurrentLevel();
		background = levelAll.background;
		userInput = levelAll.userInput;
		user = levelAll.user;
		simultateKeyPress();
	}

	private KeyEvent createKeyEvent(EventType<KeyEvent> eventType, KeyCode keyType) {
		return new KeyEvent(eventType, "", "", keyType,  false, false, false, false);
	}

	private void simultateKeyPress() {
		rightPress = createKeyEvent(KeyEvent.KEY_PRESSED, KeyCode.RIGHT);
		leftPress = createKeyEvent(KeyEvent.KEY_PRESSED, KeyCode.LEFT);
		downPress = createKeyEvent(KeyEvent.KEY_PRESSED, KeyCode.DOWN);
		upPress = createKeyEvent(KeyEvent.KEY_PRESSED, KeyCode.UP);

		rightRelease = createKeyEvent(KeyEvent.KEY_RELEASED, KeyCode.RIGHT);
		leftRelease = createKeyEvent(KeyEvent.KEY_RELEASED, KeyCode.LEFT);
		downRelease = createKeyEvent(KeyEvent.KEY_RELEASED, KeyCode.DOWN);
		upRelease = createKeyEvent(KeyEvent.KEY_RELEASED, KeyCode.UP);

		zTap = createKeyEvent(KeyEvent.KEY_TYPED, KeyCode.Z);
		xTap = createKeyEvent(KeyEvent.KEY_TYPED, KeyCode.X);
		cTap = createKeyEvent(KeyEvent.KEY_TYPED, KeyCode.C);
	}

	@Test
	public void testSingleMovement() {
		Platform.runLater(() -> {
			try {
				userInput.inputHandler();
				background.requestFocus();

				background.fireEvent(leftPress);
				assertTrue(userInput.isMovingLeft());
				background.fireEvent(leftRelease);
				assertFalse(userInput.isMovingLeft());
				assertTrue(userInput.getTimePressLeft() > 0);

				background.fireEvent(rightPress);
				assertTrue(userInput.isMovingRight());
				background.fireEvent(rightRelease);
				assertFalse(userInput.isMovingRight());
				assertTrue(userInput.getTimePressRight() > 0);

				background.fireEvent(upPress);
				assertTrue(userInput.isMovingUp());
				background.fireEvent(upRelease);
				assertFalse(userInput.isMovingUp());
				assertTrue(userInput.getTimePressUp() > 0);

				background.fireEvent(downPress);
				assertTrue(userInput.isMovingDown());
				background.fireEvent(downRelease);
				assertFalse(userInput.isMovingDown());
				assertTrue(userInput.getTimePressDown() > 0);

				assertFalse(userInput.readyMultiShot());
				background.fireEvent(zTap);
				assertFalse(userInput.readyMultiShot());

				assertFalse(userInput.readyRapidShot());
				background.fireEvent(xTap);
				assertFalse(userInput.readyRapidShot());

				assertFalse(userInput.readySingleShot());
				background.fireEvent(cTap);
				assertFalse(userInput.readySingleShot());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void testDiagonalMovement() {
		Platform.runLater(() -> {
			try {
				userInput.inputHandler();
				background.requestFocus();

				background.fireEvent(upPress);
				background.fireEvent(rightPress);
				assertTrue(userInput.isMovingUp() && userInput.isMovingRight());
				background.fireEvent(upRelease);
				background.fireEvent(rightRelease);
				assertFalse(userInput.isMovingUp() && userInput.isMovingRight());

				background.fireEvent(upPress);
				background.fireEvent(leftPress);
				assertTrue(userInput.isMovingUp() && userInput.isMovingLeft());
				background.fireEvent(upRelease);
				background.fireEvent(leftRelease);
				assertFalse(userInput.isMovingUp() && userInput.isMovingLeft());

				background.fireEvent(downPress);
				background.fireEvent(rightPress);
				assertTrue(userInput.isMovingDown() && userInput.isMovingRight());
				background.fireEvent(downRelease);
				background.fireEvent(rightRelease);
				assertFalse(userInput.isMovingDown() && userInput.isMovingRight());

				background.fireEvent(downPress);
				background.fireEvent(leftPress);
				assertTrue(userInput.isMovingDown() && userInput.isMovingLeft());
				background.fireEvent(downRelease);
				background.fireEvent(leftRelease);
				assertFalse(userInput.isMovingDown() && userInput.isMovingLeft());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void testEdgeCase() {
		Platform.runLater(() -> {
			try {
				userInput.inputHandler();
				background.requestFocus();

				background.fireEvent(rightPress);
				assertTrue(user.isMovingRight());
				background.fireEvent(leftPress);
				assertTrue(user.isMovingLeft());
				background.fireEvent(leftRelease);
				assertFalse(user.isMovingLeft());
				assertTrue(user.isMovingRight());
				background.fireEvent(rightRelease);
				assertFalse(user.isMovingX());

				background.fireEvent(upPress);
				assertTrue(user.isMovingUp());
				background.fireEvent(downPress);
				assertTrue(user.isMovingDown());
				background.fireEvent(downRelease);
				assertFalse(user.isMovingDown());
				assertTrue(user.isMovingUp());
				background.fireEvent(upRelease);
				assertFalse(user.isMovingY());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}