
package Model;

import java.util.ArrayList;

public class Trainers_Per_Course {
    private ArrayList <Trainer> trainers;
    private Course course;

    public void setTrainers(ArrayList<Trainer> trainers) {
        this.trainers = trainers;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    public Course getCourse() {
        return course;
    }

    
       
    
}
