package org.woofenterprise.dogs.service;

import java.util.Collection;


public interface BeanMappingService {
	
    public <T> T map(Object object, Class<T> type);

    public <T> Collection<T> map(Collection<?> objects, Class<T> type);
    
}