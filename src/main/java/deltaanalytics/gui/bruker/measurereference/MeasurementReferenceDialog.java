package deltaanalytics.gui.bruker.measurereference;

import deltaanalytics.bruker.data.entity.BrukerParameters;
import deltaanalytics.bruker.hardware.CommandRunner;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeasurementReferenceDialog {
    private static final Logger LOGGER = LoggerFactory.getLogger(MeasurementReferenceDialog.class);
    private final CommandRunner commandRunner;
    private BrukerParameters brukerParameters = BrukerParameters.getDefault();
    private Tab overviewTab;
    private Tab hardwareTab;
    private Tab calculationTab;
    private Tab experimentTab;
    private Tab saveTab;
    Dialog<Void> dialog;
    private TextField sampleNameTf;
    private TextField textFieldAQM;
    private TextField textFieldAPT;
    private TextField textFieldBMS;
    private TextField textFieldLFQ;
    private TextField textFieldLFW;
    private TextField textFieldOPF;
    private TextField textFieldRES;
    private TextField textFieldAPF;
    private TextField textFieldPHR;
    private TextField textFieldPHZ;
    private TextField textFieldPLF;
    private TextField textFieldCNM;
    private TextField textFieldExp;
    private TextField textFieldHFQ;
    private TextField textFieldHFW;
    private TextField textFieldNAM;
    private TextField textFieldNSS;
    private TextField textFieldPTH;
    private TextField textFieldXPP;
    private TextField textFieldDAP;
    private TextField textFieldDPA;
    private TextField textFieldDPO;
    private TextField textFieldSAN;

    public MeasurementReferenceDialog(CommandRunner commandRunner) {
        this.commandRunner = commandRunner;
    }

    void show() {
        dialog = new Dialog<>();
        dialog.setTitle("Start Reference Measurement");

        GridPane grid = new GridPane();
        TabPane tabPane = new TabPane();
        overviewTab = buildOverviewTab();
        hardwareTab = buildHardwareTab();
        calculationTab = buildCalculationTab();
        experimentTab = buildExperimentTab();
        saveTab = buildSaveTab();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        CheckBox checkBox = new CheckBox("Use Default Parameters");
        grid.add(checkBox, 0, 0);
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                tabPane.setDisable(true);
            } else {
                tabPane.setDisable(true);
            }
        });
        tabPane.getTabs().addAll(overviewTab, hardwareTab, calculationTab, experimentTab, saveTab);
        dialog.getDialogPane().setContent(tabPane);
        toggleTabs(true);
        dialog.showAndWait();
    }

    private Tab buildOverviewTab() {
        Tab tab = new Tab("Overview");
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        Label sampleNameLbl = new Label("SampleName"); //ist snm in Param
        grid.add(sampleNameLbl, 0, 0);
        sampleNameTf = new TextField();
        sampleNameTf.setPromptText("Name");
        grid.add(sampleNameTf, 1, 0);
        CheckBox checkBox = new CheckBox("Use Default Parameters");
        checkBox.setSelected(true);
        grid.add(checkBox, 0, 1);
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            toggleTabs(newValue);
        });
        Button runBtn = new Button("Run");
        runBtn.setOnAction(event -> {
            LOGGER.info(getActual().toString());
            commandRunner.measureReference("localhost", 9999, getActual());

        });
        grid.add(runBtn, 0, 2);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeCancel);

        tab.setContent(grid);
        tab.setClosable(false);
        return tab;
    }

    private void toggleTabs(Boolean newValue) {
        hardwareTab.setDisable(newValue);
        calculationTab.setDisable(newValue);
        experimentTab.setDisable(newValue);
        saveTab.setDisable(newValue);
    }

    private Tab buildHardwareTab() {
        Tab tab = new Tab("Hardware");
        tab.setClosable(false);
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Label labelAQM = new Label("AQM");
        grid.add(labelAQM, 0, 0);
        textFieldAQM = new TextField(brukerParameters.getAQM());
        grid.add(textFieldAQM, 1, 0);
        Label labelAPT = new Label("APT");
        grid.add(labelAPT, 0, 1);
        textFieldAPT = new TextField(brukerParameters.getAPT());
        grid.add(textFieldAPT, 1, 1);
        Label labelBMS = new Label("BMS");
        grid.add(labelBMS, 0, 2);
        textFieldBMS = new TextField(brukerParameters.getBMS());
        grid.add(textFieldBMS, 1, 2);
        Label labelLFQ = new Label("LFQ");
        grid.add(labelLFQ, 0, 3);
        textFieldLFQ = new TextField(String.valueOf(brukerParameters.getLFQ()));
        grid.add(textFieldLFQ, 1, 3);
        Label labelLFW = new Label("LFW");
        grid.add(labelLFW, 0, 4);
        textFieldLFW = new TextField(String.valueOf(brukerParameters.getLFW()));
        grid.add(textFieldLFW, 1, 4);
        Label labelOPF = new Label("OPF");
        grid.add(labelOPF, 0, 5);
        textFieldOPF = new TextField(brukerParameters.getOPF());
        grid.add(textFieldOPF, 1, 5);
        Label labelRES = new Label("RES");
        grid.add(labelRES, 0, 6);
        textFieldRES = new TextField(String.valueOf(brukerParameters.getRES()));
        grid.add(textFieldRES, 1, 6);


        tab.setContent(new ScrollPane(grid));
        return tab;
    }

    private Tab buildCalculationTab() {
        Tab tab = new Tab("Calculation");
        tab.setClosable(false);
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Label labelAPF = new Label("APF");
        grid.add(labelAPF, 0, 0);
        textFieldAPF = new TextField(brukerParameters.getAPF());
        grid.add(textFieldAPF, 1, 0);
        Label labelPHR = new Label("PHR");
        grid.add(labelPHR, 0, 1);
        textFieldPHR = new TextField(String.valueOf(brukerParameters.getPHR()));
        grid.add(textFieldPHR, 1, 1);
        Label labelPHZ = new Label("PHZ");
        grid.add(labelPHZ, 0, 2);
        textFieldPHZ = new TextField(brukerParameters.getPHZ());
        grid.add(textFieldPHZ, 1, 2);
        Label labelPLF = new Label("PLF");
        grid.add(labelPLF, 0, 3);
        textFieldPLF = new TextField(brukerParameters.getPLF());
        grid.add(textFieldPLF, 1, 3);

        tab.setContent(new ScrollPane(grid));
        return tab;
    }

    private Tab buildExperimentTab() {
        Tab tab = new Tab("Experiment");
        tab.setClosable(false);
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Label labelCNM = new Label("CNM");
        grid.add(labelCNM, 0, 0);
        textFieldCNM = new TextField(brukerParameters.getCNM());
        grid.add(textFieldCNM, 1, 0);

        Label labelEXP = new Label("EXP");
        grid.add(labelEXP, 0, 1);
        textFieldExp = new TextField(String.valueOf(brukerParameters.getEXP()));
        grid.add(textFieldExp, 1, 1);

        Label labelHFQ = new Label("HFQ");
        grid.add(labelHFQ, 0, 2);
        textFieldHFQ = new TextField(String.valueOf(brukerParameters.getHFQ()));
        grid.add(textFieldHFQ, 1, 2);

        Label labelHFW = new Label("HFW");
        grid.add(labelHFW, 0, 3);
        textFieldHFW = new TextField(String.valueOf(brukerParameters.getHFW()));
        grid.add(textFieldHFW, 1, 3);

        Label labelNAM = new Label("NAM");
        grid.add(labelNAM, 0, 4);
        textFieldNAM = new TextField(String.valueOf(brukerParameters.getNAM()));
        grid.add(textFieldNAM, 1, 4);

        Label labelNSS = new Label("NSS");
        grid.add(labelNSS, 0, 5);
        textFieldNSS = new TextField(String.valueOf(brukerParameters.getNSS()));
        grid.add(textFieldNSS, 1, 5);

        Label labelPTH = new Label("PTH");
        grid.add(labelPTH, 0, 6);
        textFieldPTH = new TextField(String.valueOf(brukerParameters.getPTH()));
        grid.add(textFieldPTH, 1, 6);

        Label labelXPP = new Label("XPP");
        grid.add(labelXPP, 0, 7);
        textFieldXPP = new TextField(brukerParameters.getXPP());
        grid.add(textFieldXPP, 1, 7);
        tab.setContent(new ScrollPane(grid));
        return tab;
    }

    private Tab buildSaveTab() {
        Tab tab = new Tab("Save");
        tab.setClosable(false);
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Label labelDAP = new Label("DAP");
        grid.add(labelDAP, 0, 0);
        textFieldDAP = new TextField(brukerParameters.getDAP());
        grid.add(textFieldDAP, 1, 0);
        Label labelDPA = new Label("DPA");
        grid.add(labelDPA, 0, 1);
        textFieldDPA = new TextField(String.valueOf(brukerParameters.getDPA()));
        grid.add(textFieldDPA, 1, 1);
        Label labelDPO = new Label("DPO");
        grid.add(labelDPO, 0, 2);
        textFieldDPO = new TextField(String.valueOf(brukerParameters.getDPO()));
        grid.add(textFieldDPO, 1, 2);
        Label labelSAN = new Label("SAN");
        grid.add(labelSAN, 0, 3);
        textFieldSAN = new TextField(brukerParameters.getSAN());
        grid.add(textFieldSAN, 1, 3);

        tab.setContent(new ScrollPane(grid));
        return tab;
    }

    private BrukerParameters getActual() {
        BrukerParameters brukerParameters = BrukerParameters.getDefault();

        brukerParameters.setSNM(sampleNameTf.getText());
        brukerParameters.setAQM(textFieldAQM.getText());
        brukerParameters.setAPT(textFieldAPT.getText());
        brukerParameters.setBMS(textFieldBMS.getText());
        brukerParameters.setLFQ(Double.parseDouble(textFieldLFQ.getText()));
        brukerParameters.setLFW(Double.parseDouble(textFieldLFW.getText()));
        brukerParameters.setOPF(textFieldOPF.getText());
        brukerParameters.setRES(Double.parseDouble(textFieldRES.getText()));
        brukerParameters.setAPF(textFieldAPF.getText());
        brukerParameters.setPHR(Double.parseDouble(textFieldPHR.getText()));
        brukerParameters.setPHZ(textFieldPHZ.getText());
        brukerParameters.setPLF(textFieldPLF.getText());
        brukerParameters.setCNM(textFieldCNM.getText());
        brukerParameters.setEXP(textFieldExp.getText());
        brukerParameters.setHFQ(Double.parseDouble(textFieldHFQ.getText()));
        brukerParameters.setHFW(Double.parseDouble(textFieldHFW.getText()));
        brukerParameters.setNAM(textFieldNAM.getText());
        brukerParameters.setNSS(Integer.parseInt(textFieldNSS.getText()));
        brukerParameters.setPTH(textFieldPTH.getText());
        brukerParameters.setSNM(sampleNameTf.getText());
        brukerParameters.setXPP(textFieldXPP.getText());
        brukerParameters.setDAP(textFieldDAP.getText());
        brukerParameters.setDPA(Integer.parseInt(textFieldDPA.getText()));
        brukerParameters.setDPO(Integer.parseInt(textFieldDPO.getText()));
        brukerParameters.setSAN(textFieldSAN.getText());

        return brukerParameters;
    }
}
