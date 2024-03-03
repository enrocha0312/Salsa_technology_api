package com.salsatechnology.service;

import javax.transaction.Transactional;

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
	public void createOrder(ProductOrderDTO productOrderDTO) {
		productOrderRepository.save(createProductOrder(productOrderDTO));
	}

	public List<ProductOrder> findByUsername (String username){
		return productOrderRepository.findByUsername(username);
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
				return (long)(productTotal*10.3);
			case SURFBOARD:
				return (long)(productTotal*15.6);
			case SAND_BOARD:
				return (productTotal*9);
			case BEACH_CHAIR:
				return (productTotal*5);
			case BEACH_TABLE:
				return (long)(productTotal*8.1);
		}
		return 0L;
	}
}
