
package Model;

import java.util.Date;

public class Student {
    
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private double tuitionFees;
       
    
    // Constractor dimiourgias Student kai elegxoi na min einai keno
    public Student (String firstName, String lastName, Date dateOfBirth, double tuitionFees) {
        if (firstName.length() != 0) {
            this.firstName = firstName;
        }    
        if (lastName.length() != 0) {
            this.lastName = lastName;
        }
        
        this.dateOfBirth = dateOfBirth;
        
        // Elegxos gia timi megaliteri tou 0
        if (tuitionFees > 0) {
            this.tuitionFees = tuitionFees;
           
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
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setTuitionFees(double tuitionFees) {
        if (tuitionFees > 0) {
            this.tuitionFees = tuitionFees;
        }
    }    
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public double getTuitionFees() {
        return tuitionFees;
    }  
    
}
