package View;

import javax.swing.*;
import java.awt.*;

public class AddGradeScreen extends JFrame {

    private static final String[] courses = {"Linear algebra","Infinitesimal calculus 1","Computer science Introduction","Introduction to discrete math"
            ,"Probability","Infinitesimal calculus 2","Computer structure and switching theory","Data Structures","Advanced Programming Workshop"
            ,"Computer organization and threshold language","Database systems","Computer communication networks","Graph theory","Object oriented programming"
            ,"Automatic and formal languages","Software engineering","Operating systems","Algorithm design and analysis"
            ,"Computational and Computational Algorithms","Mathematical tools","Machine learning","Programming in the Web Environment"
            ,"DevOPS","Development of server side systems in an open source environment","Developing a client side in an Android environment","Compilation Theory"
            ,"Involvement in Israeli society","Yoga","Basketball team","Football team","Information Society"};
    private static final Object[] credits = {5,6.5,5,5,
            3.5,5,4,4,3,
            2.5,4,3.5,3.5,5,
            4,4,3.5,4,
            4,5,3,3,
            3,3,3.5,3.5,
            4,1,1,1,2};
    private static final String[] year = {"First Year","Second Year","Third Year"};
    private static final String[] semester ={"First Semester","Second Semester","Third Semester"};

    private static JFrame frame;
    private static JPanel yearPanel,semesterPanel,coursePanel,quizPanel,testPanel,creditsPanel,addBtnPanel;
    private static JLabel courseLabel;
    private static JLabel quizLabel;
    private static JLabel finalTestLabel;
    private static JLabel creditsLabel;
    private static JLabel percentageSign;
    private static JLabel percentageSign2;
    private static JLabel yearLabel;
    private static JLabel semesterLabel;
    private static JComboBox courseComboBox;
    private static JComboBox yearComboBox;
    private static JComboBox semesterComboBox;
    private static JTextField textQuiz;
    private static JTextField textFinalTest;
    private static JTextField textCredits;
    private static JTextField quizPrecentage;
    private static JTextField testPrecentage;
    private static JButton btnAdd;

    static
    {
        //creating labels
        courseLabel = new JLabel("Course");
        quizLabel = new JLabel("HW/Quiz grade");
        finalTestLabel = new JLabel("Final Test");
        creditsLabel = new JLabel("Credits");
        percentageSign = new JLabel("%");
        percentageSign2 = new JLabel("%");
        yearLabel = new JLabel("Year");
        semesterLabel = new JLabel("Semester");

        //Creating ComboBox
        courseComboBox = new JComboBox(courses);
        yearComboBox = new JComboBox(year);
        semesterComboBox = new JComboBox(semester);

        //creating text fields
        textQuiz = new JTextField();
        textFinalTest = new JTextField();
        textCredits = new JTextField();
        quizPrecentage = new JTextField();
        testPrecentage = new JTextField();
        //creating buttons
        btnAdd = new JButton("Add");

        //creating panels
        yearPanel = new JPanel();
        semesterPanel = new JPanel();
        coursePanel = new JPanel();
        quizPanel = new JPanel();
        testPanel = new JPanel();
        creditsPanel = new JPanel();
        addBtnPanel = new JPanel();
        frame = new JFrame("Add Grade");
    }

    public AddGradeScreen(){
        SwingUtilities.invokeLater(() -> {


            //Setting the screen properties



            quizPrecentage.setEditable(false);
            testPrecentage.setEditable(false);

/*
            //labels dimensions
            yearLabel.setBounds(20,20,100,25);
            semesterLabel.setBounds(20,50,100,25);
            courseLabel.setBounds(20,80,100,25);
            quizLabel.setBounds(20,110,100,25);
            finalTestLabel.setBounds(20,140,100,25);
            creditsLabel.setBounds(20,170,100,25);
            percentageSign.setBounds(262,110,40,25);
            percentageLabel2.setBounds(262,140,40,25);


            //text fields dimensions
            textQuiz.setBounds(120,110,100,25);
            textFinalTest.setBounds(120,140,100,25);
            textCredits.setBounds(120,170,100,25);
            textCredits.setEditable(false);
            quizPrecentage.setBounds(222,110,40,25);
            testPrecentage.setBounds(222,140,40,25);

            //buttons dimensions
            btnAdd.setBounds(20,210,100,25);
*/
            //button action listener
            btnAdd.addActionListener(e -> {
                //ViewModel.addGrade(yearComboBox.getSelectedIndex()+1,semesterComboBox.getSelectedIndex()+1,courseComboBox.getSelectedItem().toString(),textQuiz.toString(),textFinalTest.toString());
            });

            //ComboBox dimensions
            //yearComboBox.setBounds(120,20,152,25);
            //semesterComboBox.setBounds(120,50,152,25);
            //courseComboBox.setBounds(120,80,152,25);
            courseComboBox.setSize(150,25);

            //setting panels and frame layouts
            yearPanel.setLayout(new FlowLayout());
            semesterPanel.setLayout(new FlowLayout());
            coursePanel.setLayout(new FlowLayout());
            quizPanel.setLayout(new FlowLayout());
            testPanel.setLayout(new FlowLayout());
            creditsPanel.setLayout(new FlowLayout());
            addBtnPanel.setLayout(new FlowLayout());
            frame.setLayout(new GridLayout(8,1));

            //Adding components to the frame
            coursePanel.add(courseLabel);
            quizPanel.add(quizLabel);
            testPanel.add(finalTestLabel);
            creditsPanel.add(creditsLabel);
            coursePanel.add(courseComboBox);
            quizPanel.add(textQuiz);
            testPanel.add(textFinalTest);
            creditsPanel.add(textCredits);
            addBtnPanel.add(btnAdd);
            quizPanel.add(quizPrecentage);
            testPanel.add(testPrecentage);
            quizPanel.add(percentageSign);
            testPanel.add(percentageSign2);
            yearPanel.add(yearLabel);
            semesterPanel.add(semesterLabel);
            yearPanel.add(yearComboBox);
            semesterPanel.add(semesterComboBox);

            //attaching panels to the main frame
            frame.add(yearPanel);
            frame.add(semesterPanel);
            frame.add(coursePanel);
            frame.add(quizPanel);
            frame.add(testPanel);
            frame.add(creditsPanel);
            frame.add(addBtnPanel);

            //setting combobox action listener to change credits points by the course change
            courseComboBox.addActionListener(e -> textCredits.setText(credits[courseComboBox.getSelectedIndex()].toString()));

            //setting the frame visible
            frame.setSize(400,400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public void addGradeScreen() {

    }
}
