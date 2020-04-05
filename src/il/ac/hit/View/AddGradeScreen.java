package il.ac.hit.View;

import il.ac.hit.Interfaces.ISimpleActions;
import il.ac.hit.Model.Calculations;
import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.ViewModel.CourseDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddGradeScreen {

    private static final String[] courses = {"Linear algebra", "Infinitesimal calculus 1", "Computer science Introduction", "Introduction to discrete math"
            , "Probability", "Infinitesimal calculus 2", "Computer structure and switching theory", "Data Structures", "Advanced Programming Workshop"
            , "Computer organization and threshold language", "Database systems", "Computer communication networks", "Graph theory", "Object oriented programming"
            , "Automatic and formal languages", "Software engineering", "Operating systems", "Algorithm design and analysis"
            , "Computational and Computational Algorithms", "Mathematical tools", "Machine learning", "Programming in the Web Environment"
            , "DevOPS", "Development of server side systems in an open source environment", "Developing a client side in an Android environment", "Compilation Theory"
            , "Involvement in Israeli society", "Yoga", "Basketball team", "Football team", "Information Society"};
    private static final Object[] credits = {5, 6.5, 5, 5,
            3.5, 5, 4, 4, 3,
            2.5, 4, 3.5, 3.5, 5,
            4, 4, 3.5, 4,
            4, 5, 3, 3,
            3, 3, 3.5, 3.5,
            4, 1, 1, 1, 2};
    private static final String[] year = {"First Year", "Second Year", "Third Year"};
    private static final String[] semester = {"First Semester", "Second Semester", "Third Semester"};
    private static final Integer NUM_OF_SEMESTERS = 3;

    private JFrame frame;
    private JPanel yearPanel, semesterPanel, coursePanel, quizPanel, testPanel, creditsPanel, addBtnPanel, inputErrPanel;
    private JLabel courseLabel;
    private JLabel quizLabel;
    private JLabel finalTestLabel;
    private JLabel creditsLabel;
    private JLabel percentageSign;
    private JLabel percentageSign2;
    private JLabel yearLabel;
    private JLabel semesterLabel;
    private JComboBox courseComboBox;
    private JComboBox yearComboBox;
    private JComboBox semesterComboBox;
    private JTextField textQuiz;
    private JTextField textFinalTest;
    private JTextField textCredits;
    private JTextField quizPrecentage;
    private JTextField testPrecentage;
    private JButton btnAdd;
    private JLabel inputErrLabel;

    private CourseDetails courseDetails;
    private Calculations calculations;
    private ISimpleActions tableHomeFrame;


    public AddGradeScreen(ISimpleActions tableHomeFrame) {
        this.tableHomeFrame=tableHomeFrame;
        //creating labels
        courseLabel = new JLabel("Course");
        quizLabel = new JLabel("HW/Quiz grade");
        finalTestLabel = new JLabel("Final Test");
        creditsLabel = new JLabel("Credits");
        percentageSign = new JLabel("%");
        percentageSign2 = new JLabel("%");
        yearLabel = new JLabel("Year");
        semesterLabel = new JLabel("Semester");
        inputErrLabel = new JLabel("");
        inputErrLabel.setForeground(Color.RED);

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
        textCredits.setEditable(false);
        //Linear Algebra first credit points is 5
        textCredits.setText("5");

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
        inputErrPanel = new JPanel();

        //creating frame
        frame = new JFrame("Add Grade");

        courseDetails=null;
        calculations = new Calculations();
        textCredits.setEditable(false);

        //button action listener
        btnAdd.addActionListener(e -> {
            if (textQuiz.getText().length() == 0 || textFinalTest.getText().length() == 0 || quizPrecentage.getText().length() == 0 || testPrecentage.getText().length() == 0)
            {
                JOptionPane.showMessageDialog(null, "Please input all values");
            } else {
                courseDetails = new CourseDetails(courseComboBox.getSelectedItem().toString(), yearComboBox.getSelectedIndex() + 1, semesterComboBox.getSelectedIndex() + 1
                        , Integer.valueOf(textFinalTest.getText()), Double.parseDouble(textCredits.getText())
                        , calculations.calculate_Final_Grade(Double.valueOf(textFinalTest.getText()),Double.valueOf(testPrecentage.getText())
                        ,Double.valueOf(textQuiz.getText()),Double.valueOf(quizPrecentage.getText())));

                try {
                    tableHomeFrame.addGrade(courseDetails);
                } catch (DBActionsException ex) {
                    ex.printStackTrace();
                }
            }
        });

        textQuiz.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                String data = textQuiz.getText();
                Integer val = -1;
                if(data.length()>1)
                    if(c >='0' && c <= '9')
                        val = Integer.parseInt(data+c);
                    else if(c == KeyEvent.VK_BACK_SPACE && !data.isEmpty())
                        val = Integer.parseInt(data.substring(0,data.length()-1));
                    else if (!data.isEmpty())
                        val = Integer.parseInt(data);
                    else
                        val = 0;
                if (val >= 100) {
                    textQuiz.setText("100");
                    ke.consume();
                    inputErrLabel.setText("Your grade can't be higher than 100");
                }
                else if (c >= '0' && c <= '9' || c == KeyEvent.VK_BACK_SPACE) {
                    inputErrLabel.setText("");
                }
                else {
                    ke.consume();
                    inputErrLabel.setText("* Enter only digits from 0 to 100");
                }
            }
        });

        textFinalTest.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                String data = textFinalTest.getText();
                Integer val = -1;
                if(data.length()>1)
                if(c >='0' && c <= '9')
                    val = Integer.parseInt(data+c);
                else if(c == KeyEvent.VK_BACK_SPACE && !data.isEmpty())
                    val = Integer.parseInt(data.substring(0,data.length()-1));
                else if (!data.isEmpty())
                    val = Integer.parseInt(data);
                else
                    val = 0;
                if (val >= 100) {
                    textFinalTest.setText("100");
                    ke.consume();
                    inputErrLabel.setText("Your grade can't be higher than 100");
                }
                else if (c >= '0' && c <= '9' || c == KeyEvent.VK_BACK_SPACE) {
                    inputErrLabel.setText("");
                }
                else {
                    ke.consume();
                    inputErrLabel.setText("* Enter only digits from 0 to 100");
                }
            }
        });

        quizPrecentage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                String data = quizPrecentage.getText();
                Integer val = -1;
                if(c >='0' && c <= '9')
                    val = Integer.parseInt(data+c);
                else if(c == KeyEvent.VK_BACK_SPACE && !data.isEmpty())
                    val = Integer.parseInt(data.substring(0,data.length()-1));
                else if (!data.isEmpty())
                    val = Integer.parseInt(data);
                else
                    val = 0;
                if (val >= 100) {
                    quizPrecentage.setText("100");
                    testPrecentage.setText("0");
                    ke.consume();
                    inputErrLabel.setText("Your grade can't be higher than 100");
                }
                else if (c >= '0' && c <= '9' || c == KeyEvent.VK_BACK_SPACE) {
                    inputErrLabel.setText("");
                    testPrecentage.setText(String.valueOf(100-val));
                    if(c == KeyEvent.VK_BACK_SPACE && val == 0)
                        quizPrecentage.setText(String.valueOf(val));
                }
                else {
                    ke.consume();
                    inputErrLabel.setText("* Enter only digits from 0 to 100");
                }
            }
        });

        testPrecentage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                String data = testPrecentage.getText();
                Integer val = -1;
                if(c >='0' && c <= '9')
                    val = Integer.parseInt(data+c);
                else if(c == KeyEvent.VK_BACK_SPACE && !data.isEmpty())
                    val = Integer.parseInt(data.substring(0,data.length()-1));
                else if (!data.isEmpty())
                    val = Integer.parseInt(data);
                else
                    val = 0;
                if (val >= 100) {
                    testPrecentage.setText("100");
                    quizPrecentage.setText("0");
                    ke.consume();
                    inputErrLabel.setText("Your grade can't be higher than 100");
                }
                else if (c >= '0' && c <= '9' || c == KeyEvent.VK_BACK_SPACE) {
                    inputErrLabel.setText("");
                    quizPrecentage.setText(String.valueOf(100-val));
                    if(c == KeyEvent.VK_BACK_SPACE && val == 0)
                        testPrecentage.setText(String.valueOf(val));
                }
                else {
                    ke.consume();
                    inputErrLabel.setText("* Enter only digits from 0 to 100");
                }
            }
        });

        //setting panels layouts
        yearPanel.setLayout(new FlowLayout());
        semesterPanel.setLayout(new FlowLayout());
        coursePanel.setLayout(new FlowLayout());
        quizPanel.setLayout(new FlowLayout());
        testPanel.setLayout(new FlowLayout());
        creditsPanel.setLayout(new FlowLayout());
        addBtnPanel.setLayout(new FlowLayout());
        inputErrLabel.setLayout(new FlowLayout());

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

        inputErrPanel.add(inputErrLabel);

        yearPanel.add(yearLabel);
        yearPanel.add(yearComboBox);

        semesterPanel.add(semesterLabel);
        semesterPanel.add(semesterComboBox);

        Container container = frame.getContentPane();
        //setting container layout
        container.setLayout(new GridLayout(8, 1, 5, 5));
        //attaching panels to the main frame
        container.add(yearPanel);
        container.add(semesterPanel);
        container.add(coursePanel);
        container.add(quizPanel);
        container.add(testPanel);
        container.add(creditsPanel);
        container.add(inputErrPanel);
        container.add(addBtnPanel);

        //setting combobox action listener to change credits points by the course change
        courseComboBox.addActionListener(e -> textCredits.setText(credits[courseComboBox.getSelectedIndex()].toString()));

        //setting the frame visible
        //frame.setSize(500,500);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
