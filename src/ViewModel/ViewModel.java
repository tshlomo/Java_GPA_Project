package ViewModel;

import Model.Caluclations;
import Model.DBActions;
import View.TableHomeFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewModel {

    private static DBActions dbActions;
    private static TableHomeFrame tableHomeFrame;
    private static Caluclations caluclations;
    //CREATE TABLE GPA(Course VARCHAR(255),Semester VARCHAR(255),TestGrade INT,Credits DOUBLE,finalGrade INT, PRIMARY KEY(Course))

    public ViewModel(TableHomeFrame tableHomeFrame){
        dbActions = new DBActions();
        this.tableHomeFrame = tableHomeFrame;
        caluclations = new Caluclations();
    }

    public static void addNewGrade(String courseName, Integer year, Integer semester, Integer testGrade,Double credits, Integer finalGrade)
    {
        try {
            dbActions.addGrade(courseName,year,semester,testGrade,credits,finalGrade);
            updateTable(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateGPA(Double calculate_gpa) {
        tableHomeFrame.updateGPA(calculate_gpa);
    }

    public static void updateTable(boolean clearTable) {
        try {
            tableHomeFrame.updateGradesTable(dbActions.getGradeTable(),clearTable);
            updateGPA(caluclations.calculate_GPA());
        } catch (SQLException e) {e.printStackTrace();}
    }

    public static void deleteCourse(String courseName) {
        try {
            dbActions.deleteGrade(courseName);
            updateTable(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
