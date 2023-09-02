package App;

import org.json.JSONException;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, JSONException, BadLocationException {
        MainWindow window = new MainWindow();
        window.setContentPane(window.panel);
        window.setTitle("Lab02");
        window.setSize(300,400);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MusicQuizz quiz = new MusicQuizz(window);
        window.setQuizz(quiz);


        Locale locale = new Locale("en","EN");
        ResourceBundle resourceBundle = ResourceBundle.getBundle()




    }
}
