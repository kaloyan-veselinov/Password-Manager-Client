package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConnectionSceneBuilder {

	private static Scene scene;
	private static ProgressIndicator spinner;
	private static GridPane grid;

	public static Scene buildScene(Stage primaryStage) {
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Welcome");
		grid.add(scenetitle, 1, 0);
		scenetitle.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		scenetitle.setFill(Color.WHITE);
		Label userName = new Label("User Name : ");
		grid.add(userName, 0, 1);

		TextField userField = new TextField();
		grid.add(userField, 1, 1);

		Label psswd = new Label("Password : ");
		grid.add(psswd, 0, 2);

		PasswordField psswdField = new PasswordField();
		grid.add(psswdField, 1, 2);

		Label hostname = new Label("Hostname : ");
		grid.add(hostname, 0, 3);

		TextField hostnameField = new TextField();
		grid.add(hostnameField, 1, 3);

		Button connect = new Button("Connect");
		grid.add(connect, 1, 4);
		connect.setOnAction(e -> {

			spinner = new ProgressIndicator();

			grid.add(spinner, 0, 4);
			scene = MenuSceneBuilder.buildScene(userField.getText());
			primaryStage.setScene(scene);
		});

		Scene scene = new Scene(grid, 400, 200);
		scene.getStylesheets().add(AppMain.class.getResource("application.css").toExternalForm());
		return scene;
	}

	@FXML
	public Scene menuScene() {
		Scene scene = null;
		try {
			Parent root = FXMLLoader.load(getClass().getResource(("MenuScene.fxml")));
			Image settingsImage = new Image(getClass().getResourceAsStream("settings.png"));
			Button settings = new Button("", new ImageView(settingsImage));
			root.getChildrenUnmodifiable().add(settings);
			scene = new Scene(root, 600, 400);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scene;
	}

}
