package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;

import javafx.stage.Stage;

public class MainTest extends ApplicationTest {

	private Main main = new Main();

	@Override
	public void start(Stage stage) throws Exception {
		main.start(stage);
	}

	@Test
	public void testStart() {
		assertNotNull(main.getStage());
		assertEquals("Sky Battle", main.getStage().getTitle());
		assertFalse(main.getStage().isResizable());
		assertEquals(750, main.getStage().getHeight(), 0.5);
		assertEquals(1300, main.getStage().getWidth(), 0.5);
		assertNotNull(main.getController());
	}

}