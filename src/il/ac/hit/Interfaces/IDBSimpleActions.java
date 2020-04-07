package il.ac.hit.Interfaces;

import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.ViewModel.CourseDetails;

import java.util.List;

/**
 * <code>IDBSimpleActions</code> interface in an extension for <code>ISimpleActions</code> interface and is adding up
 * upon it another functions to get all the courses table from the database or a specific course
 * @see ISimpleActions
 */
public interface IDBSimpleActions extends ISimpleActions {
    /**
     * <code>getGradeTable</code> function is used to return a <code>List</code> of <code>CourseDetails</code> objects
     * that are built from the database saved values
     * @return a <code>List</code> of <code>CourseDetails</code> containing all the courses and their details
     * @throws DBActionsException in case of an SQL error
     */
    List<CourseDetails> getGradeTable() throws DBActionsException;

    /**
     * <code>getCourse</code> function is used to get a course based on the key->value <code>courseName</code>
     * @param courseName the course name to be pulled from the database
     * @return a <code>CourseDetails</code> object with the relevant details
     * @throws DBActionsException in case of an SQL error
     */
    CourseDetails getCourse(String courseName) throws DBActionsException;
}
