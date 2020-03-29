package View;

import ViewModel.ViewModel;

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
    private static final Integer NUM_OF_SEMESTERS = 3;

    private ViewModel viewModel;

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

    public AddGradeScreen(){
        //should we use swing utilities here?
        SwingUtilities.invokeLater(() -> {
            //creating viewmodel
            viewModel = new ViewModel();
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
            textQuiz = new JTextField(3);
            textFinalTest = new JTextField(3);
            textCredits = new JTextField(3);
            quizPrecentage = new JTextField(2);
            testPrecentage = new JTextField(2);
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


            //Setting the screen properties



            quizPrecentage.setEditable(false);
            testPrecentage.setEditable(false);

            //button action listener
            btnAdd.addActionListener(e ->
                viewModel.addNewGrade(courseComboBox.getSelectedItem().toString(),((char)(semesterComboBox.getSelectedIndex()+'A'+(yearComboBox.getSelectedIndex()*NUM_OF_SEMESTERS)))
                        ,Integer.valueOf(textQuiz.getText()),Double.parseDouble(textCredits.getText())
                        ,Integer.valueOf(textFinalTest.getText()))
            );


            //setting panels layouts
            yearPanel.setLayout(new FlowLayout());
            semesterPanel.setLayout(new FlowLayout());
            coursePanel.setLayout(new FlowLayout());
            quizPanel.setLayout(new FlowLayout());
            testPanel.setLayout(new FlowLayout());
            creditsPanel.setLayout(new FlowLayout());
            addBtnPanel.setLayout(new FlowLayout());

            //Adding components to the relevant panels
            coursePanel.add(courseLabel);
            coursePanel.add(courseComboBox);

            quizPanel.add(quizLabel);
            quizPanel.add(textQuiz);
            quizPanel.add(quizPrecentage);
            quizPanel.add(percentageSign);

            testPanel.add(finalTestLabel);
            testPanel.add(textFinalTest);
            testPanel.add(testPrecentage);
            testPanel.add(percentageSign2);

            creditsPanel.add(creditsLabel);
            creditsPanel.add(textCredits);

            addBtnPanel.add(btnAdd);

            yearPanel.add(yearLabel);
            yearPanel.add(yearComboBox);

            semesterPanel.add(semesterLabel);
            semesterPanel.add(semesterComboBox);

            Container container = frame.getContentPane();
            //setting container layout
            container.setLayout(new GridLayout(8,1,5,5));
            //attaching panels to the main frame
            container.add(yearPanel);
            container.add(semesterPanel);
            container.add(coursePanel);
            container.add(quizPanel);
            container.add(testPanel);
            container.add(creditsPanel);
            container.add(addBtnPanel);

            //setting combobox action listener to change credits points by the course change
            courseComboBox.addActionListener(e -> textCredits.setText(credits[courseComboBox.getSelectedIndex()].toString()));

            //setting the frame visible
            //frame.setSize(500,500);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public void addGradeScreen() {

    }
}
