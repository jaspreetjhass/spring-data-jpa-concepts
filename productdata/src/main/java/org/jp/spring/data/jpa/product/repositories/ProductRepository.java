package org.jp.spring.data.jpa.product.repositories;

import java.util.List;

import org.jp.spring.data.jpa.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByName(String name);

	List<Product> findByNameAndDesc(String name, String desc);

	List<Product> findByPriceGreaterThan(double price);

	List<Product> findByPriceBetween(double startRange, double endRange);

	List<Product> findByDescContains(String desc);

	List<Product> findByDescLike(String desc);

	List<Product> findByIdIn(List<Integer> productIds);

}
