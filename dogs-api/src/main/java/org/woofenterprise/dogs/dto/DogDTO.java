package org.woofenterprise.dogs.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO for Dog entity.
 * 
 * @author michal.babel@embedit.cz
 */
public class DogDTO {
    private Long id;

    @NotNull
    @Size(min=2, max=255)
    private String name;

    @NotNull
    @Size(min=2, max=255)
    private String hobbies;
    
    @NotNull
    @JsonBackReference
    private CustomerDTO owner;
    
    @JsonManagedReference
    private Set<AppointmentDTO> appointments = new HashSet<>();

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
     * @param name dogs name
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
     * @param hobbies dogs hobbies
     */
    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    /**
     * Returns dogs owner.
     * 
     * @return customer owning this dog
     */
    public CustomerDTO getOwner() {
        return owner;
    }

    /**
     * Sets dogs owner.
     * 
     * @param owner customer which will take care of this dog
     */
    public void setOwner(CustomerDTO owner) {
        this.owner = owner;
    }


    /**
     * Returns customers appointments.
     *
     * @return customers dogs.
     */
    public Set<AppointmentDTO> getAppointments() {
        return appointments;
    }

    /**
     * Sets customers appointments.
     *
     * @param appointments customers appointments.
     */
    public void setAppointments(Set<AppointmentDTO> appointments) {
        this.appointments = appointments;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        if (this.id != null) {
            hash = 37 * hash + Objects.hashCode(this.id);
        } else {
            hash = 37 * hash + System.identityHashCode(this);
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DogDTO other = (DogDTO) obj;
        if (this.id != null) {
            return Objects.equals(this.id, other.id);
        } else {
            return this == other;
        }
    }
    
    
}
