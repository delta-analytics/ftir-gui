package deltaanalytics.gui;

import deltaanalytics.gui.measuresample.MeasurementSampleView;
import deltaanalytics.gui.user.UserView;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainController {
    private Stage stage;
    private GridPane mainViewGridPane;
    private MainViewPane mainViewPane;

    public MainController(Stage stage) {
        this.stage = stage;
        this.mainViewPane = new MainViewPane(this);
        this.mainViewGridPane = mainViewPane.buildComplete();
        stage.setTitle("FTIR");
        stage.setMaximized(true);
        stage.setScene(new Scene(mainViewGridPane));
        stage.show();
    }

    public void show() {
        mainViewPane.setContent(new UserView().buildContent());
    }

    public void showMeasureSampleView() {
        mainViewPane.setContent(new MeasurementSampleView().buildContent());
    }
}
