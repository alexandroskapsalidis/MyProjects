package DAO;

import static DBUtils.DBUtils.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class Students_Per_CourseDAO {

    // Methododos ektiposis listas me tous Students per Course
    public static void studentsPerCourseList() throws ParseException, SQLException {
        Connection con = getConnection();
        Statement statement = con.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT ST.STUDENT_ID, ST.FIRST_NAME, ST.LAST_NAME, ST.DATE_OF_BIRTH, C.COURSE_ID, C.STREAM, C.TYPE, C.START_DATE, C.END_DATE"
                                                    + "FROM STUDENT ST, COURSE C, STUDENT_PER_COURSE SPC"
                                                    + "WHERE ST.STUDENT_ID = SPC.STUDENT_ID"
                                                    + "AND C.COURSE_ID = SPC.COURSE_ID;");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            System.out.print(" | ");
            System.out.println(resultSet.getString(2));
            System.out.print(" | ");
            System.out.println(resultSet.getString(3));
            System.out.print(" | ");
            System.out.println(resultSet.getDate(4));
            System.out.print(" | ");
            System.out.println(resultSet.getInt(5));
            System.out.print(" | ");
            System.out.println(resultSet.getString(6));
            System.out.print(" | ");
            System.out.println(resultSet.getString(7));
            System.out.print(" | ");
            System.out.println(resultSet.getDate(8));
            System.out.print(" | ");
            System.out.println(resultSet.getDate(9));
        }

    }

}
