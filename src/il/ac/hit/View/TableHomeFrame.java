package il.ac.hit.View;
import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.Interfaces.IFindNewGPA;
import il.ac.hit.Interfaces.IViewSimpleActions;
import il.ac.hit.ViewModel.CourseDetails;
import il.ac.hit.ViewModel.ViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class holds the components of the main screen
 * hence providing the interaction between the user and the application.
 */
public class TableHomeFrame implements IViewSimpleActions {



    /**
     * creating a logger object to log messages for
     * our application components.
     */
    private Logger logger= Logger.getLogger(TableHomeFrame.class.getName());

    //Variables declarations
    /**
     * creating the AddGradeScreen class object.
     */
    private AddGradeScreen screen;

    /**
     * creating the ISimpleAction interface.
     */

    private IFindNewGPA simpleAndGPAActions;
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
    private JPanel panelTop,panelMiddle,panelBottom,labelPanel,coursePanel,desiredGradePanel,updatedGpaPanel,btnPanel,addDeleteBtnPanel,currentGpaPanel;
    /**
     * creating all the Combo box objects.
     */
    private JComboBox courseComboBox;
    /**
     * creating all the label objects.
     */
    private JLabel GPALabel,desiredGradeLabel,courseLabel,improvingGradesLabel,updatedGPA,desiredGradeLabelValidation;
    /**
     * creating all the text field objects.
     */
    private JTextField textGPA,textDesiredGrade, textUpdatedGpa;
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
    //table properties
    private static final String[] columns = {"Course", "Year", "Semester", "Test", "Credits", "Final Grade"};

    /**
     * constructor for class TableHomeFrame
     *
     * @throws DBActionsException in case of an sql error
     */

    public TableHomeFrame() throws DBActionsException {
        logger.info("instantiating all the class components");


        simpleAndGPAActions = new ViewModel(this);
        //Frame
        // instantiating the frame.
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
        panelMiddle = new JPanel();
        labelPanel = new JPanel();
        coursePanel = new JPanel();
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
        //ComboBox
        courseComboBox = new JComboBox();

        //instantiating the label objects.
        /**
         * instantiating the label objects.
         */
        logger.info("creating the labels..");
        //Labels
        GPALabel = new JLabel("Current GPA");
        desiredGradeLabel = new JLabel("Desired Grade");
        courseLabel = new JLabel("Course");
        improvingGradesLabel = new JLabel("Fill the details below to calculate your GPA after course improvement");
        updatedGPA = new JLabel("Updated GPA");
        desiredGradeLabelValidation = new JLabel("");
        desiredGradeLabelValidation.setForeground(Color.RED);

        //instantiating the text field objects.
        /**
         * instantiating the text field objects.
         */
        logger.info("creating the text fields..");
        textGPA = new JTextField(4);
        textGPA.setEditable(false);
        textUpdatedGpa = new JTextField(4);
        textUpdatedGpa.setEditable(false);
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
                if (val > 100) {
                    textDesiredGrade.setText("100");
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
        //Buttons
        btnAddGrade = new JButton("Add New Grade");
        btnDeleteGrade = new JButton("Delete Grade");
        btnDesiredGradeUpdate = new JButton("Calculate new GPA");
        btnDesiredGradeUpdate.addActionListener(e -> {
            if(textDesiredGrade.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please input desired grade");
            } else {
                try {
                    textUpdatedGpa.setText(new DecimalFormat("##.##").format(simpleAndGPAActions.newGPA(String.valueOf(courseComboBox.getSelectedItem()), Integer.valueOf(textDesiredGrade.getText()))));
                } catch (DBActionsException e1) {
                    logger.warning("Couldn't pull table grades...");
                    e1.printStackTrace();
                }
            }
        });

        //table properties
        /**
         * setting up the table properties.
         */
        logger.info("disabling the option to edit the table");
        //disabling editing the table
        table.setDefaultEditor(Object.class,null);
        //auto resizing the columns
        logger.info("disabling auto resizing the columns..");
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        logger.info("setting the model..");
        model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(columns);

        //ScrollPane
        pane = new JScrollPane(table);

        //setting layouts
        /**
         * setting up the panels layouts.
         */
        logger.info("setting up the layouts for the panels..");
        panelBottom.setLayout(new BoxLayout(panelBottom,BoxLayout.PAGE_AXIS));
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

        /**
         * adding all the relevant components to their panels.
         */
        logger.info("setting up the components in the relevant panels..");
        //setting relevant components in the relevant panels for middle panel
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

        //setting bottom panel
        coursePanel.add(courseLabel);
        coursePanel.add(courseComboBox);

        desiredGradePanel.add(desiredGradeLabel);
        desiredGradePanel.add(textDesiredGrade);

        updatedGpaPanel.add(updatedGPA);
        updatedGpaPanel.add(textUpdatedGpa);

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
        container.add(pane,BorderLayout.NORTH);
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

        /**
         * creating the action listener for the "btnAddGrade" button.
         */
        logger.info("setting up the add button listener..");
        //listener for add grade button to open new form
        btnAddGrade.addActionListener(e -> SwingUtilities.invokeLater(()-> screen = new AddGradeScreen(this)));

        //listener for delete grade button
        /**
         * creating the action listener for the "btnDeleteGrade" button.
         */
        logger.info("setting up the delete button listener..");
        //listener for delete button
        btnDeleteGrade.addActionListener(e -> {
            try {
                deleteGrade(table.getValueAt(table.getSelectedRow(),0).toString());
            } catch (DBActionsException e1) {
                logger.warning("Couldn't delete this grade from GPA table...");
                e1.printStackTrace();
            }
        });

        //updating the table for the first time with special keyword 'updateTable'
        simpleAndGPAActions.deleteGrade("updateTable");
    }

    /**
     * Updates the table with an ArrayList of CourseDetails Objects.
     * Each CourseDetails object is then displayed to the user inside the table.
     *
     * @param courseDetails The CourseDetails Class object holds all the components that assemble the grade.
     */

    //updating the view table basing on the list of course details that it receives
    @Override
    public void updateGradesTable(List<CourseDetails> courseDetails){
        //updating the course combobox first
        updateCourseComboBox(courseDetails);
        Object[] row = new Object[6];
        //clearing the table first
        table.setModel(new DefaultTableModel(null, columns));
        model= (DefaultTableModel) table.getModel();

        for (CourseDetails c: courseDetails){
            //charging the values into the object array
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
            //adding the values to the row
            model.addRow(row);
        }
        //saving all table width to compare to current frame width
        Integer columnsWidth=0;
        //resizing each column by the maximum length of each
        for (int i = 0; i < table.getColumnCount(); i++) {
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
            TableColumn col = colModel.getColumn(i);
            int width = 0;
            TableCellRenderer renderer;
            for (int r = 0; r < table.getRowCount(); r++) {
                renderer = table.getCellRenderer(r, i);
                Component comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, i),
                        false, false, r, i);
                width = Math.max(width, comp.getPreferredSize().width);
            }
            //comparing the width of the value in the column to the width of the title of the column
            width = Math.max(width,table.getColumn(columns[i]).getWidth()-5);
            //setting the prefered maximum width
            col.setPreferredWidth(width + 3);
            //adding to the sum var
            columnsWidth+=width+3;
        }
        //setting the frame size to fit
        frame.setSize(columnsWidth + 20,frame.getHeight() + 20);
    }

    /**
     * This method is triggered once the "Add Grade" button is pressed by the user.
     * It adds a CourseDetails object to the table.
     * The grade components are then displayed to the user inside the table.
     *
     * @param courseDetails The CourseDetails Class object holds all the components that assemble the grade.
     * @throws DBActionsException triggered if there's and SQL exception
     */
    //The function trying to add a grade based on the values inside the courseDetails var
    @Override
    public void addGrade(CourseDetails courseDetails) throws DBActionsException {
        simpleAndGPAActions.addGrade(courseDetails);
    }

    /**
     * This method is triggered once the "Delete Grade" button is pressed by the user.
     * It deletes the CourseDetails object from the table and
     * the grade components are then removed from the table.
     *
     * @param courseName the string that holds the name of the course
     * @throws DBActionsException triggered if there's and SQL exception
     */
    //The function trying to delete a grade based on the course name
    @Override
    public void deleteGrade(String courseName) throws DBActionsException {
        simpleAndGPAActions.deleteGrade(courseName);
    }

    //The function trying to edit a grade based on the values inside the courseDetails var

    /**
     * This method is triggered once the user is trying to add
     * the same course to the table, a message will be displayed to the
     * screen asking the user if he confirms the change of the same grade.
     *
     * @param courseDetails the objects that holds all the course details
     * @throws DBActionsException triggered if there's and SQL exception
     */
    @Override
    public void editGrade(CourseDetails courseDetails) throws DBActionsException{
        int dialogResult = JOptionPane.showConfirmDialog (null, "You've already got grade on "+courseDetails.getCourseName()+"\nWould you like to update your grades?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            simpleAndGPAActions.editGrade(courseDetails);
        }
    }

    /**
     * Sets the current GPA to be in a format of "##.##" i.e: "93.45".
     * This method is triggered once a new grade is added.
     *
     * @param calculate_gpa The outcome from the calculation of the GPA that is then formatted to "##.##" format.
     */
    //The function updating the current GPA result based on the calculate_gpa double type var it receives
    @Override
    public void updateGPA(Double calculate_gpa) {
        textGPA.setText(new DecimalFormat("##.##").format(calculate_gpa));
    }

    //updating to course combobox to the current courses

    /**
     * This method updates the "CourseComboBox" with the list
     * of all the course names.
     *
     * @param gradeTable a list that holds all the CourseDetails objects
     */
    public void updateCourseComboBox(List<CourseDetails> gradeTable) {
        courseComboBox.removeAllItems();
        for (CourseDetails c: gradeTable){
            courseComboBox.addItem(c.getCourseName());
        }
    }
}