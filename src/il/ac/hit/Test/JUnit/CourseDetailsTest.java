package il.ac.hit.Test.JUnit;

import il.ac.hit.ViewModel.CourseDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseDetailsTest {
    public CourseDetails course;

    @BeforeEach
    void setUp() {
        course = new CourseDetails("CourseDetailsTest",3,2,100,3.5,99);
    }

    @AfterEach
    void tearDown() {
        course = null;
    }

    @Test
    void getYear() {
        assertEquals(3,course.getYear());
    }

    @Test
    void getCourseName() {
        assertEquals("CourseDetailsTest",course.getCourseName());
    }

    @Test
    void getSemester() {
        assertEquals(2,course.getSemester());

    }

    @Test
    void getTestGrade() {
        assertEquals(100,course.getTestGrade());
    }

    @Test
    void getCredits() {
        assertEquals(3.5,course.getCredits());
    }

    @Test
    void getFinalGrade() {
        assertEquals(99,course.getFinalGrade());
    }
}