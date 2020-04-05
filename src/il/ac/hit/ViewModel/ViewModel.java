package il.ac.hit.ViewModel;

import il.ac.hit.Interfaces.ISimpleActions;
import il.ac.hit.Model.Calculations;
import il.ac.hit.Model.DBActions;
import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.View.TableHomeFrame;

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

    @Override
    public void addGrade(CourseDetails courseDetails) throws DBActionsException
    {
        try {
            dbActions.addGrade(courseDetails);
        } catch (Exception e) {
            tableHomeFrame.editGrade(courseDetails);
        } finally {
            updateTable();
        }
    }

    private static void updateGPA(Double calculate_gpa) {
        tableHomeFrame.updateGPA(calculate_gpa);
    }

    public static void updateTable() throws DBActionsException {
        tableHomeFrame.updateGradesTable(dbActions.getGradeTable());
        tableHomeFrame.updateCourseComboBox(dbActions.getGradeTable());
        updateGPA(calculations.calculate_GPA());
    }

    @Override
    public void deleteGrade(String courseName) throws DBActionsException {
        if(courseName.equals("updateTable"))
            updateTable();
        else {
            dbActions.deleteGrade(courseName);
            updateTable();
        }
    }

    @Override
    public void editGrade(CourseDetails courseDetails) throws DBActionsException{
        dbActions.editGrade(courseDetails);
    }
}
