package Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static Controllers.MainController.login;
import static Controllers.MainController.register;

public class MainView {

    public static void mainMenu(Stage stage) {
        Label welcomeText = new Label("Welcome to the Event Management Software");

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        loginButton.setOnAction(_ -> login(stage));

        registerButton.setOnAction(_ -> register(stage));

        StackPane stackPane = new StackPane();

        GridPane gridpane = new GridPane();
        gridpane.add(loginButton, 0, 0); // column=0 row=0
        GridPane.setMargin(loginButton, new Insets(17));
        gridpane.add(registerButton, 0, 1); // column=0 row=1
        GridPane.setMargin(registerButton, new Insets(10));

        gridpane.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(welcomeText);
        StackPane.setAlignment(welcomeText, Pos.TOP_LEFT);
        stackPane.getChildren().add(gridpane);
        StackPane.setAlignment(gridpane, Pos.CENTER);

        Scene scene = new Scene(stackPane, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
}
