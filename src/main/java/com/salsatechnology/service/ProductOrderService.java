package com.salsatechnology.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.model.ProductOrder;
import com.salsatechnology.repository.ProductOrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductOrderService {

	private final ProductOrderRepository productOrderRepository;
	
	@Transactional
	public void createOrder(ProductOrderDTO productOrderDTO) {
		productOrderRepository.save(createProductOrder(productOrderDTO));
	}

	private ProductOrder createProductOrder(ProductOrderDTO productOrderDTO) {
		ProductOrder productOrder = new ProductOrder();
		productOrder.setUserName(productOrderDTO.getUserName());
		productOrder.setProductType(productOrderDTO.getProductType());
		productOrder.setTimeHour(productOrderDTO.getTimeHour());
		
		productOrder.setProductValue(null);
		productOrder.setProductTotal(null);
		productOrder.setUserAmount(null);
		return productOrder;
	}

}
