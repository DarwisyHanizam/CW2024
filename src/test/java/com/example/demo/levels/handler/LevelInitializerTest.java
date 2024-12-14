package com.example.demo.levels.handler;
import com.example.demo.controller.Main;
import com.example.demo.levels.LevelView;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;

import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class LevelInitializerTest extends ApplicationTest{

	private LevelView levelView;
	private LevelAll levelAll;
	private Main main = new Main();

	@Override
	public void start(Stage stage) throws Exception {
		main.start(stage);
		levelAll = main.getController().getCurrentLevel();
		levelView = levelAll.levelView;
	}

	@Test
	public void testInitializeScene() {
		assertEquals(5, levelView.getHeartDisplay().getContainer().getChildren().size());
	}

	@Test
	public void testInitializeBackground() {
		ImageView background = levelAll.background;
		assertTrue(background.isFocused());
		assertEquals(750, background.getFitHeight(), 0.5);
		assertEquals(1300, background.getFitWidth(), 0.5);
		assertTrue(levelAll.getRoot().getChildren().contains(background));
	}

}