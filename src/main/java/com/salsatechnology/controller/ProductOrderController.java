package com.salsatechnology.controller;

import javax.validation.Valid;

import com.salsatechnology.model.ProductOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.service.ProductOrderService;

import java.util.List;
import java.util.Optional;

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
	@ApiOperation(value = "retornar por usuario")
	@GetMapping("/{username}")
	public ResponseEntity<List<ProductOrder>> findByUsername(@PathVariable String username){
		return new ResponseEntity<>(productOrderService.findByUsername(username), HttpStatus.OK);
	}
}
