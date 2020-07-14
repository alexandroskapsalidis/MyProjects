/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.auth.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Ioanna
 */
@Entity
@Table(name = "appointments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appointments.findAll", query = "SELECT a FROM Appointments a"),
    @NamedQuery(name = "Appointments.findByAppointmentId", query = "SELECT a FROM Appointments a WHERE a.appointmentId = :appointmentId"),
    @NamedQuery(name = "Appointments.findByTitle", query = "SELECT a FROM Appointments a WHERE a.title = :title"),
    @NamedQuery(name = "Appointments.findByDay", query = "SELECT a FROM Appointments a WHERE a.day = :day"),
    @NamedQuery(name = "Appointments.findByStartTime", query = "SELECT a FROM Appointments a WHERE a.startTime = :startTime"),
    @NamedQuery(name = "Appointments.findByEndTime", query = "SELECT a FROM Appointments a WHERE a.endTime = :endTime"),
    @NamedQuery(name = "Appointments.findByAmount", query = "SELECT a FROM Appointments a WHERE a.amount = :amount")})
public class Appointments implements Serializable {

    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "appointment_id")
    private Integer appointmentId;
    @Size(max = 45)
    @Column(name = "title")
    private String title;
    @Column(name = "day")
    @Temporal(TemporalType.DATE)
    private java.util.Date day;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    private java.util.Date startTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    private java.util.Date endTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private BigDecimal amount;
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    @ManyToOne
    private Students studentId;
    @OneToMany(mappedBy = "appointmentId")
    private Collection<Lessons> lessonsCollection;

    public Appointments() {
    }

    public Appointments(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

  


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Students getStudentId() {
        return studentId;
    }

    public void setStudentId(Students studentId) {
        this.studentId = studentId;
    }

    @XmlTransient
    public Collection<Lessons> getLessonsCollection() {
        return lessonsCollection;
    }

    public void setLessonsCollection(Collection<Lessons> lessonsCollection) {
        this.lessonsCollection = lessonsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appointmentId != null ? appointmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointments)) {
            return false;
        }
        Appointments other = (Appointments) object;
        if ((this.appointmentId == null && other.appointmentId != null) || (this.appointmentId != null && !this.appointmentId.equals(other.appointmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.myTeamProject.entities.Appointments[ appointmentId=" + appointmentId + " ]";
    }
    
}
