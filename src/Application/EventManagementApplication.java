package Application;

import Views.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class EventManagementApplication extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Event Management");

        MainView.mainMenu(primaryStage);
    }
}