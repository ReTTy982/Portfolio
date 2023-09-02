package App.window;

import ex.api.ClusterAnalysisService;

import java.io.IOException;
import java.util.ServiceLoader;

public class Main {
    ServiceLoader<ClusterAnalysisService> loader = ServiceLoader.load(ClusterAnalysisService.class);
    public static void main(String[] args) throws IOException {
        MainView.main(args);



    }



}
