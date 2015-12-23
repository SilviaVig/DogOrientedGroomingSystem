package org.woofenterprise.dogs.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

/**
 * Dog entity class representing a customers pet.
 *
 * @author michal.babel
 */
@Entity
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String hobbies;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer owner;

    @OneToMany(mappedBy = "dog", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Appointment> appointments = new HashSet<>();

    /**
     * Returns entity id.
     *
     * @return id of the entity
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets entity id.
     *
     * @param id id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns dogs name.
     *
     * @return dogs name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets dogs name.
     *
     * @param name name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns dogs hobbies.
     *
     * @return dogs hobbies
     */
    public String getHobbies() {
        return hobbies;
    }

    /**
     * Sets dogs hobbies.
     *
     * @param hobbies hobbies to set
     */
    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    /**
     * Returns dogs owner.
     *
     * @return dogs owner
     */
    public Customer getOwner() {
        return owner;
    }

    /**
     * Sets dogs owner.
     *
     * @param owner dogs owner
     */
    public void setOwner(Customer owner) {
        this.owner = owner;
    }


    /**
     * Return set of customers appointments.
     *
     * @return customers appointments
     */
    public Set<Appointment> getAppointments() {
        return Collections.unmodifiableSet(appointments);
    }

    /**
     * Adds appointment for customer.
     *
     * @param appointment appointment to set
     */
    public void addAppointment(Appointment appointment) {
        appointment.setDog(this);
        this.appointments.add(appointment);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        if (this.id != null) {
            hash = 79 * hash + Objects.hashCode(this.id);
        } else {
            hash = 79 * hash + System.identityHashCode(this);
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Dog)) {
            return false;
        }
        final Dog other = (Dog) obj;
        if (this.id != null) {
            return Objects.equals(this.id, other.id);
        } else {
            return this == other;
        }
    }

}
