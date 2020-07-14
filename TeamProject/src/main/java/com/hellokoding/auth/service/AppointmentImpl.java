/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Appointments;
import com.hellokoding.auth.repository.AppointmentRepo;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ioanna
 */
@Service
public class AppointmentImpl implements AppointmentDao{ 

    @Autowired
    AppointmentRepo appointrepo;
    
    @Override
    @Transactional
    public void insertAppointment(Appointments a) {
        appointrepo.save(a);
    }
    @Override
    public List<Appointments> getAllAppointments(){
    List<Appointments> result = appointrepo.findAll();
        return result;
    };
    @Override
    @Transactional
    public void deleteAppointmentById(int id){
        appointrepo.deleteById(id);
    };
     
}
