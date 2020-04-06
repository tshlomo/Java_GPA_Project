package il.ac.hit.View;

import il.ac.hit.Interfaces.ISimpleActions;
import il.ac.hit.Model.Calculations;
import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.ViewModel.CourseDetails;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class holds the components of the add grade screen
 * giving the user the ability to add the desired grades
 * to the main GPA table.
 */
public class AddGradeScreen {
    /**
     * creating a logger object to log messages for
     * our application components.
     */
    private Logger logger= Logger.getLogger(AddGradeScreen.class.getName());

    /**
     * courses array is the array that holds all the courses.
     */
    //Final object with the course names to choose from
    private static final String[] courses = {"Linear algebra", "Infinitesimal calculus 1", "Computer science Introduction", "Introduction to discrete math"
            , "Probability", "Infinitesimal calculus 2", "Computer structure and switching theory", "Data Structures", "Advanced Programming Workshop"
            , "Computer organization and threshold language", "Database systems", "Computer communication networks", "Graph theory", "Object oriented programming"
            , "Automatic and formal languages", "Software engineering", "Operating systems", "Algorithm design and analysis"
            , "Computational and Computational Algorithms", "Mathematical tools", "Machine learning", "Programming in the Web Environment"
            , "DevOPS", "Development of server side systems in an open source environment", "Developing a client side in an Android environment", "Compilation Theory"
            , "Involvement in Israeli society", "Yoga", "Basketball team", "Football team", "Information Society"};

    /**
     * credits array is the array that holds all the credits
     * points, their location in the array matches the courses array.
     */
    //Final object with the credit points of each course at the relevant index.
    private static final Object[] credits = {5, 6.5, 5, 5,
            3.5, 5, 4, 4, 3,
            2.5, 4, 3.5, 3.5, 5,
            4, 4, 3.5, 4,
            4, 5, 3, 3,
            3, 3, 3.5, 3.5,
            4, 1, 1, 1, 2};

    /**
     * year array is the array that holds all the years
     * that a computer science student is studying.
     */
    //Final array to set up the years string
    private static final String[] year = {"First Year", "Second Year", "Third Year"};

    /**
     * semester array is the array that holds all the semesters.
     */
    //Final array to set up the semeter strings
    private static final String[] semester = {"First Semester", "Second Semester", "Third Semester"};


    /**
     * creating the frame object.
     */
    private JFrame frame;
    /**
     * creating all the required panels.
     */
    private JPanel yearPanel, semesterPanel, coursePanel, quizPanel, testPanel, creditsPanel, addBtnPanel, inputErrPanel;
    /**
     * creating all the required labels.
     */
    private JLabel courseLabel,quizLabel,finalTestLabel,creditsLabel,percentageSign,percentageSign2,yearLabel,semesterLabel,inputErrLabel;
    /**
     * creating all the combo boxes.
     */
    private JComboBox courseComboBox,yearComboBox,semesterComboBox;
    /**
     * creating all the text fields.
     */
    private JTextField textQuiz,textFinalTest,textCredits,quizPercentage,testPercentage;
    /**
     * creating the add button.
     */
    private JButton btnAdd;

    /**
     * creating the CourseDetails object that holds all the course information.
     */
    private CourseDetails courseDetails;
    /**
     * creating the Calculations object that performs all the GPA calculations.
     */
    private Calculations calculations;
    /**
     * creating the ISimpleAction interface object.
     */
    private ISimpleActions tableHomeFrame;


    /**
     * The class constructor that creates an AddGradeScreen
     * and initializes an TableHomeFrame object.
     *
     * @param tableHomeFrame This class holds the components of the main screen
     * hence providing the interaction between the user and the application.
     */
    public AddGradeScreen(ISimpleActions tableHomeFrame) {
        this.tableHomeFrame=tableHomeFrame;
        /**
         * instantiating all the label objects.
         */
        logger.info("creating the labels...");
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

        /**
         * instantiating all the combo box objects.
         */
        //Creating ComboBox
        logger.info("creating the combo boxes...");
        courseComboBox = new JComboBox(courses);
        yearComboBox = new JComboBox(year);
        semesterComboBox = new JComboBox(semester);

        /**
         * instantiating all the text field objects.
         */
        logger.info("creating the text fields...");
        //creating text fields
        textQuiz = new JTextField(3);
        textFinalTest = new JTextField(3);
        textCredits = new JTextField(3);
        quizPercentage = new JTextField(2);
        testPercentage = new JTextField(2);
        textCredits.setEditable(false);
        //Linear Algebra first credit points is 5
        textCredits.setText("5");

        /**
         * instantiating all the button objects.
         */
        logger.info("creating a button...");
        //creating buttons
        btnAdd = new JButton("Add");

        /**
         * instantiating all the panel objects.
         */
        logger.info("creating the panels...");
        //creating panels
        yearPanel = new JPanel();
        semesterPanel = new JPanel();
        coursePanel = new JPanel();
        quizPanel = new JPanel();
        testPanel = new JPanel();
        creditsPanel = new JPanel();
        addBtnPanel = new JPanel();
        inputErrPanel = new JPanel();

        /**
         * instantiating the frame object.
         */
        //creating frame
        logger.info("creating the frame...");
        frame = new JFrame("Add Grade");

        courseDetails=null;
        /**
         * instantiating the Calculations object.
         */
        calculations = new Calculations();
        //the credits are loaded by the combobox listener
        textCredits.setEditable(false);

        /**
         * creating an action listener for the "Add" button
         */
        //Add button action listener
        btnAdd.addActionListener(e -> {
            //checking if one of the inputs is empty
            if (textQuiz.getText().isEmpty() || textFinalTest.getText().isEmpty() || quizPercentage.getText().isEmpty() || testPercentage.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please input all values");
            } else {
                //creating new courseDetails object to hold the values
                courseDetails = new CourseDetails(courseComboBox.getSelectedItem().toString(), yearComboBox.getSelectedIndex() + 1, semesterComboBox.getSelectedIndex() + 1
                        , Integer.valueOf(textFinalTest.getText()), Double.parseDouble(textCredits.getText())
                        , calculations.calculate_Final_Grade(Double.valueOf(textFinalTest.getText()),Double.valueOf(testPercentage.getText())
                        ,Double.valueOf(textQuiz.getText()),Double.valueOf(quizPercentage.getText())));

                try {
                    //trying to add the course details to the db
                    tableHomeFrame.addGrade(courseDetails);
                    logger.info("added a grade to the table...");
                } catch (DBActionsException ex) {
                    logger.info("asking the user if he would like to edit a grade from the table...");
                    ex.printStackTrace();
                }
            }
        });

        //setting quiz input listener
        /**
         * creating a key listener for the "textQuiz" text field.
         */
        //"Add" button action listener
        textQuiz.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                String data = textQuiz.getText();
                Integer val = -1;
                if(data.length()>1)
                    if(c >='0' && c <= '9')
                        //converting the number we should get with the current input
                        val = Integer.parseInt(data+c);
                    else if(c == KeyEvent.VK_BACK_SPACE && !data.isEmpty())
                        //converting the number by backspacing last character
                        val = Integer.parseInt(data.substring(0,data.length()-1));
                    else if (!data.isEmpty())
                        //converting the text that is within the textfield before the current input
                        val = Integer.parseInt(data);
                    else
                        //we deleted all values and therefore the new value is 0
                        val = 0;
                if (val >= 100) {
                    textQuiz.setText("100");
                    ke.consume();
                    logger.info("an attempt to add a grade with a higher score than 100 has been made");
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

        //"textFinalTest" key listener
        /**
         * creating a key listener for the "textFinalTest" text field.
         */
        //setting test input listener
        textFinalTest.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                String data = textFinalTest.getText();
                Integer val = -1;
                if(data.length()>1)
                if(c >='0' && c <= '9')
                    //converting the number we should get with the current input
                    val = Integer.parseInt(data+c);
                else if(c == KeyEvent.VK_BACK_SPACE && !data.isEmpty())
                    //converting the number by backspacing last character
                    val = Integer.parseInt(data.substring(0,data.length()-1));
                else if (!data.isEmpty())
                    //converting the text that is within the textfield before the current input
                    val = Integer.parseInt(data);
                else
                    //we deleted all values and therefore the new value is 0
                    val = 0;
                if (val >= 100) {
                    textFinalTest.setText("100");
                    ke.consume();
                    logger.info("an attempt to add a grade with a higher score than 100 has been made");
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

        //"quizPercentage" key listener
        /**
         * creating a key listener for the "quizPercentage" text field.
         */
        //setting quiz percentage input listener
        quizPercentage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                String data = quizPercentage.getText();
                Integer val;
                if(c >='0' && c <= '9')
                    //converting the number we should get with the current input
                    val = Integer.parseInt(data+c);
                else if(c == KeyEvent.VK_BACK_SPACE && !data.isEmpty())
                    //converting the number by backspacing last character
                    val = Integer.parseInt(data.substring(0,data.length()-1));
                else if (!data.isEmpty())
                    //converting the text that is within the textfield before the current input
                    val = Integer.parseInt(data);
                else
                    //we deleted all values and therefore the new value is 0
                    val = 0;
                if (val >= 100) {
                    quizPercentage.setText("100");
                    testPercentage.setText("0");
                    ke.consume();
                    logger.info("an attempt to add a grade with a higher score than 100 has been made");
                    inputErrLabel.setText("Your grade can't be higher than 100");
                }
                else if (c >= '0' && c <= '9' || c == KeyEvent.VK_BACK_SPACE) {
                    inputErrLabel.setText("");
                    testPercentage.setText(String.valueOf(100-val));
                    if(c == KeyEvent.VK_BACK_SPACE && val == 0)
                        quizPercentage.setText(String.valueOf(val));
                }
                else {
                    ke.consume();
                    inputErrLabel.setText("* Enter only digits from 0 to 100");
                }
            }
        });

        //"testPercentage" key listener
        /**
         * creating a key listener for the "testPercentage" text field.
         */
        //setting test percentage input listener
        testPercentage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                String data = testPercentage.getText();
                Integer val;
                if(c >='0' && c <= '9')
                    //converting the number we should get with the current input
                    val = Integer.parseInt(data+c);
                else if(c == KeyEvent.VK_BACK_SPACE && !data.isEmpty())
                    //converting the number by backspacing last character
                    val = Integer.parseInt(data.substring(0,data.length()-1));
                else if (!data.isEmpty())
                    //converting the text that is within the textfield before the current input
                    val = Integer.parseInt(data);
                else
                    //we deleted all values and therefore the new value is 0
                    val = 0;
                if (val >= 100) {
                    testPercentage.setText("100");
                    quizPercentage.setText("0");
                    ke.consume();
                    logger.info("an attempt to add a grade with a higher score than 100 has been made");
                    inputErrLabel.setText("Your grade can't be higher than 100");
                }
                else if (c >= '0' && c <= '9' || c == KeyEvent.VK_BACK_SPACE) {
                    inputErrLabel.setText("");
                    quizPercentage.setText(String.valueOf(100-val));
                    if(c == KeyEvent.VK_BACK_SPACE && val == 0)
                        testPercentage.setText(String.valueOf(val));
                }
                else {
                    ke.consume();
                    inputErrLabel.setText("* Enter only digits from 0 to 100");
                }
            }
        });

        /**
         * setting the layouts for the panels.
         */
        //setting panels layouts
        logger.info("setting the panels layouts");
        yearPanel.setLayout(new FlowLayout());
        semesterPanel.setLayout(new FlowLayout());
        coursePanel.setLayout(new FlowLayout());
        quizPanel.setLayout(new FlowLayout());
        testPanel.setLayout(new FlowLayout());
        creditsPanel.setLayout(new FlowLayout());
        addBtnPanel.setLayout(new FlowLayout());
        inputErrLabel.setLayout(new FlowLayout());

        /**
         * Adding components to the panels.
         */
        logger.info("adding components to the panels");
        //Adding components to the relevant panels
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

        inputErrPanel.add(inputErrLabel);

        yearPanel.add(yearLabel);
        yearPanel.add(yearComboBox);

        semesterPanel.add(semesterLabel);
        semesterPanel.add(semesterComboBox);

        Container container = frame.getContentPane();
        /**
         * setting up the container layout.
         */
        //setting container layout
        container.setLayout(new GridLayout(8, 1, 5, 5));
        /**
         * Adding the panels to the container.
         */
        //attaching panels to the main frame
        logger.info("adding panels to the mainframe");
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
        /**
         * setting up the frame properties.
         */
        logger.info("defining the frame properties");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
