package deltaanalytics.gui.jueke;

import deltaanalytics.gui.util.FtirGuiElement;
import deltaanalytics.jueke.hardware.CommandRunner;
import deltaanalytics.jueke.hardware.domain.ValveState;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JuekeView implements FtirGuiElement {
    private static final Logger LOGGER = LoggerFactory.getLogger(JuekeView.class);

    private CommandRunner commandRunner;

    @Override
    public void updateUI() {

    }

    @Override
    public GridPane buildContent() {
        GridPane gridPane = new GridPane();
        ToggleButton tb1 = new ToggleButton("Valve1");
        ToggleButton tb2 = new ToggleButton("Valve2");
        ToggleButton tb3 = new ToggleButton("Valve3");
        ToggleButton tb4 = new ToggleButton("Valve4");
        ToggleButton tb5 = new ToggleButton("Valve5");
        ToggleButton tb6 = new ToggleButton("Valve6");
        ToggleButton tb7 = new ToggleButton("Valve7");
        ToggleButton tb8 = new ToggleButton("Valve8");
        HBox hBox = new HBox();
        hBox.getChildren().addAll(tb1, tb2, tb3, tb4, tb5, tb6, tb7, tb8);
        gridPane.add(hBox, 0, 0);
        Button button = new Button("Set Valves");
        button.setOnAction(event -> {
            try {
                commandRunner.setValves(
                        tbToValveState(tb1.isSelected()),
                        tbToValveState(tb2.isSelected()),
                        tbToValveState(tb3.isSelected()),
                        tbToValveState(tb4.isSelected()),
                        tbToValveState(tb5.isSelected()),
                        tbToValveState(tb6.isSelected()),
                        tbToValveState(tb7.isSelected()),
                        tbToValveState(tb8.isSelected()));
            } catch (Exception e) {
                LOGGER.error("", e);
            }
        });
        gridPane.add(new Label("\n"), 0, 1);
        gridPane.add(button, 0, 2);
        return gridPane;
    }

    private ValveState tbToValveState(boolean enabled) {
        if (enabled) {
            return ValveState.ACTIVE;
        } else {
            return ValveState.INACTIVE;
        }
    }

    public void setCommandRunner(CommandRunner commandRunner) {
        this.commandRunner = commandRunner;
    }
}
