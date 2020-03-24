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

                //Setting the screen properties
                addGradeScreen.setLayout(null);
                addGradeScreen.setSize(900,400);
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

                //Creating ComboBox
                JComboBox courseComboBox = new JComboBox();

                //creating text fields
                JTextField textQuiz = new JTextField();
                JTextField textFinalTest = new JTextField();
                JTextField textCredits = new JTextField();
                JTextField textPrecentage1 = new JTextField();
                JTextField textPrecentage2 = new JTextField();

                //creating buttons
                JButton btnAdd = new JButton("Add");
                JButton btnDelete = new JButton("Delete");

                //labels dimensions
                courseLabel.setBounds(20,20,100,25);
                quizLabel.setBounds(20,50,100,25);
                finalTestLabel.setBounds(20,80,100,25);
                creditsLabel.setBounds(20,110,100,25);
                precentageLabel1.setBounds(280,50,40,25);
                precentageLabel2.setBounds(280,80,40,25);

                //text fields dimensions
                courseComboBox.setBounds(120,20,100,25);
                textQuiz.setBounds(120,50,100,25);
                textFinalTest.setBounds(120,80,100,25);
                textCredits.setBounds(120,110,100,25);
                textPrecentage1.setBounds(240,50,40,25);
                textPrecentage2.setBounds(240,80,40,25);

                //buttons dimensions
                btnAdd.setBounds(20,150,100,25);
                btnDelete.setBounds(130,150,100,25);

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
                addGradeScreen.add(textPrecentage1);
                addGradeScreen.add(textPrecentage2);
            }
        });
    }
}
