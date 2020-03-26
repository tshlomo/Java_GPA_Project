package View;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Table extends JTable {
    public Table() {

        String[] year = {"First Year","Second Year","Third Year"};
        String[] semester ={"First Semester","Second Semester","Third Semester"};
        //Objects
        JFrame frame = new JFrame();
        JTable table = new JTable();
        JComboBox courseComboBox = new JComboBox();
        JComboBox yearComboBox = new JComboBox(year);
        JComboBox semesterComboBox = new JComboBox(semester);
        JLabel GPALabel = new JLabel("Current GPA");
        JLabel desiredGradeLabel = new JLabel("Desired Grade");
        JLabel courseLabel = new JLabel("Course");
        JLabel improvingGradesLabel = new JLabel("Fill the details below to see the updated GPA after course final grade improvement");
        JLabel updatedGPA = new JLabel("Updated GPA");
        JLabel yearLabel = new JLabel("Year");
        JLabel semesterLabel = new JLabel("Semester");
        JTextField textGPA = new JTextField();
        JTextField textDesiredGrade = new JTextField();
        JTextField textUpdatedGrade = new JTextField();
        JButton btnAddGrade = new JButton("Add New Grade");
        JButton btnDeleteGrade = new JButton("Delete Grade");
        JButton btnDesiredGradeUpdate = new JButton("Update GPA");
        JScrollPane pane = new JScrollPane(table);

        //table properties
        String[] columns = {"Course", "Year", "Semester", "Final Test", "Credits", "Final Grade"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setBackground(Color.white);
        table.setForeground(Color.red);
        Font font = new Font("", 1, 16);
        table.setFont(font);
        table.setRowHeight(30);
        Object[] row = new Object[100];

        //buttons dimensions
        btnAddGrade.setBounds(1, 300, 125, 25);
        btnDeleteGrade.setBounds(1, 330, 125, 25);
        btnDesiredGradeUpdate.setBounds(89,510,120,25);
        //labels dimensions
        GPALabel.setBounds(700, 300, 150, 25);
        courseLabel.setBounds(5,450,100,25);
        improvingGradesLabel.setBounds(5,370,1000,25);
        improvingGradesLabel.setForeground(Color.red);
        desiredGradeLabel.setBounds(5,480,100,25);
        updatedGPA.setBounds(700,500,100,25);
        yearLabel.setBounds(5,400,100,25);
        semesterLabel.setBounds(5,425,100,25);
        //text fields dimensions
        textGPA.setBounds(780, 300, 100, 25);
        textDesiredGrade.setBounds(110,480,100,25);
        textUpdatedGrade.setBounds(780,500,100,25);
        //scroll pane dimensions
        pane.setBounds(0, 0, 880, 300);
        //combo box dimensions
        courseComboBox.setBounds(59,450,150,25);
        semesterComboBox.setBounds(59,425,150,25);
        yearComboBox.setBounds(59,400,150,25);


        //frame properties
        frame.setTitle("GPA");
        frame.setLayout(null);
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        improvingGradesLabel.setFont(new Font("Arial",Font.BOLD,18));

        //Adding pane
        frame.add(pane);
        //Adding ComboBox
        frame.add(yearComboBox);
        frame.add(semesterComboBox);
        frame.add(courseComboBox);
        //Adding text fields
        frame.add(textGPA);
        frame.add(textDesiredGrade);
        frame.add(textUpdatedGrade);
        //Adding labels
        frame.add(GPALabel);
        frame.add(desiredGradeLabel);
        frame.add(btnDesiredGradeUpdate);
        frame.add(courseLabel);
        frame.add(improvingGradesLabel);
        frame.add(updatedGPA);
        frame.add(yearLabel);
        frame.add(semesterLabel);
        //Adding buttons
        frame.add(btnAddGrade);
        frame.add(btnDeleteGrade);
        frame.add(btnDesiredGradeUpdate);


        //listener for add grade button
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
