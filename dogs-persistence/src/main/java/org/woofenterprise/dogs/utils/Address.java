package org.woofenterprise.dogs.utils;

import java.io.Serializable;
import java.util.Objects;

/**
 * Utility class for storing {@link Customer} address.
 *
 * @author michal.babel@embedit.cz
 */
public class Address implements Serializable {

    private final String firstLine;
    private final String secondLine;
    private final String city;
    private final String country;
    private final String code;

    private Address(Builder builder) {
        this.firstLine = builder.firstLine;
        this.secondLine = builder.secondLine;
        this.city = builder.city;
        this.country = builder.country;
        this.code = builder.code;
    }

    /**
     * Builder for {@link Address} object.
     */
    public static class Builder {

        private String firstLine;
        private String secondLine;
        private String city;
        private String country;
        private String code;

        /**
         * Public constructor.
         */
        public Builder() {
        }

        /**
         * Sets address first line.
         *
         * @param firstLine firstLine to set
         * @return builder instance
         */
        public Builder setFirstLine(String firstLine) {
            this.firstLine = firstLine;
            return this;
        }

        /**
         * Sets address second line.
         *
         * @param secondLine second line to set
         * @return builder instance
         */
        public Builder setSecondLine(String secondLine) {
            this.secondLine = secondLine;
            return this;
        }

        /**
         * Sets address city.
         *
         * @param city city to set
         * @return builder instance
         */
        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        /**
         * Sets address country.
         *
         * @param country country to set
         * @return builder instance
         */
        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        /**
         * Sets address postal code.
         *
         * @param code postal code to set
         * @return builder instance
         */
        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        /**
         * Builds {@link Address} object related to this builder.
         *
         * @return {@link Address} object
         */
        public Address build() {
            return new Address(this);
        }

    }

    /**
     * Returns address first line.
     *
     * @return address first line
     */
    public String getFirstLine() {
        return firstLine;
    }

    /**
     * Returns address second line.
     *
     * @return address second line
     */
    public String getSecondLine() {
        return secondLine;
    }

    /**
     * Returns address city.
     *
     * @return address city
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns address country.
     *
     * @return address country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns address postal code.
     *
     * @return address postal code
     */
    public String getCode() {
        return code;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.firstLine);
        hash = 31 * hash + Objects.hashCode(this.secondLine);
        hash = 31 * hash + Objects.hashCode(this.city);
        hash = 31 * hash + Objects.hashCode(this.country);
        hash = 31 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Address)) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.firstLine, other.firstLine)) {
            return false;
        }
        if (!Objects.equals(this.secondLine, other.secondLine)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }
}
