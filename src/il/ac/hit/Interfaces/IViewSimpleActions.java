package il.ac.hit.Interfaces;

import il.ac.hit.ViewModel.CourseDetails;

import java.util.List;

public interface IViewSimpleActions extends ISimpleActions {
    void updateGradesTable(List<CourseDetails> courseDetails);
    void updateGPA(Double newGPA);
}
