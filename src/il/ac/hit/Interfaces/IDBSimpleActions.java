package il.ac.hit.Interfaces;

import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.ViewModel.CourseDetails;

import java.util.List;

public interface IDBSimpleActions extends ISimpleActions {
    List<CourseDetails> getGradeTable() throws DBActionsException;
}
