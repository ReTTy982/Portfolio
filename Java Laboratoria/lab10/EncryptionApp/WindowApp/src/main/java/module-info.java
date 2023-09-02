module edu.pwr {
    requires javafx.controls;
    requires javafx.fxml;

    exports edu.pwr.Window to javafx.graphics;
    opens edu.pwr.Window to javafx.fxml;
    requires EncryptionLib;

}