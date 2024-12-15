package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";
	private Controller myController;
	private Stage stage;

	@Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		setStage(stage);
		getStage().setTitle(TITLE);
		getStage().setResizable(false);
		getStage().setHeight(SCREEN_HEIGHT);
		getStage().setWidth(SCREEN_WIDTH);
		createController(getStage());
		getController().launchGame();
	}

	public static void main(String[] args) {
		launch();
	}

	public Controller getController() {
		return myController;
	}

	private void createController(Stage stage) {
		myController = new Controller(stage);
	}

	public Stage getStage() {
		return stage;
	}

	private void setStage(Stage stage) {
		this.stage = stage;
	}

}