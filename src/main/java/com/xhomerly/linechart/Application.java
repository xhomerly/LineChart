package com.xhomerly.linechart;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application implements Init {
    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        Label label = new Label("Input Od hodnotu:");
        TextField textField = new TextField();
        textField.setText(FROM+"");

        Label label2 = new Label("Input Do hodnotu:");
        TextField textField2 = new TextField();
        textField2.setText(TO+"");

        Label label3 = new Label("Input počet:");
        TextField textField3 = new TextField();
        textField3.setText(N+"");

        Button button = new Button("Vykreslit graf");
        button.setOnAction(e -> {
            double input1 = Double.parseDouble(textField.getText());
            double input2 = Double.parseDouble(textField2.getText());
            int input3 = Integer.parseInt(textField3.getText());

            Scene scene = new Scene(drawLineChart(input1, input2, input3));
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        });
        Button button2 = new Button("Vykreslit canvas");
        button2.setOnAction(e -> {
            Scene scene = new Scene(showCanvas());
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        });
        root.getChildren().addAll(label, textField, label2, textField2, label3, textField3, button, button2);

        Scene scene = new Scene(root);
        stage.setTitle("LineChart");
        stage.setScene(scene);
        stage.show();
    }

    public LineChart<Number, Number> drawLineChart(double from, double to, int n) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (int i = 0; i <= n; i++) {
            double x = from + i * (to - from)/n;
            series.getData().add(new XYChart.Data<>(x, Math.sin(x)));
        }
        series.setName("y = sin(x)");

        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        for (int i = 0; i <= n; i++) {
            double x = from + i * (to - from)/n;
            series2.getData().add(new XYChart.Data<>(x, Math.cos(x)));
        }
        series2.setName("y = cos(x)");

        lineChart.getData().add(series);
        lineChart.getData().add(series2);

        return lineChart;
    }

    public Pane showCanvas() {
        Canvas canvas = new Canvas(1000, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeRect(50, 50, 900, 700);
        gc.strokeLine(100, 150, 300, 350); gc.strokeLine(300, 350, 500, 150);
        gc.strokeLine(500, 150, 700, 700); gc.strokeLine(700, 700, 900, 250);
        gc.strokeText("*** Canvas graf ***", 450, 700);
        Pane root = new Pane();
        root.getChildren().add(canvas);

        return root;
    }

    public static void main(String[] args) {
        launch();
    }
}