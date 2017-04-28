package com.focuson.webshop.service.product.impl;

import com.focuson.webshop.bean.product.ProductType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.EntityManager;

/**
 * @author Focuson created on 17-3-14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ImportResource("classpath:persistence.xml")
@PropertySource("classpath:webshop-dev.properties")
@ComponentScan(basePackages = {"com.focuson.webshop.bean", "com.focuson.webshop.config", "com.focuson.webshop.service.product"})
public class ProductTypeServiceImplTest {

    private ProductTypeServiceImpl productTypeService;

    @Before
    public void setUp() {
        productTypeService = new ProductTypeServiceImpl();
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        ReflectionTestUtils.setField(productTypeService, "em", entityManager);
    }
    @Test
    public void testDelete() throws Exception {

//        productTypeService.delete(ProductType.class, new Object[]{1});
    }
}