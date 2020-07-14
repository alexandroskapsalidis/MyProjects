package com.hellokoding.auth.repository;



import com.hellokoding.auth.model.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointments, Integer>{
    
}
