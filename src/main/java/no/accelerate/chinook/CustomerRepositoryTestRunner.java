package no.accelerate.chinook;

import no.accelerate.chinook.models.Customer;
import no.accelerate.chinook.models.CustomerCountry;
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

        //Print customer(s) with the name Stanislaw
        List<Customer> customerFindByName = customerRepository.findByName("Stanislaw");

        System.out.println("\n" + "Find by customer(s) name:");
        for (Customer customer : customerFindByName) {
            System.out.println("Customer(s) with the name Stanislaw: " + customer);
        }

        //List of 5 customers after ID 20
        List<Customer> customersPage = customerRepository.getCustomerSubset(5, 20);

        System.out.println("\n" + "List of 5 customers after ID 20");
        for (Customer customer : customersPage) {
            System.out.println(customer);
        }

        //Add new customer to the database
        Customer customerAddition = new Customer();
        customerAddition.setFirstName("Ozan");
        customerAddition.setLastName("Kara");
        customerAddition.setCountry("Norway");
        customerAddition.setPostalCode("0977");
        customerAddition.setPhoneNumber("112");
        customerAddition.setEmail("ozan.kara@haugenstua.no");

        System.out.println("\n" + "Add new customer: ");

        // add the new customer
        int rowsAffected = customerRepository.insert(customerAddition);

        if (rowsAffected > 0) {
            System.out.println("New customer added successfully");

            // Check if the new customer exists by searching using its name, reuse findByName method
            List<Customer> foundCustomer = customerRepository.findByName("Ozan");

            if (foundCustomer != null) {
                for (Customer customer : foundCustomer) {
                    System.out.println("Customer found: " + customer);
                }
            } else {
                System.out.println("Customer not found");
            }
        } else {
            System.out.println("Failed to add new customer");
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

        //Print country with most customers
        CustomerCountry countryWithMostCustomers = customerRepository.getCountryWithMostCustomers();

        System.out.println("\n" + "Country with the most customers: " + countryWithMostCustomers.getCountryName()
                + ", Customer Count: " + countryWithMostCustomers.getCustomerCount());
    }
}
