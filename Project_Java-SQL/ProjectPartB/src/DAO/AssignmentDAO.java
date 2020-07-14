package DAO;

import DBUtils.DBUtils;
import static DBUtils.DBUtils.getConnection;
import Model.Assignment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class AssignmentDAO {

    // Methodods ektiposis listas me ta Assignments
    public static void assignmentsList() throws ParseException, SQLException {

        Connection con = getConnection();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ASSIGNMENT");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            System.out.print(" | ");
            System.out.println(resultSet.getString(2));
            System.out.print(" | ");
            System.out.println(resultSet.getString(3));
            System.out.print(" | ");
            System.out.println(resultSet.getTimestamp(4));
            System.out.print(" | ");
            System.out.println(resultSet.getInt(5));
        }

    }

    // Methodos gia dimiourgia Assignment
    public static void addAssignment() throws ParseException {

        Scanner sc = new Scanner(System.in);

        // Eisagogi Assignment   
        System.out.println("Inserting an Assignment to the Data Base");
        System.out.println("Give the Tittle of the Assignment");
        String tittle = sc.next();
        System.out.println("Give the Description of the Assignment");
        String description = sc.next();

        // Submission Date kai eyresi imeras tis evdomadas
        int dayOfWeek = 1;
        Date subDateTime = null;
        while ((dayOfWeek == 1) || (dayOfWeek == 7)) {
            System.out.println("Give the Submission Date of the Assignment (dd/MM/yyyy)");
            System.out.println("It must be a Week Day not Weekend!");
            String subDate = sc.next();
            SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/yyyy");
            subDateTime = dateForm.parse(subDate);
            Calendar c = Calendar.getInstance();
            c.setTime(subDateTime);
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        }

        // Dimioyrgeia Assignment
        Assignment assignment = new Assignment(tittle, description, subDateTime);

        // Esagogi tou Assignment sti Basi
        AssignmentDAO.insertAssignment(assignment);

    }

    // Εισαγωγή Assignment sti Basi με χρήση prepared statement. 
    public static void insertAssignment(Assignment assignment) {
        String sql = "insert into trainer values(null,?,?,?)";
        System.out.println(sql);
        Connection con = DBUtils.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, assignment.getTittle());
            ps.setString(2, assignment.getDescription());
            ps.setTimestamp(3, (Timestamp) assignment.getSubDateTime());
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
