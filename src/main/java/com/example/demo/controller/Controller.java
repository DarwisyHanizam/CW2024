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

public class Controller implements ActionListener {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.levels.types.LevelOne";
	private LevelTemplate currentLevel;
	private final Stage stage;

	public Controller(Stage stage) {
		this.stage = stage;
	}

	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {

		stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}

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

	public LevelTemplate getCurrentLevel(){
		return currentLevel;
	}

	private void setCurrentLevel(LevelTemplate level){
		currentLevel = level;
	}

}