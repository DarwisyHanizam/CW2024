module com.example.demo {
	requires transitive javafx.graphics;
	requires transitive java.desktop;
	requires javafx.controls;
	requires javafx.fxml;

	exports com.example.demo.actors;
	exports com.example.demo.actors.friendly;
	exports com.example.demo.controller;
	exports com.example.demo.levels;
	exports com.example.demo.levels.handler;
	exports com.example.demo.viewImage;
}