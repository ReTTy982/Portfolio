package edu.pwr;

import javafx.scene.control.Button;

import java.util.ArrayList;

public class Square extends Button {
    private int squareId;
    private Status currentStatus;
    private Status nextStatus;
    private ArrayList<Square> neighbors = new ArrayList<>();
    private double size = 30;
    public Square(int id, Status status){
        this.squareId = id;
        this.currentStatus = status;
        this.reprint();

    }
    public void setNeighbor(ArrayList<Square> list){
        this.neighbors = list;
    }
    public void printNeighbor(){
        for (var neighbor : neighbors){
            if (neighbor!=null){
                System.out.print(neighbor.getSquareId() + " ");
            }
            else{
                System.out.print("null ");
            }

        }
        System.out.println("\n");
    }

    public String getJsString(){
        String s = "";
        for(var neighbor : this.neighbors){
            if (neighbor == null || neighbor.getCurrentStatus() == Status.WHITE){
                s += "0";
            }
            else{
                s += "1";
            }
        }
        return s;
    }
    public void reprint(){
        setStyle(String.format("-fx-background-color: %s; -fx-border-color: black; -fx-border-width: 2px;",this.currentStatus.toString()));
    }
    public void reprintNext( ){
        this.currentStatus = this.nextStatus;
        setStyle(String.format("-fx-background-color: %s; -fx-border-color: black; -fx-border-width: 2px;",this.currentStatus.toString()));
    }

    public void setNextStatus(Status status){
        this.nextStatus = status;
    }



    public int getSquareId() {
        return squareId;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Status status){
        this.currentStatus = status;
    }
}
