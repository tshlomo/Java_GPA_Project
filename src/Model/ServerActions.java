package Model;

import java.sql.SQLException;

interface ServerActions {
     public void add_grade(String course,String semester,int testGrade,double credit,int finalGrade) throws SQLException;
     public int[] getAllGrades() throws SQLException;
    public void deleteGrade(String coursename)throws SQLException;



}
