package com.example.demo.levels.handler;

import com.example.demo.controller.Main;
import com.example.demo.levels.LevelBuilder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.stage.Stage;

public class LevelSceneTest extends ApplicationTest {

	private LevelBuilder levelBuilder;
	private Main main = new Main();

	@Override
	public void start(Stage stage) throws Exception {
		main.start(stage);
		levelBuilder = main.getController().getCurrentLevel();
	}

	@Test
	public void testRemoveAllActors() {
		Platform.runLater(() -> {
			try {
				levelBuilder.getSceneUpdater().removeAllActors();
				assertEquals(0, levelBuilder.getFriendlyUnits().size());
				assertEquals(0, levelBuilder.getEnemyUnits().size());
				assertEquals(0, levelBuilder.getUserProjectiles().size());
				assertEquals(0, levelBuilder.getEnemyProjectiles().size());
				assertTrue(levelBuilder.getTimeline().getStatus() == Animation.Status.STOPPED);
				assertEquals(0, levelBuilder.getRoot().getChildren().size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}