package application;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class ManageUserPage extends Application {

    private ObservableList<User> users = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manage Users");

        // Create UI components
        TableView<User> userTable = createTable();
        Button addUserButton = new Button("Add User");
        addUserButton.setOnAction(event -> showAddUserDialog());

        // Layout
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));
        borderPane.setCenter(userTable);

        HBox bottomBox = new HBox(10);
        bottomBox.setAlignment(Pos.CENTER_RIGHT);
        bottomBox.getChildren().add(addUserButton);
        borderPane.setBottom(bottomBox);

        // Scene
        Scene scene = new Scene(borderPane, 400, 300);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    private TableView<User> createTable() {
        TableView<User> table = new TableView<>();
        table.setItems(users);

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<User, String> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));

        // Set custom cell factory for action column
        actionColumn.setCellFactory(param -> new TableCell<>() {
            final Button removeButton = new Button("Remove");

            {
                removeButton.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    showRemoveUserConfirmation(user);
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(removeButton);
                }
            }
        });

        table.getColumns().addAll(usernameColumn, passwordColumn, actionColumn);

        return table;
    }

    private void showAddUserDialog() {
        Dialog<User> dialog = new Dialog<>();
        dialog.setTitle("Add User");

        // Set the button types
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        // Create and set the username and password fields
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        dialog.getDialogPane().setContent(new VBox(10, new Label("Username:"), usernameField, new Label("Password:"), passwordField));

        // Enable/Disable add button depending on whether a username was entered.
        dialog.getDialogPane().lookupButton(addButton).setDisable(true);

        // Do some validation
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            dialog.getDialogPane().lookupButton(addButton).setDisable(newValue.trim().isEmpty());
        });

        // Convert the result to a username-password-pair when the add button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                return new User(usernameField.getText(), passwordField.getText());
            }
            return null;
        });

        Optional<User> result = dialog.showAndWait();
        result.ifPresent(newUser -> users.add(newUser));
    }

    private void showRemoveUserConfirmation(User user) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remove User");
        alert.setHeaderText("Are you sure you want to remove the user?");
        alert.setContentText("Username: " + user.getUsername());

        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                users.remove(user);
            }
        });
    }

    public static class User {
        private final SimpleStringProperty username;
        private final SimpleStringProperty password;
        private final SimpleStringProperty action;

        public User(String username, String password) {
            this.username = new SimpleStringProperty(username);
            this.password = new SimpleStringProperty(password);
            this.action = new SimpleStringProperty("Remove");
        }

        public String getUsername() {
            return username.get();
        }

        public String getPassword() {
            return password.get();
        }

        public String getAction() {
            return action.get();
        }
    }
}
