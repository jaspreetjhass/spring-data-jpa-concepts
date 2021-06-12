package org.jp.spring.data.jpa.association.mapping.entity.onetomany;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "customer_contacts", joinColumns = {
			@JoinColumn(name = "customer_Id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "phone_id", referencedColumnName = "id") })
	private List<PhoneNumber> phoneNumberList;

}
