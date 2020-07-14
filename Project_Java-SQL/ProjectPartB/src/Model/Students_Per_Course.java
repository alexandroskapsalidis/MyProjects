
package Model;

import java.util.ArrayList;

public class Students_Per_Course {
    private ArrayList <Student> students;
    private Course course;

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Course getCourse() {
        return course;
    }   
    
}
