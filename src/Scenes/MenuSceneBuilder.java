package Scenes;

import java.util.LinkedList;

import application.Account;
import application.AppMain;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuSceneBuilder {

	public static Scene buildScene(String userName, Stage primaryStage) {
		BorderPane root = new BorderPane();
		GridPane topBox = new GridPane();
		topBox.setAlignment(Pos.TOP_RIGHT);
		topBox.setPadding(new Insets(15, 12, 15, 12));
		topBox.getChildren().add(new Label(userName));
		root.setTop(topBox);
		root.getTop().maxHeight(100);

		VBox vbox = new VBox();
		TextField textField = new TextField();
		vbox.getChildren().add(textField);
		ScrollPane centerPane = new ScrollPane();
		centerPane.setStyle("-fx-background-color: #097fc3;");

		centerPane.setMaxHeight(250);
		Accordion accordion = new Accordion();
		accordion.setStyle("-fx-background-color: #097fc3;");
		accordion.minWidth(centerPane.getWidth());
		TitledPane[] content = new TitledPane[AppMain.tempNumAccount];

		// TODO fill with real accounts from the database
		for (int i = 0; i < AppMain.tempNumAccount; i++) {
			if (AppMain.accounts.size() < AppMain.tempNumAccount) {
				AppMain.accounts.add(new Account("myUserName", i + ".exemple.com"));
				content[i] = new TitledPane(
						AppMain.accounts.get(AppMain.accounts.size() - 1).getLogin() + " - "
								+ AppMain.accounts.get(AppMain.accounts.size() - 1).getDomain(),
						newAccountPane(primaryStage, AppMain.accounts.get(AppMain.accounts.size() - 1).getLogin(),
								AppMain.accounts.get(AppMain.accounts.size() - 1).getDomain()));

				content[i].setMinWidth(600);
				content[i].setStyle("-fx-text-fill: white;");
			} else {
				content[i] = new TitledPane(
						AppMain.accounts.get(i).getLogin() + " - "
								+ AppMain.accounts.get(i).getDomain(),
						newAccountPane(primaryStage, AppMain.accounts.get(i).getLogin(),
								AppMain.accounts.get(i).getDomain()));

				content[i].setMinWidth(600);
				content[i].setStyle("-fx-text-fill: white;");
			}
		}
		accordion.getPanes().addAll(content);
		centerPane.setContent(accordion);
		vbox.getChildren().add(centerPane);
		root.setCenter(vbox);

		GridPane hbox = new GridPane();
		hbox.setHgap(250);
		hbox.setPadding(new Insets(10, 100, 10, 100));
		Image settingsImage = new Image("settings.png");
		Button settings = new Button("", new ImageView(settingsImage));
		settings.setMinHeight(60);
		settings.setMaxWidth(60);
		settings.setStyle("	-fx-background-color : white; " + "-fx-background-radius : 100em; "
				+ "-fx-text-fill: #097fc3; " + "-fx-font-weight: bold; " + "-fx-font: 20px Verdana; ");

		settings.setOnAction(e -> {
			primaryStage.setScene(SettingsSceneBuilder.buildScene(primaryStage));
		});

		hbox.add(settings, 0, 0);
		Button addAccount = new Button("+");
		addAccount.setMinHeight(60);
		addAccount.setMinWidth(60);
		addAccount.setStyle("	-fx-background-color : white; " + "-fx-background-radius : 100em; "
				+ "-fx-text-fill: #097fc3; " + "-fx-font-weight: bold; " + "-fx-font: 20px Verdana; ");
		hbox.add(addAccount, 1, 0);
		addAccount.setOnAction(e -> {
			primaryStage.setScene(AddAccountSceneBuilder.buildScene(primaryStage));
		});

		textField.setOnKeyTyped(e -> {
			// Used to search elements but it's slow if there are more than 100
			// elements. Needs a better solution.
			// TODO sort by alphabetical order
			String search = textField.getText();
			LinkedList<TitledPane> removed = new LinkedList<TitledPane>();
			boolean done = false;
			while (!done) {
				for (int i = 0; i < accordion.getPanes().size(); i++) {
					if (!(accordion.getPanes().get(i).getText().contains(search))) {
						accordion.getPanes().get(i).setVisible(false);
						removed.add(accordion.getPanes().remove(i));

					} else {
						accordion.getPanes().get(i).setVisible(true);
					}

				}
				done = true;
				for (int i = 0; i < accordion.getPanes().size(); i++) {
					if (!(accordion.getPanes().get(i).getText().contains(search))) {
						done = false;
					}
				}

			}
			accordion.getPanes().addAll(removed);
			removed.clear();
			centerPane.setVvalue(0);

		});

		root.setBottom(hbox);
		Scene scene = new Scene(root, 600, 400);
		scene.getStylesheets().add(AppMain.class.getResource("Menu.css").toExternalForm());
		scene.getStylesheets().add(AppMain.class.getResource("application.css").toExternalForm());
		return scene;
	}

	private static GridPane newAccountPane(Stage primaryStage, String userName, String domain) {
		GridPane pane = new GridPane();
		pane.setStyle("-fx-background-color : #097fc3;");
		pane.setHgap(10);
		Button edit = new Button("Edit");
		pane.add(edit, 0, 0);
		edit.setOnAction(e -> {
			Account account = null;
			for (int i = 0; i < AppMain.accounts.size(); i++) {
				Account testAccount = AppMain.accounts.get(i);
				if (testAccount.getLogin().equals(userName) && testAccount.getDomain().equals(domain)) {
					account = testAccount;
				}
			}
			primaryStage.setScene(EditAccountSceneBuilder.buildScene(primaryStage, account));
		});
		Button login = new Button("Copy User Name");
		pane.add(login, 1, 0);
		Button psswd = new Button("Copy Password");
		pane.add(psswd, 2, 0);
		Button remove = new Button("Remove");
		pane.add(remove, 3, 0);
		remove.setOnAction(e -> {
			Account account = null;
			int index = -1;
			for (int i = 0; i < AppMain.accounts.size(); i++) {
				Account testAccount = AppMain.accounts.get(i);
				if (testAccount.getLogin().equals(userName) && testAccount.getDomain().equals(domain)) {
					account = testAccount;
					index = i;
				}
			}
			if (index >= 0) {
				AppMain.tempNumAccount--;
				AppMain.accounts.remove(index);
			}
			primaryStage.setScene(MenuSceneBuilder.buildScene(AppMain.userName,primaryStage));
		});

		return pane;
	}

}
