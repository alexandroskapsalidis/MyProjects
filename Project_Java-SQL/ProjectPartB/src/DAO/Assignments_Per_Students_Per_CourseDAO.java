package DAO;

import static DBUtils.DBUtils.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class Assignments_Per_Students_Per_CourseDAO {

    // Methododos ektiposis listas me ta Assignments per Student per Course
    public static void assignmentsPerStudentPerCourseList() throws ParseException, SQLException {
        Connection con = getConnection();
        Statement statement = con.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT S.STUDENT_ID, S.FIRST_NAME, S.LAST_NAME, C.COURSE_ID, C.STREAM, C.TYPE, A.ASSIGNMENT_ID, A.TITTLE, A.SUB_DATE_TIME"
                                                    + "FROM STUDENT S, COURSE C, ASSIGNMENT A, ASSIGNMENT_PER_COURSE_PER_STUDENT APCS"
                                                    + "WHERE A.ASSIGNMENT_ID = APCS.ASSIGNMENT_ID"
                                                    + "AND C.COURSE_ID = APCS.COURSE_ID"
                                                    + "AND S.STUDENT_ID = APCS.STUDENT_ID;");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            System.out.print(" | ");
            System.out.println(resultSet.getString(2));
            System.out.print(" | ");
            System.out.println(resultSet.getString(3));
            System.out.print(" | ");
            System.out.println(resultSet.getInt(4));
            System.out.print(" | ");
            System.out.println(resultSet.getString(5));
            System.out.print(" | ");
            System.out.println(resultSet.getString(6));
            System.out.print(" | ");
            System.out.println(resultSet.getInt(7));
            System.out.print(" | ");
            System.out.println(resultSet.getString(8));
            System.out.print(" | ");
            System.out.println(resultSet.getTimestamp(9));
        }

    }

}
