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
import javafx.scene.paint.Color;
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
//            stage.setMaximized(true);
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

        // Nakreslení rámečku a osy
        gc.strokeRect(50, 50, 900, 700);

        // Nakreslení osy X a Y
        gc.setStroke(Color.BLACK);
        gc.strokeLine(50, 400, 950, 400);  // osa X
        gc.strokeLine(500, 50, 500, 750);  // osa Y

        // Nastavení měřítka pro grafy
        double scaleY = 100; // Jak daleko budou jednotlivé body na ose Y

        // Vykreslení grafu funkce sínus
        gc.setStroke(Color.RED); // Červená pro sínus
        gc.beginPath();
        for (int x = 0; x <= 900; x++) {
            double angle = Math.toRadians(x - 450); // Převod na úhel (rohy do radiánů)
            double y = Math.sin(angle) * scaleY;
            if (x == 0) {
                gc.moveTo(x + 50, 400 - y); // Nastavení počátečního bodu
            } else {
                gc.lineTo(x + 50, 400 - y); // Vykreslení čáry
            }
        }
        gc.stroke();

        // Vykreslení grafu funkce kosínus
        gc.setStroke(Color.BLUE); // Modrá pro kosínus
        gc.beginPath();
        for (int x = 0; x <= 900; x++) {
            double angle = Math.toRadians(x - 450); // Převod na úhel (rohy do radiánů)
            double y = Math.cos(angle) * scaleY;
            if (x == 0) {
                gc.moveTo(x + 50, 400 - y); // Nastavení počátečního bodu
            } else {
                gc.lineTo(x + 50, 400 - y); // Vykreslení čáry
            }
        }
        gc.stroke();

        Pane root = new Pane();
        root.getChildren().add(canvas);

        return root;
    }

    public Pane krciluvCanvas() {
        Canvas canvas = new Canvas(1000, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeRect(50, 50, 900, 700);

        int n = 100;

        for (int i = 0; i < n; i++) {
            double x = 6*(double)i/n;
            double y = Math.sin(x);
            double ix = 100 + 100 * x;
            double iy = 400 + 300 * y;

            gc.strokeLine(ix, iy, ix + 100, iy + 100);
        }

        Pane root = new Pane();
        root.getChildren().add(canvas);

        return root;
    }

    public static void main(String[] args) {
        launch();
    }
}