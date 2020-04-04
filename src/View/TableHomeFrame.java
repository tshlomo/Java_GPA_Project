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


public class TableHomeFrame extends JTable {

    private static final String[] courses = {"Linear algebra","Infinitesimal calculus 1","Computer science Introduction","Introduction to discrete math"
            ,"Probability","Infinitesimal calculus 2","Computer structure and switching theory","Data Structures","Advanced Programming Workshop"
            ,"Computer organization and threshold language","Database systems","Computer communication networks","Graph theory","Object oriented programming"
            ,"Automatic and formal languages","Software engineering","Operating systems","Algorithm design and analysis"
            ,"Computational and Computational Algorithms","Mathematical tools","Machine learning","Programming in the Web Environment"
            ,"DevOPS","Development of server side systems in an open source environment","Developing a client side in an Android environment","Compilation Theory"
            ,"Involvement in Israeli society","Yoga","Basketball team","Football team","Information Society"};
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

    private static final String[] year = {"First Year","Second Year","Third Year"};
    private static final String[] semester ={"First Semester","Second Semester","Third Semester"};

    private AddGradeScreen screen;

    private ISimpleActions simpleActions;

    private JFrame frame;
    private JTable table;
    private JPanel panelTop,panelMiddle,panelBottom,labelPanel,yearPanel,semesterPanel,coursePanel,desiredGradePanel,updatedGpaPanel,btnPanel,addDeleteBtnPanel,currentGpaPanel;
    private JComboBox courseComboBox;
    private JComboBox yearComboBox;
    private JComboBox semesterComboBox;
    private JLabel GPALabel;
    private JLabel desiredGradeLabel;
    private JLabel courseLabel;
    private JLabel improvingGradesLabel;
    private JLabel updatedGPA;
    private JLabel yearLabel;
    private JLabel semesterLabel;
    private JLabel desiredGradeLabelValidation;
    private JTextField textGPA;
    private JTextField textDesiredGrade;
    private JTextField textUpdatedGrade;
    private JButton btnAddGrade;
    private JButton btnDeleteGrade;
    private JButton btnDesiredGradeUpdate;
    private JScrollPane pane;
    private DefaultTableModel model;


    //table properties
    private static final String[] columns = {"Course", "Year", "Semester", "Final Test", "Credits", "Final Grade"};

    public TableHomeFrame() {
        simpleActions = new ViewModel(this);
        //Frame
        frame = new JFrame("GPA");
        //Table
        table = new JTable();
        //Panels
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
        //ComboBox
        courseComboBox = new JComboBox(courses);
        yearComboBox = new JComboBox(year);
        semesterComboBox = new JComboBox(semester);
        //Labels
        GPALabel = new JLabel("Current GPA");
        desiredGradeLabel = new JLabel("Desired Grade");
        courseLabel = new JLabel("Course");
        improvingGradesLabel = new JLabel("Fill the details below to calculate your GPA after course improvement");
        updatedGPA = new JLabel("Updated GPA");
        yearLabel = new JLabel("Year");
        semesterLabel = new JLabel("Semester");
        desiredGradeLabelValidation = new JLabel("");
        desiredGradeLabelValidation.setForeground(Color.RED);
        //TextFields
        textGPA = new JTextField(4);
        textGPA.setEditable(false);
        textUpdatedGrade = new JTextField(3);
        textUpdatedGrade.setEditable(false);
        textDesiredGrade = new JTextField(3);

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

        //Buttons
        btnAddGrade = new JButton("Add New Grade");
        btnDeleteGrade = new JButton("Delete Grade");
        btnDesiredGradeUpdate = new JButton("Calculate new GPA");

        btnDesiredGradeUpdate.addActionListener(e -> {
            try{
                //TODO this func
            } catch (Exception q) { q.printStackTrace(); }
        });

        //table properties
        Font font = new Font("", 1, 16);
        //disabling editing the table
        table.setDefaultEditor(Object.class,null);
        //auto resizing the columns
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(columns);
/*      table.setBackground(Color.white);
        table.setModel(model);
        table.setForeground(Color.red);
        table.setFont(font);
        table.setRowHeight(30);
*/
        //ScrollPane
        pane = new JScrollPane(table);

        //setting layouts
        panelBottom.setLayout(new BoxLayout(panelBottom,BoxLayout.PAGE_AXIS));
        //panelBottom.setLayout(new GridLayout(7,1,5,5));
        panelTop.setLayout(new BorderLayout());
        panelMiddle.setLayout(new BoxLayout(panelMiddle,BoxLayout.PAGE_AXIS));
        labelPanel.setLayout(new FlowLayout());
//        yearPanel.setLayout(new FlowLayout());
//        semesterPanel.setLayout(new FlowLayout());
        coursePanel.setLayout(new FlowLayout());
        desiredGradePanel.setLayout(new FlowLayout());
        btnPanel.setLayout(new FlowLayout());
        updatedGpaPanel.setLayout(new FlowLayout());
        addDeleteBtnPanel.setLayout(new FlowLayout());
        currentGpaPanel.setLayout(new FlowLayout());

        improvingGradesLabel.setFont(new Font("Arial",Font.BOLD,13));

        //adding top panel
        panelTop.add(pane);

        //setting relevant components in the relevant panels for middle panel
        currentGpaPanel.add(addDeleteBtnPanel);
        currentGpaPanel.add(GPALabel);
        currentGpaPanel.add(textGPA);
        addDeleteBtnPanel.add(btnAddGrade);
        addDeleteBtnPanel.add(btnDeleteGrade);
        labelPanel.add(improvingGradesLabel);
        panelMiddle.add(currentGpaPanel);
        //panelMiddle.add(addDeleteBtnPanel);
        panelMiddle.add(labelPanel);
        panelMiddle.add(coursePanel);
        panelMiddle.add(desiredGradeLabelValidation);

        //setting bottom panel
//        yearPanel.add(yearLabel);
//        yearPanel.add(yearComboBox);
//        semesterPanel.add(semesterLabel);
//        semesterPanel.add(semesterComboBox);
        coursePanel.add(courseLabel);
        coursePanel.add(courseComboBox);
        desiredGradePanel.add(desiredGradeLabel);
        desiredGradePanel.add(textDesiredGrade);
        updatedGpaPanel.add(updatedGPA);
        updatedGpaPanel.add(textUpdatedGrade);
        btnPanel.add(btnDesiredGradeUpdate);
        panelMiddle.add(desiredGradePanel);
        panelMiddle.add(btnPanel);

//        panelBottom.add(yearPanel);
//        panelBottom.add(semesterPanel);


        panelBottom.add(updatedGpaPanel);

        //creating container to handle the frame content pane
        Container container = frame.getContentPane();
        //setting container layout
        container.setLayout(new BorderLayout());
        //attaching relevant panels to the container
        container.add(panelTop,BorderLayout.NORTH);
        container.add(panelMiddle,BorderLayout.CENTER);
        container.add(panelBottom,BorderLayout.SOUTH);

        //frame properties
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        //listener for add grade button
        btnAddGrade.addActionListener(e -> screen = new AddGradeScreen(this));

        btnDeleteGrade.addActionListener(e -> simpleActions.deleteGrade(table.getValueAt(table.getSelectedRow(),0).toString()));

        simpleActions.deleteGrade("updateTable");
    }

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

    public void addGrade(CourseDetails courseDetails){
        simpleActions.addGrade(courseDetails);
    }

    public void updateGPA(Double calculate_gpa) {
        textGPA.setText(new DecimalFormat("##.##").format(calculate_gpa));
    }
}