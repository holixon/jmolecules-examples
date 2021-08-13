/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jmolecules.examples.jpa.customer;

import lombok.Getter;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;
import org.jmolecules.examples.jpa.customer.Customer.CustomerId;
import org.springframework.util.Assert;

/**
 * @author Oliver Drotbohm
 */
@Getter
public class Customer implements AggregateRoot<Customer, CustomerId> {

	private final CustomerId id;
	private String firstname, lastname;
	private List<Address> addresses;

	public Customer(String firstname, String lastname, Address address) {

		Assert.notNull(address, "Address must not be null!");

		this.id = CustomerId.of(UUID.randomUUID().toString());

		this.firstname = firstname;
		this.lastname = lastname;

		this.addresses = new ArrayList<>();
		this.addresses.add(address);
	}

	@Value(staticConstructor = "of")
	public static class CustomerId implements Identifier {
		private final String id;
	}
}
