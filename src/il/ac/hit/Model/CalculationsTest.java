package il.ac.hit.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationsTest {

    @Test
    void calculate_Final_Grade() {
        Calculations calc= new Calculations();
        Integer finalGrade=calc.calculate_Final_Grade(90.0,70.0,80.0,30.0);
        assertEquals(87,finalGrade);
    }

    @Test
    void calculate_GPA() {
    }

    @Test
    void gpaByGrade() {
    }
}