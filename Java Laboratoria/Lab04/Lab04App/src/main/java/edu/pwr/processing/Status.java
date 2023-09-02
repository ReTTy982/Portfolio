package edu.pwr.processing;
public class Status {


    private final int taskID;

    private final int progress;

    public int getProgress() {
        return progress;
    }

    public int getTaskID() {
        return taskID;
    }

    public Status(int taskID, int progress) {
        this.taskID = taskID;
        this.progress = progress;
    }
}
