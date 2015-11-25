package org.woofenterprice.dogs.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for Customer entity.
 * @author michal.babel@embedit.cz
 */
public class CustomerDTO {
    private Long id;

    private String name;

    private String surname;

    private String addressFirstLine;

    private String addressSecondLine;

    private String addressCity;

    private String addressCountry;

    private String addressPostalCode;

    private String phoneNumber;

    private Set<DogDTO> dogs = new HashSet<>();

    private Set<AppointmentDTO> appointments = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddressFirstLine() {
        return addressFirstLine;
    }

    public void setAddressFirstLine(String addressFirstLine) {
        this.addressFirstLine = addressFirstLine;
    }

    public String getAddressSecondLine() {
        return addressSecondLine;
    }

    public void setAddressSecondLine(String addressSecondLine) {
        this.addressSecondLine = addressSecondLine;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getAddressPostalCode() {
        return addressPostalCode;
    }

    public void setAddressPostalCode(String addressPostalCode) {
        this.addressPostalCode = addressPostalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<DogDTO> getDogs() {
        return dogs;
    }

    public void setDogs(Set<DogDTO> dogs) {
        this.dogs = dogs;
    }

    public Set<AppointmentDTO> getAppointments() {
        return appointments;
    }

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
        final CustomerDTO other = (CustomerDTO) obj;
        if (this.id != null) {
            return Objects.equals(this.id, other.id);
        } else {
            return this == other;
        }
    }
    
    
    
}
