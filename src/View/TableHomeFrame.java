package View;
import Model.DBActions;
import ViewModel.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
        credits = new Object[]{(Object) 5,(Object) 6.5,(Object) 5,(Object) 5,
                (Object) 3.5,(Object) 5,(Object) 4,(Object) 4,(Object) 3,
                (Object) 2.5,(Object) 4,(Object) 3.5,(Object) 3.5,(Object) 5,
                (Object) 4,(Object) 4,(Object) 3.5,(Object) 4,
                (Object) 4,(Object) 5,(Object) 3,(Object) 3,
                (Object) 3,(Object) 3,(Object) 3.5,(Object) 3.5,
                (Object) 4,(Object) 1,(Object) 1,(Object) 1,(Object) 2};
    }

    private static final String[] year = {"First Year","Second Year","Third Year"};
    private static final String[] semester ={"First Semester","Second Semester","Third Semester"};

    private AddGradeScreen screen;

    private DBActions dbActions;
    private ViewModel viewModel;

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
    private JTextField textGPA;
    private JTextField textDesiredGrade;
    private JTextField textUpdatedGrade;
    private JButton btnAddGrade;
    private JButton btnDeleteGrade;
    private JButton btnDesiredGradeUpdate;
    private JScrollPane pane;
    private DefaultTableModel model;

    //table properties
    String[] columns = {"Course", "Year", "Semester", "Final Test", "Credits", "Final Grade"};

    public TableHomeFrame() {
        viewModel = new ViewModel(this);
        dbActions = new DBActions();
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
        improvingGradesLabel = new JLabel("Fill the details below to see the updated GPA after course final grade improvement");
        updatedGPA = new JLabel("Updated GPA");
        yearLabel = new JLabel("Year");
        semesterLabel = new JLabel("Semester");
        //TextFields
        textGPA = new JTextField(4);
        textDesiredGrade = new JTextField(3);
        textUpdatedGrade = new JTextField(3);
        //Buttons
        btnAddGrade = new JButton("Add New Grade");
        btnDeleteGrade = new JButton("Delete Grade");
        btnDesiredGradeUpdate = new JButton("Update GPA");

        btnDesiredGradeUpdate.addActionListener(e -> {
            try{

                dbActions.getGradeTable();
            } catch (Exception q) { q.printStackTrace(); }
        });

        Font font = new Font("", 1, 16);
        model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(columns);
/*        table.setModel(model);
        table.setBackground(Color.white);
        table.setForeground(Color.red);
        table.setFont(font);
        table.setRowHeight(30);
*/
        //ScrollPane
        pane = new JScrollPane(table);

        //setting layouts
        panelBottom.setLayout(new BoxLayout(panelBottom,BoxLayout.PAGE_AXIS));
        //panelBottom.setLayout(new GridLayout(7,1,5,5));
        panelTop.setLayout(new FlowLayout());
        panelMiddle.setLayout(new BoxLayout(panelMiddle,BoxLayout.PAGE_AXIS));
        labelPanel.setLayout(new FlowLayout());
        yearPanel.setLayout(new FlowLayout());
        semesterPanel.setLayout(new FlowLayout());
        coursePanel.setLayout(new FlowLayout());
        desiredGradePanel.setLayout(new FlowLayout());
        btnPanel.setLayout(new FlowLayout());
        updatedGpaPanel.setLayout(new FlowLayout());
        addDeleteBtnPanel.setLayout(new FlowLayout());
        currentGpaPanel.setLayout(new FlowLayout());

        improvingGradesLabel.setFont(new Font("Arial",Font.BOLD,13));
        textGPA.setEditable(false);
        textUpdatedGrade.setEditable(false);

        //adding top panel
        panelTop.add(pane);

        //setting relevant components in the relevant panels for middle panel
        currentGpaPanel.add(GPALabel);
        currentGpaPanel.add(textGPA);
        addDeleteBtnPanel.add(btnAddGrade);
        addDeleteBtnPanel.add(btnDeleteGrade);
        labelPanel.add(improvingGradesLabel);

        currentGpaPanel.add(addDeleteBtnPanel);

        panelMiddle.add(currentGpaPanel);
        //panelMiddle.add(addDeleteBtnPanel);
        panelMiddle.add(labelPanel);

        //setting bottom panel
        yearPanel.add(yearLabel);
        yearPanel.add(yearComboBox);
        semesterPanel.add(semesterLabel);
        semesterPanel.add(semesterComboBox);
        coursePanel.add(courseLabel);
        coursePanel.add(courseComboBox);
        desiredGradePanel.add(desiredGradeLabel);
        desiredGradePanel.add(textDesiredGrade);
        updatedGpaPanel.add(updatedGPA);
        updatedGpaPanel.add(textUpdatedGrade);
        btnPanel.add(btnDesiredGradeUpdate);

        panelBottom.add(yearPanel);
        panelBottom.add(semesterPanel);
        panelBottom.add(coursePanel);
        panelBottom.add(desiredGradePanel);
        panelBottom.add(updatedGpaPanel);
        panelBottom.add(btnPanel);

        //creating container to handle the frame content pane
        Container container = frame.getContentPane();
        //setting container layout
        container.setLayout(new BorderLayout());
        //attaching relevant panels to the container
        container.add(panelTop,BorderLayout.NORTH);
        container.add(panelMiddle,BorderLayout.WEST);
        container.add(panelBottom,BorderLayout.SOUTH);

        //frame properties
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        //listener for add grade button
        btnAddGrade.addActionListener(e -> screen = new AddGradeScreen());

        btnDeleteGrade.addActionListener(e -> ViewModel.deleteCourse(table.getValueAt(table.getSelectedRow(),0).toString()));

        viewModel.updateTable(true);
    }

    public void updateGradesTable(ArrayList<CourseDetails> courseDetails,boolean clearTable){
        Object[] row = new Object[6];
        //clearing the table first
        if(clearTable) {
            table.setModel(new DefaultTableModel(null, columns));
            model= (DefaultTableModel) table.getModel();
        }
        courseDetails.forEach((c) -> {
            row[0] = c.getCourseName();
            row[1] = c.getYear();
            row[2] = c.getSemester();
            row[3] = c.getTestGrade();
            row[4] = c.getCredits();
            row[5] = c.getFinalGrade();
            model.addRow(row);
        });
    }

    public void updateGPA(Double calculate_gpa) {
        textGPA.setText(new DecimalFormat("##.##").format(calculate_gpa));
    }
}