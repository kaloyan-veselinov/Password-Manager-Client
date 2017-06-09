package application;
	

import Scenes.ConnectionSceneBuilder;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;



public class AppMain extends Application {
	
	public static String userName;
	
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
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			Scene connectionScene = ConnectionSceneBuilder.buildScene(primaryStage);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(connectionScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
