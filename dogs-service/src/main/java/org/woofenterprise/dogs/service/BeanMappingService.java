package org.woofenterprise.dogs.service;

import java.util.Collection;

/**
 *
 * @author Michal.Babel
 */
public interface BeanMappingService {
	
    /**
     *
     * @param <T>
     * @param object
     * @param type
     * @return
     */
    public <T> T map(Object object, Class<T> type);

    /**
     *
     * @param <T>
     * @param objects
     * @param type
     * @return
     */
    public <T> Collection<T> map(Collection<?> objects, Class<T> type);
    
}