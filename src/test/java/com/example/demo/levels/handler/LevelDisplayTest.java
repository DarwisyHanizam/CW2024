package com.example.demo.levels.handler;

import com.example.demo.controller.Main;
import com.example.demo.levels.LevelBuilder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.stage.Stage;

public class LevelDisplayTest extends ApplicationTest {

	private LevelDisplay LevelDisplay;
	private LevelBuilder levelBuilder;
	private Group root;
	private Main main = new Main();

	@Override
	public void start(Stage stage) throws Exception {
		main.start(stage);
		levelBuilder = main.getController().getCurrentLevel();
		LevelDisplay = levelBuilder.getLevelDisplay();
		root = LevelDisplay.getRoot();
	}

	@Test
	public void testShowHeartDisplay() {
		Platform.runLater(() -> {
			try {
				LevelDisplay.showHeartDisplay();
				assertTrue(root.getChildren().contains(LevelDisplay.getHeartDisplay().getContainer()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void testShowWinImage() {
		Platform.runLater(() -> {
			try {
				LevelDisplay.showWinImage();
				assertTrue(root.getChildren().contains(LevelDisplay.getWinImage()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void testShowGameOverImage() {
		Platform.runLater(() -> {
			try {
				LevelDisplay.showGameOverImage();
				assertTrue(root.getChildren().contains(LevelDisplay.getGameOverImage()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}