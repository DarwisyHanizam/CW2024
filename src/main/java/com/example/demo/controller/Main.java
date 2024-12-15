package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main entry point for the JavaFX application.
 * Extends the Application class and sets up the primary stage and controller.
 */
public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";
	private Controller myController;
	private Stage stage;

	/**
	 * Starts the JavaFX application.
	 * 
	 * @param stage the primary stage for the application
	 * @throws ClassNotFoundException if the controller class cannot be found
	 * @throws NoSuchMethodException if the controller class constructor is not found
	 * @throws SecurityException if there is a security violation
	 * @throws InstantiationException if the controller class cannot be instantiated
	 * @throws IllegalAccessException if the controller class or its constructor is not accessible
	 * @throws IllegalArgumentException if the arguments for the controller class constructor are invalid
	 * @throws InvocationTargetException if the constructor throws an exception
	 */
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

	/**
	 * The main method that launches the application.
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch();
	}

	/**
	 * Gets the controller for the application.
	 * 
	 * @return the controller
	 */
	public Controller getController() {
		return myController;
	}

	/**
	 * Creates the controller for the application.
	 * 
	 * @param stage the primary stage
	 */
	private void createController(Stage stage) {
		myController = new Controller(stage);
	}

	/**
	 * Gets the primary stage for the application.
	 * 
	 * @return the primary stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * Sets the primary stage for the application.
	 * 
	 * @param stage the primary stage to set
	 */
	private void setStage(Stage stage) {
		this.stage = stage;
	}

}