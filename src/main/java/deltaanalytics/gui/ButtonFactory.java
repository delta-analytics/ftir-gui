package deltaanalytics.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class ButtonFactory {
    public static Button build(String text) {
        Button button = new Button(text);
        button.setStyle("fx-border-radius: 10 10 10 10;-fx-background-radius: 10 10 10 10;");
        return button;
    }

    public static Button buildMaxWidthAndCenterLeft(String text){
        Button button = new Button(text);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        return button;
    }
}
