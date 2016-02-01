package deltaanalytics.gui.mainframe;

import deltaanalytics.gui.first.FirstView;
import deltaanalytics.gui.jueke.JuekeView;
import deltaanalytics.gui.bruker.measuresample.MeasurementSampleView;
import deltaanalytics.gui.user.UserView;
import deltaanalytics.jueke.hardware.CommandRunner;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainController {
    private final JuekeView juekeView;
    private Stage stage;
    private GridPane mainViewGridPane;
    private MainViewPane mainViewPane;
    private MeasurementSampleView measurementSampleView;

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
        measurementSampleView = new MeasurementSampleView();
        deltaanalytics.bruker.hardware.CommandRunner brukerCommandRunner = new deltaanalytics.bruker.hardware.CommandRunner();
        measurementSampleView.setbCommandRunner(brukerCommandRunner);
    }

    public void showFirstView(){
        mainViewPane.setContent(new FirstView().buildContent());
    }

    public void showUserView() {
        mainViewPane.setContent(new UserView().buildContent());
    }

    public void showMeasureSampleView() {
        mainViewPane.setContent(measurementSampleView.buildContent());
    }

    public void showJuekeView() {
        mainViewPane.setContent(juekeView.buildContent());
    }
}
