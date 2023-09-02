package edu.pwr;

import edu.pwr.controler.ClassHandler;
import edu.pwr.controler.ListenerHandler;
import edu.pwr.controler.MyClassLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TableView<TableItem> classTable;

    @FXML
    private TableColumn<TableItem, String> nameColumn;

    @FXML
    private TableColumn<TableItem, Integer> progressColumn;

    @FXML
    private TextField taskField;

    @FXML
    private TableColumn<TableItem, String> statusColumn;

    @FXML
    private TableColumn<TableItem,String> taskColumn;
    @FXML
    private TableColumn<TableItem,String> resultColumn;
    private ObservableList<TableItem> tableList = FXCollections.observableArrayList();


    @FXML
    private ListView<String> classView;
    private File currentPath;
    private HashMap<Path,ClassHandler> loadedClassMap = new HashMap<>();


    @FXML
    private void selectDirectory(ActionEvent e) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException, IOException {
        DirectoryChooser fileChooser = new DirectoryChooser();
        //File currentDirectory = new File("C:\\Users\\polek\\Desktop\\Projekty\\JavaLaby\\Lab04\\Lab04App\\src\\main\\resources\\edu\\pwr\\processors"); // TODO CHANGE DIR TO LOCATION OF JAR
        File currentDirectory = new File(System.getProperty("user.home"));
        fileChooser.setInitialDirectory(currentDirectory);
        currentPath = fileChooser.showDialog(null);
    }

    @FXML
    private void refresh() throws IOException {
        MyClassLoader myClassLoader = new MyClassLoader(currentPath.toPath());
        List<String> classesList = myClassLoader.loadAllClassesLocations();
        classView.getItems().clear();
        classView.getItems().addAll(classesList);

    }

    @FXML
    private void loadClass() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        String className = classView.getSelectionModel().getSelectedItem();
        Path path = getClassPath(className);
        if(loadedClassMap.get(path)==null){
            List<TableItem> foundItems = tableList.filtered(item -> item.getName().equals(className));
            TableItem foundItem = null;
            if(!foundItems.isEmpty()){
                foundItem = foundItems.get(0);
            }

            MyClassLoader myClassLoader = new MyClassLoader(currentPath.toPath());
            Class<?> myClass = myClassLoader.loadClass(className);
            ClassHandler classHandler = new ClassHandler(myClass);
            loadedClassMap.put(path,classHandler);
            if(foundItem!=null){
                foundItem.setStatus("loaded");
            }
            else{
                tableList.add(new TableItem(className,"loaded",0,classHandler.getInfo(),null));
                classTable.setItems(tableList);

            }


            classTable.refresh();
        }

    }

    @FXML
    private void unloadClass(){
        String className  = classView.getSelectionModel().getSelectedItem();
        Path path = getClassPath(className);
        if(loadedClassMap.get(path)!=null){
            loadedClassMap.remove(path);
            TableItem foundItem = tableList.filtered(item -> item.getName().equals(className)).get(0);
            foundItem.setStatus("unloaded");
            classTable.refresh();
            System.gc();
        }


    }

    @FXML
    private void executeTask() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException {
        String className = classView.getSelectionModel().getSelectedItem();
        Path path = getClassPath(className);
        ClassHandler classHandler = loadedClassMap.get(path);

        if(classHandler!=null){
            TableItem foundItem = tableList.filtered(item -> item.getName().equals(className)).get(0);
            MyStatusListener myListener = new MyStatusListener(foundItem,classTable, classHandler.getResult());
            classHandler.submitTask(taskField.getText(),myListener);

        }



        }


    @FXML
    private void showStatus() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String className  = classView.getSelectionModel().getSelectedItem();
        Path path = getClassPath(className);
        ClassHandler classHandler = loadedClassMap.get(path);
        TableItem foundItem = tableList.filtered(item -> item.getName().equals(className)).get(0);
        foundItem.setResult(classHandler.getResult());
        classTable.refresh();

    }


    private Path getClassPath(String className){
        className = className.replace(".",FileSystems.getDefault().getSeparator());
        className = currentPath + className;
        Path path = new File(className).toPath();
        return path;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<TableItem, String>("name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<TableItem, String>("status"));
        progressColumn.setCellValueFactory(new PropertyValueFactory<TableItem, Integer>("progress"));
        taskColumn.setCellValueFactory(new PropertyValueFactory<TableItem,String>("task"));
        resultColumn.setCellValueFactory(new PropertyValueFactory<TableItem,String>("result"));
        classTable.setItems(tableList);
    }
}