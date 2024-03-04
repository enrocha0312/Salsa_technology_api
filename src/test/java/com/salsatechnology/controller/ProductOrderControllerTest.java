package com.salsatechnology.controller;

import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.exceptions.service.ResourceNotFoundException;
import com.salsatechnology.model.ProductOrder;
import com.salsatechnology.model.ProductType;
import com.salsatechnology.service.ProductOrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ProductOrderControllerTest {
    @Mock
    private ProductOrderService productOrderService;
    @InjectMocks
    private ProductOrderController productOrderController;
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
    void createProductOrderSuccesfully(){
        when(productOrderService.createOrder(any())).thenReturn(productOrder);
        ResponseEntity<ProductOrder> response = productOrderController.createOrder(productOrderDTO);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getBody().getId(), ID);
        assertEquals(response.getBody().getTimeHour(), TIMEHOUR);
    }
    @Test
    void findByUsernameSuccessfully(){
        when(productOrderService.findByUsername(anyString())).thenReturn(productOrders);
        ResponseEntity<List<ProductOrder>> response = productOrderController.findByUsername(USERNAME);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().size(), 2);
        assertNotNull(response);
        assertEquals(response.getBody().get(0).getUserName(), USERNAME);
        assertEquals(response.getBody().get(1).getUserName(), USERNAME);
    }
    @Test
    void findByUsernameDoesNotFoundAnything(){
        when(productOrderService.findByUsername(anyString())).thenThrow(ResourceNotFoundException.class);
        try{
            ResponseEntity<List<ProductOrder>> response = productOrderController.findByUsername(USERNAME);
        }catch (Exception e){
            assertEquals(e.getClass(), ResourceNotFoundException.class);
        }

    }
}
