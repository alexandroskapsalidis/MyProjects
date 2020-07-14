package DAO;

import DBUtils.DBUtils;
import static DBUtils.DBUtils.getConnection;
import Model.Student;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO {

    // Methododos ektiposis listas me tous Students
    public static void studentsList() throws ParseException, SQLException {
        Connection con = getConnection();
        Statement statement = con.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENT");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            System.out.print(" | ");
            System.out.println(resultSet.getString(2));
            System.out.print(" | ");
            System.out.println(resultSet.getString(3));
            System.out.print(" | ");
            System.out.println(resultSet.getDate(4));
            System.out.print(" | ");
            System.out.println(resultSet.getDouble(5));
        }

    }

    // Methodos gia dimiourgia Student
    public static void addStudent() throws ParseException {

        Scanner sc = new Scanner(System.in);
        
        
        // Eisagogi steixeion Student
        System.out.println("Give the Name of the Student:");
        String fname = sc.next();
        System.out.println("Give the Surname of the Student:");
        String lname = sc.next();
        System.out.println("Give the Date of Birth (dd/MM/yyyy):");
        String birthDate = sc.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dateOfBirth = dateFormat.parse(birthDate);           // Date of birth
        System.out.println("Give the Tuition Fees: ");
        double tuitionFees = sc.nextDouble();

        // Dimiourgia Student
        Student st = new Student(fname, lname, dateOfBirth, tuitionFees);

        // Eisagogi Student sti Basi
        StudentDAO.insertStudent(st);

    }

    // Methodos gia eisagogi Student sth Basi
    public static void insertStudent(Student student) {
        String sql = "insert into student values(null,?,?,?,?)";
        System.out.println(sql);
        Connection con = DBUtils.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setDate(3, (Date) student.getDateOfBirth());
            ps.setString(4, student.getLastName());
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
