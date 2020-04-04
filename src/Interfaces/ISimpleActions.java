package Interfaces;

import Model.DBActionsException;
import ViewModel.CourseDetails;

public interface ISimpleActions {
    public void addGrade(CourseDetails courseDetails) throws DBActionsException;
    public void deleteGrade(String courseName) throws DBActionsException;
}
