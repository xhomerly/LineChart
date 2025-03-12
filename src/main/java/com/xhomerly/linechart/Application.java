package com.xhomerly.linechart;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        Label label = new Label("Input Od hodnotu:");
        TextField textField = new TextField();

        Label label2 = new Label("Input Do hodnotu:");
        TextField textField2 = new TextField();

        Label label3 = new Label("Input počet:");
        TextField textField3 = new TextField();

        Button button = new Button("Vykreslit graf");
        button.setOnAction(e -> {
            int input1 = Integer.parseInt(textField.getText());
            int input2 = Integer.parseInt(textField2.getText());
            int input3 = Integer.parseInt(textField3.getText());

            Scene scene = new Scene(drawLineChart(input1, input2, input3));
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        });
        Button button2 = new Button("Vykreslit canvas");
        root.getChildren().addAll(label, textField, label2, textField2, label3, textField3, button, button2);

        Scene scene = new Scene(root);
        stage.setTitle("LineChart");
        stage.setScene(scene);
        stage.show();
    }

    public LineChart<Number, Number> drawLineChart(double input1, double input2, int count) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(1, 1));
        series.getData().add(new XYChart.Data<>(2, 2));
        series.setName("Nevim");

        lineChart.getData().add(series);

        return lineChart;
    }

    public static void main(String[] args) {
        launch();
    }
}