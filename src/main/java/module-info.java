module com.xhomerly.linechart {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.xhomerly.linechart to javafx.fxml;
    exports com.xhomerly.linechart;
}