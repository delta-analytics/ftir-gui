package deltaanalytics.gui.bruker.measuresample;

import deltaanalytics.bruker.data.entity.MeasureSample;
import deltaanalytics.bruker.data.repository.MeasureSampleRepository;
import deltaanalytics.bruker.hardware.dto.MeasureSampleDto;
import deltaanalytics.gui.util.FtirGuiElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MeasurementSampleView implements FtirGuiElement {
    private TableView<MeasureSampleDto> tableView;

    @Override
    public void updateUI() {
        ObservableList observableSamples = FXCollections.observableList(buildDtos());
        tableView.setItems(observableSamples);
    }

    private List<MeasureSampleDto> buildDtos() {
        List<MeasureSampleDto> measureSampleDtos = new ArrayList<>();
        List<MeasureSample> measureSamples = new MeasureSampleRepository().findAll();
        for (MeasureSample measureSample : measureSamples) {
            measureSampleDtos.add(new MeasureSampleDto(measureSample));
        }
        return measureSampleDtos;
    }

    public GridPane buildContent() {
        GridPane content = new GridPane();

        RowConstraints contentRow1 = new RowConstraints();
        contentRow1.setPercentHeight(5);
        RowConstraints contentRow2 = new RowConstraints();
        contentRow2.setPercentHeight(90);
        RowConstraints contentRow3 = new RowConstraints();
        contentRow3.setPercentHeight(5);
        content.setGridLinesVisible(false);
        content.getRowConstraints().addAll(contentRow1, contentRow2, contentRow3);
        ColumnConstraints contentColumn = new ColumnConstraints();
        contentColumn.setPercentWidth(100);
        contentColumn.setHgrow(Priority.ALWAYS);
        content.getColumnConstraints().add(contentColumn);
        content.add(buildContentHeader(), 0, 0);
        content.add(buildContentCenter(), 0, 1);
        content.add(buildContentFooter(), 0, 2);
        return content;
    }

    public GridPane buildContentCenter() {
        GridPane gridPane = new GridPane();

        tableView = new TableView();

        TableColumn tableColumn1 = new TableColumn("ID");
        tableColumn1.setCellValueFactory(new PropertyValueFactory<MeasureSampleDto, Long>("id"));
        TableColumn tableColumn2 = new TableColumn("StartedAt");
        tableColumn2.setCellValueFactory(new PropertyValueFactory<MeasureSampleDto, LocalDateTime>("createdAt"));
        TableColumn tableColumn3 = new TableColumn("FinishedAt");
        tableColumn3.setCellValueFactory(new PropertyValueFactory<MeasureSampleDto, LocalDateTime>("finishedAt"));
        TableColumn tableColumn4 = new TableColumn("State");
        tableColumn4.setCellValueFactory(new PropertyValueFactory<MeasureSampleDto, LocalDateTime>("brukerStateEnum"));
        TableColumn tableColumn5 = new TableColumn("Error");

        ObservableList observableSamples = FXCollections.observableList(buildDtos());

        tableView.getColumns().addAll(tableColumn1, tableColumn2, tableColumn3, tableColumn4,
                tableColumn5);

        tableView.setItems(observableSamples);
        ColumnConstraints contentColumn = new ColumnConstraints();
        contentColumn.setPercentWidth(100);
        contentColumn.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().add(contentColumn);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(100);
        gridPane.getRowConstraints().add(row1);
        gridPane.add(tableView, 0, 0);
        return gridPane;
    }

    public HBox buildContentFooter() {
        HBox hBox = new HBox();
        hBox.getChildren().addAll(new Button("1"), new TextField("SEARCH"));
        return hBox;
    }

    public GridPane buildContentHeader() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(0, 5, 5, 0));
        Button startBtn = new Button("Start new Measurement");
        startBtn.setOnAction(event -> {
            new StartMeasurementDialog().showNeuDialog();
        });
        gridPane.add(startBtn, 0, 0);
        return gridPane;
    }
}
