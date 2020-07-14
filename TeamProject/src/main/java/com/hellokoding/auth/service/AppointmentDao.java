
package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Appointments;
import java.util.List;



public interface AppointmentDao{
    public void insertAppointment(Appointments a);
   // public void delete
    public List<Appointments> getAllAppointments();
    public void deleteAppointmentById(int id);
}
