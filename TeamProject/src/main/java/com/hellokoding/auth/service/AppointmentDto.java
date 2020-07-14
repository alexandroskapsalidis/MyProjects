/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Students;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author panos
 */
public class AppointmentDto {
    private String title;
    private String day;
    private String startTime;
    private String endTime;
    private BigDecimal amount;
    private Long allstudents;

    public AppointmentDto(){}
    
    public AppointmentDto(String title, String day, String startTime, String endTime, BigDecimal amount, Long allstudents) {
        this.title = title;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.amount = amount;
        this.allstudents = allstudents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getAllstudents() {
        return allstudents;
    }

    public void setAllstudents(Long allstudents) {
        this.allstudents = allstudents;
    }

    @Override
    public String toString() {
        return "AppointmentDto{" + "title=" + title + ", day=" + day + ", startTime=" + startTime + ", endTime=" + endTime + ", amount=" + amount + ", allstudents=" + allstudents + '}';
    }
    
    
    
}
