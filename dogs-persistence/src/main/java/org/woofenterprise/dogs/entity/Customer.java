package org.woofenterprise.dogs.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.woofenterprise.dogs.utils.Address;

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

    @Column(nullable = false, length = 512)
    private Address address;

    private String phoneNumber;

    @OneToMany(mappedBy = "owner")
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
        this.dogs.add(dog);
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
     * Returns customers address.
     *
     * @return customers address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets customers address.
     *
     * @param address address to set
     */
    public void setAddress(Address address) {
        this.address = address;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.surname);
        hash = 53 * hash + Objects.hashCode(this.address);
        hash = 53 * hash + Objects.hashCode(this.phoneNumber);
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        return true;
    }

}
