
package Model;

import java.util.ArrayList;

public class Assignments_Per_Students_Per_Course {
    private ArrayList <Assignment> assignments;
    private ArrayList <Student> students;
    private Course course;

    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Course getCourse() {
        return course;
    }
          
    
}
