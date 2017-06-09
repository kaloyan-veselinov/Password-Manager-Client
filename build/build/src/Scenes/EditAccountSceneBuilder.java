package Scenes;

import application.Account;
import application.AppMain;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditAccountSceneBuilder {
	
	public static Scene buildScene(Stage primaryStage,Account account){
		BorderPane root = new BorderPane();
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Label userLabel = new Label ("UserName : ");
		grid.add(userLabel, 0, 0);
		TextField userField = new TextField(account.getLogin());
		grid.add(userField, 1, 0);
		
		Label domainLabel = new Label("Domain : ");
		grid.add(domainLabel, 0, 1);
		TextField domainField = new TextField(account.getDomain());
		grid.add(domainField, 1, 1);
		
		Button cancel = new Button("Cancel");
		grid.add(cancel, 0, 4);
		cancel.setOnAction(e -> {
			primaryStage.setScene(MenuSceneBuilder.buildScene(AppMain.userName, primaryStage));
		});
		
		Button save = new Button ("Save");
		grid.add(save, 1, 4);
		save.setOnAction(e -> {
			account.setLogin(userField.getText());
			account.setDomain(domainField.getText());
			primaryStage.setScene(MenuSceneBuilder.buildScene(AppMain.userName, primaryStage));
		});
		
		
		root.setCenter(grid);
		Scene scene = new Scene (root,600,400);
		scene.getStylesheets().add(AppMain.class.getResource("application.css").toExternalForm());

		return scene;
	}

}
