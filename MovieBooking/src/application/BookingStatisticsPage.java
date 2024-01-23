package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class BookingStatisticsPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Booking Statistics");

        // Create a simple bar chart for movie bookings
        BarChart<String, Number> barChart = createBarChart();
        VBox chartLayout = new VBox(barChart);
        chartLayout.setAlignment(Pos.CENTER);

        // Display additional statistics
        VBox statisticsLayout = createStatisticsLayout();

        // Combine chart and statistics
        HBox mainLayout = new HBox(chartLayout, statisticsLayout);
        mainLayout.setSpacing(20);
        mainLayout.setAlignment(Pos.CENTER);

        // Set up the linear gradient background
        Stop[] stops = new Stop[]{new Stop(0, Color.WHITE), new Stop(1, Color.LIGHTBLUE)};
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        BackgroundFill backgroundFill = new BackgroundFill(linearGradient, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);

        // Set the background
        mainLayout.setBackground(background);

        Scene scene = new Scene(mainLayout, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private BarChart<String, Number> createBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Movie Bookings - Previous Week");
        

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Seats");
        

        // Use constant data for illustration purposes
        series.getData().add(new XYChart.Data<>("Room One", 40));
        series.getData().add(new XYChart.Data<>("Room Two", 25));
        series.getData().add(new XYChart.Data<>("Room Three", 35));
        series.getData().add(new XYChart.Data<>("Total", 100));

        // Set light green color to fill the bars
        for (XYChart.Data<String, Number> data : series.getData()) {
            data.nodeProperty().addListener((ov, oldNode, node) -> {
                if (node != null) {
                    node.setStyle("-fx-bar-fill: orangered;");
                }
            });
        }

        barChart.getData().add(series);
        return barChart;
    }

    private VBox createStatisticsLayout() {
        VBox statisticsLayout = new VBox(20);
        statisticsLayout.setAlignment(Pos.CENTER);
        statisticsLayout.setPadding(new Insets(20));

        Label titleLabel = new Label("Booking Statistics");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        

        // Add various statistics, you can customize and extend this part
        Label totalBookingsLabel = new Label("Total Bookings: 100");
        Label totalRevenueLabel = new Label("Total Revenue: $1700");
        Label averageBookingLabel = new Label("Average Bookings per Movie: 16");

        

        // Add these statistics to the layout
        statisticsLayout.getChildren().addAll(titleLabel, totalBookingsLabel, totalRevenueLabel, averageBookingLabel);
        return statisticsLayout;
    }
}
