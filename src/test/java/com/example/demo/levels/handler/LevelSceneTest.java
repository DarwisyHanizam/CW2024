package com.example.demo.levels.handler;
import com.example.demo.controller.Main;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;

import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.application.Platform;

public class LevelSceneTest extends ApplicationTest {

	private LevelAll levelAll;
	private Main main = new Main();

	@Override
	public void start(Stage stage) throws Exception {
		main.start(stage);
		levelAll = main.getController().getCurrentLevel();
	}

	@Test
	public void testRemoveAllActors() {
		Platform.runLater(() -> {
			try {
				levelAll.levelScene.removeAllActors();
				assertEquals(0, levelAll.friendlyUnits.size());
				assertEquals(0, levelAll.enemyUnits.size());
				assertEquals(0, levelAll.userProjectiles.size());
				assertEquals(0, levelAll.enemyProjectiles.size());
				assertTrue(levelAll.timeline.getStatus() == Animation.Status.STOPPED);
				assertEquals(0, levelAll.root.getChildren().size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}