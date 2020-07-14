package DAO;

import DBUtils.DBUtils;
import static DBUtils.DBUtils.getConnection;
import Model.Trainer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Logger;
import sun.util.logging.PlatformLogger;
import sun.util.logging.PlatformLogger.Level;

public class TrainerDAO {

    // Methodods ektiposis listas me tous trainers
    public static void trainersList() throws ParseException, SQLException {
        Connection con = getConnection();
        Statement statement = con.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM TRAINER");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            System.out.print(" | ");
            System.out.println(resultSet.getString(2));
            System.out.print(" | ");
            System.out.println(resultSet.getString(3));
            System.out.print(" | ");
            System.out.println(resultSet.getString(4));
            System.out.print(" | ");
            System.out.println(resultSet.getInt(5));
        }

    }

    // Methodos gia dimiourgia Trainer
    public static void addTrainer() throws ParseException {
        
        Scanner sc = new Scanner(System.in);
        
        // Eisagogi steixeion Trainer
        System.out.println("Inserting a Trainer to the Data Base");
        System.out.println("Give the First Name of the Trainer:");
        String firstName = sc.next();
        System.out.println("Give the Second Name of the Trainer:");
        String lastName = sc.next();
        System.out.println("Give the Subject of the Trainer:");
        String subject = sc.next();

        // Dimioyrgia Trainer
        Trainer trainer = new Trainer(firstName, lastName, subject);

        // Eisagogi tou Trainer sti Basi
        TrainerDAO.insertTrainer(trainer);

    }

    // Εισαγωγή trainer sti Basi με χρήση Prepared Statement. 
    public static void insertTrainer(Trainer trainer) {
        String sql = "insert into trainer values(null,?,?,?)";
        System.out.println(sql);
        Connection con = DBUtils.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, trainer.getFirstName());
            ps.setString(2, trainer.getLastName());
            ps.setString(3, trainer.getSubject());
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
