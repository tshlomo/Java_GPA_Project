package View;

import Interfaces.ISimpleActions;
import ViewModel.CourseDetails;
import ViewModel.ViewModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * This class holds the components of the main screen
 * hence providing the interaction between the user and the application.
 */

public class TableHomeFrame extends JTable {

    /**
     * creating a logger object to log messages for
     * our application components.
     */

    private Logger logger= Logger.getLogger(TableHomeFrame.class.getName());

    /**
     * courses array is the array that holds all the courses.
     */

    private static final String[] courses = {"Linear algebra","Infinitesimal calculus 1","Computer science Introduction","Introduction to discrete math"
            ,"Probability","Infinitesimal calculus 2","Computer structure and switching theory","Data Structures","Advanced Programming Workshop"
            ,"Computer organization and threshold language","Database systems","Computer communication networks","Graph theory","Object oriented programming"
            ,"Automatic and formal languages","Software engineering","Operating systems","Algorithm design and analysis"
            ,"Computational and Computational Algorithms","Mathematical tools","Machine learning","Programming in the Web Environment"
            ,"DevOPS","Development of server side systems in an open source environment","Developing a client side in an Android environment","Compilation Theory"
            ,"Involvement in Israeli society","Yoga","Basketball team","Football team","Information Society"};

    /**
     * credits array is the array that holds all the credits
     * points, their location in the array matches the courses array.
     */

    private static final Object[] credits;
    static {
        credits = new Object[]{ 5, 6.5, 5, 5,
                3.5, 5, 4, 4, 3,
                2.5, 4, 3.5, 3.5, 5,
                4,4, 3.5, 4,
                4, 5, 3, 3,
                3,3, 3.5, 3.5,
                4, 1, 1, 1, 2};
    }

    /**
     * year array is the array that holds all the years
     * that a computer science student is studying.
     */

    private static final String[] year = {"First Year","Second Year","Third Year"};

    /**
     * semester array is the array that holds all the semesters.
     */

    private static final String[] semester ={"First Semester","Second Semester","Third Semester"};

    //Variables declarations
    /**
     * creating the AddGradeScreen class object.
     */
    private AddGradeScreen screen;

    /**
     * creating the ISimpleAction interface.
     */

    private ISimpleActions simpleActions;
    /**
     * creating the frame object.
     */
    private JFrame frame;
    /**
     * creating the table object.
     */
    private JTable table;
    /**
     * creating all the panel objects.
     */
    private JPanel panelTop,panelMiddle,panelBottom,labelPanel,yearPanel,semesterPanel,coursePanel,desiredGradePanel,updatedGpaPanel,btnPanel,addDeleteBtnPanel,currentGpaPanel;
    /**
     * creating all the Combo box objects.
     */
    private JComboBox courseComboBox,yearComboBox,semesterComboBox;
    /**
     * creating all the label objects.
     */
    private JLabel GPALabel,desiredGradeLabel,courseLabel,improvingGradesLabel,updatedGPA,yearLabel,semesterLabel,desiredGradeLabelValidation;
    /**
     * creating all the text field objects.
     */
    private JTextField textGPA,textDesiredGrade,textUpdatedGrade;
    /**
     * creating all the button objects.
     */
    private JButton btnAddGrade,btnDeleteGrade,btnDesiredGradeUpdate;
    /**
     * creating the scroll pane object.
     */
    private JScrollPane pane;
    /**
     * creating the DefaulTableModel object.
     */
    private DefaultTableModel model;


    /**
     * creating the column array that hold all the table columns.
     */

    private static final String[] columns = {"Course", "Year", "Semester", "Final Test", "Credits", "Final Grade"};

    /**
     * creating the constructor for the TableHomeFrame class.
     */

    public TableHomeFrame() {
        logger.info("instantiating all the class components");
        simpleActions = new ViewModel(this);

        //instantiating the frame.
        /**
         * instantiating the frame object.
         */
        logger.info("creating the frame..");
        frame = new JFrame("GPA");

        //instantiating the table.
        /**
         * instantiating the table object.
         */
        logger.info("creating the table..");
        table = new JTable();

        //instantiating the panels.
        /**
         * instantiating the panel objects.
         */
        logger.info("creating the panels..");
        panelBottom = new JPanel();
        panelTop = new JPanel();
        panelMiddle = new JPanel();
        labelPanel = new JPanel();
        yearPanel = new JPanel();
        coursePanel = new JPanel();
        semesterPanel = new JPanel();
        desiredGradePanel = new JPanel();
        updatedGpaPanel = new JPanel();
        btnPanel = new JPanel();
        addDeleteBtnPanel = new JPanel();
        currentGpaPanel = new JPanel();

        //instantiating the combo boxes.
        /**
         * instantiating the combo box objects.
         */
        logger.info("creating the combo boxes..");
        courseComboBox = new JComboBox(courses);
        yearComboBox = new JComboBox(year);
        semesterComboBox = new JComboBox(semester);

        //instantiating the label objects.
        /**
         * instantiating the label objects.
         */
        logger.info("creating the labels..");
        GPALabel = new JLabel("Current GPA");
        desiredGradeLabel = new JLabel("Desired Grade");
        courseLabel = new JLabel("Course");
        improvingGradesLabel = new JLabel("Fill the details below to calculate your GPA after course improvement");
        updatedGPA = new JLabel("Updated GPA");
        yearLabel = new JLabel("Year");
        semesterLabel = new JLabel("Semester");
        desiredGradeLabelValidation = new JLabel("");
        desiredGradeLabelValidation.setForeground(Color.RED);

        //instantiating the text field objects.
        /**
         * instantiating the text field objects.
         */
        logger.info("creating the text fields..");
        textGPA = new JTextField(4);
        textGPA.setEditable(false);
        textUpdatedGrade = new JTextField(3);
        textUpdatedGrade.setEditable(false);
        textDesiredGrade = new JTextField(3);

        //instantiating the button objects
        /**
         * instantiating the button objects.
         */
        logger.info("creating the buttons..");
        btnAddGrade = new JButton("Add New Grade");
        btnDeleteGrade = new JButton("Delete Grade");
        btnDesiredGradeUpdate = new JButton("Calculate new GPA");

        //instantiating the scroll pane object.
        /**
         * instantiating the scroll pane object.
         */
        logger.info("creating the scroll pane..");
        pane = new JScrollPane(table);

        //key listener for the "textDesiredGrade" text field
        /**
         * creating key listener for the "textDesiredGrade" text field.
         */
        textDesiredGrade.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                String data = textDesiredGrade.getText();
                Integer val = -1;
                if(data.length()>1)
                    try {
                        val = Integer.parseInt(data+c);
                        if (val >= 100) {
                            textDesiredGrade.setText("100");
                        }
                    } catch (Exception e) {e.printStackTrace();}
                if(val >= 100) {
                    ke.consume();
                    logger.info("an attempt to add a grade with a higher score than 100 has been made");
                    desiredGradeLabelValidation.setText("Your grade can't be higher than 100");
                }
                else if (c >= '0' && c <= '9' || c == KeyEvent.VK_BACK_SPACE) {
                    desiredGradeLabelValidation.setText("");
                }
                else {
                    ke.consume();
                    desiredGradeLabelValidation.setText("* Enter only digits from 0 to 100");
                }
            }
        });

        //btnDesiredGradeUpdate action listener
        /**
         * creating action listener for the btnDesiredGradeUpdate button.
         */
        btnDesiredGradeUpdate.addActionListener(e -> {
            try{
                //TODO this func
            } catch (Exception q) { q.printStackTrace(); }
        });

        //table properties
        /**
         * setting up the table properties.
         */
        logger.info("disabling the option to edit the table");
        table.setDefaultEditor(Object.class,null);
        logger.info("auto resizing the columns..");
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        logger.info("setting the model..");
        model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(columns);

        //setting layouts
        /**
         * setting up the panels layouts.
         */
        logger.info("setting up the layouts for the panels..");
        panelBottom.setLayout(new BoxLayout(panelBottom,BoxLayout.PAGE_AXIS));
        panelTop.setLayout(new BorderLayout());
        panelMiddle.setLayout(new BoxLayout(panelMiddle,BoxLayout.PAGE_AXIS));
        labelPanel.setLayout(new FlowLayout());
        coursePanel.setLayout(new FlowLayout());
        desiredGradePanel.setLayout(new FlowLayout());
        btnPanel.setLayout(new FlowLayout());
        updatedGpaPanel.setLayout(new FlowLayout());
        addDeleteBtnPanel.setLayout(new FlowLayout());
        currentGpaPanel.setLayout(new FlowLayout());
        logger.info("setting up the improving grades label font..");
        improvingGradesLabel.setFont(new Font("Arial",Font.BOLD,13));

        //adding top panel
        /**
         * adding the top panel to the scroll pane.
         */
        logger.info("adding the scroll pane..");
        panelTop.add(pane);

        //setting relevant components in the relevant panels
        /**
         * adding all the relevant components to their panels.
         */
        logger.info("setting up the components in the relevant panels..");
        currentGpaPanel.add(addDeleteBtnPanel);
        currentGpaPanel.add(GPALabel);
        currentGpaPanel.add(textGPA);
        addDeleteBtnPanel.add(btnAddGrade);
        addDeleteBtnPanel.add(btnDeleteGrade);
        labelPanel.add(improvingGradesLabel);
        panelMiddle.add(currentGpaPanel);
        panelMiddle.add(labelPanel);
        panelMiddle.add(coursePanel);
        panelMiddle.add(desiredGradeLabelValidation);
        coursePanel.add(courseLabel);
        coursePanel.add(courseComboBox);
        desiredGradePanel.add(desiredGradeLabel);
        desiredGradePanel.add(textDesiredGrade);
        updatedGpaPanel.add(updatedGPA);
        updatedGpaPanel.add(textUpdatedGrade);
        btnPanel.add(btnDesiredGradeUpdate);
        panelMiddle.add(desiredGradePanel);
        panelMiddle.add(btnPanel);
        panelBottom.add(updatedGpaPanel);

        //creating the container
        /**
         * creating the container to handle the frame content pane.
         */
        logger.info("creating the container..");
        Container container = frame.getContentPane();

        //setting container layout
        /**
         * setting the container layout
         */
        logger.info("setting up the container layout..");
        container.setLayout(new BorderLayout());

        //attaching relevant panels to the container
        /**
         * adding the panels to the container.
         */
        logger.info("adding the panels to the container..");
        container.add(panelTop,BorderLayout.NORTH);
        container.add(panelMiddle,BorderLayout.CENTER);
        container.add(panelBottom,BorderLayout.SOUTH);

        //frame properties
        /**
         * setting up the frame properties.
         */
        logger.info("setting up the frame properties..");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        //listener for add grade button
        /**
         * creating the action listener for the "btnAddGrade" button.
         */
        logger.info("setting up the add button listener..");
        btnAddGrade.addActionListener(e -> screen = new AddGradeScreen(this));

        //listener for delete grade button
        /**
         * creating the action listener for the "btnDeleteGrade" button.
         */
        logger.info("setting up the delete button listener..");
        btnDeleteGrade.addActionListener(e -> simpleActions.deleteGrade(table.getValueAt(table.getSelectedRow(),0).toString()));
        simpleActions.deleteGrade("updateTable");
    }

    /**
     * Updates the table with an ArrayList of CourseDetails Objects.
     * Each CourseDetails object is then displayed to the user inside the table.
     *
     * @param courseDetails The CourseDetails Class object holds all the components that assemble the grade.
     */

    public void updateGradesTable(ArrayList<CourseDetails> courseDetails){
        Object[] row = new Object[6];
        //clearing the table first
        table.setModel(new DefaultTableModel(null, columns));
        model= (DefaultTableModel) table.getModel();

        courseDetails.forEach((c) -> {
            row[0] = c.getCourseName();
            switch (c.getYear()){
                case 1: row[1] = "First";
                    break;
                case 2: row[1] = "Second";
                    break;
                case 3: row[1] = "Third";
                    break;
                default: row[1]="???";
            }
            switch (c.getSemester()){
                case 1: row[2] = "First";
                    break;
                case 2: row[2] = "Second";
                    break;
                case 3: row[2] = "Summer";
                    break;
                default: row[2]="???";
            }
            row[3] = c.getTestGrade();
            row[4] = c.getCredits();
            row[5] = c.getFinalGrade();
            model.addRow(row);
        });
    }

    /**
     * This method is triggered once the "Add Grade" button is pressed by the user.
     * It adds a CourseDetails object to the table.
     * The grade components are then displayed to the user inside the table.
     *
     * @param courseDetails The CourseDetails Class object holds all the components that assemble the grade.
     */

    public void addGrade(CourseDetails courseDetails){
        simpleActions.addGrade(courseDetails);
    }

    /**
     * Sets the updated GPA to be in a format of "##.##" i.e: "93.45".
     * This method is triggered once a new grade is added.
     *
     * @param calculate_gpa The outcome from the calculation of the GPA that is then formatted to "##.##" format.
     */
    public void updateGPA(Double calculate_gpa) {
        textGPA.setText(new DecimalFormat("##.##").format(calculate_gpa));
    }
}