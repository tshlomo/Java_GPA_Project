package View;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TableHomeFrame extends JTable {

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

    private AddGradeScreen screen;

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
    private JSeparator topSeparator;
    private JSeparator bottomSeperator;

    public TableHomeFrame() {
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
        textGPA = new JTextField(3);
        textDesiredGrade = new JTextField(3);
        textUpdatedGrade = new JTextField(3);
        //Buttons
        btnAddGrade = new JButton("Add New Grade");
        btnDeleteGrade = new JButton("Delete Grade");
        btnDesiredGradeUpdate = new JButton("Update GPA");
        //Separators
        //topSeparator = new JSeparator();
        //bottomSeperator = new JSeparator();

        //table properties
        String[] columns = {"Course", "Year", "Semester", "Final Test", "Credits", "Final Grade"};
        Font font = new Font("", 1, 16);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setBackground(Color.white);
        table.setForeground(Color.red);
        table.setFont(font);
        table.setRowHeight(30);

        //ScrollPane
        pane = new JScrollPane(table);

        //why we need this row?>
        Object[] row = new Object[100];

        /*
        //buttons dimensions
        btnAddGrade.setBounds(1, 300, 125, 25);
        btnDeleteGrade.setBounds(1, 330, 125, 25);
        btnDesiredGradeUpdate.setBounds(89,530,120,25);
        //labels dimensions
        GPALabel.setBounds(700, 300, 150, 25);
        courseLabel.setBounds(5,465,100,25);
        improvingGradesLabel.setBounds(5,365,1000,25);
        improvingGradesLabel.setForeground(Color.red);
        desiredGradeLabel.setBounds(5,500,100,25);
        updatedGPA.setBounds(700,530,100,25);
        yearLabel.setBounds(5,400,100,25);
        semesterLabel.setBounds(3,432,100,25);
        //text fields dimensions
        textGPA.setBounds(780, 300, 100, 25);
        textDesiredGrade.setBounds(110,500,100,25);
        textUpdatedGrade.setBounds(780,530,100,25);
        //scroll pane dimensions
        pane.setBounds(0, 0, 880, 300);
        //combo box dimensions
        courseComboBox.setBounds(59,465,150,25);
        semesterComboBox.setBounds(59,432,150,25);
        yearComboBox.setBounds(59,400,150,25);
        //Separators
        topSeparator.setBounds(5,360,875,1);
        bottomSeperator.setBounds(5,390,875,1);
        */


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
        //Container container = frame.getContentPane();
        //container.setLayout(new BorderLayout());




        improvingGradesLabel.setFont(new Font("Arial",Font.BOLD,13));
        textGPA.setEditable(false);
        textUpdatedGrade.setEditable(false);

        //adding top panel
        panelTop.add(pane);

        //setting middle panel + label panel into middle panel
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



        //Separators
        //frame.add(topSeparator);
        //frame.add(bottomSeperator);


//        frame.add(panelTop,BorderLayout.NORTH);
//        frame.add(panelMiddle,BorderLayout.CENTER);
//        frame.add(panelBottom,BorderLayout.SOUTH);
        Container container = frame.getContentPane();
        //setting container layout
        container.setLayout(new BorderLayout());

        container.add(panelTop,BorderLayout.NORTH);
        container.add(panelMiddle,BorderLayout.WEST);
        container.add(panelBottom,BorderLayout.SOUTH);

        //frame properties
        //frame.setSize(1400, 1000);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        //listener for add grade button
        btnAddGrade.addActionListener(e -> screen = new AddGradeScreen());
    }
}


//        //Setting up an action listener for btnAdd
//        btnAdd.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                row[0]= textCourse.getText();
//                row[1]= textQuiz.getText();
//                row[2]= textFinalTest.getText();
//                row[3]= textCredits.getText();
//                row[4]= Double.parseDouble(textQuiz.getText())*quizWeight + Double.parseDouble(textFinalTest.getText())*finalTestWeight;
//                model.addRow(row);
//            }
//        });
//
//        //Setting up an action listener for btnDelete
//        btnDelete.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int i = table.getSelectedRow();
//                if (i >= 0) {
//                    model.removeRow(i);
//                }
//                else System.out.println("Deletion Error");
//            }
//        });
//
//        //Setting a mouse listener for the selected row
//        table.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e){
//                int i = table.getSelectedRow();
//                textCourse.setText(model.getValueAt(i,0).toString());
//                textQuiz.setText(model.getValueAt(i,1).toString());
//                textFinalTest.setText(model.getValueAt(i,2).toString());
//                textCredits.setText(model.getValueAt(i,3).toString());
//
//            }
//        });
//
//
//    }
//}
