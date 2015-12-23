package org.woofenterprise.dogs.dto;

/**
 * DTO for Dog entity.
 * 
 * @author michal.babel@embedit.cz
 */
public class DogCreateDTO {
    private Long ownerId;

    private String name;

    private String hobbies;
    
    private CustomerDTO owner;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public CustomerDTO getOwner() {
        return owner;
    }

    public void setOwner(CustomerDTO owner) {
        this.owner = owner;
    }
    
    
    
    
}
