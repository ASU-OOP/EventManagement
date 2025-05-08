import java.util.Scanner;

import Views.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EventManagementApplication extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btOK = new Button("OK");
        Scene scene = new Scene(btOK, 200, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Events Bro");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);

        MainView.welcome();
    }
}