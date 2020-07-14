package DAO;

import static DBUtils.DBUtils.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class Assignments_Per_CourseDAO {

     // Methodods ektiposis listas me ta Assignments Per Course
    public static void AssignmentsPerCourseList() throws ParseException, SQLException {
        Connection con = getConnection();
        Statement statement = con.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT A. ASSIGNMENT_ID, A.TITTLE, A.SUB_DATE_TIME,C.COURSE_ID, C.STREAM, C.TYPE"
                                                    + "FROM ASSIGNMENT A, COURSE C"
                                                    + "WHERE A.COURSE_ID = C.COURSE_ID;");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            System.out.print(" | ");
            System.out.println(resultSet.getString(2));
            System.out.print(" | ");
            System.out.println(resultSet.getTimestamp(3));
            System.out.print(" | ");
            System.out.println(resultSet.getInt(4));
            System.out.print(" | ");
            System.out.println(resultSet.getString(5));
            System.out.print(" | ");
            System.out.println(resultSet.getString(6));

        }

    }

}
