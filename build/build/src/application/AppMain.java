package application;
	

import java.util.ArrayList;

import Scenes.ConnectionSceneBuilder;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;



public class AppMain extends Application {
	
	public static String userName;
	public static Scene currentMenuScene;
	public static ArrayList<Account> accounts;
	public static int tempNumAccount;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setMinWidth(400);
			primaryStage.setMinHeight(200);
			primaryStage.setOnCloseRequest(e -> {
				try {
					stop();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			Scene connectionScene = ConnectionSceneBuilder.buildScene(primaryStage);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(connectionScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		tempNumAccount = 50;
		accounts = new ArrayList<Account>();
		launch(args);
	}
	
	
}
