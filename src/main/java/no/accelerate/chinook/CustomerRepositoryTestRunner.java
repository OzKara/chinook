package no.accelerate.chinook;

import no.accelerate.chinook.models.Customer;
import no.accelerate.chinook.models.CustomerSpender;
import no.accelerate.chinook.repositories.CustomerRepositoryImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerRepositoryTestRunner implements ApplicationRunner {
    private final CustomerRepositoryImpl customerRepository;

    public CustomerRepositoryTestRunner(CustomerRepositoryImpl customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //Print all customers
        List<Customer> customers = customerRepository.findAll();

        System.out.println("\n" + "All customers:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }

        //Print customer with ID 3
        Customer customerFindById = customerRepository.findById(3L);

        System.out.println("\n" + "Find by ID:");
        System.out.println("Customer with ID 3:" + customerFindById);

        //List of 5 customers after ID 20
        List<Customer> customersPage = customerRepository.getCustomerSubset(5, 20);

        System.out.println("\n" + "List of 5 customers after ID 20");
        for (Customer customer : customersPage) {
            System.out.println(customer);
        }

        //Update customer with Id 18:
        Customer customerUpdate = customerRepository.findById(18L);

        customerUpdate.setFirstName("John");
        customerUpdate.setLastName("Smith");
        customerUpdate.setCountry("China");
        customerUpdate.setPhoneNumber("455 557 421 301");
        customerUpdate.setPostalCode("342312");

        customerRepository.update(customerUpdate);

        System.out.println("\n" + "Update customer: ");
        System.out.println(customerUpdate);

        //Print the highest spender
        CustomerSpender highestSpender = customerRepository.findHighestSpender();

        System.out.println("\n" + "Highest spender");
        System.out.println(highestSpender);


    }
}
