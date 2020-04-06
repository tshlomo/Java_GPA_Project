package il.ac.hit.View;
import il.ac.hit.Interfaces.IFindNewGPA;
import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.Interfaces.IViewSimpleActions;
import il.ac.hit.ViewModel.*;

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


public class TableHomeFrame implements IViewSimpleActions {

    private static AddGradeScreen screen;

    private IFindNewGPA simpleAndGPAActions;

    private JFrame frame;
    private JTable table;
    private JPanel panelMiddle,panelBottom,labelPanel,coursePanel,desiredGradePanel,updatedGpaPanel,btnPanel,addDeleteBtnPanel,currentGpaPanel;
    private JComboBox courseComboBox;
    private JLabel GPALabel;
    private JLabel desiredGradeLabel;
    private JLabel courseLabel;
    private JLabel improvingGradesLabel;
    private JLabel updatedGPA;
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
    private static final String[] columns = {"Course", "Year", "Semester", "Test", "Credits", "Final Grade"};

    public TableHomeFrame() throws DBActionsException {
        simpleAndGPAActions = new ViewModel(this);
        //Frame
        frame = new JFrame("GPA");
        //Table
        table = new JTable();
        //Panels
        panelBottom = new JPanel();
        panelMiddle = new JPanel();
        labelPanel = new JPanel();
        coursePanel = new JPanel();
        desiredGradePanel = new JPanel();
        updatedGpaPanel = new JPanel();
        btnPanel = new JPanel();
        addDeleteBtnPanel = new JPanel();
        currentGpaPanel = new JPanel();
        //ComboBox
        courseComboBox = new JComboBox();
        //Labels
        GPALabel = new JLabel("Current GPA");
        desiredGradeLabel = new JLabel("Desired Grade");
        courseLabel = new JLabel("Course");
        improvingGradesLabel = new JLabel("Fill the details below to calculate your GPA after course improvement");
        updatedGPA = new JLabel("Updated GPA");
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
                    textDesiredGrade.setText("100");
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
            if(textDesiredGrade.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please input desired grade");
            } else {
                try {
                    textUpdatedGrade.setText(simpleAndGPAActions.newGPA(String.valueOf(courseComboBox.getSelectedItem()), Integer.valueOf(textDesiredGrade.getText())).toString());
                } catch (DBActionsException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //disabling editing the table
        table.setDefaultEditor(Object.class,null);
        //auto resizing the columns
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(columns);

        //ScrollPane
        pane = new JScrollPane(table);

        //setting layouts
        panelBottom.setLayout(new BoxLayout(panelBottom,BoxLayout.PAGE_AXIS));
        panelMiddle.setLayout(new BoxLayout(panelMiddle,BoxLayout.PAGE_AXIS));
        labelPanel.setLayout(new FlowLayout());
        coursePanel.setLayout(new FlowLayout());
        desiredGradePanel.setLayout(new FlowLayout());
        btnPanel.setLayout(new FlowLayout());
        updatedGpaPanel.setLayout(new FlowLayout());
        addDeleteBtnPanel.setLayout(new FlowLayout());
        currentGpaPanel.setLayout(new FlowLayout());

        improvingGradesLabel.setFont(new Font("Arial",Font.BOLD,13));

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

        //creating container to handle the frame content pane
        Container container = frame.getContentPane();
        //setting container layout
        container.setLayout(new BorderLayout());
        //attaching relevant panels to the container
        container.add(pane,BorderLayout.NORTH);
        container.add(panelMiddle,BorderLayout.CENTER);
        container.add(panelBottom,BorderLayout.SOUTH);

        //frame properties
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        //listener for add grade button to open new form
        btnAddGrade.addActionListener(e -> SwingUtilities.invokeLater(()-> screen = new AddGradeScreen(this)));

        //listener for delete button
        btnDeleteGrade.addActionListener(e -> {
            try {
                deleteGrade(table.getValueAt(table.getSelectedRow(),0).toString());
            } catch (DBActionsException e1) {
                e1.printStackTrace();
            }
        });

        //updating the table for the first time with special keyword 'updateTable'
        simpleAndGPAActions.deleteGrade("updateTable");
    }

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
            //setting the preffered maximum width
            col.setPreferredWidth(width + 3);
            //adding to the sum var
            columnsWidth+=width+3;
        }
        //setting the frame size to fit
        frame.setSize(columnsWidth + 20,frame.getHeight() + 20);
    }

    //The function trying to add a grade based on the values inside the courseDetails var
    @Override
    public void addGrade(CourseDetails courseDetails) throws DBActionsException {
        simpleAndGPAActions.addGrade(courseDetails);
    }

    //The function trying to delete a grade based on the course name
    @Override
    public void deleteGrade(String courseName) throws DBActionsException {
        simpleAndGPAActions.deleteGrade(courseName);
    }

    //The function trying to edit a grade based on the values inside the courseDetails var
    @Override
    public void editGrade(CourseDetails courseDetails) throws DBActionsException{
        int dialogResult = JOptionPane.showConfirmDialog (null, "You've already got grade on "+courseDetails.getCourseName()+"\nWould you like to update your grades?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            simpleAndGPAActions.editGrade(courseDetails);
        }
    }

    //The function updating the current GPA result based on the calculate_gpa double type var it receives
    @Override
    public void updateGPA(Double calculate_gpa) {
        textGPA.setText(new DecimalFormat("##.##").format(calculate_gpa));
    }

    //updating to course combobox to the current courses
    public void updateCourseComboBox(List<CourseDetails> gradeTable) {
        courseComboBox.removeAllItems();
        for (CourseDetails c: gradeTable){
            courseComboBox.addItem(c.getCourseName());
        }
    }
}