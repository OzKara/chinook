package no.accelerate.chinook;

import no.accelerate.chinook.models.Customer;
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
        List<Customer> customers = customerRepository.findAll();
        Customer customerFindById = customerRepository.findById(3L);

        System.out.println("\n" + "All customers:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }

        System.out.println("\n" + "Find by ID:");
        System.out.println("Customer with ID 3:" + customerFindById);
    }
}
