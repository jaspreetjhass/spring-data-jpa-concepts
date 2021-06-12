package org.jp.spring.data.jpa.inheritance.relationship;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Condition;
import org.jp.spring.data.jpa.inheritance.relationship.entity.Product;
import org.jp.spring.data.jpa.inheritance.relationship.repositories.ProductRepository;
import org.jp.spring.data.jpa.inheritance.relationship.starter.InheritanceRelationshipApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest(bootstrapMode = BootstrapMode.LAZY)
@ContextConfiguration(classes = InheritanceRelationshipApplication.class)
class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	@DisplayName("this test case persist the product in database")
	void saveProduct() {
		long totalrecords = productRepository.count();
		final Product product = Product.builder().name("product100").desc("product desc100").price(100).build();
		productRepository.save(product);
		long totalrecordsAfterInsertion = productRepository.count();
		assertEquals(totalrecords + 1, totalrecordsAfterInsertion);
	}

	@Test
	@DisplayName("this test case fetch the product data by productId")
	void findById() {
		final Product product = productRepository.findById(1).get();
		assertNotNull(product);
		assertEquals("product desc01", product.getDesc());
	}

	@Test
	@DisplayName("this test case update the product in database based on productId")
	void updateProduct() {
		final Product product = productRepository.findById(5).get();
		product.setPrice(00.00);
		productRepository.save(product);
		final Product actualProduct = productRepository.findById(5).get();
		assertEquals(00.00, actualProduct.getPrice());
	}

	@Test
	@DisplayName("this test case delete the product in database based on productId")
	void deleteProduct() {
		long totalRecords = productRepository.count();
		productRepository.deleteById(2);
		long totalRecordsAfterDelete = productRepository.count();
		assertEquals(totalRecords - 1, totalRecordsAfterDelete);
	}

	@Test
	@DisplayName("this test case fetch the product data by productName")
	void findByName() {
		final List<Product> productList = productRepository.findByName("product02");
		assertThat(productList).hasSize(1).allMatch(product -> product.getDesc().equals("product desc02"));
	}

	@Test
	@DisplayName("this test case fetch the product data by productName & productDesc")
	void findByNameAndDesc() {
		final List<Product> productList = productRepository.findByNameAndDesc("product03", "product desc03");
		assertThat(productList).hasSize(1).allMatch(product -> product.getPrice() == 40.67);
	}

	@Test
	@DisplayName("this test case fetch the product data having price greater than specified value")
	void findByPriceGreaterThan() {
		final List<Product> productList = productRepository.findByPriceGreaterThan(200);
		assertThat(productList).hasSize(4).allMatch(product -> product.getName().contains("product"));
	}

	@Test
	@DisplayName("this test case fetch the product data having price between two values")
	void findByPriceBetween() {
		final List<Product> productList = productRepository.findByPriceBetween(200, 2000);
		assertThat(productList).hasSize(2).anyMatch(product -> product.getName().equals("product05"));
	}

	@Test
	@DisplayName("this test case fetch the product data which contain the specified desc")
	void findByDescContains() {
		final List<Product> productList = productRepository.findByDescContains("desc");
		assertThat(productList).hasSize(6);
	}

	@Test
	@DisplayName("this test case fetch the product data which is similar to the specified desc")
	void findByDescLike() {
		final List<Product> productList = productRepository.findByDescLike("%desc04");
		assertThat(productList).hasSize(1);
	}

	@Test
	@DisplayName("this test case fetch all the product data whose id matches with the specified values")
	void findByProductIdIn() {
		final List<Product> productList = productRepository.findByIdIn(Arrays.asList(2, 3, 6));
		assertThat(productList).hasSize(3).areExactly(1,
				new Condition<>(product -> product.getName().equals("product02"), "product02 details"));
	}
}
