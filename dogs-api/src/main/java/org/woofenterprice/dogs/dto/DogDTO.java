package org.woofenterprice.dogs.dto;

import java.util.Objects;

/**
 * DTO for Dog entity.
 * @author michal.babel@embedit.cz
 */
public class DogDTO {
    private Long id;

    private String name;

    private String hobbies;
    
    private CustomerDTO owner;

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getHobbies() {
        return hobbies;
    }

    /**
     *
     * @param hobbies
     */
    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    /**
     *
     * @return
     */
    public CustomerDTO getOwner() {
        return owner;
    }

    /**
     *
     * @param owner
     */
    public void setOwner(CustomerDTO owner) {
        this.owner = owner;
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
