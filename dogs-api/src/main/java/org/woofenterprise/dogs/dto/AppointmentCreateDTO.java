package org.woofenterprise.dogs.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;

import org.woofenterprise.dogs.utils.Procedure;

/**
 * DTO for Appointment entity.
 *
 * @author michal.babel@embedit.cz
 */
public class AppointmentCreateDTO {

    
    private Long dogId;

    public Long getDogId() {
        return dogId;
    }

    public void setDogId(Long dogId) {
        this.dogId = dogId;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    private Set<Procedure> procedures = new HashSet<>();


    

    /**
     * Returns procedures for this appointment.
     *
     * @return procedures for this appointment
     */
    public Set<Procedure> getProcedures() {
        return procedures;
    }

    /**
     * Sets procedures for this appointment.
     *
     * @param procedures procedures for this appointment
     */
    public void setProcedures(Set<Procedure> procedures) {
        this.procedures = procedures;
    }


}
