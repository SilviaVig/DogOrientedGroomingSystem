package org.woofenterprise.dogs.service;

import java.util.Collection;

/**
 * Service for bean mapping.
 * 
 * @author Michal.Babel
 */
public interface BeanMappingService {
	
    /**
     * Maps object to given class.
     * @param <T> Type of class to be mapped to
     * @param object Object to map
     * @param type class to be mapped to
     * @return mapped object of given class
     */
    public <T> T map(Object object, Class<T> type);

    /**
     *  Maps collection of objects to given class
     * @param <T> Type of class to be mapped to
     * @param objects objects to map
     * @param type class to be mapped to
     * @return colledction of mapped objects of given class
     */
    public <T> Collection<T> map(Collection<?> objects, Class<T> type);
    
}