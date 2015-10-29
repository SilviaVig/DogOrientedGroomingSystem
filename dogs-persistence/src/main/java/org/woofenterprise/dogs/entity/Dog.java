package org.woofenterprise.dogs.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.name);
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.owner, other.owner)) {
            return false;
        }
        return true;
    }

}
