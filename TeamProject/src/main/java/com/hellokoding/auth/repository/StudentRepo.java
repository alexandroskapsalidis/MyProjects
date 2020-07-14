/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Students;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Ioanna
 */
@Repository
public interface StudentRepo extends JpaRepository<Students, Integer> {
    
    
    List<Students> fetchAllStudentsByTeachersId(Long id);   
    Students findByStudentId(int id);
    
//    @Query("SELECT s FROM students s WHERE id = ?1")
//    List<Students> findUserByTeachersId(Integer id);
    
    
}
