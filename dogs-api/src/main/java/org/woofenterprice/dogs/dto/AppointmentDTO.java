package org.woofenterprice.dogs.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.woofenterprise.dogs.utils.Procedure;

/**
 * DTO for Appointment entity.
 * @author michal.babel@embedit.cz
 */
public class AppointmentDTO {
    private Long id;

    private CustomerDTO customer;

    private DogDTO dog;

    private Date startTime;

    private Date endTime;

    private Set<Procedure> procedures = new HashSet<>();

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public CustomerDTO getCustomer() {
        return customer;
    }

    /**
     *
     * @param customer
     */
    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    /**
     *
     * @return
     */
    public DogDTO getDog() {
        return dog;
    }

    /**
     *
     * @param dog
     */
    public void setDog(DogDTO dog) {
        this.dog = dog;
    }

    /**
     *
     * @return
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     *
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     *
     * @return
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     *
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     *
     * @return
     */
    public Set<Procedure> getProcedures() {
        return procedures;
    }

    /**
     *
     * @param procedures
     */
    public void setProcedures(Set<Procedure> procedures) {
        this.procedures = procedures;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        if (id != null) {
            hash = 67 * hash + Objects.hashCode(this.id);
        } else {
            hash = 67 * hash + System.identityHashCode(this);
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AppointmentDTO)) {
            return false;
        }
        final AppointmentDTO other = (AppointmentDTO) obj;
        
        if (this.id != null) {
            return Objects.equals(this.id, other.id);
        } else {
            return this == other;
        }
    }
    
    
}
