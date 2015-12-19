package deltaanalytics.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application {

    public void start(Stage stage) {
        new MainController(stage).show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}
