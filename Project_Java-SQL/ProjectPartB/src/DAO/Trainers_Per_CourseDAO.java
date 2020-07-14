package DAO;

import static DBUtils.DBUtils.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class Trainers_Per_CourseDAO {

    // Methodods ektiposis listas me tous trainers per Course
    public static void trainersPerCourseList() throws ParseException, SQLException {
        Connection con = getConnection();
        Statement statement = con.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT T.TRAINER_ID, T.FIRST_NAME, T.LAST_NAME, T.SUBJECT, C.COURSE_ID, C.STREAM, C.TYPE, C.START_DATE, C.END_DATE"
                                                    + "FROM TRAINER T, COURSE C"
                                                    + "WHERE T.COURSE_ID = C.COURSE_ID");
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
