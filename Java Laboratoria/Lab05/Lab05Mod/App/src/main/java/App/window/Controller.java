package App.window;
import App.handlers.CsvHandler;
import App.handlers.TableItem;
import ex.api.ClusterAnalysisService;
import ex.api.ClusteringException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.ServiceLoader;
import java.util.stream.Stream;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.DirectoryChooser;


public class Controller implements Initializable {

    private Path currentPath;
    private CsvHandler csvHandler;

    @FXML
    private Label resultLabel;
    @FXML
    private Label algorithmLabel;

    @FXML
    private TableView<TableItem> dataTable;
    @FXML
    private ChoiceBox<String> dataChoiceBox;
    @FXML
    private ChoiceBox<String> algorithmChoiceBox;
    private ServiceLoader<ClusterAnalysisService> loader;
    private ObservableList<TableItem> tableList = FXCollections.observableArrayList();
    private ObservableList<String> fileList = FXCollections.observableArrayList();
    private ObservableList<String> algorithmList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loader = ServiceLoader.load(ClusterAnalysisService.class);
        getArrayOfAlgorithms();

        TableColumn<TableItem,Integer> columnA = new TableColumn<>("a");
        columnA.setCellValueFactory(new PropertyValueFactory<TableItem,Integer>("a"));

        TableColumn<TableItem,Integer> columnB = new TableColumn<>("b");
        columnB.setCellValueFactory(new PropertyValueFactory<TableItem,Integer>("b"));

        TableColumn<TableItem,Integer> columnC = new TableColumn<>("c");
        columnC.setCellValueFactory(new PropertyValueFactory<TableItem,Integer>("c"));

        TableColumn<TableItem,Integer> columnD = new TableColumn<>("d");
        columnD.setCellValueFactory(new PropertyValueFactory<TableItem,Integer>("d"));

        TableColumn<TableItem,Integer> columnE = new TableColumn<>("e");
        columnE.setCellValueFactory(new PropertyValueFactory<TableItem,Integer>("e"));

        TableColumn<TableItem,Integer> columnF = new TableColumn<>("f");
        columnF.setCellValueFactory(new PropertyValueFactory<TableItem,Integer>("f"));



        dataTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        dataTable.getSelectionModel().setCellSelectionEnabled(true);
        dataTable.setEditable(true);
        dataTable.getColumns().addAll(columnA,columnB,columnC,columnD,columnE,columnF);
        dataTable.setItems(tableList);
        dataTable.refresh();

        dataChoiceBox.setOnAction(this::loadDataToTable);


    }

    @FXML
    private void loadDataToTable(ActionEvent event) {
        tableList.clear();
        Path filePath = Path.of(currentPath + FileSystems.getDefault().getSeparator() + getData());
        csvHandler = new CsvHandler(filePath);
        try {
            csvHandler.readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var data = csvHandler.getDataSet().getData();
        for(String[] row : data){
            TableItem ti  = new TableItem(row[0],row[1],row[2],row[3],row[4],row[5]);
            tableList.add(ti);

        }
        dataTable.setItems(tableList);
        dataTable.refresh();
    }

    @FXML
    private void calculate() throws ClusteringException {
        for(var service : loader){
            if(service.getName() == algorithmChoiceBox.getValue()){
                service.submit(csvHandler.getDataSet());
                resultLabel.setText(String.valueOf(service.getResult()));
                algorithmLabel.setText(service.getName());
            }
        }



    }

    private String getData(){
        String fileName = dataChoiceBox.getValue();
        return fileName;
    }

    private String getAlgorithm(){
        String algorithm = algorithmChoiceBox.getValue();
        return algorithm;
    }

    @FXML
    private void SelectDirectory(ActionEvent e){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        currentPath = directoryChooser.showDialog(null).toPath();
        updateChoiceList();



    }

    private void updateChoiceList(){
        dataChoiceBox.getItems().clear();
        String[] files = getArrayOfFiles();
        dataChoiceBox.getItems().addAll(files);

    }



    private void getArrayOfAlgorithms(){
        algorithmChoiceBox.getItems().clear();
        for(var service:loader){
            algorithmChoiceBox.getItems().add(service.getName());
        }
    }




    private String[] getArrayOfFiles(){
        String[] files;
        try(Stream<Path> stream = Files.walk(currentPath)){
            files = stream.filter(path -> path.toString().endsWith(".csv")).map(path -> path.getFileName().toString()).toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return files;
    }




}
