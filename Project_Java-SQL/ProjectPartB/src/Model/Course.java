
package Model;

import java.util.Date;

public class Course {

    private String stream;
    private String type;
    private Date start_date;
    private Date end_date;
    
     // Constractor dimiourgias Course kai elegxoi na min einai keno
    public Course(String stream, String type, Date start_date, Date end_date) {
        if (stream.length() != 0) {
            this.stream = stream;
        }
        if (type.length() != 0) {
            this.type = type;
        }
        this.stream = "CB9";
        this.start_date = start_date;
        this.end_date = end_date; 
       
    }

    public void setStream(String stream) {
        if (stream.length() != 0) {
            this.stream = stream;
        }
    }

    public void setType(String type) {
        if (type.length() != 0) {
            this.type = type;
        }
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getStream() {
        return stream;
    }

    public String getType() {
        return type;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

}   