package org.woofenterprise.dogs.entity;

import org.woofenterprise.dogs.utils.Procedure;

import javax.persistence.*;
import java.util.*;

/**
 * Appointment class representing appointment.
 *
 * @author Silvia Vigasova
 */

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Dog dog;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Procedure> procedures = new HashSet<>();


    /**
     * Returns appointment id.
     *
     * @return id of the entity
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets appointment id.
     *
     * @param id id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns dog that has booked appointment.
     *
     * @return dog in appointment
     */
    public Dog getDog() {
        return dog;
    }

    /**
     * Sets the dog that has booked appointment.
     *
     * @param dog dog to set
     */
    public void setDog(Dog dog) {
        this.dog = dog;
    }

    /**
     * Returns the start time of appointment.
     *
     * @return start time of appointment
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of appointment.
     *
     * @param startTime time to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns the end time of appointment.
     *
     * @return end time of appointment
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of appointment.
     *
     * @param endTime end time to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Returns the set of procedures performed at the appointment
     * @return set of procedures
     */
    public Set<Procedure> getProcedures() {
        return Collections.unmodifiableSet(procedures);
    }

    /**
     * Adds a procedure to the set of procedures
     * @param procedure  procedure to add
     */
    public void addProcedure(Procedure procedure) {
        this.procedures.add(procedure);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Appointment)) {
            return false;
        }

        Appointment that = (Appointment) o;

        if (this.id != null) {
            return Objects.equals(this.id, that.id);
        } else {
            return this == that;
        }
    }

    @Override
    public int hashCode() {

        int hash = 7;
        if (this.id != null) {
            hash = 13 * hash + Objects.hashCode(this.id);
        } else {
            hash = 13 * hash + System.identityHashCode(this);
        }
        return hash;
    }
}
