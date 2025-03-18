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
            Scene scene = new Scene(krciluvCanvas()); //TODO: zde muzes menit funkce
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
            double x = FROM + (TO - FROM)*(double)i/n;
            double y = Math.sin(x);
            double ix = 100 + 100 * x;
            double iy = 400 + 300 * y;

            gc.strokeLine(ix, iy, ix + 10, iy + 10);
        }

        Pane root = new Pane();
        root.getChildren().add(canvas);

        return root;
    }

    //TODO: níže je Karlosovo AI kód

    private void drawSinCos(GraphicsContext gc, double xStart, double xEnd, double yMin, double yMax) {
        gc.beginPath();
        double midY = (yMax + yMin) / 2;
        double amplitude = (yMax - yMin) / 4;
        double frequency = 0.02;

        for (double x = xStart; x <= xEnd; x++) {
            double ySin = midY - amplitude * Math.sin(frequency * (x - xStart) * 10);
            double yCos = midY - amplitude * Math.cos(frequency * (x - xStart) * 10);

            if (x == xStart) {
                gc.moveTo(x, ySin);
            } else {
                gc.lineTo(x, ySin);
            }
        }
        gc.stroke(); // Sinusoida

        gc.beginPath();
        for (double x = xStart; x <= xEnd; x++) {
            double yCos = midY - amplitude * Math.cos(frequency * (x - xStart) * 10);

            if (x == xStart) {
                gc.moveTo(x, yCos);
            } else {
                gc.lineTo(x, yCos);
            }
        }
        gc.stroke(); // Kosinusovka
    }

    private void drawCircularSpiral(GraphicsContext gc, double centerX, double centerY, int n) {
        gc.beginPath();
        double angleStep = Math.PI / 20;
        double maxAngle = 2 * Math.PI * n;
        double radiusStep = 10;

        for (double angle = 0; angle <= maxAngle; angle += angleStep) {
            double radius = (angle / maxAngle) * n * radiusStep;
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);

            if (angle == 0) {
                gc.moveTo(x, y);
            } else {
                gc.lineTo(x, y);
            }
        }
        gc.stroke();
    }

    private void drawSquareSpiral(GraphicsContext gc, double startX, double startY, int n) {
        gc.beginPath();
        double x = startX, y = startY;
        double step = 20;
        int direction = 0; // 0 - doprava, 1 - dolů, 2 - doleva, 3 - nahoru

        for (int i = 1; i <= n * 2; i++) {
            double length = i * step;
            switch (direction) {
                case 0 -> gc.lineTo(x += length, y); // doprava
                case 1 -> gc.lineTo(x, y += length); // dolů
                case 2 -> gc.lineTo(x -= length, y); // doleva
                case 3 -> gc.lineTo(x, y -= length); // nahoru
            }
            direction = (direction + 1) % 4;
        }
        gc.stroke();
    }

    public static void main(String[] args) {
        launch();
    }
}