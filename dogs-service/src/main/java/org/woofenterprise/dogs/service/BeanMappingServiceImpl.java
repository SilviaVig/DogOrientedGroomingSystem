package org.woofenterprise.dogs.service;

import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author Michal.Babel
 */
@Service
public class BeanMappingServiceImpl implements BeanMappingService {
    
    @Inject
    Mapper mapper;

    @Override
    public <T> T map(Object object, Class<T> type) {
        return mapper.map(object, type);
    }

    @Override
    public <T> Collection<T> map(Collection<?> objects, Class<T> type) {
        Collection<T> result = new ArrayList<>();
        for (Object object : objects) {
            result.add(mapper.map(object, type));
        }
        return result;
    }

}
