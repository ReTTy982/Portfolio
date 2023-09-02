package edu.pwr;

import edu.pwr.processing.Status;
import edu.pwr.processing.StatusListener;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MyStatusListener implements StatusListener {
    private final ConcurrentHashMap<Integer,Status> map;

    public MyStatusListener() {
        this.map = new ConcurrentHashMap();
    }


    public void statusChanged(Status s) {
        if(s.getProgress()>=100){
            map.remove(s.getTaskID());
            return;
        }
        map.put(s.getTaskID(),s);

    }

    public synchronized List<String> getStatuses(){
        //return map.values().stream().map(Status::toString).toList();<
        return map.values().stream().map(Status::toString).toList();
    }

}
