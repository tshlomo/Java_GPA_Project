package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Caluclations {

    public static int calcu_final_grade(int testGrade,int testPercent,int hwGrade,int hwPercent){
        int score= testGrade*(testPercent/100)+hwGrade*(hwPercent/100);
        return score;
    }

    }

