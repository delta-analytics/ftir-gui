package deltaanalytics.gui.user;

import deltaanalytics.gui.FtirGuiElement;
import deltaanalytics.gui.MainViewPane;
import deltaanalytics.gui.ButtonFactory;
import deltaanalytics.user.entity.User;
import deltaanalytics.user.repository.UserRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.time.LocalDateTime;
import java.util.List;

public class UserView  implements FtirGuiElement {
    private TableView<User> tableView;
    private UserRepository userRepository;

    public UserView() {
        userRepository = new UserRepository();
    }

    private List<User> buildDtos() {
        return new UserRepository().findAll();
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
        tableColumn1.setCellValueFactory(new PropertyValueFactory<User, Long>("id"));
        TableColumn tableColumn2 = new TableColumn("Firstname");
        tableColumn2.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
        TableColumn tableColumn3 = new TableColumn("Lastname");
        tableColumn3.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
        TableColumn tableColumn4 = new TableColumn("Account");
        tableColumn4.setCellValueFactory(new PropertyValueFactory<User, String>("account"));
        TableColumn tableColumn5 = new TableColumn("Enabled");
        tableColumn5.setCellValueFactory(new PropertyValueFactory<User, Boolean>("enabled"));
        TableColumn tableColumn6 = new TableColumn("CreatedAt");
        tableColumn6.setCellValueFactory(new PropertyValueFactory<User, LocalDateTime>("createdAt"));


        ObservableList observableSamples = FXCollections.observableList(buildDtos());

        tableView.getColumns().addAll(tableColumn1, tableColumn2, tableColumn3, tableColumn4, tableColumn5, tableColumn6);

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
        gridPane.setHgap(5);
        gridPane.setPadding(new Insets(0, 5, 5, 0));
        Button neu = ButtonFactory.build("Neu");
        neu.setOnAction((event -> {
            new UserCreateOrUpdateDialog().showNeuDialog(this, userRepository, new User());
        }));
        gridPane.add(neu, 0, 0);
        Button aendern = ButtonFactory.build("Bearbeiten");
        aendern.setOnAction((event -> {
            User user = tableView.getSelectionModel().getSelectedItem();
            if (user != null) {
                new UserCreateOrUpdateDialog().showNeuDialog(this, userRepository, user);
            } else {
                Alert info = new Alert(Alert.AlertType.WARNING);
                info.initStyle(StageStyle.UNDECORATED);
                info.initModality(Modality.APPLICATION_MODAL);
                info.setTitle("Kein Nutzer selektiert.");
                info.setHeaderText("Sie müssen den Nutzer selektieren.");
                info.show();
            }
        }));
        gridPane.add(aendern, 1, 0);
        return gridPane;
    }


    @Override
    public void updateUI() {
        tableView.setItems(FXCollections.observableList(buildDtos()));
        tableView.requestLayout();
    }
}
