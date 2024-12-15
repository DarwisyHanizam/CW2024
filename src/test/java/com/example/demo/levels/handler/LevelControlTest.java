package com.example.demo.levels.handler;

import com.example.demo.controller.Main;
import com.example.demo.levels.LevelBuilder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;

import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.application.Platform;

public class LevelControlTest extends ApplicationTest {

	private Progression progression;
	private LevelBuilder levelBuilder;
	private Main main = new Main();

	@Override
	public void start(Stage stage) throws Exception {
		main.start(stage);
		levelBuilder = main.getController().getCurrentLevel();
		progression = levelBuilder.getProgression();
	}

	@Test
	public void testStartGame() {
		assertTrue(levelBuilder.getBackground().isFocused());
		assertTrue(levelBuilder.getTimeline().getStatus() == Animation.Status.RUNNING);
	}

	@Test
	public void testGoToNextLevel() {
		String levelLocation = "com.example.demo.levels.types.";
		String levelName = "LevelTwo";
		Platform.runLater(() -> {
			try {
				progression.goToNextLevel(levelName);
				assertEquals(levelLocation + levelName, main.getController().getCurrentLevel().getClass().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void testWinGame() {
		Platform.runLater(() -> {
			try {
				progression.winGame();
				assertTrue(levelBuilder.getTimeline().getStatus() == Animation.Status.STOPPED);
				assertTrue(levelBuilder.getLevelDisplay().getWinImage().isVisible());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void testLoseGame() {
		Platform.runLater(() -> {
			try {
				progression.loseGame();
				assertTrue(levelBuilder.getTimeline().getStatus() == Animation.Status.STOPPED);
				assertTrue(levelBuilder.getLevelDisplay().getGameOverImage().isVisible());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void testAddActionListener() {
		int initialSize = levelBuilder.getActionListeners().size();
		progression.addActionListener(main.getController());
		assertEquals(initialSize + 1, levelBuilder.getActionListeners().size());
	}

}