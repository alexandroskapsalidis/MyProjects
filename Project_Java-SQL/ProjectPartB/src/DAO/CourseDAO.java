package DAO;

import DBUtils.DBUtils;
import static DBUtils.DBUtils.getConnection;
import Model.Course;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class CourseDAO {

    // Methododos ektiposis listas me ta Courses
    public static void coursesList() throws ParseException, SQLException {
        Connection con = getConnection();
        Statement statement = con.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM COURSE");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            System.out.print(" | ");
            System.out.println(resultSet.getString(2));
            System.out.print(" | ");
            System.out.println(resultSet.getString(3));
            System.out.print(" | ");
            System.out.println(resultSet.getDate(4));
            System.out.print(" | ");
            System.out.println(resultSet.getDate(5));
        }

    }

    // Methodos gia dimiourgia Course
    public static void addCourse() throws ParseException {

        Scanner sc = new Scanner(System.in);

        // Eisagogi Titlou
        System.out.println("Give the Stream of the Course: ");
        System.out.println("Press 1 for Java Course");
        System.out.println("Press 2 for C# Course");
        String streamCourse = "";
        int tittleC = sc.nextInt();
        switch (tittleC) {
            case 1:
                streamCourse = "Java";
                break;
            case 2:
                streamCourse = "C#";
                break;
            default:
                System.out.println("Invalid Number!");
                break;
        }
        //Eisagogi Typou
        System.out.println("Give the Type of the Course: ");
        System.out.println("Press 1 for Full Time");
        System.out.println("Press 2 for Part Time");
        String typeCourse = "";
        int typeC = sc.nextInt();
        switch (typeC) {
            case 1:
                typeCourse = "Full Time";
                break;
            case 2:
                typeCourse = "Part Time";
                break;
            default:
                System.out.println("Invalid Number!");
                break;
        }

        System.out.println("Give the start date of the course (dd/MM/yyyy): ");
        String startDate = sc.next();
        SimpleDateFormat startDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date start_date = startDateFormat.parse(startDate);             // start date of the course

        System.out.println("Give the end date of the course (dd/MM/yyyy): ");
        String endDate = sc.next();
        SimpleDateFormat endDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date end_date = endDateFormat.parse(startDate);                 // end date of the Course

        // Dimioyrgia Course
        Course course = new Course(streamCourse, typeCourse, start_date, end_date);

        // Eisagogi tou Course sti Basi                
        CourseDAO.insertCourse(course);

    }

    // Εισαγωγή Course με χρήση prepared statement. 
    public static void insertCourse(Course course) {
        String sql = "insert into trainer values(null,?,?,?,?)";
        System.out.println(sql);
        Connection con = DBUtils.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, course.getStream());
            ps.setString(2, course.getType());
            ps.setDate(3, (Date) course.getStart_date());
            ps.setDate(4, (Date) course.getEnd_date());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
