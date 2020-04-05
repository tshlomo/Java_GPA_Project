package View;

import org.apache.log4j.BasicConfigurator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.logging.Logger;

/**
 * The Table class is used to display and edit regular
 * two-dimensional tables of cells, the Table class has many facilities
 * that make it possible to customize and edit but provides defaults
 * for these features so that simple tables can be set up easily.
 */

public class Table {
    protected static Logger logger = Logger.getLogger("Table");

    public static void main(String[] args) {
        BasicConfigurator.configure();

        JFrame frame = new JFrame();
        JTable table = new JTable();
        //AddGradeScreen screen = new AddGradeScreen();

        String[] columns = {"Course", "Year", "Semester", "Final Test", "Credits", "Final Grade"};
        logger.info("setting table");
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);

        table.setBackground(Color.white);
        logger.info("setting background color for table white");
        table.setForeground(Color.red);
        logger.info("setting background color for table red");
        Font font = new Font("", Font.BOLD, 16);
        table.setFont(font);
        table.setRowHeight(30);

        //creating labels
        JLabel GPALabel = new JLabel("GPA");
        logger.info("setting gpa label");

        //creating text fields
        JTextField textGPA = new JTextField();
        logger.info("creating text fields");

        //Adding button
        JButton btnAddGrade = new JButton("Add New Grade");
        JButton btnDeleteGrade = new JButton("Delete Grade");
        logger.info("adding buttons");

        //buttons dimensions
        btnAddGrade.setBounds(1,200,125,25);
        btnDeleteGrade.setBounds(1,230,125,25);
        logger.info("buttons dimensions");

        //labels dimensions
        GPALabel.setBounds(750, 200, 100, 25);
        logger.info("labels dimensions");

        //text fields dimensions
        textGPA.setBounds(780, 200, 100, 25);
        logger.info("text fields dimensions");

        //Adding scroll pane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);
        logger.info("Adding scroll pane");

        //Adding pane
        frame.add(pane);
        logger.info("Adding pane");

        //Adding text fields
        frame.add(textGPA);
        logger.info("Adding text fields");

        //Adding labels
        frame.add(GPALabel);
        logger.info("Adding labels");

        //Adding buttons
        frame.add(btnAddGrade);
        frame.add(btnDeleteGrade);
        logger.info("Adding buttons");

        frame.setLayout(null);
        frame.setSize(900, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Object[] row = new Object[100];

        btnAddGrade.addActionListener(e -> {
            //AddGradeScreen.addGradeScreen();
            logger.info("action has been performed - button has been pressed");
        });
    }
}

