package ViewModel;

import Interfaces.ISimpleActions;
import Model.Calculations;
import Model.DBActions;
import View.TableHomeFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewModel implements ISimpleActions {

    private static DBActions dbActions;
    private static TableHomeFrame tableHomeFrame;
    private static Calculations calculations;
    //CREATE TABLE GPA(Course VARCHAR(255),Semester VARCHAR(255),TestGrade INT,Credits DOUBLE,finalGrade INT, PRIMARY KEY(Course))

    public ViewModel(TableHomeFrame tableHomeFrame){
        dbActions = new DBActions();
        this.tableHomeFrame = tableHomeFrame;
        calculations = new Calculations();
    }

    public void addGrade(CourseDetails courseDetails)
    {
        try {
            dbActions.addGrade(courseDetails);
            updateTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateGPA(Double calculate_gpa) {
        tableHomeFrame.updateGPA(calculate_gpa);
    }

    public static void updateTable() {
        try {
            tableHomeFrame.updateGradesTable(dbActions.getGradeTable());
            updateGPA(calculations.calculate_GPA());
        } catch (SQLException e) {e.printStackTrace();}
    }

    public void deleteGrade(String courseName) {
        if(courseName.equals("updateTable"))
            updateTable();
        else {
            try {
                dbActions.deleteGrade(courseName);
                updateTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
