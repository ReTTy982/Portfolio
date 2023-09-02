package edu.pwr;

import edu.pwr.processing.Status;
import edu.pwr.processing.StatusListener;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MyStatusListener implements StatusListener {
    private final ConcurrentHashMap<Integer,Status> map;
    TableItem tableItem;
    TableView<TableItem> tableView;
    String result;

    public MyStatusListener(TableItem tableItem,TableView<TableItem> tableView, String result) {
        this.map = new ConcurrentHashMap();
        this.tableItem = tableItem;
        this.tableView = tableView;
        this.result = result;

    }
    public MyStatusListener(){
        this.map = new ConcurrentHashMap<>();
    }


    public void statusChanged(Status s) {
        if(s.getProgress()>=100){
            if (result!=null){
                tableItem.setProgress(100);

                tableItem.setResult(result);
                tableView.refresh();
                return;

            }

        }
        tableItem.setProgress(s.getProgress());
        tableView.refresh();


    }

    public synchronized List<String> getStatuses(){
        return map.values().stream().map(Status::toString).toList();
    }

}
