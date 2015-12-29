package deltaanalytics.gui.mainframe;

import deltaanalytics.gui.util.ButtonFactory;
import deltaanalytics.jueke.data.entity.JuekeStatus;
import deltaanalytics.jueke.hardware.CommandRunner;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainViewPane {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainViewPane.class);
    private MainController mainController;
    private GridPane centerGridPane;
    private Button activeButton;
    private CommandRunner juekeCommandRunner;

    public MainViewPane(MainController mainController) {
        this.mainController = mainController;
        centerGridPane = new GridPane();
    }

    public final GridPane buildComplete() {
        GridPane wholeGridPane = new GridPane();
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(5);
        wholeGridPane.setVgap(10);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(80);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(15);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100);
        wholeGridPane.getRowConstraints().addAll(row1, row2, row3);
        wholeGridPane.getColumnConstraints().add(columnConstraints);

        wholeGridPane.add(buildTopRow(), 0, 0);

        centerGridPane = new GridPane();

        ColumnConstraints columnConstraintsForCenterLeft = new ColumnConstraints();
        columnConstraintsForCenterLeft.setPercentWidth(15);
        ColumnConstraints columnConstraintsForCenterMiddle = new ColumnConstraints();
        columnConstraintsForCenterMiddle.setPercentWidth(70);
        ColumnConstraints columnConstraintsForCenterRight = new ColumnConstraints();
        columnConstraintsForCenterRight.setPercentWidth(15);
        centerGridPane.getColumnConstraints().addAll(columnConstraintsForCenterLeft, columnConstraintsForCenterMiddle, columnConstraintsForCenterRight);
        RowConstraints centerGridPaneRowConstraints = new RowConstraints();
        centerGridPaneRowConstraints.setVgrow(Priority.ALWAYS);
        centerGridPane.getRowConstraints().add(centerGridPaneRowConstraints);
        centerGridPane.add(buildLeftNavigation(), 0, 0);

        wholeGridPane.add(centerGridPane, 0, 1);

        HBox child = null;
        try {
            child = buildBottomRow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        wholeGridPane.add(child, 0, 2);
        return wholeGridPane;
    }

    public void setContent(GridPane content) {
        centerGridPane.getChildren().clear();
        centerGridPane.add(buildLeftNavigation(), 0, 0);
        centerGridPane.add(content, 1, 0);
        if (activeButton != null)
            activeButton.setStyle("-fx-background-color: red;");
    }

    public HBox buildBottomRow() throws Exception {
        HBox hBox = new HBox();
        hBox.setStyle("-fx-background-color:#D0D0D0;");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number, Number> lineChart =
                new LineChart<>(xAxis, yAxis);

        lineChart.setCreateSymbols(false);
        lineChart.setAnimated(false);
        lineChart.setLegendVisible(false);
        xAxis.setForceZeroInRange(false);
        XYChart.Series series1 = new XYChart.Series();
        lineChart.getData().add(series1);
        lineChart.setPadding(new Insets(5));
        Timeline animation = new Timeline();
        final int[] i = {0};
        /*animation.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            try {
                JuekeStatus juekeStatus = juekeCommandRunner.getStatus();
                LOGGER.info(juekeStatus.toString());
                LOGGER.info("=> " + juekeStatus.getActualTempHeater());
                series1.getData().add(new XYChart.Data<Number, Number>(i[0]++, juekeStatus.getTempPT100_1()));
                //series1.getData().add(new XYChart.Data<Number, Number>(i[0]++, Math.random() * 10));
                if (series1.getData().size() > 30) {
                    series1.getData().remove(0);
                }
            } catch (Exception e) {
                LOGGER.error("", e);
            }
        }));*/
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(lineChart);
        return hBox;
    }

    public GridPane buildLeftNavigation() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(60, 0, 0, 0));
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100);
        gridPane.getColumnConstraints().add(columnConstraints);
        Button measurementReferenceButton = ButtonFactory.buildMaxWidthAndCenterLeft("MeasurementReference");
        Button measurementSampleButton = ButtonFactory.buildMaxWidthAndCenterLeft("MeasurementSample");

        measurementSampleButton.setOnAction(event1 -> {
            activeButton = measurementSampleButton;
            mainController.showMeasureSampleView();
        });


        Button juekeCommandsButton = ButtonFactory.buildMaxWidthAndCenterLeft("JuekeCommands");
        juekeCommandsButton.setOnAction(event2 -> {
            activeButton = juekeCommandsButton;
            mainController.showJuekeView();
        });
        Button calibrationButton = ButtonFactory.buildMaxWidthAndCenterLeft("Calibration");
        Button userButton = ButtonFactory.buildMaxWidthAndCenterLeft("Users");
        activeButton = userButton;
        userButton.setOnAction(event1 -> {
            activeButton = userButton;
            mainController.show();
        });
        Button calculationButton = ButtonFactory.buildMaxWidthAndCenterLeft("Calculations");
        Button attendanceButton = ButtonFactory.buildMaxWidthAndCenterLeft("Attendance");
        VBox vButtons = new VBox();
        vButtons.getChildren().addAll(measurementReferenceButton, measurementSampleButton, juekeCommandsButton, calibrationButton,
                userButton, calculationButton, attendanceButton);
        measurementReferenceButton.setOnAction(event -> {
            Thread thread = new Thread(getTask());
            thread.start();
        });
        gridPane.add(vButtons, 0, 0);
        return gridPane;
    }

    private Task<Void> getTask() {
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                //new CommandRunner().measureSample("localhost", 80, BrukerParameters.getDefault());
                Thread.sleep(2000);
                return null;
            }
        };
        task.setOnSucceeded(e -> {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.initStyle(StageStyle.UNDECORATED);
            info.initModality(Modality.NONE);
            info.setTitle("MeasureSample beendet.");
            info.show();
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(5),
                    event -> {
                        info.hide();
                    }));
            timeline.play();
        });
        task.setOnFailed(event -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.initModality(Modality.NONE);
            alert.setTitle("MeasureSample mit Fehler beendet.");
            alert.show();
            String message = task.exceptionProperty().getValue().getMessage();
            alert.setHeaderText(message);

        });
        return task;
    }

    public void setJuekeCommandRunner(CommandRunner juekeCommandRunner) {
        this.juekeCommandRunner = juekeCommandRunner;
    }

    public GridPane buildTopRow() {
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color:#D0D0D0;");
        return gridPane;
    }

}
