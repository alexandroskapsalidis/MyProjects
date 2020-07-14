/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.auth.service;


import com.hellokoding.auth.model.Students;
import com.hellokoding.auth.repository.StudentRepo;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ioanna
 */
@Service
public class StudentImpl implements StudentDao{
    
    @Autowired
    StudentRepo studentRepo;
    
    @Override
    public List<Students> getAllStudents() {
        List<Students> result = (List<Students>) studentRepo.findAll();
        return result;
    }
    @Override
    @Transactional
    public void updateStudent(Students s) {
       studentRepo.save(s);
    }
    
    @Override
    @Transactional
    public void insertStudent(Students s) {
       studentRepo.save(s);
    }
    
    @Override
    @Transactional
    public List<Students> fetchAllStudentsByTeachersId(Long teacherid) {
        List<Students> result = studentRepo.fetchAllStudentsByTeachersId(teacherid);
        return result;
    }

    @Override 
    @Transactional
    public Students findByStudentId(int id) {
        Students stu = studentRepo.findByStudentId(id);
        return stu;
    }
    
        @Override
    public Students createOrUpdateStudent(Students changedOrNewStudent) {
        Optional<Students> studentTochange = studentRepo.findById(changedOrNewStudent.getStudentId());

        if (studentTochange.isPresent()) {
            Students newEntity = studentTochange.get();
            newEntity.setAddressArea(changedOrNewStudent.getAddressArea());
            newEntity.setAddressNumber(changedOrNewStudent.getAddressNumber());
            newEntity.setAddressTK(changedOrNewStudent.getAddressTK());
            newEntity.setParentName(changedOrNewStudent.getParentName());
            newEntity.setPhone1(changedOrNewStudent.getPhone1());
            newEntity.setPhone2(changedOrNewStudent.getPhone2());
            newEntity.setStreetName(changedOrNewStudent.getStreetName());
            newEntity.setStudentFirstName(changedOrNewStudent.getStudentFirstName());
            newEntity.setStudentLastName(changedOrNewStudent.getStudentLastName());
            newEntity.setStudentLevel(changedOrNewStudent.getStudentLevel());

            newEntity = studentRepo.save(newEntity);

            return newEntity;
        } else {
            changedOrNewStudent = studentRepo.save(changedOrNewStudent);

            return changedOrNewStudent;
        }
    }
    
        @Override
    public void deleteStudentById(int id){
        studentRepo.deleteById(id);
    }
    
    @Override
        public Students get(int id) {
        return studentRepo.findById(id).get();
    }
    
}
