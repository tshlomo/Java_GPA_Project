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
    /**
     * creating a logger object to log messages for
     * our application components.
     */
    private Logger logger= Logger.getLogger(AddGradeScreen.class.getName());

    /**
     * courses array is the array that holds all the courses.
     */

    private static final String[] courses = {"Linear algebra", "Infinitesimal calculus 1", "Computer science Introduction", "Introduction to discrete math","Java Basics"
            , "Probability", "Infinitesimal calculus 2", "Computer structure and switching theory", "Data Structures", "Advanced Programming Workshop","C#"
            , "Computer organization and threshold language", "Database systems", "Computer communication networks", "Graph theory", "Object oriented programming","Advanced Python"
            , "Automatic and formal languages", "Software engineering", "Operating systems", "Algorithm design and analysis"
            , "Computational and Computational Algorithms", "Mathematical tools", "Machine learning", "Programming in the Web Environment","Design patterns"
            , "DevOPS", "Development of server side systems in an open source environment", "Developing a client side in an Android environment", "Compilation Theory"
            , "Involvement in the Israeli society", "Yoga", "Basketball team", "Football team", "Information Society","Developing a client side in an Android environment 2"};

    /**
     * credits array is the array that holds all the credits
     * points, their location in the array matches the courses array.
     */

    private static final Object[] credits = {5, 6.5, 5, 5,4,
            3.5, 5, 4, 4, 3,3,
            2.5, 4, 3.5, 3.5, 5,4,
            4, 4, 3.5, 4,
            4, 5, 3, 3,4,
            3, 3, 3.5, 3.5,
            4, 1, 1, 1, 2,3};
    /**
     * year array is the array that holds all the years
     * that a computer science student is studying.
     */

    private static final String[] year = {"First Year", "Second Year", "Third Year"};

    /**
     * semester array is the array that holds all the semesters.
     */

    private static final String[] semester = {"First Semester", "Second Semester", "Third Semester"};

    /**
     * NUM_OF_SEMESTERS is a constant variable that holds the number of
     * semesters in a student year.
     */

    private static final Integer NUM_OF_SEMESTERS = 3;
    /**
     * creating the frame object.
     */
    private static JFrame frame;
    /**
     * creating all the required panels.
     */
    private static JPanel yearPanel, semesterPanel, coursePanel, quizPanel, testPanel, creditsPanel, addBtnPanel;
    /**
     * creating all the required labels.
     */
    private static JLabel courseLabel,quizLabel,finalTestLabel,creditsLabel,percentageSign,percentageSign2,yearLabel,semesterLabel;
    /**
     * creating all the combo boxes.
     */
    private static JComboBox courseComboBox,yearComboBox,semesterComboBox;
    /**
     * creating all the text fields.
     */
    private static JTextField textQuiz,textFinalTest,textCredits,quizPercentage,testPercentage;
    /**
     * creating the add button.
     */
    private static JButton btnAdd;
    /**
     * creating the CourseDetails object that holds all the course information.
     */
    private CourseDetails courseDetails;
    /**
     * creating the Calculations object that performs all the GPA calculations.
     */
    private Calculations calculations;
    /**
     * creating the TableHomeFrame class object.
     */
    private TableHomeFrame tableHomeFrame;

    /**
     * The class constructor that creates an AddGradeScreen
     * and initializes an TableHomeFrame object.
     *
     * @param tableHomeFrame This class holds the components of the main screen
     * hence providing the interaction between the user and the application.
     */
    public AddGradeScreen(TableHomeFrame tableHomeFrame) {
        //should we use swing utilities here?
        SwingUtilities.invokeLater(() -> {
            this.tableHomeFrame=tableHomeFrame;
            //instantiating the labels
            /**
             * instantiating all the label objects.
             */
            logger.info("creating the labels...");
            courseLabel = new JLabel("Course");
            quizLabel = new JLabel("HW/Quiz grade");
            finalTestLabel = new JLabel("Final Test");
            creditsLabel = new JLabel("Credits");
            percentageSign = new JLabel("%");
            percentageSign2 = new JLabel("%");
            yearLabel = new JLabel("Year");
            semesterLabel = new JLabel("Semester");


            //Instantiating text fields
            /**
             * instantiating all the text field objects.
             */
            logger.info("creating the text fields...");
            textQuiz = new JTextField(3);
            textFinalTest = new JTextField(3);
            textCredits = new JTextField(3);
            quizPercentage = new JTextField(2);
            testPercentage = new JTextField(2);
            textCredits.setEditable(false);

            //Instantiating buttons
            /**
             * instantiating all the button objects.
             */
            logger.info("creating a button...");
            btnAdd = new JButton("Add");

            //Instantiating panels
            /**
             * instantiating all the panel objects.
             */
            logger.info("creating the panels...");
            yearPanel = new JPanel();
            semesterPanel = new JPanel();
            coursePanel = new JPanel();
            quizPanel = new JPanel();
            testPanel = new JPanel();
            creditsPanel = new JPanel();
            addBtnPanel = new JPanel();

            //Instantiating frame
            /**
             * instantiating the frame object.
             */
            logger.info("creating the frame...");
            frame = new JFrame("Add Grade");

            courseDetails=null;
            /**
             * instantiating the Calculations object.
             */
            calculations = new Calculations();
            textCredits.setEditable(false);

            //Instantiating ComboBoxes
            /**
             * instantiating all the combo box objects.
             */
            logger.info("creating the combo boxes...");
            courseComboBox = new JComboBox(courses);
            yearComboBox = new JComboBox(year);
            semesterComboBox = new JComboBox(semester);

            //"Add" button action listener
            /**
             * creating an action listener for the "Add" button
             */
            btnAdd.addActionListener(e -> {
                courseDetails = new CourseDetails(courseComboBox.getSelectedItem().toString(), yearComboBox.getSelectedIndex() + 1, semesterComboBox.getSelectedIndex() + 1
                        , Integer.valueOf(textFinalTest.getText()), Double.parseDouble(textCredits.getText())
                        , calculations.calculate_Final_Grade(Double.valueOf(textFinalTest.getText()),Double.valueOf(testPercentage.getText())
                        ,Double.valueOf(textQuiz.getText()),Double.valueOf(quizPercentage.getText())));
                logger.info("adding a grade to the table...");
                tableHomeFrame.addGrade(courseDetails);
                frame.dispose();
            });

            //"textQuiz" key listener
            /**
             * creating a key listener for the "textQuiz" text field.
             */
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

            //"textFinalTest" key listener
            /**
             * creating a key listener for the "textFinalTest" text field.
             */
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

            //"quizPercentage" key listener
            /**
             * creating a key listener for the "quizPercentage" text field.
             */
            quizPercentage.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent ke) {
                    char c = ke.getKeyChar();
                    String data = quizPercentage.getText();
                    Integer val = -1;
                    try {
                        if(c>='0' && c<='9')
                            val = Integer.parseInt(data+c);
                        else if(c == KeyEvent.VK_BACK_SPACE && !data.isEmpty())
                            val = Integer.parseInt(data);
                        else
                            val = 0;
                        if (val >= 100) {
                            quizPercentage.setText("100");
                            testPercentage.setText("0");
                        }
                    } catch (Exception e) { e.printStackTrace();}
                    if(val >= 100) {
                        ke.consume();
                        logger.info("an attempt to add a grade with a higher score than 100 has been made");
                        //desiredGradeLabelValidation.setText("Your grade can't be higher than 100");
                    }
                    else if (c >= '0' && c <= '9' || c == KeyEvent.VK_BACK_SPACE) {
                        //textFinalTest.setText("");
                        testPercentage.setText(String.valueOf(100-val));
                    }
                    else {
                        ke.consume();
                        //textFinalTest.setText("* Enter only digits from 0 to 100");
                    }
                }
            });
            //"testPercentage" key listener
            /**
             * creating a key listener for the "testPercentage" text field.
             */
            testPercentage.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent ke) {
                    char c = ke.getKeyChar();
                    String data = testPercentage.getText();
                    Integer val = -1;
                    try {
                        if(c>='0' && c<='9')
                            val = Integer.parseInt(data+c);
                        else if(c == KeyEvent.VK_BACK_SPACE && !data.isEmpty())
                            val = Integer.parseInt(data);
                        else
                            val = 0;
                        if (val >= 100) {
                            testPercentage.setText("100");
                            quizPercentage.setText("0");
                        }
                    } catch (Exception e) { e.printStackTrace();}
                    if(val >= 100) {
                        ke.consume();
                        logger.info("an attempt to add a grade with a higher score than 100 has been made");
                        //desiredGradeLabelValidation.setText("Your grade can't be higher than 100");
                    }
                    else if (c >= '0' && c <= '9' || c == KeyEvent.VK_BACK_SPACE) {
                        //textFinalTest.setText("");
                        quizPercentage.setText(String.valueOf(100-val));
                    }
                    else {
                        ke.consume();
                        //textFinalTest.setText("* Enter only digits from 0 to 100");
                    }
                }
            });

            //setting panels layouts
            /**
             * setting the layouts for the panels.
             */
            logger.info("setting the panels layouts");
            yearPanel.setLayout(new FlowLayout());
            semesterPanel.setLayout(new FlowLayout());
            coursePanel.setLayout(new FlowLayout());
            quizPanel.setLayout(new FlowLayout());
            testPanel.setLayout(new FlowLayout());
            creditsPanel.setLayout(new FlowLayout());
            addBtnPanel.setLayout(new FlowLayout());

            //Adding components to the relevant panels
            /**
             * Adding components to the panels.
             */
            logger.info("adding components to the panels");
            coursePanel.add(courseLabel);
            coursePanel.add(courseComboBox);

            quizPanel.add(quizLabel);
            quizPanel.add(textQuiz);
            quizPanel.add(quizPercentage);
            quizPanel.add(percentageSign);

            testPanel.add(finalTestLabel);
            testPanel.add(textFinalTest);
            testPanel.add(testPercentage);
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
            /**
             * setting up the container layout.
             */
            container.setLayout(new GridLayout(8, 1, 5, 5));
            //adding panels to container
            /**
             * Adding the panels to the container.
             */
            logger.info("adding panels to the mainframe");
            container.add(yearPanel);
            container.add(semesterPanel);
            container.add(coursePanel);
            container.add(quizPanel);
            container.add(testPanel);
            container.add(creditsPanel);
            container.add(addBtnPanel);

            //setting combo box action listener to change credits points by the course change
            courseComboBox.addActionListener(e -> textCredits.setText(credits[courseComboBox.getSelectedIndex()].toString()));

            //setting the frame visible
            /**
             * setting up the frame properties.
             */
            logger.info("defining the frame properties");
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
