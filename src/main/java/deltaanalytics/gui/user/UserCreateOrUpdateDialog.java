package deltaanalytics.gui.user;

import deltaanalytics.gui.FtirGuiElement;
import deltaanalytics.user.entity.User;
import deltaanalytics.user.repository.UserRepository;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class UserCreateOrUpdateDialog {
    void showNeuDialog(FtirGuiElement ftirGuiElement, UserRepository userRepository, User givenUser) {
        // Create the custom dialog.
        Dialog<User> dialog = new Dialog<>();
        dialog.setTitle("Nutzer anlegen");
        dialog.setHeaderText("Bitte die Daten für den Nutzer eingeben.");

        ButtonType loginButtonType = new ButtonType("Speichern", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField vorname = new TextField();
        vorname.setPromptText("Vorname");
        TextField nachname = new TextField();
        nachname.setPromptText("Nachname");
        TextField account = new TextField();
        account.setPromptText("Account");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        CheckBox enabled = new CheckBox();



        grid.add(new Label("Vorname:"), 0, 0);
        grid.add(vorname, 1, 0);
        grid.add(new Label("Nachname:"), 0, 1);
        grid.add(nachname, 1, 1);
        grid.add(new Label("Account:"), 0, 2);
        grid.add(account, 1, 2);
        grid.add(new Label("Password:"), 0, 3);
        grid.add(password, 1, 3);
        grid.add(new Label("Enabled:"), 0, 4);
        grid.add(enabled, 1, 4);

        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        vorname.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        vorname.setText(givenUser.getFirstname());
        nachname.setText(givenUser.getLastname());
        account.setText(givenUser.getAccount());
        password.setText(givenUser.getPassword());
        enabled.setSelected(givenUser.isEnabled());

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> vorname.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                User user = new User();
                user.setId(givenUser.getId());
                user.setCreatedAt(givenUser.getCreatedAt());
                user.setFirstname(vorname.getText());
                user.setLastname(nachname.getText());
                user.setAccount(account.getText());
                user.setPassword(password.getText());
                user.setEnabled(enabled.isSelected());
                return user;
            }
            return null;
        });

        Optional<User> result = dialog.showAndWait();

        result.ifPresent(user -> {
            userRepository.createOrUpdate(user);
            ftirGuiElement.updateUI();
        });
    }

}
