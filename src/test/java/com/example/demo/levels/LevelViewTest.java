package com.example.demo.levels;
import com.example.demo.controller.Main;
import com.example.demo.levels.handler.LevelAll;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.stage.Stage;

public class LevelViewTest extends ApplicationTest {

	private LevelView levelView;
	private LevelAll levelAll;
	private Group root;
	private Main main = new Main();

	@Override
	public void start(Stage stage) throws Exception {
		main.start(stage);
		levelAll = main.getController().getCurrentLevel();
		levelView = levelAll.getLevelView();
		root = levelView.getRoot();
	}

	@Test
	public void testShowHeartDisplay() {
		Platform.runLater(() -> {
			try {
				levelView.showHeartDisplay();
				assertTrue(root.getChildren().contains(levelView.getHeartDisplay().getContainer()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void testShowWinImage() {
		Platform.runLater(() -> {
			try {
				levelView.showWinImage();
				assertTrue(root.getChildren().contains(levelView.getWinImage()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void testShowGameOverImage() {
		Platform.runLater(() -> {
			try {
				levelView.showGameOverImage();
				assertTrue(root.getChildren().contains(levelView.getGameOverImage()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}