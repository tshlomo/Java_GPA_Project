package ViewModel;

import Model.DBActions;
import View.TableHomeFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewModel {

    private static DBActions dbActions;
    private static TableHomeFrame tableHomeFrame;
    //CREATE TABLE GPA(Course VARCHAR(255),Semester VARCHAR(255),TestGrade INT,Credits DOUBLE,finalGrade INT, PRIMARY KEY(Course))

    public ViewModel(TableHomeFrame tableHomeFrame){
        dbActions = new DBActions();
        this.tableHomeFrame = tableHomeFrame;
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

    public static void updateTable(boolean clearTable) {
        try {
            tableHomeFrame.updateGradesTable(dbActions.getGradeTable(),clearTable);
        } catch (SQLException e) {e.printStackTrace();}
    }
}
