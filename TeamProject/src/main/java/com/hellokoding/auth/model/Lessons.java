/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.auth.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ioanna
 */
@Entity
@Table(name = "lessons")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lessons.findAll", query = "SELECT l FROM Lessons l"),
    @NamedQuery(name = "Lessons.findByLessonId", query = "SELECT l FROM Lessons l WHERE l.lessonId = :lessonId"),
    @NamedQuery(name = "Lessons.findByLessonDate", query = "SELECT l FROM Lessons l WHERE l.lessonDate = :lessonDate"),
    @NamedQuery(name = "Lessons.findByAmount", query = "SELECT l FROM Lessons l WHERE l.amount = :amount"),
    @NamedQuery(name = "Lessons.findByPaid", query = "SELECT l FROM Lessons l WHERE l.paid = :paid"),
    @NamedQuery(name = "Lessons.findByDone", query = "SELECT l FROM Lessons l WHERE l.done = :done")})
public class Lessons implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lesson_id")
    private Integer lessonId;
    @Size(max = 45)
    @Column(name = "lesson_date")
    private String lessonDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "paid")
    private Integer paid;
    @Column(name = "done")
    private Integer done;
    @JoinColumn(name = "appointment_id", referencedColumnName = "appointment_id")
    @ManyToOne
    private Appointments appointmentId;

    public Lessons() {
    }

    public Lessons(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getPaid() {
        return paid;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public Appointments getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Appointments appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lessonId != null ? lessonId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lessons)) {
            return false;
        }
        Lessons other = (Lessons) object;
        if ((this.lessonId == null && other.lessonId != null) || (this.lessonId != null && !this.lessonId.equals(other.lessonId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.myTeamProject.entities.Lessons[ lessonId=" + lessonId + " ]";
    }
    
}
