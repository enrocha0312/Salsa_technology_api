package com.salsatechnology.service;

import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.exceptions.service.ResourceNotFoundException;
import com.salsatechnology.model.ProductOrder;
import com.salsatechnology.model.ProductType;
import com.salsatechnology.repository.ProductOrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ProductOrderServiceTest {
    @InjectMocks
    private ProductOrderService productOrderService;
    @Mock
    private ProductOrderRepository productOrderRepository;
    private ProductOrderDTO productOrderDTO = new ProductOrderDTO();
    private ProductOrder productOrder = new ProductOrder();
    private ProductOrder prodOrder = new ProductOrder();
    private List<ProductOrder> productOrders;

    private static String USERNAME = "user";
    private static Integer TIMEHOUR = 2;
    private static Long ID = 1L;

    private static ProductType TYPE = ProductType.SURFBOARD;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        productOrderDTO.setProductType(TYPE);
        productOrderDTO.setUserName(USERNAME);
        productOrderDTO.setTimeHour(TIMEHOUR);
        productOrder.setId(ID);
        productOrder.setUserName(USERNAME);
        productOrder.setTimeHour(TIMEHOUR);
        productOrder.setProductType(TYPE);
        productOrder.setProductValue(5000L);
        productOrder.setProductTotal(10000L);
        productOrder.setUserAmount(1560L);
        prodOrder.setId(2L);
        prodOrder.setUserName(USERNAME);
        prodOrder.setTimeHour(TIMEHOUR);
        prodOrder.setProductType(ProductType.SUNSHADE);
        prodOrder.setProductValue(4000L);
        prodOrder.setProductTotal(8000L);
        prodOrder.setUserAmount(824L);
        productOrders = Stream.of(productOrder, prodOrder).collect(Collectors.toList());
    }

    @Test
    void createProductOrderSuccessfully(){
        when(productOrderRepository.save(any())).thenReturn(productOrder);
        ProductOrder productOrderResponse = productOrderService.createOrder(productOrderDTO);
        assertNotNull(productOrderResponse);
        assertEquals(ProductOrder.class, productOrderResponse.getClass());
        assertEquals(USERNAME, productOrderResponse.getUserName());
        assertEquals(TIMEHOUR, productOrderResponse.getTimeHour());
        assertEquals(productOrder.getProductTotal(), productOrderResponse.getProductTotal());
    }
    @Test
    void findByUserNameSuccessfully(){
        when(productOrderRepository.findByUsername(anyString())).thenReturn(productOrders);
        List<ProductOrder> response = productOrderService.findByUsername(USERNAME);
        assertNotNull(response);
        assertEquals(response.size(),2);
        assertEquals(response.get(0).getUserName(), USERNAME);
        assertEquals(response.get(1).getUserName(), USERNAME);
    }
    @Test
    void findByUserNameDoesNotFoundAnyRegister(){
        when(productOrderRepository.findByUsername(anyString())).thenReturn(Collections.emptyList());
        try{
            productOrderService.findByUsername(USERNAME);
        }catch (Exception e){
            assertEquals(e.getClass(), ResourceNotFoundException.class);
            assertEquals("Product Orders with username " + USERNAME + " not found", e.getMessage());
        }
    }
}
