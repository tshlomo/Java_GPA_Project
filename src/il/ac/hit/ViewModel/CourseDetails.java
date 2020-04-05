package il.ac.hit.ViewModel;

public class CourseDetails {

//(Course VARCHAR(255),Semester VARCHAR(255),TestGrade INT,Credits DOUBLE,finalGrade INT, PRIMARY KEY(Course)

    //choose # of semesters
    private static final Integer NUM_OF_SEMESTERS=3;

    private String courseName;
    private Integer year;
    private Integer semester;
    private Integer testGrade;
    private Double credits;
    private Integer finalGrade;



    public CourseDetails(String courseName, Integer year, Integer semester, Integer testGrade, Double credits, Integer finalGrade) {
        this.courseName = courseName;
        this.year = year;
        this.semester = semester;
        this.testGrade = testGrade;
        this.credits = credits;
        this.finalGrade = finalGrade;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public void setTestGrade(Integer testGrade) {
        this.testGrade = testGrade;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public void setFinalGrade(Integer finalGrade) {
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
}
