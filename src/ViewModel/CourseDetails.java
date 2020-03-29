package ViewModel;

public class CourseDetails {

//(Course VARCHAR(255),Semester VARCHAR(255),TestGrade INT,Credits DOUBLE,finalGrade INT, PRIMARY KEY(Course)

    //choose # of semesters
    private static final Integer NUM_OF_SEMESTERS=2;

    private String courseName;
    private String semester;
    private Integer testGrade;
    private Double credits;
    private Integer finalGrade;

    public CourseDetails(String courseName, String semester, Integer testGrade, Double credits, Integer finalGrade) {
        this.courseName = courseName;
        this.semester = semester;
        this.testGrade = testGrade;
        this.credits = credits;
        this.finalGrade = finalGrade;
    }

    public Integer getYear(){
        //return (getSemester())/NUM_OF_SEMESTERS;
        return 0;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Integer getTestGrade() {
        return testGrade;
    }

    public void setTestGrade(Integer testGrade) {
        this.testGrade = testGrade;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public Integer getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Integer finalGrade) {
        this.finalGrade = finalGrade;
    }
}
