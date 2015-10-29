package org.woofenterprise.dogs.entity;

import org.woofenterprise.dogs.utils.Procedure;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Customer customer;

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
     * Returns customer that booked appointment.
     *
     * @return customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets customer that booked this appointment.
     *
     * @param customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
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
        return procedures;
    }

    /**
     * Adds a procedure to the set of procedures
     * @param procedure  procedure to add
     */
    public void addProcedures(Procedure procedure) {
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

        if (!customer.equals(that.customer)) return false;
        if (!dog.equals(that.dog)) return false;
        if (!startTime.equals(that.startTime)) return false;

        return endTime.equals(that.endTime);
    }

    @Override
    public int hashCode() {
        int result = customer.hashCode();
        result = 31 * result + dog.hashCode();
        result = 31 * result + startTime.hashCode();
        result = 31 * result + endTime.hashCode();
        return result;
    }
}
