package org.woofenterprise.dogs.dto;


import java.util.Objects;

/**
 * Created by silvia.vigasova on 17.12.2015.
 */
public class CustomerCreateDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String addressFirstLine;
    private String addressCity;
    private String addressCountry;
    private String addressPostalCode;

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
        if (!(obj instanceof CustomerCreateDTO)) {
            return false;
        }
        final CustomerCreateDTO other = (CustomerCreateDTO) obj;
        if (this.id != null) {
            return Objects.equals(this.id, other.id);
        } else {
            return this == other;
        }
    }

}
