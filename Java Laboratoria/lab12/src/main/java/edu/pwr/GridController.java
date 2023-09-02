package edu.pwr;

import javafx.beans.binding.Bindings;
import javafx.scene.layout.GridPane;

import javax.script.ScriptException;
import java.util.ArrayList;


public class GridController {
    private static final int GRID_SIZE = 15;
    private ArrayList<ArrayList<Square>> squareGridArray = new ArrayList<>();



    public void createGrid(GridPane gridPane, ArrayList<Integer> startingCells){
        int id =0;
        Status status;
        for (int row = 0; row < GRID_SIZE; row++) {
            ArrayList<Square> rowArray = new ArrayList<>();
            for (int col = 0; col < GRID_SIZE; col++) {
                if (startingCells.contains(id)){
                    status = Status.BLACK;
                }
                else{
                    status = Status.WHITE;
                }
                Square square = new Square(id,status);
                rowArray.add(square);
                id++;
                GridPane.setRowIndex(square, row);
                GridPane.setColumnIndex(square, col);
                gridPane.getChildren().add(square);

                square.prefWidthProperty().bind(Bindings.divide(gridPane.widthProperty(), GRID_SIZE));
                square.prefHeightProperty().bind(Bindings.divide(gridPane.heightProperty(), GRID_SIZE));
            }
            squareGridArray.add(rowArray);

        }
        this.createNeighbor();
//        for (int row = 0; row < GRID_SIZE; row++) {
//            for (int col = 0; col < GRID_SIZE; col++) {
//                System.out.println(squareGridArray.get(row).get(col).getSquareId());
//                squareGridArray.get(row).get(col).printNeighbor();
//            }}


    }

    private void createNeighbor(){
        for (int row = 0; row < GRID_SIZE; row++){
            for (int col = 0; col < GRID_SIZE; col++) {
                ArrayList<Square> list = new ArrayList<>();
                // Upper neighbors
                if(row -1 < 0){
                    for(int i=0;i<3;i++){
                        list.add(null);
                    }
                }
                else{
                    for(int i=-1;i<2;i++){
                        if(col +i <0 || col+i>=GRID_SIZE){
                            list.add(null);
                        }
                        else{
                            list.add(squareGridArray.get(row-1).get(col+i));
                        }
                    }
                }

                // Same row neighbors
                if(col - 1 < 0 ){
                    list.add(null);
                }
                else{
                    list.add(squareGridArray.get(row).get(col-1));
                }
                if(col+1>=GRID_SIZE){
                    list.add(null);
                }
                else{
                    list.add(squareGridArray.get(row).get(col+1));
                }

                // Lower neighbors
                if(row+1 >= GRID_SIZE){
                    for(int i=0;i<3;i++){
                        list.add(null);
                    }
                }
                else{
                    for(int i=-1;i<2;i++){
                        if(col+i <0 || col +i>=GRID_SIZE){
                            list.add(null);
                        }
                        else{
                            list.add(squareGridArray.get(row+1).get(col+i));
                        }
                    }
                }

                squareGridArray.get(row).get(col).setNeighbor(list);

            }
        }
    }

    public Thread threadTest(ScriptController controller){
        int size = squareGridArray.size();
        return new Thread(()->{
            while(true){
                boolean state;
                Status status;
                for(int i =0; i< squareGridArray.size();i++){
                    for (int j = 0 ; j <squareGridArray.size();j++){
                        Square square = squareGridArray.get(i).get(j);
                        try {
                            state = controller.checkState(square.getJsString());
                        } catch (ScriptException | NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        }
                        if(state){
                            status = Status.BLACK;
                        }
                        else{
                            status = Status.WHITE;
                        }
                        square.setNextStatus(status);

                    }}
                for(int i =0; i< size;i++){
                    for (int j = 0 ; j <size;j++){
                        synchronized (squareGridArray){
                            squareGridArray.get(i).get(j).reprintNext();

                        }
                    }}
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

//    public Thread threadTest(){
//        Thread runningThread = new Thread(()->{
//           while(true){
//                for(int i =0; i< squareGridArray.size();i++){
//                    for (int j = 0 ; j <squareGridArray.size();j++){
//                        Square square = squareGridArray.get(i).get(j);
//                        Status status  = square.getCurrentStatus();
//                        if (status == Status.BLACK){
//                            square.setCurrentStatus(Status.WHITE);
//                        } else if (status == Status.WHITE) {
//                            square.setCurrentStatus(Status.BLACK);
//
//                        }
//                    }
//                }
//                int size = squareGridArray.size();
//               for(int i =0; i< size;i++){
//                   for (int j = 0 ; j <size;j++){
//                       squareGridArray.get(i).get(j).reprint();
//                   }}
//               try {
//                   Thread.sleep(2000);
//               } catch (InterruptedException e) {
//                   throw new RuntimeException(e);
//               }
//           }
//        });
//        return runningThread;
//    }






}

