package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddGradeScreen extends JFrame {

    private static AddGradeScreen addGradeScreen = new AddGradeScreen();

    public AddGradeScreen(){ }

    public static void addGradeScreen() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddGradeScreen();
                //Linking data to ComboBoxes
                String[] year = {"A","B","C"};
                String[] semester ={"A","B","C"};
                //Setting the screen properties
                addGradeScreen.setLayout(null);
                addGradeScreen.setSize(400,400);
                addGradeScreen.setLocationRelativeTo(null);
                addGradeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                addGradeScreen.setVisible(true);

                //creating labels
                JLabel courseLabel = new JLabel("Course");
                JLabel quizLabel = new JLabel("HW/Quiz grade");
                JLabel finalTestLabel = new JLabel("Final Test");
                JLabel creditsLabel = new JLabel("Credits");
                JLabel precentageLabel1 = new JLabel("%");
                JLabel precentageLabel2 = new JLabel("%");
                JLabel yearLabel = new JLabel("Year");
                JLabel semesterLabel = new JLabel("Semester");

                //Creating ComboBox
                JComboBox courseComboBox = new JComboBox();
                JComboBox yearComboBox = new JComboBox(year);
                JComboBox semesterComboBox = new JComboBox(semester);

                //creating text fields
                JTextField textQuiz = new JTextField();
                JTextField textFinalTest = new JTextField();
                JTextField textCredits = new JTextField();
                JTextField textPercentage1 = new JTextField();
                JTextField textPercentage2 = new JTextField();

                //creating buttons
                JButton btnAdd = new JButton("Add");
                JButton btnDelete = new JButton("Delete");

                //labels dimensions
                yearLabel.setBounds(20,20,100,25);
                semesterLabel.setBounds(20,50,100,25);
                courseLabel.setBounds(20,80,100,25);
                quizLabel.setBounds(20,110,100,25);
                finalTestLabel.setBounds(20,140,100,25);
                creditsLabel.setBounds(20,170,100,25);
                precentageLabel1.setBounds(280,110,40,25);
                precentageLabel2.setBounds(280,140,40,25);

                //text fields dimensions
                courseComboBox.setBounds(120,80,100,25);
                textQuiz.setBounds(120,110,100,25);
                textFinalTest.setBounds(120,140,100,25);
                textCredits.setBounds(120,170,100,25);
                textPercentage1.setBounds(240,110,40,25);
                textPercentage2.setBounds(240,140,40,25);

                //buttons dimensions
                btnAdd.setBounds(20,210,100,25);
                btnDelete.setBounds(130,210,100,25);

                //ComboBox dimensions
                yearComboBox.setBounds(120,20,100,25);
                semesterComboBox.setBounds(120,50,100,25);

                //Adding components to the frame
                addGradeScreen.add(courseLabel);
                addGradeScreen.add(quizLabel);
                addGradeScreen.add(finalTestLabel);
                addGradeScreen.add(creditsLabel);
                addGradeScreen.add(courseComboBox);
                addGradeScreen.add(textQuiz);
                addGradeScreen.add(textFinalTest);
                addGradeScreen.add(textCredits);
                addGradeScreen.add(btnAdd);
                addGradeScreen.add(btnDelete);
                addGradeScreen.add(precentageLabel1);
                addGradeScreen.add(precentageLabel2);
                addGradeScreen.add(textPercentage1);
                addGradeScreen.add(textPercentage2);
                addGradeScreen.add(yearComboBox);
                addGradeScreen.add(semesterComboBox);
                addGradeScreen.add(yearLabel);
                addGradeScreen.add(semesterLabel);
            }
        });
    }
}
