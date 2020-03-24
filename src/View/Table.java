package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Table {

    public static final double quizWeight = 0.2;
    public static final double finalTestWeight = 0.8;

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JTable table = new JTable();

        String[] columns = {"Course","Year","Semester","Final Test","Credits","Final Grade"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);

        table.setBackground(Color.white);
        table.setForeground(Color.red);
        Font font = new Font("",1,16);
        table.setFont(font);
        table.setRowHeight(30);

        //creating labels
        JLabel courseLabel = new JLabel("Course");
        JLabel quizLabel = new JLabel("HW/Quiz grade");
        JLabel finalTestLabel = new JLabel("Final Test");
        JLabel creditsLabel = new JLabel("Credits");
        JLabel GPALabel = new JLabel("GPA");
        //creating text fields
        JTextField textCourse = new JTextField();
        JTextField textQuiz = new JTextField();
        JTextField textFinalTest = new JTextField();
        JTextField textCredits = new JTextField();
        JTextField textGPA = new JTextField();
        //creating buttons
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        //labels dimensions
        courseLabel.setBounds(20,200,100,25);
        quizLabel.setBounds(20,230,100,25);
        finalTestLabel.setBounds(20,260,100,25);
        creditsLabel.setBounds(20,290,100,25);
        GPALabel.setBounds(750,200,100,25);
        //text fields dimensions
        textCourse.setBounds(120,200,100,25);
        textQuiz.setBounds(120,230,100,25);
        textFinalTest.setBounds(120,260,100,25);
        textCredits.setBounds(120,290,100,25);
        textGPA.setBounds(780,200,100,25);
        //buttons dimensions
        btnAdd.setBounds(250,200,100,25);
        btnDelete.setBounds(250,230,100,25);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0,0,880,200);

        frame.setLayout(null);
        //Adding pane
        frame.add(pane);
        //Adding text fields
        frame.add(textCourse);
        frame.add(textQuiz);
        frame.add(textFinalTest);
        frame.add(textCredits);
        frame.add(textGPA);
        //Adding buttons
        frame.add(btnAdd);
        frame.add(btnDelete);
        //Adding labels
        frame.add(courseLabel);
        frame.add(quizLabel);
        frame.add(finalTestLabel);
        frame.add(creditsLabel);
        frame.add(GPALabel);

        frame.setSize(900,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Object[] row = new Object[100];

        //Setting up an action listener for btnAdd
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                row[0]= textCourse.getText();
                row[1]= textQuiz.getText();
                row[2]= textFinalTest.getText();
                row[3]= textCredits.getText();
                row[4]= Double.parseDouble(textQuiz.getText())*quizWeight + Double.parseDouble(textFinalTest.getText())*finalTestWeight;
                model.addRow(row);
            }
        });

        //Setting up an action listener for btnDelete
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = table.getSelectedRow();
                if (i >= 0) {
                    model.removeRow(i);
                }
                else System.out.println("Deletion Error");
            }
        });

        //Setting a mouse listener for the selected row
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                int i = table.getSelectedRow();
                textCourse.setText(model.getValueAt(i,0).toString());
                textQuiz.setText(model.getValueAt(i,1).toString());
                textFinalTest.setText(model.getValueAt(i,2).toString());
                textCredits.setText(model.getValueAt(i,3).toString());

            }
        });
    }
}
