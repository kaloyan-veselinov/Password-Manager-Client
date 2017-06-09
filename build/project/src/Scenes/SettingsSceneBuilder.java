package Scenes;

import application.AppMain;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsSceneBuilder {
	
	public static Scene buildScene(Stage primaryStage){
		BorderPane root = new BorderPane();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setMaxWidth(600);
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.minWidth(600);
		grid.minHeight(100);
		grid.setStyle("-fx-background-color : #097fc3;");
		
		for (int i=0; i<10; i++){
			grid.add(new CheckBox(), 0, i);
			VBox vbox = new VBox();
			vbox.setMinWidth(600);
			Label settingTitle = new Label ("Setting Title");
			Label settingDescription = new Label ("Setting Description ...");
			settingDescription.setStyle("-fx-font-weight: normal;");
			vbox.getChildren().add(settingTitle);
			vbox.getChildren().add(settingDescription);
			vbox.setStyle("-fx-background-color : #097fc3;");
			grid.add(vbox, 1, i);
		}
		scrollPane.setContent(grid);
		root.setCenter(scrollPane);
		Scene scene = new Scene(root,600,400);
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(25, 25, 25, 25));
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(200);
		
		Button cancel = new Button("Cancel");
		hbox.getChildren().add(cancel);
		cancel.setOnAction(e -> {
			primaryStage.setScene(MenuSceneBuilder.buildScene(AppMain.userName, primaryStage));
		});

		Button save = new Button("Save");
		hbox.getChildren().add(save);
		
	
		
		root.setBottom(hbox);
		
		scene.getStylesheets().add(AppMain.class.getResource("application.css").toExternalForm());
		scene.getStylesheets().add(AppMain.class.getResource("settings.css").toExternalForm());

		return scene;
	}

}
