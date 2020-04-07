/**
 * The package <code>Test.JUnit</code> is in charged on the testing in the project
 */
package il.ac.hit.Test.JUnit;

import il.ac.hit.Model.Calculations;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationsTest {

    Calculations calc;

    @BeforeEach
    void beforeEach(){
        calc = new Calculations();
    }

    @AfterEach
    void afterEach(){
        calc=null;
    }

    @Test
    void calculate_Final_Grade() {
        Integer finalGrade = calc.calculate_Final_Grade(90.0, 70.0, 80.0, 30.0);
        assertEquals(87, finalGrade);
    }



}