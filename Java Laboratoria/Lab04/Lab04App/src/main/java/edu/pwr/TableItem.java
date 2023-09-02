package edu.pwr;

public class TableItem {
    private String name;
    private String status;
    private int progress;
    private String task;
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }



    public TableItem(String name, String status, int progress, String task,String result) {
        this.name = name;
        this.status = status;
        this.progress = progress;
        this.task = task;
        this.result=result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
