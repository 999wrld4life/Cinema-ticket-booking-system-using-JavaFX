package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RoomOnePage extends Application {

    public ArrayList<Integer> seatNumbers = new ArrayList<>();
    private static final String SEAT_NUMBERS_FILE = "roomOne.txt";
    private static final String BOOKINGS_FILE = "roomOneReservations.txt";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("WELCOME TO ROOM ONE");
        seatNumbers = loadSeatNumbers(SEAT_NUMBERS_FILE);

//        for (int no : seatNumbers) {
//            System.out.println(no);
//        }

        GridPane gridPane = createGridPane();
        Stop[] stops = new Stop[]{new Stop(0, Color.BLUE), new Stop(1, Color.DARKBLUE)};
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        BackgroundFill backgroundFill = new BackgroundFill(linearGradient, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);

        TextField seatTextField = createSeatTextField();
        Button bookButton = createBookButton(seatTextField, gridPane, primaryStage);

        Button goBackButton = createGoBackButton(primaryStage);

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setBackground(background);
        root.setPadding(new Insets(20));
        root.add(goBackButton, 0, 0);
        root.add(gridPane, 0, 1);
        root.add(seatTextField, 0, 2);
        root.add(bookButton, 0, 3);

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(event -> saveSeatNumbers());

        primaryStage.show();
    }

    public ArrayList<Integer> loadSeatNumbers(String filename) {
        ArrayList<Integer> loadedSeatNumbers = new ArrayList<>();
        try {
            File file = new File(filename);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            if (file.exists()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    int seatNumber = Integer.parseInt(line);
                    System.out.print(seatNumber + " *");
                    loadedSeatNumbers.add(seatNumber);
                }
            }

            reader.close();
            System.out.println("file loaded successfully");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return loadedSeatNumbers;
    }

    private void saveSeatNumbers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SEAT_NUMBERS_FILE))) {
            for (Integer seat : seatNumbers) {
                writer.write(seat.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for (Integer seatNumber : seatNumbers) {
            StackPane box = createBox(seatNumber);
            gridPane.add(box, (seatNumber - 1) % 5, (seatNumber - 1) / 5);
        }

        return gridPane;
    }

    private StackPane createBox(int number) {
        Rectangle box = new Rectangle(50, 50);
        box.setFill(Color.LIGHTGRAY);
        box.setStroke(Color.BLACK);
        box.setStrokeWidth(2);

        box.setOnMouseClicked(event -> {
            System.out.println("Box " + number + " clicked!");
        });

        Text text = new Text(String.valueOf(number));
        text.setFill(Color.BLACK);
        StackPane stackPane = new StackPane(box, text);

        return stackPane;
    }

    private TextField createSeatTextField() {
        TextField seatTextField = new TextField();
        seatTextField.setPromptText("Enter a seat (1-15)");
        seatTextField.setStyle("-fx-text-fill: black;");
        return seatTextField;
    }

    private Button createBookButton(TextField seatTextField, GridPane gridPane, Stage primaryStage) {
        Button bookButton = new Button("Book Seat");
        bookButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #4CAF50, #2196F3); -fx-text-fill: white;");
        bookButton.setOnAction(event -> {
        	
            String input = seatTextField.getText();
            try {
                int selectedSeat = Integer.parseInt(input);
                if (seatNumbers.contains(selectedSeat)) {
                    seatNumbers.remove(Integer.valueOf(selectedSeat));
                    bookSeatAndWriteToFile(selectedSeat);
                    updateGrid(gridPane);
                    showAlert("Seat " + selectedSeat + " booked!");
                    primaryStage.close();
                } else {
                    showAlert("Invalid seat selection. Seat already booked or doesn't exist.");
                }
            } catch (NumberFormatException e) {
                showAlert("Invalid input. Please enter a valid seat number.");
            }
            seatTextField.clear();
            new ThankYouPage().start(new Stage());
            
        });
        return bookButton;
    }

    private void bookSeatAndWriteToFile(int selectedSeat) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKINGS_FILE, true))) {
            String userNumber = getUserNumber();
            String dateTime = getCurrentDateTime();
            int roomNumber = 1;  

            String bookingInfo = String.format("user%s, %s, %d, %d", userNumber, dateTime, roomNumber, selectedSeat);
            writer.write(bookingInfo);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateGrid(GridPane gridPane) {
        gridPane.getChildren().clear();
        for (Integer seatNumber : seatNumbers) {
            StackPane box = createBox(seatNumber);
            gridPane.add(box, (seatNumber - 1) % 5, (seatNumber - 1) / 5);
        }
        saveSeatNumbers();
    }

    private Button createGoBackButton(Stage primaryStage) {
        Button goBackButton = new Button("Go Back");
        goBackButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #FFD700, #FF8C00); -fx-text-fill: black;");
        goBackButton.setOnAction(event -> {
            primaryStage.close();
        });
        return goBackButton;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String getUserNumber() {
        
        return String.valueOf(System.currentTimeMillis());
    }

    private String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
}
