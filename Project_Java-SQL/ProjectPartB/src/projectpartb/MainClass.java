package projectpartb;

import DAO.AssignmentDAO;
import DAO.Assignments_Per_CourseDAO;
import DAO.Assignments_Per_Students_Per_CourseDAO;
import DAO.CourseDAO;
import DAO.StudentDAO;
import DAO.Students_Per_CourseDAO;
import DAO.TrainerDAO;
import DAO.Trainers_Per_CourseDAO;
import static DBUtils.DBUtils.getConnection;
import Model.Assignment;
import Model.Course;
import Model.Student;
import Model.Trainer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) throws SQLException, ParseException {

        Scanner sc = new Scanner(System.in);
        int choiceMenu = 1;
        while (choiceMenu != 0) {
            System.out.println("Welcome!");
            System.out.println("Press 1 to Print a List of all the Students");
            System.out.println("Press 2 to Print a List of all the Trainers");
            System.out.println("Press 3 to Print a List of all the Assignments");
            System.out.println("Press 4 to Print a List of all the Courses");
            System.out.println("Press 5 to Print a List of all the Students per Course");
            System.out.println("Press 6 to Print a List of all the Trainers per Course");
            System.out.println("Press 7 to Print a List of all the Assignments per Course");
            System.out.println("Press 8 to Print a List of all the Assignments per Course per Student");
            System.out.println("Press 9 to Insert a Student");
            System.out.println("Press 10 to Insert a Course");
            System.out.println("Press 11 to Insert a Trainer");
            System.out.println("Press 12 to Insert an Assignment");
            System.out.println("Press 13 to Insert a Student per Course");

            choiceMenu = sc.nextInt();

            switch (choiceMenu) {
                
                case 1:
                                      
                    // A list of all the students                    
                    StudentDAO.studentsList();  
                    
                case 2:
                    // A list of all the trainers
                    TrainerDAO.trainersList();                    
                    
                case 3:
                    // A list of all the assignments                    
                    AssignmentDAO.assignmentsList();
                    
                case 4:
                    // A list of all the courses
                    CourseDAO.coursesList();

                case 5:
                    // All the students per course                    
                    Students_Per_CourseDAO.studentsPerCourseList();
                  
                case 6:
                    // All the trainers per course                    
                    Trainers_Per_CourseDAO.trainersPerCourseList();
                    
                case 7:
                    // All the assignments per course                     
                    Assignments_Per_CourseDAO.AssignmentsPerCourseList();

                case 8:
                    // All the assignments per course per student                    
                    Assignments_Per_Students_Per_CourseDAO.assignmentsPerStudentPerCourseList();

                case 9:
                                        
                     // Dimiourgia Student                     
                    StudentDAO.addStudent();
                
                case 10:
                    
                    // Dimiourgia Course                    
                    CourseDAO.addCourse();
                    
                case 11:
                    
                    // Dimiourgia Trainer                      
                    TrainerDAO.addTrainer();
                    
                case 12:
//                    // Dimiourgia Assignment                         
                    AssignmentDAO.addAssignment();
               
            }
            sc.close();
        }

    }

}
