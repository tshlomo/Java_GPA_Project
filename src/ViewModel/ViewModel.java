package ViewModel;

import Model.DBActions;

import java.sql.SQLException;

public class ViewModel {

    private DBActions dbActions;
    //CREATE TABLE GPA(Course VARCHAR(255),Semester VARCHAR(255),TestGrade INT,Credits DOUBLE,finalGrade INT, PRIMARY KEY(Course))

    public ViewModel(){
        dbActions = new DBActions();
    }
    public void addNewGrade(String courseName, Character semester, Integer testGrade,Double credits, Integer finalGrade)
    {
        try {
            dbActions.addGrade(courseName,semester.toString(),testGrade,credits,finalGrade);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
