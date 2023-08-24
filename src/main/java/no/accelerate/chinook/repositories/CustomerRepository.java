package no.accelerate.chinook.repositories;

import no.accelerate.chinook.models.Customer;
import no.accelerate.chinook.models.CustomerCountry;
import no.accelerate.chinook.models.CustomerGenre;
import no.accelerate.chinook.models.CustomerSpender;

import java.util.List;

public interface CustomerRepository extends CRUDRepository<Customer, Long> {

    /**
     * Finds customers by their name.
     *
     * @param name The name of the customer to search for.
     * @return A list of customers matching the provided name.
     */
    List<Customer> findByName(String name);

    /**
     * Retrieves a subset of customers with specified limits.
     *
     * @param limit The maximum number of customers to retrieve.
     * @param offset The starting index of customers to retrieve.
     * @return A list of customers within the specified limit and offset.
     */
    List<Customer> getCustomerSubset(int limit, int offset);

    /**
     * Finds the customer who has spent the most money.
     *
     * @return The customer with the highest total spent.
     */
    CustomerSpender findHighestSpender();

    /**
     * Finds the most popular genres of tracks purchased by a customer.
     *
     * @param customerId The ID of the customer.
     * @return A list of the most popular genres.
     */
    List<CustomerGenre> findMostPopularGenres(Long customerId);


    /**
     * Retrieves the country with the highest number of customers.
     *
     * @return The country with the most customers.
     */
    CustomerCountry getCountryWithMostCustomers();

}
