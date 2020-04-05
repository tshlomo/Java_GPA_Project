package View;

import Model.Calculations;
import ViewModel.CourseDetails;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

/**
 * This class holds the components of the add grade screen
 * giving the user the ability to add the desired grades
 * to the main GPA table.
 */

public class AddGradeScreen extends JFrame {

    private Logger logger= Logger.getLogger(AddGradeScreen.class.getName());

    private static final String[] courses = {"Linear algebra", "Infinitesimal calculus 1", "Computer science Introduction", "Introduction to discrete math","Java Basics"
            , "Probability", "Infinitesimal calculus 2", "Computer structure and switching theory", "Data Structures", "Advanced Programming Workshop","C#"
            , "Computer organization and threshold language", "Database systems", "Computer communication networks", "Graph theory", "Object oriented programming","Advanced Python"
            , "Automatic and formal languages", "Software engineering", "Operating systems", "Algorithm design and analysis"
            , "Computational and Computational Algorithms", "Mathematical tools", "Machine learning", "Programming in the Web Environment","Design patterns"
            , "DevOPS", "Development of server side systems in an open source environment", "Developing a client side in an Android environment", "Compilation Theory"
            , "Involvement in the Israeli society", "Yoga", "Basketball team", "Football team", "Information Society","Developing a client side in an Android environment 2"};
    private static final Object[] credits = {5, 6.5, 5, 5,4,
            3.5, 5, 4, 4, 3,3,
            2.5, 4, 3.5, 3.5, 5,4,
            4, 4, 3.5, 4,
            4, 5, 3, 3,4,
            3, 3, 3.5, 3.5,
            4, 1, 1, 1, 2,3};
    private static final String[] year = {"First Year", "Second Year", "Third Year"};
    private static final String[] semester = {"First Semester", "Second Semester", "Third Semester"};
    private static final Integer NUM_OF_SEMESTERS = 3;

    private static JFrame frame;
    private static JPanel yearPanel, semesterPanel, coursePanel, quizPanel, testPanel, creditsPanel, addBtnPanel;
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

    private CourseDetails courseDetails;
    private Calculations calculations;
    private TableHomeFrame tableHomeFrame;


    public AddGradeScreen(TableHomeFrame tableHomeFrame) {
        //should we use swing utilities here?
        SwingUtilities.invokeLater(() -> {
            this.tableHomeFrame=tableHomeFrame;
            //creating labels
            logger.info("creating the labels...");
            courseLabel = new JLabel("Course");
            quizLabel = new JLabel("HW/Quiz grade");
            finalTestLabel = new JLabel("Final Test");
            creditsLabel = new JLabel("Credits");
            percentageSign = new JLabel("%");
            percentageSign2 = new JLabel("%");
            yearLabel = new JLabel("Year");
            semesterLabel = new JLabel("Semester");


            //creating text fields
            logger.info("creating the text fields...");
            textQuiz = new JTextField(3);
            textFinalTest = new JTextField(3);
            textCredits = new JTextField(3);
            quizPrecentage = new JTextField(2);
            testPrecentage = new JTextField(2);
            textCredits.setEditable(false);

            //creating buttons
            logger.info("creating a button...");
            btnAdd = new JButton("Add");

            //creating panels
            logger.info("creating the panels...");
            yearPanel = new JPanel();
            semesterPanel = new JPanel();
            coursePanel = new JPanel();
            quizPanel = new JPanel();
            testPanel = new JPanel();
            creditsPanel = new JPanel();
            addBtnPanel = new JPanel();

            //creating frame
            logger.info("creating the frame...");
            frame = new JFrame("Add Grade");

            courseDetails=null;
            calculations = new Calculations();
            textCredits.setEditable(false);

            //Creating ComboBox
            logger.info("creating the combo boxes...");
            courseComboBox = new JComboBox(courses);
            yearComboBox = new JComboBox(year);
            semesterComboBox = new JComboBox(semester);

            //button action listener
            btnAdd.addActionListener(e -> {
                courseDetails = new CourseDetails(courseComboBox.getSelectedItem().toString(), yearComboBox.getSelectedIndex() + 1, semesterComboBox.getSelectedIndex() + 1
                        , Integer.valueOf(textFinalTest.getText()), Double.parseDouble(textCredits.getText())
                        , calculations.calculate_Final_Grade(Double.valueOf(textFinalTest.getText()),Double.valueOf(testPrecentage.getText())
                        ,Double.valueOf(textQuiz.getText()),Double.valueOf(quizPrecentage.getText())));
                logger.info("adding a grade to the table...");
                tableHomeFrame.addGrade(courseDetails);
                frame.dispose();
            });

            //text quiz action listener
            textQuiz.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent ke) {
                    char c = ke.getKeyChar();
                    String data = textQuiz.getText();
                    Integer val = -1;
                    if(data.length()>1)
                        try {
                            val = Integer.parseInt(data+c);
                            if (val >= 100) {
                                textQuiz.setText("100");
                            }
                        } catch (Exception e) {e.printStackTrace();}
                    if(val >= 100) {
                        ke.consume();
                        logger.info("an attempt to add a grade with a higher score than 100 has been made");
                        //desiredGradeLabelValidation.setText("Your grade can't be higher than 100");
                    }
                    else if (c >= '0' && c <= '9' || c == KeyEvent.VK_BACK_SPACE) {
                        //textQuiz.setText("");
                    }
                    else {
                        ke.consume();
                        //textQuiz.setText("* Enter only digits from 0 to 100");
                    }
                }
            });

            //text final quiz action listener
            textFinalTest.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent ke) {
                    char c = ke.getKeyChar();
                    String data = textFinalTest.getText();
                    Integer val = -1;
                    if(data.length()>1)
                        try {
                            val = Integer.parseInt(data+c);
                            if (val >= 100) {
                                textFinalTest.setText("100");
                            }
                        } catch (Exception e) {e.printStackTrace();}
                    if(val >= 100) {
                        ke.consume();
                        logger.info("an attempt to add a grade with a higher score than 100 has been made");
                        //desiredGradeLabelValidation.setText("Your grade can't be higher than 100");
                    }
                    else if (c >= '0' && c <= '9' || c == KeyEvent.VK_BACK_SPACE) {
                        //textFinalTest.setText("");
                    }
                    else {
                        ke.consume();
                        //textFinalTest.setText("* Enter only digits from 0 to 100");
                    }
                }
            });

            //quiz percentage action listener
            quizPrecentage.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent ke) {
                    char c = ke.getKeyChar();
                    String data = quizPrecentage.getText();
                    Integer val = -1;
                    try {
                        if(c>='0' && c<='9')
                            val = Integer.parseInt(data+c);
                        else if(c == KeyEvent.VK_BACK_SPACE && !data.isEmpty())
                            val = Integer.parseInt(data);
                        else
                            val = 0;
                        if (val >= 100) {
                            quizPrecentage.setText("100");
                            testPrecentage.setText("0");
                        }
                    } catch (Exception e) { e.printStackTrace();}
                    if(val >= 100) {
                        ke.consume();
                        logger.info("an attempt to add a grade with a higher score than 100 has been made");
                        //desiredGradeLabelValidation.setText("Your grade can't be higher than 100");
                    }
                    else if (c >= '0' && c <= '9' || c == KeyEvent.VK_BACK_SPACE) {
                        //textFinalTest.setText("");
                        testPrecentage.setText(String.valueOf(100-val));
                    }
                    else {
                        ke.consume();
                        //textFinalTest.setText("* Enter only digits from 0 to 100");
                    }
                }
            });
            //test percentage action listener
            testPrecentage.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent ke) {
                    char c = ke.getKeyChar();
                    String data = testPrecentage.getText();
                    Integer val = -1;
                    try {
                        if(c>='0' && c<='9')
                            val = Integer.parseInt(data+c);
                        else if(c == KeyEvent.VK_BACK_SPACE && !data.isEmpty())
                            val = Integer.parseInt(data);
                        else
                            val = 0;
                        if (val >= 100) {
                            testPrecentage.setText("100");
                            quizPrecentage.setText("0");
                        }
                    } catch (Exception e) { e.printStackTrace();}
                    if(val >= 100) {
                        ke.consume();
                        logger.info("an attempt to add a grade with a higher score than 100 has been made");
                        //desiredGradeLabelValidation.setText("Your grade can't be higher than 100");
                    }
                    else if (c >= '0' && c <= '9' || c == KeyEvent.VK_BACK_SPACE) {
                        //textFinalTest.setText("");
                        quizPrecentage.setText(String.valueOf(100-val));
                    }
                    else {
                        ke.consume();
                        //textFinalTest.setText("* Enter only digits from 0 to 100");
                    }
                }
            });

            //setting panels layouts
            logger.info("setting the panels layouts");
            yearPanel.setLayout(new FlowLayout());
            semesterPanel.setLayout(new FlowLayout());
            coursePanel.setLayout(new FlowLayout());
            quizPanel.setLayout(new FlowLayout());
            testPanel.setLayout(new FlowLayout());
            creditsPanel.setLayout(new FlowLayout());
            addBtnPanel.setLayout(new FlowLayout());

            //Adding components to the relevant panels
            logger.info("adding components to the panels");
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
            container.setLayout(new GridLayout(8, 1, 5, 5));
            //attaching panels to the main frame
            logger.info("adding panels to the mainframe");
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
            logger.info("defining the frame properties");
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
