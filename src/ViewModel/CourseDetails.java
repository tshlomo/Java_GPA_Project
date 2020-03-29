package ViewModel;

public class CourseDetails {

//(Course VARCHAR(255),Semester VARCHAR(255),TestGrade INT,Credits DOUBLE,finalGrade INT, PRIMARY KEY(Course)

    //choose # of semesters
    private static final Integer NUM_OF_SEMESTERS=3;

    private String courseName;
    private Integer semester;
    private Integer testGrade;
    private Double credits;
    private Integer finalGrade;

    public CourseDetails(String courseName, Integer semester, Integer testGrade, Double credits, Integer finalGrade) {
        this.courseName = courseName;
        this.semester = semester;
        this.testGrade = testGrade;
        this.credits = credits;
        this.finalGrade = finalGrade;
    }

    public Integer getYear(){
       return (semester/NUM_OF_SEMESTERS)+1;
       //return String.valueOf(((Integer.valueOf(semester.toUpperCase()))-'A')/NUM_OF_SEMESTERS);
    }

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
