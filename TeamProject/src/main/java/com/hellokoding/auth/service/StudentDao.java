/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.auth.service;



import com.hellokoding.auth.model.Students;
import java.util.List;

/**
 *
 * @author Ioanna
 */

public interface StudentDao {
    

    public Students findByStudentId(int id);

    public List<Students> getAllStudents();

    public void updateStudent(Students s);

    public void insertStudent(Students s);

    public Students createOrUpdateStudent(Students changedOrNewStudent);

    //public void deleteStudentById(Long id);
    
    public List<Students> fetchAllStudentsByTeachersId(Long id);
    
   // public Students get(long id);
    public void deleteStudentById(int id);
    
    public Students get(int id);
    
}
