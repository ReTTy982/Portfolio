package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import encoder.Encoder;

public class MainWindow extends JFrame {
    private JTextPane textPane1;
    private JButton button1;
    private JPanel panelmain;
    private JButton zapiszStanButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton wybierzSnapaButton;

    private Path path;

    public MainWindow() {

        button1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fc.setAcceptAllFileFilterUsed(false);
            fc.showOpenDialog(panelmain);


            textField1.setText(fc.getSelectedFile().toString());
            path = Paths.get(fc.getSelectedFile().toString());

        }
    });
        zapiszStanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Encoder encoder = new Encoder();
                try {
                    var logs = encoder.runEncoder(path,textField2.getText());
                    textPane1.setText(logBuilder(logs));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        wybierzSnapaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(System.getProperty("user.home"));
                fc.setAcceptAllFileFilterUsed(false);
                fc.showOpenDialog(panelmain);



                textField2.setText(fc.getSelectedFile().toString());
            }
        });
    }
    public void setText(String text){
        textPane1.setText(text);
    }

    private String logBuilder(HashMap<String, List<Path>> logs) {
        String stringBuilder = "Zmiany:" + "\n";
        for (String i : logs.keySet()) {
            stringBuilder += i + "\n";
            for (Path j : logs.get(i)) {
                stringBuilder += j + "\n";
            }
        }
        return stringBuilder;
    }


    public static void main(String[] args) {
        MainWindow h = new MainWindow();
        h.setContentPane(h.panelmain);
        h.setTitle("Zadanie 1");
        h.setSize(300,300);
        h.setVisible(true);

    }
}
