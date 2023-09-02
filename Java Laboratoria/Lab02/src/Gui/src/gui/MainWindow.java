package gui;

import test.CsvElement;
import test.DataHandler;
import test.FileBrowser;
import test.FileNode;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class MainWindow extends JFrame {
    FileBrowser fileBrowser = new FileBrowser();

    String[] columnNames ={"Ciśnienie [hPa]", "Temperatura [℃]", "Wilgotność [%]"};
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    private JTree tree;
    private JTextPane textPane1;
    public JPanel panel;
    private JScrollPane scrollPane;
    private JButton button1;
    private JTable table1;
    private JScrollPane Scroll2;
    private JTable table2;
    private JLabel label;
    private DataHandler dataHandler = new DataHandler();

    public MainWindow() {

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int approved = fc.showOpenDialog(MainWindow.this);
                if (approved == JFileChooser.APPROVE_OPTION){

                    Path path  = fc.getSelectedFile().toPath();

                    DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();


                    try {
                        DefaultMutableTreeNode newRoot= fileBrowser.addNodes(path);
                        treeModel.setRoot(newRoot);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    treeModel.reload();

                }
            }
        });
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath treePath = e.getNewLeadSelectionPath();
                if (treePath != null){
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    FileNode node = (FileNode) selectedNode.getUserObject();
                    File file = node.getFile();
                    CsvElement element = new CsvElement(file);
                    try {
                        if (dataHandler.getFilesWeakHashMap().get(file.toPath()) == null){
                            element.readFile();
                            dataHandler.getFilesWeakHashMap().put(file.toPath(),element);
                            label.setText("Dysk");

                        }
                        else{
                            label.setText("Pamiec");
                            element = dataHandler.getFilesWeakHashMap().get(file.toPath());

                        }
                        //element.readFile();
                        dataHandler.populateTable(element,table1,table2);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }
        });
    }

    private void createUIComponents() throws IOException {
        tree = new JTree(new DefaultTreeModel(new DefaultMutableTreeNode("Test")));
        table1 = new JTable();
        table2 = new JTable();
    }

    private void populateTable(List<List<String>> records, JTable table){
        tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        int n = records.size();
        for (int i=0;i<n;i++){
            Object[] objects = {records.get(i).get(0),
                    records.get(i).get(1),
                    records.get(i).get(2),
                    records.get(i).get(3)};
            tableModel.addRow(objects);
            System.out.println(tableModel.getColumnCount());

        }
        table.repaint();



    }

}
