package com.example.demo.levels.handler;
import com.example.demo.controller.Main;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;

import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.application.Platform;

public class LevelControlTest extends ApplicationTest {

	private LevelControl levelControl;
	private LevelAll levelAll;
	private Main main = new Main();

	@Override
	public void start(Stage stage) throws Exception {
		main.start(stage);
		levelAll = main.getController().getCurrentLevel();
		levelControl = levelAll.levelControl;
	}

	@Test
	public void testStartGame() {
		assertTrue(levelAll.background.isFocused());
		assertTrue(levelAll.timeline.getStatus() == Animation.Status.RUNNING);
	}

	@Test
	public void testGoToNextLevel() {
		String levelName = "com.example.demo.levels.LevelTwo";
		Platform.runLater(() -> {
			try {
				levelControl.goToNextLevel(levelName);
				assertEquals(levelName, main.getController().getCurrentLevel().getClass().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void testWinGame() {
		Platform.runLater(() -> {
			try {
				levelControl.winGame();
				assertTrue(levelAll.timeline.getStatus() == Animation.Status.STOPPED);
				assertTrue(levelAll.levelView.getWinImage().isVisible());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void testLoseGame() {
		Platform.runLater(() -> {
			try {
				levelControl.loseGame();
				assertTrue(levelAll.timeline.getStatus() == Animation.Status.STOPPED);
				assertTrue(levelAll.levelView.getGameOverImage().isVisible());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void testAddActionListener() {
		int initialSize = levelAll.actionListeners.size();
		levelControl.addActionListener(main.getController());
		assertEquals(initialSize + 1, levelAll.actionListeners.size());
	}

}