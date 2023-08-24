package no.accelerate.chinook.repositories;

import no.accelerate.chinook.models.Customer;
import no.accelerate.chinook.models.CustomerSpender;

import java.util.List;

public interface CustomerRepository extends CRUDRepository {
    int update(Customer customer);

    Customer findByName(String name);

    List<Customer> getCustomerSubset(int limit, int offset);

    CustomerSpender findHighestSpender();
}
