package Scenes;

import application.AppMain;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NewUserSceneBuilder {
	
	public static Scene buildScene(Stage primaryStage){
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.minWidth(200);
		grid.minHeight(100);
		
		Label userName = new Label("User Name : ");
		grid.add(userName, 0, 0);

		TextField userField = new TextField();
		grid.add(userField, 1, 0);

		Label psswd = new Label("Password : ");
		grid.add(psswd, 0, 1);

		PasswordField psswdField = new PasswordField();
		grid.add(psswdField, 1, 1);
		
		Label psswdConf = new Label("Confirm Password : ");
		grid.add(psswdConf, 0, 2);
		
		PasswordField psswdFieldConf = new PasswordField();
		grid.add(psswdFieldConf, 1, 2);
		
		Button create = new Button("Create Account");
		grid.add(create, 1, 3);
		
		Button cancel = new Button("Cancel");
		grid.add(cancel, 0, 3);
		cancel.setOnAction(e ->{
			primaryStage.setScene(ConnectionSceneBuilder.buildScene(primaryStage));
		});

		
		Scene scene = new Scene(grid, 400, 200);
		scene.getStylesheets().add(AppMain.class.getResource("application.css").toExternalForm());
		return scene;
	}

}
