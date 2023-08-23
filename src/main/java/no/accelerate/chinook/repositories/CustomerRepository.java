package no.accelerate.chinook.repositories;

import no.accelerate.chinook.models.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll();

}
