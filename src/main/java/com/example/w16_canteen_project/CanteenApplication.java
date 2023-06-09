package com.example.w16_canteen_project;

import Model.Basket;
import Model.Employee;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;

public class CanteenApplication extends Application {

    private static ArrayList<SceneController> scenes = new ArrayList<>();
    private static Stage primaryStageHolder = null;

    static DBDAO db = new DBDAOImpl();

    static Basket basket = new Basket();
    static Employee employee;
    LoginController loginController;
    static MainMenuController mainMenuController;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStageHolder = stage;

        FXMLLoader loginViewFXML = new FXMLLoader(getClass().getResource("LoginView.fxml"));
        Parent loginViewPane = loginViewFXML.load();
        Scene loginScene = new Scene(loginViewPane);
        loginController = loginViewFXML.getController();

        FXMLLoader mainMenuFXML = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent mainMenuViewPane = mainMenuFXML.load();
        Scene mainMenuScene = new Scene(mainMenuViewPane);
        mainMenuController = mainMenuFXML.getController();

        scenes.add(new SceneController(loginScene, ControllerNames.Login));
        scenes.add(new SceneController(mainMenuScene, ControllerNames.MainMenu));

        stage.setTitle("Login Screen");
        stage.setScene(loginScene);
        stage.show();
    }


    public static void changeScene(ControllerNames sceneName) {
        primaryStageHolder.setTitle(sceneName.toString());

        for (SceneController sceneCtrlNamePair : scenes) {
            if (sceneCtrlNamePair.getName().equals(sceneName)) {
                primaryStageHolder.setScene(sceneCtrlNamePair.getScene());
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}