package il.ac.hit.Interfaces;

import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.ViewModel.CourseDetails;

public interface ISimpleActions {
    void addGrade(CourseDetails courseDetails) throws DBActionsException;
    void deleteGrade(String courseName) throws DBActionsException;
    void editGrade(CourseDetails courseDetails) throws  DBActionsException;

}
