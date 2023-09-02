module edu.pwr {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.controlsfx.controls;

    //opens edu.pwr.lab04app to javafx.fxml;
    exports edu.pwr;
    exports edu.pwr.processing;
    opens edu.pwr to javafx.fxml;
}