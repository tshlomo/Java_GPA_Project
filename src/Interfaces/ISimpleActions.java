package Interfaces;

import ViewModel.CourseDetails;

public interface ISimpleActions {
    public void addGrade(CourseDetails courseDetails);
    public void deleteGrade(String courseName);
}
