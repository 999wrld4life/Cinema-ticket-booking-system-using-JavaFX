package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MovieListingPage extends Application {
    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Movie Listing App");
        stage.setMaximized(true);

        Pane moviesPanel = initializeUI();
        Scene scene = new Scene(moviesPanel, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }

    private Pane initializeUI() {
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(
                LinearGradient.valueOf("linear-gradient(to bottom right, #4CAF50, #2196F3)"),
                CornerRadii.EMPTY,
                Insets.EMPTY)));

        // Movies Panel
        VBox moviesContainer = new VBox();
        moviesContainer.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        moviesContainer.setPadding(new Insets(10));

        // Movie 1: 21 Bridges
        createMovieContainer(moviesContainer, "21 Bridges", "6.6", "4 PM", "Action/Thriller",
                "Andre, an aggressive police officer with a controversial reputation, decides to put an end to two war veterans after they shoot down a few police officers during a heist.", "bridges.jpg", "6 PM", "2023-12-10", "Room One", "$15");

        // Movie 2: Saltburn
        createMovieContainer(moviesContainer, "Saltburn", "7.6", "2 PM", "Thriller",
                "Distraught by his classmate Oliver's unfortunate living situation, Felix, a rich student, invites him over to his estate. Soon, a series of horrifying events engulf Felix's family.", "saltburn.jpg", "4 PM", "2023-12-19", "Room Two", "$12");

        // Movie 3: Foe
        createMovieContainer(moviesContainer, "Foe", "5.4", "12 PM", "Sci-Fi",
                "Hen and Junior's quiet life is thrown into turmoil when an uninvited stranger shows up at their door with a startling proposal.", "foe.jpg", "2 PM", "2023-12-27", "Room Three", "$10");

        ScrollPane moviesScrollPane = new ScrollPane(moviesContainer);
        moviesScrollPane.setFitToWidth(true);
        root.setCenter(moviesScrollPane);

        return root;
    }

    private void createMovieContainer(VBox moviesContainer, String title, String rating, String startTime, String genre, String description, String imagePath, String endTime, String releaseDate, String room, String ticketPrice) {
        VBox movieContainer = new VBox();
        movieContainer.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        movieContainer.setSpacing(20);
        movieContainer.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        movieContainer.setAlignment(Pos.CENTER); 
        movieContainer.setPadding(new Insets(20)); 

        // Movie Poster
        ImageView movieImageView = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
        movieImageView.setFitHeight(400);
        movieImageView.setPreserveRatio(true);

        // Use a StackPane to center the image
        StackPane imageStackPane = new StackPane(movieImageView);
        imageStackPane.setAlignment(Pos.CENTER);
        movieContainer.getChildren().add(imageStackPane);

        // Movie Title
        Label titleLabel = new Label(title);
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setStyle("-fx-font-size: 40; -fx-font-family: 'Impact'; -fx-font-weight: bold;"); // Increased text size
        movieContainer.getChildren().add(titleLabel);

        // Movie Details
        VBox detailsContainer = new VBox();
        detailsContainer.setAlignment(Pos.CENTER);
        detailsContainer.setSpacing(10);

        Label ratingLabel = new Label("Rating: " + rating);
        Label startTimeLabel = new Label("Start Time: " + startTime);
        Label endTimeLabel = new Label("End Time: " + endTime);
        Label genreLabel = new Label("Genre: " + genre);
        Label releaseDateLabel = new Label("Release Date: " + releaseDate);
        Label roomLabel = new Label("Showing Room: " + room);
        Label priceLabel = new Label("Ticket Price: " + ticketPrice);

        ratingLabel.setTextFill(Color.WHITE);
        startTimeLabel.setTextFill(Color.WHITE);
        endTimeLabel.setTextFill(Color.WHITE);
        genreLabel.setTextFill(Color.WHITE);
        releaseDateLabel.setTextFill(Color.WHITE);
        roomLabel.setTextFill(Color.WHITE);
        priceLabel.setTextFill(Color.WHITE);

        ratingLabel.setStyle("-fx-font-size: 20;");
        startTimeLabel.setStyle("-fx-font-size: 20;");
        endTimeLabel.setStyle("-fx-font-size: 20;");
        genreLabel.setStyle("-fx-font-size: 20;");
        releaseDateLabel.setStyle("-fx-font-size: 20;");
        roomLabel.setStyle("-fx-font-size: 20;");
        priceLabel.setStyle("-fx-font-size: 20;");

        detailsContainer.getChildren().addAll(ratingLabel, startTimeLabel, endTimeLabel, genreLabel, releaseDateLabel, roomLabel, priceLabel);
        movieContainer.getChildren().add(detailsContainer);

        // Movie Description
        Label descriptionLabel = new Label(description);
        descriptionLabel.setTextFill(Color.WHITE);
        descriptionLabel.setWrapText(true);
        descriptionLabel.setStyle("-fx-font-size: 20;"); // Increased text size
        movieContainer.getChildren().add(descriptionLabel);

        // Book Button
        Button bookButton = createBookButton(title);
        VBox.setMargin(bookButton, new Insets(20, 0, 20, 0)); // Increase top and bottom margin
        movieContainer.getChildren().add(bookButton);

        // Add Movie Container to Movies Container
        moviesContainer.getChildren().add(movieContainer);
    }

    private Button createBookButton(String title) {
        Button bookButton = new Button("Book Now");
        bookButton.setTextFill(Color.WHITE);
        bookButton.setBackground(new Background(new BackgroundFill(
                LinearGradient.valueOf("linear-gradient(to bottom right, #4CAF50, #2196F3)"),
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        bookButton.setOnAction(event -> handleBookButtonClick(title, stage));
        bookButton.setEffect(new DropShadow(10, Color.BLACK));
        bookButton.setStyle("-fx-font-size: 20; -fx-min-width: 200px;"); // Increase text size and set minimum width

        return bookButton;
    }

    private void handleBookButtonClick(String title, Stage primaryStage) {
        switch (title) {
            case "21 Bridges":
                new RoomOnePage().start(new Stage());
                primaryStage.close();
                break;
            case "Saltburn":
                new RoomTwoPage().start(new Stage());
                primaryStage.close();
                break;
            case "Foe":
                new RoomThreePage().start(new Stage());
                primaryStage.close();
                break;
            default:
                showAlert("Room not available for " + title);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
