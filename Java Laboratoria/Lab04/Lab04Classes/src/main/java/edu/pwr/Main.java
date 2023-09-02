package edu.pwr;
import edu.pwr.processors.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        UpperCaseProc upperCaseProc = new UpperCaseProc();
        FiftyFifityProc fiftyFifityProc = new FiftyFifityProc();
        LowerCaseProc lowerCaseProc = new LowerCaseProc();
        MyStatusListener myStatusListener = new MyStatusListener();


        upperCaseProc.submitTask("abs",myStatusListener);
        upperCaseProc.submitTask("abs",myStatusListener);

        upperCaseProc.submitTask("abs",myStatusListener);


        Thread.sleep(80);
        myStatusListener.getStatuses();
        for (int i = 0; i<100;i++){
            Thread.sleep(20);
            System.out.println(upperCaseProc.getResult());
        }











    }
}