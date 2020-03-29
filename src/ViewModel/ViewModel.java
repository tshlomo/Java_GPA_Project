package ViewModel;

import Model.DBActions;
import View.TableHomeFrame;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewModel {

    private DBActions dbActions;
    private TableHomeFrame tableHomeFrame;
    //CREATE TABLE GPA(Course VARCHAR(255),Semester VARCHAR(255),TestGrade INT,Credits DOUBLE,finalGrade INT, PRIMARY KEY(Course))

    public ViewModel(TableHomeFrame tableHomeFrame){
        dbActions = new DBActions();
        this.tableHomeFrame = tableHomeFrame;
    }
    public void addNewGrade(String courseName, Integer semester, Integer testGrade,Double credits, Integer finalGrade)
    {
        try {
            dbActions.addGrade(courseName,semester,testGrade,credits,finalGrade);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTable() {
        try {
            ArrayList<CourseDetails> courseDetails = dbActions.getGradeTable();
            tableHomeFrame.updateGradesTable(courseDetails);
        } catch (SQLException e) {e.printStackTrace();}
    }
}
