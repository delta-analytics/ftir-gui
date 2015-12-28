package deltaanalytics.gui;

import deltaanalytics.gui.login.LoginView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("/logo.jpg")));
        primaryStage.setFullScreen(false);
        primaryStage.setTitle("Delta Analytics");
        primaryStage.setScene(new LoginView().build(primaryStage));
        primaryStage.centerOnScreen();
        primaryStage.sizeToScene();
        primaryStage.show();
    }


}

