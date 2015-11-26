package org.woofenterprise.dogs.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.woofenterprise.dogs.service.utils.BaseTestCase;

/**
 *
 * @author michal.babel@embedit.cz
 */
public class BeanMappingServiceImplTest extends BaseTestCase {
    
    @Mock
    Mapper mapper;
    
    @InjectMocks
    BeanMappingServiceImpl beanMappingService;
    
    @Test
    public void testMap() {
        ExampleObjectType e = new ExampleObjectType();
        e.setAttribute("example");
        
        ExampleObjectTypeAlt expected = new ExampleObjectTypeAlt();
        expected.setAttribute("example");
        
        when(mapper.map(e, ExampleObjectTypeAlt.class)).thenReturn(expected);
        
        ExampleObjectTypeAlt result = beanMappingService.map(e, ExampleObjectTypeAlt.class);
        
        verify(mapper).map(e, ExampleObjectTypeAlt.class);
        Assert.assertEquals(expected, result);
    }
    
    @Test
    public void testMapCollection() {
        ExampleObjectType e = new ExampleObjectType();
        e.setAttribute("example");
        ExampleObjectType e2 = new ExampleObjectType();
        e2.setAttribute("example two");
        
        ExampleObjectTypeAlt expected = new ExampleObjectTypeAlt();
        expected.setAttribute("example");
        ExampleObjectTypeAlt expected2 = new ExampleObjectTypeAlt();
        expected.setAttribute("example two");
        
        List<ExampleObjectType> list = new ArrayList<>();
        list.add(e);
        list.add(e2);
                
        when(mapper.map(e, ExampleObjectTypeAlt.class)).thenReturn(expected);
        when(mapper.map(e2, ExampleObjectTypeAlt.class)).thenReturn(expected2);
        
        Collection<ExampleObjectTypeAlt> result = beanMappingService.map(list, ExampleObjectTypeAlt.class);
        
        verify(mapper).map(e, ExampleObjectTypeAlt.class);
        verify(mapper).map(e2, ExampleObjectTypeAlt.class);
        
        Assert.assertTrue(result.contains(expected));
        Assert.assertTrue(result.contains(expected2));
    }
    
    private class ExampleObjectType {
        private String attribute;

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }        
    }
    
    private class ExampleObjectTypeAlt {
        private String attribute;

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }        
    }
    
}
