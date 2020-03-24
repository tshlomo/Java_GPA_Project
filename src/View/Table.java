package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Table {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JTable table = new JTable();

        String[] columns = {"Course", "Year", "Semester", "Final Test", "Credits", "Final Grade"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);

        table.setBackground(Color.white);
        table.setForeground(Color.red);
        Font font = new Font("", 1, 16);
        table.setFont(font);
        table.setRowHeight(30);

        //creating labels
        JLabel GPALabel = new JLabel("GPA");

        //creating text fields
        JTextField textGPA = new JTextField();

        //Adding button
        JButton btnAddGrade = new JButton("Add New Grade");
        JButton btnDeleteGrade = new JButton("Delete Grade");

        //buttons dimensions
        btnAddGrade.setBounds(1,200,125,25);
        btnDeleteGrade.setBounds(1,230,125,25);

        //labels dimensions
        GPALabel.setBounds(750, 200, 100, 25);

        //text fields dimensions
        textGPA.setBounds(780, 200, 100, 25);

        //Adding scroll pane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);

        //Adding pane
        frame.add(pane);

        //Adding text fields
        frame.add(textGPA);

        //Adding labels
        frame.add(GPALabel);

        //Adding buttons
        frame.add(btnAddGrade);
        frame.add(btnDeleteGrade);

        frame.setLayout(null);
        frame.setSize(900, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Object[] row = new Object[100];

        btnAddGrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddGradeScreen screen = new AddGradeScreen();
                screen.addGradeScreen();
            }
        });
    }
}

//        //Setting up an action listener for btnAdd
//        btnAdd.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                row[0]= textCourse.getText();
//                row[1]= textQuiz.getText();
//                row[2]= textFinalTest.getText();
//                row[3]= textCredits.getText();
//                row[4]= Double.parseDouble(textQuiz.getText())*quizWeight + Double.parseDouble(textFinalTest.getText())*finalTestWeight;
//                model.addRow(row);
//            }
//        });
//
//        //Setting up an action listener for btnDelete
//        btnDelete.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int i = table.getSelectedRow();
//                if (i >= 0) {
//                    model.removeRow(i);
//                }
//                else System.out.println("Deletion Error");
//            }
//        });
//
//        //Setting a mouse listener for the selected row
//        table.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e){
//                int i = table.getSelectedRow();
//                textCourse.setText(model.getValueAt(i,0).toString());
//                textQuiz.setText(model.getValueAt(i,1).toString());
//                textFinalTest.setText(model.getValueAt(i,2).toString());
//                textCredits.setText(model.getValueAt(i,3).toString());
//
//            }
//        });
//
//
//    }
//}
