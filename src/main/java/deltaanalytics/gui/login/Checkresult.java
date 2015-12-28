package deltaanalytics.gui.login;

import deltaanalytics.gui.App;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;


/**
 * Created by Willi on 17.12.2015.
 */
public class Checkresult extends Application {
    private Stage nextstage = new Stage();
    private GridPane resultgrid = new GridPane();
    private Scene scene;
    private String fontTyp = new String("Tahoma");
    private int boldKlein = 14;
    private int boldGross = 18;
    private Image i_logo = new Image(getClass()
            .getResource("/logo.jpg").toExternalForm(), 100, 100, false, false);
    private Button weiterbtn;

    private ImageView v_logo = new ImageView(i_logo);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Checkresult.class);

    public void startCheckResult() {
        start(nextstage);
    }

    public void start(Stage nextstage) {
        build(nextstage);
        nextstage.getIcons().add(new Image(App.class.getResourceAsStream("/logo.jpg")));
        nextstage.setFullScreen(false);
        nextstage.setTitle("Delta Analytics");
        nextstage.setScene(scene);
        nextstage.centerOnScreen();
        nextstage.setWidth(600);
        nextstage.setHeight(300);
        nextstage.show();
    }

    public void build(Stage nextstage) {
        resultgrid = new GridPane();
        resultgrid.getColumnConstraints().add(new ColumnConstraints(0)); // column 0
        resultgrid.getColumnConstraints().add(new ColumnConstraints(80)); // column 1
        resultgrid.getColumnConstraints().add(new ColumnConstraints(200)); // column 2
        resultgrid.getColumnConstraints().add(new ColumnConstraints(100)); // column 3
        resultgrid.add(pruefenView(i_logo), 1, 0);
        Label messagelb = new Label();
        messagelb.setText("Message");
        Text messagetxt = new Text();
        resultgrid.add(messagelb, 2, 3);
        resultgrid.add(messagetxt, 3, 3);
        weiterbtn = new Button("Weiter");
        resultgrid.add(weiterbtn, 2, 4);

        scene = new Scene(resultgrid);

        weiterbtn.setOnAction(event ->
                        logger.info("Hier muss wieder die Connecion geprüft werden!")
        );

    }

    private GridPane pruefenView(Image image) {
        GridPane pruefgrid = new GridPane();
        ImageView vlogo;
        Label brukerVerbindunglb = new Label();
        Label juekeVerbindunglb = new Label();
        Label dbchecklb = new Label();


        Text logintopTx = new Text();

        pruefgrid.getColumnConstraints().add(new ColumnConstraints(100)); // column 0
        pruefgrid.getColumnConstraints().add(new ColumnConstraints(180)); // column 1
        pruefgrid.getColumnConstraints().add(new ColumnConstraints(200)); // column 2
        pruefgrid.getColumnConstraints().add(new ColumnConstraints(80)); // column 3
        pruefgrid.setHgap(30);
        pruefgrid.setVgap(15);
        pruefgrid.setPadding(new Insets(10));


        vlogo = new ImageView(image);

        GridPane.setHalignment(vlogo, HPos.LEFT);
        GridPane.setValignment(vlogo, VPos.TOP);


        logintopTx.setText("Error Connection-Report for DITA");

        logintopTx.setFont(Font.font(fontTyp, FontWeight.BOLD, boldGross));
        pruefgrid.add(logintopTx, 1, 0);
        GridPane.setHalignment(logintopTx, HPos.CENTER);
        GridPane.setValignment(logintopTx, VPos.TOP);

        brukerVerbindunglb.setText("Bruker Verbindung");
        brukerVerbindunglb.setFont(Font.font(fontTyp, FontWeight.BOLD, boldKlein));
        pruefgrid.add(brukerVerbindunglb, 1, 1);

        juekeVerbindunglb.setText("Jüke Verbindung");
        juekeVerbindunglb.setFont(Font.font(fontTyp, FontWeight.BOLD, boldKlein));
        pruefgrid.add(juekeVerbindunglb, 1, 2);

        dbchecklb.setText("DatenBank Verbindung");
        dbchecklb.setFont(Font.font(fontTyp, FontWeight.BOLD, boldKlein));
        pruefgrid.add(dbchecklb, 1, 3);


        return pruefgrid;
    }

}

