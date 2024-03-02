package com.salsatechnology.controller;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.service.ProductOrderService;
@Api(tags = "ProductOrder")
@RestController
@RequestMapping("/orders")
public class ProductOrderController {
	
	@Autowired
	private ProductOrderService productOrderService;

	@ApiOperation(value = "criar pedido de produto")
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public void createOrder(@RequestBody @Valid ProductOrderDTO productOrderDTO) {
		productOrderService.createOrder(productOrderDTO);
	}
}
