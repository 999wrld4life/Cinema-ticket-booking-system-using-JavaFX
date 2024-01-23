package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ManageMoviesPage extends Application {
	private static final String MOVIE_DATA_FILE = "movies.txt";
    private Map<String, Movie> moviesMap = new HashMap<>();
    private File selectedImageFile;
    private Background background;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cinema App");

        // Set up the linear gradient background
        Stop[] stops = new Stop[]{new Stop(0, Color.DODGERBLUE), new Stop(1, Color.MIDNIGHTBLUE)};
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        BackgroundFill backgroundFill = new BackgroundFill(linearGradient, CornerRadii.EMPTY, Insets.EMPTY);
        background = new Background(backgroundFill);

        // Create UI components
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));

        // Movie details text fields
        TextField titleField = new TextField();
        TextField synopsisField = new TextField();
        TextField ratingField = new TextField();
        TextField genreField = new TextField();

        // Date picker, start time, and end time
        DatePicker datePicker = new DatePicker();
        TextField startTimeField = new TextField();
        TextField endTimeField = new TextField();

        // Image upload section
        HBox imageUploadLayout = createImageUploadLayout(primaryStage);

        // Button to manage movies
        Button addButton = new Button("Add Movie");
        addButton.setTextFill(Color.WHITE);
        addButton.setFont(new Font(18));
        addButton.setBackground(new Background(new BackgroundFill(
                LinearGradient.valueOf("linear-gradient(to bottom right, #4CAF50, #2196F3)"),
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        addButton.setOnAction(event -> {
            if (areFieldsFilled(titleField, synopsisField, ratingField, genreField, datePicker, startTimeField, endTimeField)) {
                String title = titleField.getText();
                Movie movie = new Movie(title, synopsisField.getText(), ratingField.getText(), genreField.getText(), selectedImageFile);
                movie.setStartTime(startTimeField.getText());
                movie.setEndTime(endTimeField.getText());
                movie.setDate(datePicker.getValue());

                moviesMap.put(title, movie);

                // Clear fields after managing movie
                clearFields(titleField, synopsisField, ratingField, genreField, datePicker, startTimeField, endTimeField, imageUploadLayout);
                selectedImageFile = null;

                showAlert("Movie " + title + " managed successfully!");
            } else {
                showAlert("Please fill in all fields.");
            }
        });

        // Button to view movie information
        Button viewInfoButton = new Button("View Movie Information");
        viewInfoButton.setTextFill(Color.WHITE);
        viewInfoButton.setFont(new Font(18));

        viewInfoButton.setBackground(new Background(new BackgroundFill(
                LinearGradient.valueOf("linear-gradient(to bottom right, #4CAF50, #2196F3)"),
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        viewInfoButton.setOnAction(event -> {
            String selectedTitle = showMovieSelectionDialog();
            if (selectedTitle != null) {
                showMovieInformationDialog(selectedTitle);
            }
        });

        // Button to delete movie
        Button deleteButton = new Button("Delete Movie");
        deleteButton.setTextFill(Color.WHITE);
        deleteButton.setFont(new Font(18));
        deleteButton.setBackground(new Background(new BackgroundFill(
                LinearGradient.valueOf("linear-gradient(to bottom right, #4CAF50, #2196F3)"),
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        deleteButton.setOnAction(event -> {
            String selectedTitle = showMovieSelectionDialog();
            if (selectedTitle != null) {
                moviesMap.remove(selectedTitle);
                showAlert("Movie " + selectedTitle + " deleted successfully!");
            }
        });

        // Button to update movie
        Button updateButton = new Button("Update Movie");
        updateButton.setTextFill(Color.WHITE);
        updateButton.setFont(new Font(18));
        updateButton.setBackground(new Background(new BackgroundFill(
                LinearGradient.valueOf("linear-gradient(to bottom right, #4CAF50, #2196F3)"),
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        updateButton.setOnAction(event -> {
            String selectedTitle = showMovieSelectionDialog();
            if (selectedTitle != null) {
                updateMovieDetails(selectedTitle);
            }
        });

        // Add components to the layout
        mainLayout.getChildren().addAll(
                createStyledLabel("Title:"), titleField,
                createStyledLabel("Synopsis:"), synopsisField,
                createStyledLabel("Rating:"), ratingField,
                createStyledLabel("Genre:"), genreField,
                createStyledLabel("Date:"), datePicker,
                createStyledLabel("Start Time:"), startTimeField,
                createStyledLabel("End Time:"), endTimeField,
                createStyledLabel("Upload Poster:"),
                imageUploadLayout,
                addButton,
                viewInfoButton,
                deleteButton,
                updateButton
        );

        // Set the background
        mainLayout.setBackground(background);

        // Make the page scrollable
        ScrollPane scrollPane = new ScrollPane(mainLayout);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createImageUploadLayout(Stage primaryStage) {
        HBox imageUploadLayout = new HBox(10);

        Label imageLabel = new Label("No Image Selected");
        imageLabel.setFont(new Font(20));
        imageLabel.setTextFill(Color.WHITE);
        Button chooseImageButton = new Button("Choose Image");
        chooseImageButton.setTextFill(Color.WHITE);
        chooseImageButton.setFont(new Font(18));
        chooseImageButton.setBackground(new Background(new BackgroundFill(
                LinearGradient.valueOf("linear-gradient(to bottom right, #4CAF50, #2196F3)"),
                CornerRadii.EMPTY,
                Insets.EMPTY)));

        chooseImageButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Movie Poster");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
            );
            selectedImageFile = fileChooser.showOpenDialog(primaryStage);

            if (selectedImageFile != null) {
                // Update the label with the selected image file name
                imageLabel.setText(selectedImageFile.getName());
            }
        });

        imageUploadLayout.getChildren().addAll(chooseImageButton, imageLabel);
        return imageUploadLayout;
    }

    private Label createStyledLabel(String labelText) {
        Label label = new Label(labelText);
        label.setTextFill(Color.WHITE);
        label.setFont(new Font(24));
        return label;
    }

    private String showMovieSelectionDialog() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>(null, moviesMap.keySet());
        dialog.setTitle("Select Movie");
        dialog.setHeaderText("Choose a movie:");
        dialog.setContentText("Movie:");

        return dialog.showAndWait().orElse(null);
    }

    private void showMovieInformationDialog(String movieTitle) {
        Movie movie = moviesMap.get(movieTitle);
        if (movie != null) {
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Movie Information: " + movieTitle);

            VBox dialogLayout = new VBox(20);
            dialogLayout.setAlignment(Pos.CENTER);
            
            dialogLayout.setPadding(new Insets(20));

            // Display movie details
            Label titleLabel = new Label("Title: " + movie.getTitle());
            titleLabel.setTextFill(Color.WHITE);
            Label synopsisLabel = new Label("Synopsis: " + movie.getSynopsis());
            synopsisLabel.setTextFill(Color.WHITE);
            Label ratingLabel = new Label("Rating: " + movie.getRating());
            ratingLabel.setTextFill(Color.WHITE);
            Label genreLabel = new Label("Genre: " + movie.getGenre());
            genreLabel.setTextFill(Color.WHITE);
            Label dateLabel = new Label("Date: " + movie.getDate());
            dateLabel.setTextFill(Color.WHITE);
            Label startTimeLabel = new Label("Start Time: " + movie.getStartTime());
            startTimeLabel.setTextFill(Color.WHITE);
            Label endTimeLabel = new Label("End Time: " + movie.getEndTime());
            endTimeLabel.setTextFill(Color.WHITE);

            // Display movie poster
            if (movie.getPosterFile() != null) {
                Image posterImage = new Image(movie.getPosterFile().toURI().toString());
                ImageView posterImageView = new ImageView(posterImage);
                posterImageView.setFitWidth(200);
                posterImageView.setFitHeight(300);
                dialogLayout.getChildren().add(posterImageView);
            }

            // Add components to the dialog layout
            dialogLayout.getChildren().addAll(titleLabel, synopsisLabel, ratingLabel, genreLabel, dateLabel, startTimeLabel, endTimeLabel);

            // Set the background
            dialogLayout.setBackground(background);

            Scene dialogScene = new Scene(dialogLayout, 400, 600);
            dialogStage.setScene(dialogScene);
            dialogStage.show();
        } else {
            showAlert("Movie not found.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields(TextField titleField, TextField synopsisField, TextField ratingField,
                             TextField genreField, DatePicker datePicker, TextField startTimeField,
                             TextField endTimeField, HBox imageUploadLayout) {
        titleField.clear();
        synopsisField.clear();
        ratingField.clear();
        genreField.clear();
        datePicker.getEditor().clear();
        startTimeField.clear();
        endTimeField.clear();
        ((Label) imageUploadLayout.getChildren().get(1)).setText("No Image Selected");
    }

    private boolean areFieldsFilled(TextField titleField, TextField synopsisField, TextField ratingField,
                                    TextField genreField, DatePicker datePicker, TextField startTimeField,
                                    TextField endTimeField) {
        return !titleField.getText().isEmpty() &&
                !synopsisField.getText().isEmpty() &&
                !ratingField.getText().isEmpty() &&
                !genreField.getText().isEmpty() &&
                datePicker.getValue() != null &&
                !startTimeField.getText().isEmpty() &&
                !endTimeField.getText().isEmpty();
    }

    private void updateMovieDetails(String movieTitle) {
        Movie movie = moviesMap.get(movieTitle);
        if (movie != null) {
            // Create a new window to update movie details
            Stage updateStage = new Stage();
            updateStage.setTitle("Update Movie: " + movieTitle);

            // Create UI components for updating movie
            VBox updateLayout = new VBox(20);
            updateLayout.setAlignment(Pos.CENTER);
            updateLayout.setPadding(new Insets(20));

            // Updated movie details text fields
            TextField updatedSynopsisField = new TextField(movie.getSynopsis());
            TextField updatedRatingField = new TextField(movie.getRating());
            TextField updatedGenreField = new TextField(movie.getGenre());

            // Updated date picker, start time, and end time
            DatePicker updatedDatePicker = new DatePicker(movie.getDate());
            TextField updatedStartTimeField = new TextField(movie.getStartTime());
            TextField updatedEndTimeField = new TextField(movie.getEndTime());

            // Button to confirm update
            Button confirmUpdateButton = new Button("Confirm Update");
            confirmUpdateButton.setTextFill(Color.WHITE);
            confirmUpdateButton.setFont(new Font(18));
            confirmUpdateButton.setBackground(new Background(new BackgroundFill(
                    LinearGradient.valueOf("linear-gradient(to bottom right, #4CAF50, #2196F3)"),
                    CornerRadii.EMPTY,
                    Insets.EMPTY)));
            confirmUpdateButton.setOnAction(event -> {
                // Update movie details
                movie.setSynopsis(updatedSynopsisField.getText());
                movie.setRating(updatedRatingField.getText());
                movie.setGenre(updatedGenreField.getText());
                movie.setDate(updatedDatePicker.getValue());
                movie.setStartTime(updatedStartTimeField.getText());
                movie.setEndTime(updatedEndTimeField.getText());

                showAlert("Movie " + movieTitle + " updated successfully!");

                // Close the update window
                updateStage.close();
            });

            // Add components to the update layout
            updateLayout.getChildren().addAll(
                    createStyledLabel("Updated Synopsis:"), updatedSynopsisField,
                    createStyledLabel("Updated Rating:"), updatedRatingField,
                    createStyledLabel("Updated Genre:"), updatedGenreField,
                    createStyledLabel("Updated Date:"), updatedDatePicker,
                    createStyledLabel("Updated Start Time:"), updatedStartTimeField,
                    createStyledLabel("Updated End Time:"), updatedEndTimeField,
                    confirmUpdateButton
            );

            // Set the background
            updateLayout.setBackground(background);

            // Make the update window scrollable
            ScrollPane updateScrollPane = new ScrollPane(updateLayout);
            updateScrollPane.setFitToWidth(true);

            Scene updateScene = new Scene(updateScrollPane, 400, 400);
            updateStage.setScene(updateScene);
            updateStage.show();
        } else {
            showAlert("Movie not found.");
        }
    }

    private static class Movie {
        private String title;
        private String synopsis;
        private String rating;
        private String genre;
        private File posterFile;
        private String startTime;
        private String endTime;
        private LocalDate date;

        public Movie(String title, String synopsis, String rating, String genre, File posterFile) {
            this.title = title;
            this.synopsis = synopsis;
            this.rating = rating;
            this.genre = genre;
            this.posterFile = posterFile;
        }

        public String getTitle() {
            return title;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public String getRating() {
            return rating;
        }

        public String getGenre() {
            return genre;
        }

        public File getPosterFile() {
            return posterFile;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate localDate) {
            this.date = localDate;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }
    }
}
