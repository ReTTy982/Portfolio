package App;

import gui.MainWindow;
import test.DataHandler;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MainWindow window = new MainWindow();
        window.setContentPane(window.panel);
        window.setTitle("Lab02");
        window.setSize(300,400);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}