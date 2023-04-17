package com.example.w16_canteen_project;


import javafx.scene.Scene;

public class SceneController {

    private Scene scene;
    private ControllerNames name;

    public Scene getScene() {
        return scene;
    }

    public ControllerNames getName() {
        return name;
    }

    public SceneController(Scene scene, ControllerNames name) {
        this.scene = scene;
        this.name = name;
    }
}

