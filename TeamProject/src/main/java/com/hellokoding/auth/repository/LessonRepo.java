/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.auth.repository;


import com.hellokoding.auth.model.Lessons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ioanna
 */
@Repository
public interface LessonRepo extends JpaRepository<Lessons, Integer>{
    
}
