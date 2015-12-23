package org.woofenterprise.dogs.entity;

import java.util.*;
import javax.persistence.*;

/**
 * Customer entity class.
 *
 * @author michal.babel
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String addressFirstLine;

    private String addressSecondLine;

    @Column(nullable = false)
    private String addressCity;

    @Column(nullable = false)
    private String addressCountry;

    @Column(nullable = false)
    private String addressPostalCode;

    private String phoneNumber;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private Set<Dog> dogs = new HashSet<>();

    /**
     * Returns customers Id.
     *
     * @return customers id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets customers id.
     *
     * @param id id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns customers name.
     *
     * @return customers name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets customers name.
     *
     * @param name name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns customers dogs.
     *
     * @return customers dogs
     */
    public Set<Dog> getDogs() {
        return Collections.unmodifiableSet(dogs);
    }

    /**
     * Adds customers dog.
     *
     * @param dog dog to add
     */
    public void addDog(Dog dog) {
        dog.setOwner(this);
        this.dogs.add(dog);
    }

    
    /**
     * Returns customers email.
     *
     * @return customers email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets customers email.
     *
     * @param email email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
    /**
     * Returns customers phone number.
     *
     * @return customers phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets customers phone number.
     *
     * @param phoneNumber phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns customers surname.
     *
     * @return customers surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets customers surname.
     *
     * @param surname surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Returns first line of customers address.
     *
     * @return first line of customers address.
     */
    public String getAddressFirstLine() {
        return addressFirstLine;
    }

    /**
     * Sets first line of customers address.
     *
     * @param addressFirstLine first line of customers address.
     */
    public void setAddressFirstLine(String addressFirstLine) {
        this.addressFirstLine = addressFirstLine;
    }

    /**
     * Returns second line of customers address. The second line is optional.
     *
     * @return second line of customers address
     */
    public String getAddressSecondLine() {
        return addressSecondLine;
    }

    /**
     * Sets the second line of customers address. The second line is optional.
     *
     * @param addressSecondLine second line of customers address
     */
    public void setAddressSecondLine(String addressSecondLine) {
        this.addressSecondLine = addressSecondLine;
    }

    /**
     * Returns city of customers address.
     *
     * @return city of customers address
     */
    public String getAddressCity() {
        return addressCity;
    }

    /**
     * Sets city of customers address.
     *
     * @param addressCity city of customers address
     */
    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    /**
     * Returns country of customers address.
     *
     * @return country of customers address
     */
    public String getAddressCountry() {
        return addressCountry;
    }

    /**
     * Sets country of customers address.
     *
     * @param addressCountry country of customers address
     */
    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    /**
     * Returns postal code of customers address.
     *
     * @return postal code of customers address
     */
    public String getAddressPostalCode() {
        return addressPostalCode;
    }

    /**
     * Sets postal code of customers address.
     *
     * @param addressPostalCode postal code of customers address
     */
    public void setAddressPostalCode(String addressPostalCode) {
        this.addressPostalCode = addressPostalCode;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        if (this.id != null) {
            hash = 53 * hash + Objects.hashCode(this.id);
        } else {
            hash = 53 * hash + System.identityHashCode(this);
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Customer)) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (this.id != null) {
            return Objects.equals(this.id, other.id);
        } else {
            return this == other;
        }
    }
    
    

}
