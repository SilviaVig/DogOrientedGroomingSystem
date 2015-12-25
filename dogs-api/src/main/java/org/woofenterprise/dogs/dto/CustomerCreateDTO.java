package org.woofenterprise.dogs.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 * Created by silvia.vigasova on 17.12.2015.
 */
public class CustomerCreateDTO {

    @NotNull(message = "Must be present.")
    @Size(min=2, max=255)
    private String name;
    
    @NotNull(message = "Must be present.")
    @Size(min=2, max=255)
    private String surname;
    
    @NotNull(message = "Must be present.")
    @Size(min=2, max=255)
    @Pattern(regexp = "^\\S+@\\S+$", message = "Must be an email address.")
    private String email;
    
    @NotNull(message = "Must be present.")
    @Size(min=2, max=255)
    private String addressFirstLine;
    private String addressSecondLine;
    
    @NotNull(message = "Must be present.")
    @Size(min=2, max=255)
    private String addressCity;
    
    @NotNull(message = "Must be present.")
    @Size(min=2, max=255)
    private String addressCountry;
    
    @NotNull(message = "Must be present.")
    @Size(min=2, max=255)
    private String addressPostalCode;
    
    @Pattern(regexp = "[0-9-()+ ]*", message = "Must contain only 0-9, (), +- or spaces")
    private String phoneNumber;

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

    /**
     * Returns second line of customers address.
     *
     * @return second line of address
     */
    public String getAddressSecondLine() {
        return addressSecondLine;
    }

    /**
     * Sets second line of address.
     *
     * @param addressSecondLine second line of address.
     */
    public void setAddressSecondLine(String addressSecondLine) {
        this.addressSecondLine = addressSecondLine;
    }

    /**
     * Returns phone number of customer.
     *
     * @return phone number of customer.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number of customer.
     *
     * @param phoneNumber phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerCreateDTO that = (CustomerCreateDTO) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (addressFirstLine != null ? !addressFirstLine.equals(that.addressFirstLine) : that.addressFirstLine != null)
            return false;
        if (addressCity != null ? !addressCity.equals(that.addressCity) : that.addressCity != null) return false;
        if (addressCountry != null ? !addressCountry.equals(that.addressCountry) : that.addressCountry != null)
            return false;
        return addressPostalCode != null ? addressPostalCode.equals(that.addressPostalCode) : that.addressPostalCode == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (addressFirstLine != null ? addressFirstLine.hashCode() : 0);
        result = 31 * result + (addressCity != null ? addressCity.hashCode() : 0);
        result = 31 * result + (addressCountry != null ? addressCountry.hashCode() : 0);
        result = 31 * result + (addressPostalCode != null ? addressPostalCode.hashCode() : 0);
        return result;
    }
}
