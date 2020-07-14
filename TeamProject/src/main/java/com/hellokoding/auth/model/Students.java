/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.auth.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ioanna
 */
@Entity
@Table(name = "students")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Students.findAll", query = "SELECT s FROM Students s"),
    @NamedQuery(name = "Students.findByStudentId", query = "SELECT s FROM Students s WHERE s.studentId = :studentId"),
    @NamedQuery(name = "Students.findByStudentFirstName", query = "SELECT s FROM Students s WHERE s.studentFirstName = :studentFirstName"),
    @NamedQuery(name = "Students.findByStudentLastName", query = "SELECT s FROM Students s WHERE s.studentLastName = :studentLastName"),
    @NamedQuery(name = "Students.findByStudentLevel", query = "SELECT s FROM Students s WHERE s.studentLevel = :studentLevel"),
    @NamedQuery(name = "Students.findByStreetName", query = "SELECT s FROM Students s WHERE s.streetName = :streetName"),
    @NamedQuery(name = "Students.findByAddressNumber", query = "SELECT s FROM Students s WHERE s.addressNumber = :addressNumber"),
    @NamedQuery(name = "Students.findByAddressArea", query = "SELECT s FROM Students s WHERE s.addressArea = :addressArea"),
    @NamedQuery(name = "Students.findByAddressTK", query = "SELECT s FROM Students s WHERE s.addressTK = :addressTK"),
    @NamedQuery(name = "Students.findByPhone1", query = "SELECT s FROM Students s WHERE s.phone1 = :phone1"),
    @NamedQuery(name = "Students.findByPhone2", query = "SELECT s FROM Students s WHERE s.phone2 = :phone2"),
    @NamedQuery(name = "Students.findByParentName", query = "SELECT s FROM Students s WHERE s.parentName = :parentName")})
    @NamedQuery(name = "Students.fetchAllStudentsByTeachersId", query = "SELECT s FROM Students s WHERE s.id.id = :id")
public class Students implements Serializable {
    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "student_id")
    private Integer studentId;
    @Size(max = 45)
    @Column(name = "student_first_name")
    private String studentFirstName;
    @Size(max = 45)
    @Column(name = "student_last_name")
    private String studentLastName;
    @Size(max = 45)
    @Column(name = "student_level")
    private String studentLevel;
    @Size(max = 45)
    @Column(name = "street_name")
    private String streetName;
    @Column(name = "address_number")
    private Integer addressNumber;
    @Size(max = 45)
    @Column(name = "address_area")
    private String addressArea;
    @Column(name = "address_TK")
    private Integer addressTK;
    @Size(max = 10)
    @Column(name = "phone_1")
    private String phone1;
    @Size(max = 10)
    @Column(name = "phone_2")
    private String phone2;
    @Size(max = 45)
    @Column(name = "parent_name")
    private String parentName;
    @OneToMany(mappedBy = "studentId")
    private Collection<Appointments> appointmentsCollection;
    @JoinColumn(name = "id", referencedColumnName = "id")
    @ManyToOne
    private User id;

    public Students() {
    }

    public Students(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentLevel() {
        return studentLevel;
    }

    public void setStudentLevel(String studentLevel) {
        this.studentLevel = studentLevel;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(Integer addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressArea() {
        return addressArea;
    }

    public void setAddressArea(String addressArea) {
        this.addressArea = addressArea;
    }

    public Integer getAddressTK() {
        return addressTK;
    }

    public void setAddressTK(Integer addressTK) {
        this.addressTK = addressTK;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @XmlTransient
    public Collection<Appointments> getAppointmentsCollection() {
        return appointmentsCollection;
    }

    public void setAppointmentsCollection(Collection<Appointments> appointmentsCollection) {
        this.appointmentsCollection = appointmentsCollection;
    }

    public User getId() {
        return id;
    }

    public void setId(User teacherId) {
        this.id = teacherId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentId != null ? studentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Students)) {
            return false;
        }
        Students other = (Students) object;
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.myTeamProject.entities.Students[ studentId=" + studentId + " ]";
    }
    
}
