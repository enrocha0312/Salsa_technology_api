package com.salsatechnology.service;

import javax.transaction.Transactional;

import com.salsatechnology.exceptions.service.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.model.ProductOrder;
import com.salsatechnology.repository.ProductOrderRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductOrderService {

	private final ProductOrderRepository productOrderRepository;
	
	@Transactional
	public ProductOrder createOrder(ProductOrderDTO productOrderDTO) {
		return productOrderRepository.save(createProductOrder(productOrderDTO));
	}

	public List<ProductOrder> findByUsername (String username){
		List<ProductOrder> productOrders = productOrderRepository.findByUsername(username);
		if (productOrders.isEmpty()){
			throw new ResourceNotFoundException(username);
		}
		return productOrders;
	}

	private ProductOrder createProductOrder(ProductOrderDTO productOrderDTO) {
		ProductOrder productOrder = new ProductOrder();
		productOrder.setUserName(productOrderDTO.getUserName());
		productOrder.setProductType(productOrderDTO.getProductType());
		productOrder.setTimeHour(productOrderDTO.getTimeHour());
		Long productValue = calculateProductValue(productOrderDTO);
		Long productTotal = calculateProductTotal(productOrderDTO, productValue);
		Long userAmount = calculateUserAmount(productOrderDTO, productTotal);
		productOrder.setProductValue(productValue);
		productOrder.setProductTotal(productTotal);
		productOrder.setUserAmount(userAmount);
		return productOrder;
	}

	private Long calculateProductValue(ProductOrderDTO productOrderDTO){
		switch (productOrderDTO.getProductType()){
			case SUNSHADE:
				return (40L*100L);
			case SURFBOARD:
				return (50L*100L);
			case SAND_BOARD:
				return (25L*100L);
			case BEACH_CHAIR:
				return (35L*100L);
			case BEACH_TABLE:
				return (25L*100L);
		}
		return 0L;
	}
	private Long calculateProductTotal(ProductOrderDTO productOrderDTO, Long productValue){
		return (productValue * productOrderDTO.getTimeHour());
	}
	private Long calculateUserAmount(ProductOrderDTO productOrderDTO, Long productTotal){
		switch (productOrderDTO.getProductType()){
			case SUNSHADE:
				return (long)(productTotal*0.103);
			case SURFBOARD:
				return (long)(productTotal*0.156);
			case SAND_BOARD:
				return (long)(productTotal*0.09);
			case BEACH_CHAIR:
				return (long)(productTotal*0.05);
			case BEACH_TABLE:
				return (long)(productTotal*0.081);
		}
		return 0L;
	}
}
