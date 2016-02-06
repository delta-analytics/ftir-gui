package deltaanalytics.gui.login;

import deltaanalytics.gui.mainframe.MainController;
import deltaanalytics.user.repository.UserRepository;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginView {
    private UserRepository userRepository = new UserRepository();
    private Scene scene;
    private GridPane gridSene;

    private HBox hbBtn;
    private Button btnlogin;
    private TextField meldungTX;
    private GridPane grid;
    private ImageView v_logo;
    private Image i_logo;
    private Text logintopTx;
    private Label accountLB;
    private Label passwordLB;
    private TextField accountTX;
    private PasswordField password;
    private String fontTyp = new String("Tahoma");
    private int boldKlein = 14;
    private int boldGross = 18;
    private String errormsg1 =
            new String("Account or password is incorrect!");
    private static final Logger logger = LoggerFactory.getLogger(LoginView.class);

    public Scene build(Stage primaryStage) {
        Label meldungLB;

        gridSene = new GridPane();

        gridSene.getColumnConstraints().add(new ColumnConstraints(0)); // column 0
        gridSene.getColumnConstraints().add(new ColumnConstraints(80)); // column 1
        gridSene.getColumnConstraints().add(new ColumnConstraints(280)); // column 2
        gridSene.getColumnConstraints().add(new ColumnConstraints(80)); // column 3

        btnlogin = new Button("LOGIN");

        meldungLB = new Label("Message");
        gridSene.add(meldungLB, 1, 2);
        meldungTX = new TextField();
        meldungLB.setFont(Font.font(fontTyp, FontWeight.BOLD, boldKlein));
        gridSene.add(meldungTX, 2, 2);
        meldungTX.setFont(Font.font(fontTyp, FontWeight.BOLD, boldKlein));
        meldungTX.setEditable(false);

        GridPane.setHalignment(btnlogin, HPos.CENTER);
        GridPane.setValignment(btnlogin, VPos.TOP);

        hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);

        hbBtn.getChildren().add(btnlogin);

        gridSene.setHgap(30);
        gridSene.setVgap(15);
        gridSene.setPadding(new Insets(10));

        gridSene.add(loginView(), 0, 1);
        gridSene.add(btnlogin, 2, 4);

        scene = new Scene(gridSene);


        btnlogin.setOnAction(event -> {
                    try {
                        login_check(primaryStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
		Platform.runLater(() -> accountstart() );

        accountTX.setOnMousePressed(event -> {
            setMeldungsEffekt(accountTX, meldungTX, password);
        });

        password.setOnMousePressed(event -> {
            setMeldungsEffekt(accountTX, meldungTX, password);
        });

	
        return scene;
    }
	
	private void accountstart() {
        accountTX.requestFocus();
        accountTX.setPromptText("account");
    }

    private void setMeldungsEffekt(TextField accountTX
            , TextField meldungTX
            , PasswordField password) {

        accountTX.setEffect(getDropShadow(Color.AQUA, 20));
        meldungTX.setEffect(getDropShadow(Color.BLUE, 0));
        password.setEffect(getDropShadow(Color.AQUA, 0));
        meldungTX.setText("");
    }

    public GridPane loginView() {
        grid = new GridPane();

        grid.setGridLinesVisible(false);

        grid.getColumnConstraints().add(new ColumnConstraints(0)); // column 1
        grid.getColumnConstraints().add(new ColumnConstraints(80)); // column 2
        grid.getColumnConstraints().add(new ColumnConstraints(220)); // column 3
        grid.getColumnConstraints().add(new ColumnConstraints(80));

        grid.setHgap(30);
        grid.setVgap(15);
        grid.setPadding(new Insets(10));

        i_logo = new Image(getClass()
                .getResource("/logo.jpg").toExternalForm(), 100, 100, false, false);

        v_logo = new ImageView(i_logo);

        GridPane.setHalignment(v_logo, HPos.LEFT);
        GridPane.setValignment(v_logo, VPos.TOP);

        logintopTx = new Text();
        logintopTx.setText("LOGIN BY DITA");

        logintopTx.setFont(Font.font(fontTyp, FontWeight.BOLD, boldGross));
        grid.add(logintopTx, 2, 0);
        GridPane.setHalignment(logintopTx, HPos.CENTER);
        GridPane.setValignment(logintopTx, VPos.TOP);

        accountLB = new Label("Account");
        accountLB.setFont(Font.font(fontTyp, FontWeight.BOLD, boldKlein));

        grid.add(accountLB, 1, 2);
        accountTX = new TextField();
        accountTX.setFont(Font.font(fontTyp, FontWeight.BOLD, boldKlein));
        accountTX.setPromptText("Enter account name");
        grid.add(accountTX, 2, 2);

        passwordLB = new Label("Password");
        passwordLB.setFont(Font.font(fontTyp, FontWeight.BOLD, boldKlein));
        grid.add(passwordLB, 1, 3);
        password = new PasswordField();
        password.setPromptText("Enter password");
        password.setFont(Font.font(fontTyp, FontWeight.BOLD, boldKlein));
        grid.add(password, 2, 3);

        return grid;
    }

    public void start(Stage primaryStage) {
        build(primaryStage);
        primaryStage.getIcons().add(new Image(LoginView.class.getResourceAsStream("/logo.jpg")));
        primaryStage.setFullScreen(false);
        primaryStage.setTitle("Delta Analytics");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public void login_check(Stage primaryStage) throws Exception {
        new MainController(primaryStage).showFirstView();
        Checken checken = new Checken();
        if (userRepository.exists(accountTX.getText(), password.getText())) {
            if (checken.resultermitteln() == true) {
                new MainController(primaryStage).showFirstView();
                logger.info("alle verbindungen ok ==> Anwendung starten");
            } else {
                logger.info("Fehlermaske starten");
                primaryStage.close();
                ViewCheckresult chresult = new ViewCheckresult();
                chresult.startCheckResult();
            }
        } else {
            meldungTX.setText(errormsg1);
            DropShadow borderGlow = new DropShadow();
            meldungTX.setEffect(getDropShadow(Color.RED, 60));
        }
    }

    public DropShadow getDropShadow(Color color, int depth) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(0f);
        dropShadow.setOffsetX(0f);
        dropShadow.setColor(color);
        dropShadow.setWidth(depth);
        dropShadow.setHeight(depth);
        return dropShadow;
    }
}

