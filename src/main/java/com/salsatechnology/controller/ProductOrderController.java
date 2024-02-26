package com.salsatechnology.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.service.ProductOrderService;

@RestController
@RequestMapping("/orders")
public class ProductOrderController {
	
	@Autowired
	private ProductOrderService productOrderService;

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public void createOrder(@RequestBody @Valid ProductOrderDTO productOrderDTO) {
		productOrderService.createOrder(productOrderDTO);
	}
}
