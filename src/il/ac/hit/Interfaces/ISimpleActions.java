package il.ac.hit.Interfaces;

import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.ViewModel.CourseDetails;

/**
 * <code>ISimpleActions</code> interface is used to perform simple actions
 * such as <code>addGrade</code>, <code>deleteGrade</code> or <code>editGrade</code>
 * <p>
 *     This whole interface is used for adding, deleting or editing a grade
 *     by the <code>CourseDetails</code> param it receives
 * </p>
 * @see CourseDetails the object used to pass the course info
 * @see IDBSimpleActions the interface that extends this interface
 * @see IFindNewGPA the interface that extends this interface
 */
public interface ISimpleActions {
    /**
     * <code>addGrade</code> function adds the course with the relevant info that the user has entered inside the database.
     * @param courseDetails the course data that the user has sent
     * @throws DBActionsException in case of an SQL error
     */
    void addGrade(CourseDetails courseDetails) throws DBActionsException;

    /**
     * <code>deleteGrade</code> function is used by the user to delete a grade give the key-<code>courseName</code>
     * @param courseName    the key-value for the database to select the relevant course
     * @throws DBActionsException in case of an SQL error
     */
    void deleteGrade(String courseName) throws DBActionsException;

    /**
     * <code>editGrade</code> function is for editing an existing course details within the database using the values the user has entered
     * @param courseDetails the course and it's relevant details to be edited
     * @throws DBActionsException in case of an SQL error
     */
    void editGrade(CourseDetails courseDetails) throws  DBActionsException;
}
