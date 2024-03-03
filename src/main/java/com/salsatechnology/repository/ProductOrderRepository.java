package com.salsatechnology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salsatechnology.model.ProductOrder;

import java.util.List;


@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    @Query(value="select * from product_order where user_name = ?1", nativeQuery = true)
    List<ProductOrder> findByUsername(String username);
}
