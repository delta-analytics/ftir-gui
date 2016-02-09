package deltaanalytics.gui.login;

import deltaanalytics.gui.mainframe.MainController;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

/**
 * Created by Willi on 05.02.2016.
 */
public class Checkresultview {
    private Stage nextstage = new Stage();
    private GridPane resultgrid = new GridPane();
    private Scene scene;
    private static String fontTyp = new String("Tahoma");
    private int boldKlein = 14;
    private int boldGross = 18;
    private Image i_logo = new Image(getClass()
            .getResource("/logo.jpg").toExternalForm(), 100, 100, false, false);
    private Button weiterbtn;
    private Button errorweiterbnt;

    private ImageView v_logo = new ImageView(i_logo);
    private final Circle circle = new Circle(6, 10, 10);
    private Color farbe;
    private Checken checken;
    private Text messagetxt = new Text();
    private Text brukerVerbTxt = new Text();
    private Text brukerPingTxt = new Text();
    private Text juekeTxt = new Text();
    private Text dbconTxt = new Text();
    private Text brukerGetTxt = new Text();
    private Integer wieoft = 0;
    private Boolean errorvorh = false;

    private static final org.slf4j.Logger logger  = LoggerFactory.getLogger( Checkresultview.class);

    public Checkresultview(Checken checken) {
        this.checken = checken;
    }

    public void startCheckResult() {
        start(nextstage);
    }

    public void start(Stage nextstage) {
            build(nextstage);
            nextstage.getIcons().add(new Image(Checkresultview.class.getResourceAsStream("/logo.jpg")));
            nextstage.setFullScreen(false);
            nextstage.setTitle("Delta Analytics");
            nextstage.setScene(scene);
            nextstage.centerOnScreen();
            nextstage.setWidth(850);
            nextstage.setHeight(450);
            nextstage.show();
    }
    public void build(Stage nextstage) {
        resultgrid    =   new GridPane();
     /*   resultgrid.getColumnConstraints().add(new ColumnConstraints(0)); // column 0
        resultgrid.getColumnConstraints().add(new ColumnConstraints(80)); // column 1
        resultgrid.getColumnConstraints().add(new ColumnConstraints(200)); // column 2
        resultgrid.getColumnConstraints().add(new ColumnConstraints(100)); // column 3
       */
            resultgrid.setHgap(20);
            resultgrid.setVgap(20);
            resultgrid.setPadding(new Insets(10));

            resultgrid.add(pruefenView(i_logo),1,2);

            weiterbtn = new Button("Continue");
            weiterbtn.setFont(Font.font(fontTyp, FontWeight.BOLD,boldKlein));

            resultgrid.add(weiterbtn,1,3);
            resultgrid.setHalignment(weiterbtn, HPos.CENTER);

            errorweiterbnt = new Button("Continue with Connection-Error");
            errorweiterbnt.setFont(Font.font(fontTyp, FontWeight.BOLD,boldKlein));
            resultgrid.add(errorweiterbnt,1,4);
            resultgrid.setHalignment(errorweiterbnt, HPos.CENTER);

            resultgrid.setGridLinesVisible(false);

            scene = new Scene(resultgrid);

            weiterbtn.setOnAction(event -> {
                        try {
                            pruefeVerbJetztOK(nextstage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            );

            errorweiterbnt.setOnAction(event -> {
                        try {
                            new MainController(nextstage).showFirstView();
                            logger.info("Weiter obwohl Connectionn Fehler");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            );
    }

    private void pruefeVerbJetztOK(Stage primaryStage)throws Exception {

            checken.resultermitteln();
            if (checken.isResult()) {
                new MainController(primaryStage).showFirstView();
                logger.info("Anwendung starten");
            } else {
                startCheckResult();
            }
    }

    private GridPane pruefenView(Image image) {

            GridPane    pruefgrid       =   new GridPane();
            ImageView   vlogo;
            Label brukerVerbindunglb  =   new Label();
            Label       juekeVerbindunglb   =   new Label();
            Label       dbchecklb          =   new Label();

            Text logintopTx = new Text();

            pruefgrid.getColumnConstraints().add(new ColumnConstraints(100)); // column 0
            pruefgrid.getColumnConstraints().add(new ColumnConstraints(180)); // column 1
            pruefgrid.getColumnConstraints().add(new ColumnConstraints(30)); // column 2
            pruefgrid.getColumnConstraints().add(new ColumnConstraints(280)); // column 3
            pruefgrid.setHgap(20);
            pruefgrid.setVgap(20);
            pruefgrid.setPadding(new Insets(10));

            vlogo = new ImageView(image);

            GridPane.setHalignment(vlogo, HPos.LEFT);
            GridPane.setValignment(vlogo, VPos.TOP);

            logintopTx.setText("Error Connection-Report for DITA");

            logintopTx.setFont(Font.font(fontTyp, FontWeight.BOLD, boldGross));
            pruefgrid.add(logintopTx, 2, 0);
            GridPane.setHalignment(logintopTx, HPos.CENTER);
            GridPane.setValignment(logintopTx, VPos.TOP);

            brukerVerbindunglb.setText("Bruker Connection");
            brukerVerbindunglb.setFont(Font.font(fontTyp,FontWeight.BOLD,boldKlein));
            pruefgrid.add(brukerVerbindunglb,1,2);

            pruefgrid.add(isConnectCicleColor(checken.isBrukerVerbindung(),"brukerVer"),2,2);
            pruefgrid.add(brukerVerbTxt,3,2);

            juekeVerbindunglb.setText("Jueke Connection");
            juekeVerbindunglb.setFont(Font.font(fontTyp,FontWeight.BOLD,boldKlein));
            pruefgrid.add(juekeVerbindunglb,1,3);

            pruefgrid.add(isConnectCicleColor(checken.isJuekeVerbindung(),"jueke"),2,3);
            pruefgrid.add(juekeTxt,3,3);

            dbchecklb.setText("Database Connection");
            dbchecklb.setFont(Font.font(fontTyp,FontWeight.BOLD,boldKlein));
            pruefgrid.add(dbchecklb,1,4);

            pruefgrid.add(isConnectCicleColor(checken.isDbconnect(),"db"),2,4);
            pruefgrid.add(dbconTxt,3,4);

            Label   messagelb   =   new Label();
            messagelb.setText("Message");
            messagelb = setLabelFont(messagelb,boldKlein);
            pruefgrid.add(messagelb,1,7);
            pruefgrid.add(messagetxt,2,7);
            pruefgrid.setGridLinesVisible(false);

            return pruefgrid;
    }

    public Text getMessagetx(String string) {
            messagetxt = setTextFont(messagetxt,boldKlein);
            switch (string) {
                case "brukerVer"   :brukerVerbTxt.setText("General connecting error" +
                        " to the Brucker-System!");
                    brukerVerbTxt = setTextFont(brukerVerbTxt,boldKlein);
                    break;
                case "brukerGet"   : brukerGetTxt.setText("Brucker LAN connecting error!");
                    brukerGetTxt = setTextFont(brukerGetTxt,boldKlein);
                    break;
                case "brukerping"  : brukerPingTxt.setText("Brucker connecting error!");
                    brukerPingTxt = setTextFont(brukerPingTxt,boldKlein);
                    break;
                case "jueke"        : juekeTxt.setText("Jueke USB connection error!");
                    juekeTxt = setTextFont(juekeTxt,boldKlein);
                    break;
                case "db"           : dbconTxt.setText("Database connection error!");
                    dbconTxt = setTextFont(dbconTxt,boldKlein);
                    break;
                case "info"         : messagetxt.setText("Connection is OK");
                    break;
                case "error"        : messagetxt.setText("Please check the connection");
                    break;
                default:    messagetxt.setText("No Message in switch");
            };
            return messagetxt;
    }


    private  Text setTextFont(Text text, int boldzahl) {
            text.setFont(Font.font(fontTyp,FontWeight.BOLD,boldzahl));
            return text;
    }
    private Label setLabelFont(Label label, int boldzahl) {
            label.setFont(Font.font(fontTyp,FontWeight.BOLD,boldzahl));
            return label;
    }

    public Circle isConnectCicleColor(Boolean connectOk, String string) {
            if (connectOk) {
                farbe   =   Color.GREEN;
            }else {
                farbe   =   Color.RED;
                wieoft++;
            }
            if (wieoft > 0) {
                getMessagetx(string);
                getMessagetx("error");
                errorvorh=true;
                wieoft--;
            } else  {
                if (errorvorh) {
                    getMessagetx("error");
                } else {
                    getMessagetx("info");
                }
            }
            return getCircle(farbe);
    }

    public Circle getCircle(Color color) {
            Circle circle;
            circle = new Circle(6, 10, 10);
            circle.setStroke(Color.BLACK);
            circle.setFill(color);
            circle.setStrokeWidth(2);
            return circle;
    }
}



