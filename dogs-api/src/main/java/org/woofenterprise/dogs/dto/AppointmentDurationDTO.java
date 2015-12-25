package org.woofenterprise.dogs.dto;

import java.util.Date;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.woofenterprise.dogs.utils.Procedure;
import org.woofenterprise.dogs.validator.DateBefore;

public class AppointmentDurationDTO {

    private Long id;
    
    @NotNull
    private DogDTO dog;
    
    @NotNull(message = "Must be in format \"YYYY-MM-DD HH:MM\"")
    @DateBefore("9999-12-31")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;
    
    @DateBefore("9999-12-31")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;
    
    @NotNull(message = "Must have at least 1 item selected")
    @Size(min=1)
    private Set<Procedure> procedures;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DogDTO getDog() {
        return dog;
    }

    public void setDog(DogDTO dog) {
        this.dog = dog;
    }

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

    public Set<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(Set<Procedure> procedures) {
        this.procedures = procedures;
    }
    
}
