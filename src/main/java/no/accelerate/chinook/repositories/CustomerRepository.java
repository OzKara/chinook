package no.accelerate.chinook.repositories;

import no.accelerate.chinook.models.Customer;

import java.util.List;

public interface CustomerRepository extends CRUDRepository {
    Customer findByName(String name);

    List<Customer> getCustomerSubset(int limit, int offset);

}
