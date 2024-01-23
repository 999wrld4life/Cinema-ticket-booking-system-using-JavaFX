package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RoomTwoReservationHistory extends Application {

    private static final String RESERVATIONS_FILE = "roomTwoReservations.txt";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Room Two Reservations");

        ListView<String> reservationsListView = new ListView<>();
        ObservableList<String> reservationsList = getReservationsList();
        reservationsListView.setItems(reservationsList);

        GridPane gridPane = createGridPane();

        BorderPane root = new BorderPane();
        root.setTop(gridPane);
        root.setCenter(reservationsListView);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(30);
        gridPane.setVgap(40);
        gridPane.setPadding(new Insets(20));

        // Row titles
        Label userLabel = new Label("User");
        Label dateLabel = new Label("Date");
        Label timeLabel = new Label("Time");
        Label roomLabel = new Label("Room");
        Label seatLabel = new Label("Seat");

        // Add row titles to the grid
        gridPane.add(userLabel, 0, 0);
        gridPane.add(dateLabel, 1, 0);
        gridPane.add(timeLabel, 2, 0);
        gridPane.add(roomLabel, 3, 0);
        gridPane.add(seatLabel, 4, 0);

        // Align titles with columns
        GridPane.setHalignment(userLabel, HPos.CENTER);
        GridPane.setHalignment(dateLabel, HPos.CENTER);
        GridPane.setHalignment(timeLabel, HPos.CENTER);
        GridPane.setHalignment(roomLabel, HPos.CENTER);
        GridPane.setHalignment(seatLabel, HPos.CENTER);

        return gridPane;
    }

    private ObservableList<String> getReservationsList() {
        ObservableList<String> reservationsList = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(RESERVATIONS_FILE)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                reservationsList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reservationsList;
    }
}