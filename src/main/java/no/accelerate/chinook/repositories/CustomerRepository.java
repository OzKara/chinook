package no.accelerate.chinook.repositories;

import no.accelerate.chinook.models.Customer;
import no.accelerate.chinook.models.CustomerCountry;
import no.accelerate.chinook.models.CustomerSpender;

import java.util.List;

public interface CustomerRepository extends CRUDRepository<Customer, Long> {

    List<Customer> findByName(String name);

    List<Customer> getCustomerSubset(int limit, int offset);

    CustomerSpender findHighestSpender();

    CustomerCountry getCountryWithMostCustomers();

}
