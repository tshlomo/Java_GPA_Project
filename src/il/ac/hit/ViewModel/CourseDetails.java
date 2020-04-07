package il.ac.hit.ViewModel;

/**
 * This class is used to
 * hold the values of every course with ease
 *
 */
public class CourseDetails {

    private String courseName;
    private Integer year;
    private Integer semester;
    private Integer testGrade;
    private Double credits;
    private Integer finalGrade;

    /**
     * constructor for the <code>CourseDetails</code> class instantiates class variables
     *
     * @param courseName name of the course
     * @param year year of course
     * @param semester semester of course
     * @param testGrade final test score of course
     * @param credits credits of course
     * @param finalGrade final grade of course
     */


    public CourseDetails(String courseName, Integer year, Integer semester, Integer testGrade, Double credits, Integer finalGrade) {
        setCourseName(courseName);
        setYear(year);
        setSemester(semester);
        setTestGrade(testGrade);
        setCredits(credits);
        setFinalGrade(finalGrade);
    }

    /**
     * sets course name
     *
     * @param courseName name of course
     */
    private void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * sets course year
     *
     * @param year course year
     */

    private void setYear(Integer year) {
        this.year = year;
    }

    /**
     * sets course semester
     *
     * @param semester course semester
     */

    private void setSemester(Integer semester) { this.semester = semester; }

    /**
     * sets course test Grade
     *
     * @param testGrade course test Grade
     */

    private void setTestGrade(Integer testGrade) {
        this.testGrade = testGrade;
    }

    /**
     * sets course credits
     *
     * @param credits course credits
     */


    private void setCredits(Double credits) {
        this.credits = credits;
    }

    /**
     * sets course final Grade
     *
     * @param finalGrade course final Grade
     */


    private void setFinalGrade(Integer finalGrade) {
        this.finalGrade = finalGrade;
    }

    /**
     * gets course year
     *
     * @return course year
     */

    public Integer getYear(){ return year; }

    /**
     * gets course name
     *
     * @return name of course
     */

    public String getCourseName() {
        return courseName;
    }

    /**
     * gets course semester
     *
     * @return course semester
     */

    public Integer getSemester() {
        return semester;
    }

    /**
     * gets course test grade
     *
     * @return course test grade
     */

    public Integer getTestGrade() {
        return testGrade;
    }

    /**
     * gets course credits
     *
     * @return course credits
     */

    public Double getCredits() {
        return credits;
    }

    /**
     * gets course final grade
     *
     * @return course final grade
     */

    public Integer getFinalGrade() {
        return finalGrade;
    }

    /**
     * compares two <code>CourseDetails</code> objects
     * checks if all the variables in the object are equal
     *
     * @return true/false
     */

    public Boolean isSameCourse(CourseDetails courseDetails) {
        if (getCourseName().equals(courseDetails.getCourseName()))
            if (getYear() == courseDetails.getYear())
                if (getSemester() == courseDetails.getSemester())
                    if (getCredits().equals(courseDetails.getCredits()))
                        if (getTestGrade() == courseDetails.getTestGrade())
                            if (getFinalGrade() == courseDetails.getFinalGrade())
                                return true;
        return false;
    }
}
