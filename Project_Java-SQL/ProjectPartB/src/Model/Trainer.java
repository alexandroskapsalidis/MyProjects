
package Model;

public class Trainer {

    private String firstName;
    private String lastName;
    private String subject;
    private Course course;
    
    
     // Constractor dimiourgias Trainer kai elegxoi na min einai keno
     public Trainer(String firstName, String lastName, String subject) {
        if (firstName.length() != 0) {
            this.firstName = firstName;
        }
        if (lastName.length() != 0) {
            this.lastName = lastName;
        }
        if (subject.length() != 0) {
            this.subject = subject;
        }

    }    
    
    public void setFirstName(String firstName) {
        if (firstName.length() != 0) {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if (lastName.length() != 0) {
            this.lastName = lastName;
        }
    }

    public void setSubject(String subject) {
        if (subject.length() != 0) {
            this.subject = subject;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSubject() {
        return subject;
    }
  

}
