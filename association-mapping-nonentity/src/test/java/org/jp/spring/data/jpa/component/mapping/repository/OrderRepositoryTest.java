package org.jp.spring.data.jpa.component.mapping.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jp.spring.data.jpa.association.mapping.nonentity.entity.OrderDetail;
import org.jp.spring.data.jpa.association.mapping.nonentity.entity.Product;
import org.jp.spring.data.jpa.association.mapping.nonentity.repository.OrderRepository;
import org.jp.spring.data.jpa.association.mapping.nonentity.starter.AssociationMappingNonEntityApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest(bootstrapMode = BootstrapMode.LAZY)
@ContextConfiguration(classes = AssociationMappingNonEntityApp.class)
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	@Test
	public void findById() {
		final Optional<OrderDetail> optionalOrderDetail = orderRepository.findById(2l);
		optionalOrderDetail.ifPresent(System.out::println);
		assertThat(optionalOrderDetail.get()).extracting(OrderDetail::getProductList).asList()
				.anyMatch(product -> ((Product) product).getName().equals("google"));
	}

	@Test
	public void findByProductListAmountBetween() {
		final List<OrderDetail> orderDetailList = orderRepository.findByProductListAmountBetween(400, 2000);
		orderDetailList.forEach(System.out::println);
		assertThat(orderDetailList).hasSize(2);
	}

	@Test
	public void findAllOrderDetail() {
		final List<OrderDetail> orderDetailList = orderRepository.findAll();
		orderDetailList.forEach(System.out::println);
		assertThat(orderDetailList).hasSize(5);
	}

	@Test
	public void deleteOrderDetailById() {
		orderRepository.deleteById(3l);
		final List<OrderDetail> orderDetailList = orderRepository.findAll();
		orderDetailList.forEach(System.out::println);
		assertThat(orderDetailList).hasSize(4);
	}

	@Test
	public void deleteAllOrderDetail() {
		orderRepository.deleteAll();
		final List<OrderDetail> orderDetailList = orderRepository.findAll();
		orderDetailList.forEach(System.out::println);
		assertThat(orderDetailList).isEmpty();
	}

	@Test
	public void deleteAllProductForOrderDetail() {
		final Optional<OrderDetail> optionalOrderDetail = orderRepository.findById(4l);
		OrderDetail orderDetail = optionalOrderDetail.get();
		orderDetail.getProductList().clear();
		orderRepository.save(orderDetail);

		final List<OrderDetail> orderDetailList = orderRepository.findAll();
		orderDetailList.forEach(System.out::println);
		assertThat(orderDetailList.get(3).getProductList()).isEmpty();
	}

	@Test
	public void updateProductAmountInOrderDetail() {
		final Optional<OrderDetail> optionalOrderDetail = orderRepository.findById(2l);
		optionalOrderDetail.ifPresent(System.out::println);
		OrderDetail orderDetail = optionalOrderDetail.get();

		orderDetail.setProductList(orderDetail.getProductList().stream().map(p -> {
			if (p.getName().equals("google"))
				p.setAmount(56000);
			return p;
		}).collect(Collectors.toList()));

		orderRepository.save(orderDetail);

		assertThat(orderRepository.findById(2l).get()).extracting(OrderDetail::getProductList).asList()
				.filteredOn(product -> ((Product) product).getName().equals("google"))
				.allMatch(product -> ((Product) product).getAmount() == 56000);
	}

	@Test
	public void updateOrderDesc() {
		final Optional<OrderDetail> optionalOrderDetail = orderRepository.findById(2l);
		OrderDetail orderDetail = optionalOrderDetail.get();
		orderDetail.setDesc("electronics gadget");

		orderRepository.save(orderDetail);

		assertThat(orderRepository.findById(2l).get()).extracting(OrderDetail::getDesc).isEqualTo("electronics gadget");
	}

	@Test
	public void saveOrderDetail() {
		orderRepository.save(OrderDetail.builder().desc("stationary")
				.productList(Arrays.asList(Product.builder().name("pencil").amount(2.90).build())).build());
		final List<OrderDetail> orderDetailList = orderRepository.findAll();
		orderDetailList.forEach(System.out::println);
		assertThat(orderDetailList).hasSize(6).anyMatch(order -> order.getDesc().equals("stationary"));
	}

}
