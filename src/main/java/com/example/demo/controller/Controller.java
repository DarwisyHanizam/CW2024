package com.example.demo.controller;

import com.example.demo.levels.LevelTemplate;
import com.example.demo.levels.handler.Progression;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Controls the game flow and manages transitions between levels.
 * Implements ActionListener to handle level progression events.
 */
public class Controller implements ActionListener {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.levels.types.LevelOne";
	private LevelTemplate currentLevel;
	private final Stage stage;

	/**
	 * Constructs a new Controller instance.
	 * 
	 * @param stage the primary stage for the application
	 */
	public Controller(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Launches the game by showing the stage and starting the first level.
	 * 
	 * @throws ClassNotFoundException if the level class cannot be found
	 * @throws NoSuchMethodException if the level class constructor is not found
	 * @throws SecurityException if there is a security violation
	 * @throws InstantiationException if the level class cannot be instantiated
	 * @throws IllegalAccessException if the level class or its constructor is not accessible
	 * @throws IllegalArgumentException if the arguments for the level class constructor are invalid
	 * @throws InvocationTargetException if the constructor throws an exception
	 */
	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {

		stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	/**
	 * Transitions to the specified level.
	 * 
	 * @param className the fully qualified name of the level class
	 * @throws ClassNotFoundException if the level class cannot be found
	 * @throws NoSuchMethodException if the level class constructor is not found
	 * @throws SecurityException if there is a security violation
	 * @throws InstantiationException if the level class cannot be instantiated
	 * @throws IllegalAccessException if the level class or its constructor is not accessible
	 * @throws IllegalArgumentException if the arguments for the level class constructor are invalid
	 * @throws InvocationTargetException if the constructor throws an exception
	 */
	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if (getCurrentLevel() != null) {
			currentLevel.getSceneUpdater().removeAllActors();
		}

		Class<?> myClass = Class.forName(className);
		Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
		LevelTemplate myLevel = (LevelTemplate) constructor.newInstance(stage.getHeight(), stage.getWidth());
		Progression progress = myLevel.getProgression();
		progress.addActionListener(this);
		Scene scene = myLevel.getConfiguration().initializeScene();
		stage.setScene(scene);
		progress.startGame();
		setCurrentLevel(myLevel);
	}

	/**
	 * Handles action events for level progression.
	 * 
	 * @param arg the action event
	 */
	@Override
	public void actionPerformed(ActionEvent arg) {
		try {
			goToLevel((String)arg.getActionCommand());
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
			e.printStackTrace();;
		}
	}

	/**
	 * Gets the current level.
	 * 
	 * @return the current level
	 */
	public LevelTemplate getCurrentLevel(){
		return currentLevel;
	}

	/**
	 * Sets the current level.
	 * 
	 * @param level the level to set as current
	 */
	private void setCurrentLevel(LevelTemplate level){
		currentLevel = level;
	}

}