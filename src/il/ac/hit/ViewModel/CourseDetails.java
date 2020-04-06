package il.ac.hit.ViewModel;

public class CourseDetails {

    private static final Integer NUM_OF_SEMESTERS=3;

    private String courseName;
    private Integer year;
    private Integer semester;
    private Integer testGrade;
    private Double credits;
    private Integer finalGrade;

    public CourseDetails(String courseName, Integer year, Integer semester, Integer testGrade, Double credits, Integer finalGrade) {
        setCourseName(courseName);
        setYear(year);
        setSemester(semester);
        setTestGrade(testGrade);
        setCredits(credits);
        setFinalGrade(finalGrade);
    }
    private void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    private void setYear(Integer year) {
        this.year = year;
    }

    private void setSemester(Integer semester) {
        this.semester = semester;
    }

    private void setTestGrade(Integer testGrade) {
        this.testGrade = testGrade;
    }

    private void setCredits(Double credits) {
        this.credits = credits;
    }

    private void setFinalGrade(Integer finalGrade) {
        this.finalGrade = finalGrade;
    }


    public Integer getYear(){ return year; }

    public String getCourseName() {
        return courseName;
    }

    public Integer getSemester() {
        return semester % NUM_OF_SEMESTERS;
    }

    public Integer getTestGrade() {
        return testGrade;
    }

    public Double getCredits() {
        return credits;
    }

    public Integer getFinalGrade() {
        return finalGrade;
    }

    public Boolean isSameCourse(CourseDetails courseDetails) {
        if (getCourseName().equals(courseDetails.getCourseName()))
            if (getYear() == courseDetails.getYear())
                if (getSemester() == courseDetails.getSemester())
                    if (getCredits() == courseDetails.getCredits())
                        if (getTestGrade() == courseDetails.getTestGrade())
                            if (getFinalGrade() == courseDetails.getFinalGrade())
                                return true;
        return false;
    }
}
