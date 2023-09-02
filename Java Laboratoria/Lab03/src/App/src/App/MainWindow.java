package App;

import org.json.JSONException;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class MainWindow extends JFrame {
    public JPanel panel;
    private JButton btnB;
    private JButton btnC;
    private JButton btnD;
    private JButton btnA;
    private JTextField textField1;
    private JTextPane textPane;
    private JRadioButton radioPL;
    private JRadioButton radioEN;
    public JMenuBar menuBar;
    public JMenu file;

    private MusicQuizz quizz;


    public void setButtons(List<String> list){
        btnA.setText(list.get(0));
        btnB.setText(list.get(1));
        btnC.setText(list.get(2));
        btnD.setText(list.get(3));

    }

    public void setQuizz(MusicQuizz quizz){
        this.quizz = quizz;
    }


    public void addText(String text) throws BadLocationException {
        StyledDocument doc = textPane.getStyledDocument();
        doc.insertString(0,text,null);

    }

    public void setQuestionType(String type){
        if (type == "open"){
            btnA.setEnabled(false);
            btnB.setEnabled(false);
            btnC.setEnabled(false);
            btnD.setEnabled(false);
            textField1.setEnabled(true);
        }
        else if (type == "closed"){
            btnA.setEnabled(true);
            btnB.setEnabled(true);
            btnC.setEnabled(true);
            btnD.setEnabled(true);
            textField1.setEnabled(false);
        }
    }
public MainWindow() {



    btnA.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                quizz.questionChecker(btnA.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        }
    });

    btnB.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                quizz.questionChecker(btnB.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        }
    });

    btnC.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                quizz.questionChecker(btnC.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        }
    });

    btnD.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                quizz.questionChecker(btnD.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
    textField1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                quizz.questionChecker(textField1.getText());
                textField1.setText("");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
}



}
