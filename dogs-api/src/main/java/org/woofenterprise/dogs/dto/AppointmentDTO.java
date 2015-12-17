package org.woofenterprise.dogs.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.woofenterprise.dogs.utils.Procedure;

/**
 * DTO for Appointment entity.
 *
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
     * Returns id of referenced entity.
     *
     * @return id of referenced entity
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of referenced entity.
     *
     * @param id id of referenced entity.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns customer associated with the appointment.
     *
     * @return customer associated with the appointment
     */
    public CustomerDTO getCustomer() {
        return customer;
    }

    /**
     * Sets the customer for this appointment.
     *
     * @param customer the customer for this appointment
     */
    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    /**
     * Returns dog associated with the appointment.
     *
     * @return dog associated with the appointment
     */
    public DogDTO getDog() {
        return dog;
    }

    /**
     * Sets the dog for this appointment.
     *
     * @param dog the dog for this appointment
     */
    public void setDog(DogDTO dog) {
        this.dog = dog;
    }

    /**
     * Returns the start time for the appointment.
     *
     * @return the start time for the appointment
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time for the appointment.
     *
     * @param startTime the start time for the appointment
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns the end time for the appointment.
     *
     * @return the end time for the appointment
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time for the appointment.
     *
     * @param endTime the end time for the appointment
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

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
