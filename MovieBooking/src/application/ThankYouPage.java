package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ThankYouPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Booking Successful Page");

        // Set up the linear gradient background
        Stop[] stops = new Stop[]{new Stop(0, Color.DARKBLUE), new Stop(1, Color.BLUE)};
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 1, true, javafx.scene.paint.CycleMethod.NO_CYCLE, stops);
        BackgroundFill backgroundFill = new BackgroundFill(linearGradient, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
        Background background = new Background(backgroundFill);

        // Create a label with the specified text and style
        Label successLabel = new Label("Booking Successful!\nTake the receipt to the counter and pay accordingly!\n\n\nThank You for Choosing Us!");
        successLabel.setFont(Font.font("Arial", 25));
        successLabel.setTextFill(Color.WHITE);
        successLabel.setAlignment(Pos.CENTER);

        // Create a Done button
        Button doneButton = new Button("Done");
        doneButton.setTextFill(Color.WHITE);
        doneButton.setFont(new Font(20));
        doneButton.setBackground(new Background(new BackgroundFill(
                LinearGradient.valueOf("linear-gradient(to bottom right, #4CAF50, #2196F3)"),
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        doneButton.setOnAction(event -> primaryStage.close());

        // Create a border pane to arrange the label and button
        BorderPane root = new BorderPane();
        root.setBackground(background);
        BorderPane.setAlignment(successLabel, Pos.CENTER);
        BorderPane.setAlignment(doneButton, Pos.CENTER);
        BorderPane.setMargin(successLabel, new Insets(20));
        BorderPane.setMargin(doneButton, new Insets(20));
        root.setCenter(successLabel);
        root.setBottom(doneButton);

        // Set up the scene
        Scene scene = new Scene(root, 700, 400);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }
}
