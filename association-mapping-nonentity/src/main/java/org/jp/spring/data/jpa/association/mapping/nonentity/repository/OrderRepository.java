package org.jp.spring.data.jpa.association.mapping.nonentity.repository;

import java.util.List;

import org.jp.spring.data.jpa.association.mapping.nonentity.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDetail, Long> {

	List<OrderDetail> findByDesc(String desc);

	List<OrderDetail> findByProductListAmountBetween(double fromAmount, double toAmount);

}
