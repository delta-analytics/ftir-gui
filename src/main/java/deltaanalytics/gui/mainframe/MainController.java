package deltaanalytics.gui.mainframe;

import deltaanalytics.gui.first.FirstView;
import deltaanalytics.gui.jueke.JuekeView;
import deltaanalytics.gui.measuresample.MeasurementSampleView;
import deltaanalytics.gui.user.UserView;
import deltaanalytics.jueke.hardware.CommandRunner;
import deltaanalytics.jueke.hardware.serial.JuekeSerialConnectionFactory;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainController {
    private final JuekeView juekeView;
    private Stage stage;
    private GridPane mainViewGridPane;
    private MainViewPane mainViewPane;

    public MainController(Stage stage) throws Exception {
        this.stage = stage;
        this.mainViewPane = new MainViewPane(this);
        //COM1  or /dev/tty.usbserial-J0000031
//        JuekeSerialConnectionFactory.establishConnection("COM1");
        CommandRunner commandRunner = new CommandRunner();
        mainViewPane.setJuekeCommandRunner(commandRunner);

        this.mainViewGridPane = mainViewPane.buildComplete();
        stage.setTitle("FTIR");
        stage.getScene().setRoot(mainViewGridPane);
        stage.setMaximized(true);
        stage.show();
        juekeView = new JuekeView();
        juekeView.setCommandRunner(commandRunner);
    }

    public void showFirstView(){
        mainViewPane.setContent(new FirstView().buildContent());
    }

    public void showUserView() {
        mainViewPane.setContent(new UserView().buildContent());
    }

    public void showMeasureSampleView() {
        mainViewPane.setContent(new MeasurementSampleView().buildContent());
    }

    public void showJuekeView() {
        mainViewPane.setContent(juekeView.buildContent());
    }
}
