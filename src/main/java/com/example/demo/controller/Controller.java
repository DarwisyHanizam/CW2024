package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.example.demo.levels.handler.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Controller implements ActionListener {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.levels.LevelOne";
	private LevelAbstract currentLevel;
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
			
			if (currentLevel != null) {
				currentLevel.levelScene.removeAllActors();
			}
			
			Class<?> myClass = Class.forName(className);
			Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
			LevelAbstract myLevel = (LevelAbstract) constructor.newInstance(stage.getHeight(), stage.getWidth());
			myLevel.levelControl.addActionListener(this);
			Scene scene = myLevel.levelInitializer.initializeScene();
			stage.setScene(scene);
			myLevel.levelControl.startGame();
			currentLevel = myLevel;

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

}
