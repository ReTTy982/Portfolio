import ex.api.ClusterAnalysisService;
module App {
    requires Api;
    //requires Implementation;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires org.controlsfx.controls;
    uses ClusterAnalysisService;
    exports App.window;
    opens App.window to javafx.fxml;

}