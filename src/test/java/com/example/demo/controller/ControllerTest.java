package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;

import javafx.stage.Stage;

public class ControllerTest extends ApplicationTest {

	private Controller controller;
	private Main main = new Main();

	@Override
	public void start(Stage stage) throws Exception {
		main.start(stage);
		controller = main.getController();
	}

	@Test
	public void testLaunchGame() throws Exception {
 		String levelName = "com.example.demo.levels.types.LevelOne";
		assertTrue(main.getStage().isShowing());
		assertNotNull(controller.getCurrentLevel());
		assertEquals(levelName, controller.getCurrentLevel().getClass().getName());
	}

}