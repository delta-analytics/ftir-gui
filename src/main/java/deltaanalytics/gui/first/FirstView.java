package deltaanalytics.gui.first;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class FirstView {
    public GridPane buildContent() {
        GridPane content = new GridPane();
        content.setStyle("-fx-background-color: whitesmoke");
        Label label = new Label("Welcome at FTIR.");
        label.setFont(new Font("Arial", 30));
        VBox vbox = new VBox(8); // spacing = 8
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getChildren().addAll(label, new Label("Options"));
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
        //content.add(buildContentHeader(), 0, 0);
        content.add(vbox, 0, 1);
        //content.add(buildContentFooter(), 0, 2);
        return content;
    }
}
