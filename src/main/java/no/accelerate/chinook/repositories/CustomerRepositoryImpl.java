package no.accelerate.chinook.repositories;

import no.accelerate.chinook.models.Customer;
import no.accelerate.chinook.models.CustomerSpender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository{
    private final String url;
    private final String username;
    private final String password;

    public CustomerRepositoryImpl(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email FROM customer";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("customer_id"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setCountry(resultSet.getString("country"));
                customer.setPostalCode(resultSet.getString("postal_code"));
                customer.setPhoneNumber(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
        return customers;
    }

    //Method for finding customer by ID
    @Override
    public Customer findById(Long id) {
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email "
                   + "FROM customer "
                   + "WHERE customer_id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("customer_id"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setCountry(resultSet.getString("country"));
                customer.setPostalCode(resultSet.getString("postal_code"));
                customer.setPhoneNumber(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                return customer;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
        return null;
    }

    @Override
    public int insert(Object object) {
        return 0;
    }

    @Override
    public int update(Object object) {
        return 0;
    }

    //Method for updating customer
    @Override
    public int update(Customer customer) {
        String sql = "UPDATE customer "
                + "SET first_name = ?, last_name = ?, country = ?, postal_code = ?, phone = ?, email = ? "
                + "WHERE customer_id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getCountry());
            preparedStatement.setString(4, customer.getPostalCode());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setString(6, customer.getEmail());
            preparedStatement.setLong(7, customer.getId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
        return 0;
    }

    @Override
    public int delete(Object object) {
        return 0;
    }

    @Override
    public int deleteById(Object id) {
        return 0;
    }

    @Override
    public Customer findByName(String name) {
        // SQL query to search for customers with a matching first name or last name
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email "
                + "FROM customer "
                + "WHERE first_name LIKE ? OR last_name LIKE ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set the first parameter in the prepared statement for the first_name parameter
            // The % symbols are used to match any characters before or after the input name
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, "%" + name + "%"); //for last_name

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // If a matching customer is found in the result set, create a Customer object
                    Customer customer = new Customer();
                    customer.setId(resultSet.getLong("customer_id"));
                    customer.setFirstName(resultSet.getString("first_name"));
                    customer.setLastName(resultSet.getString("last_name"));
                    customer.setCountry(resultSet.getString("country"));
                    customer.setPostalCode(resultSet.getString("postal_code"));
                    customer.setPhoneNumber(resultSet.getString("phone"));
                    customer.setEmail(resultSet.getString("email"));
                    return customer;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
        return null; // Customer not found
    }

    //Method for getting a customer subset
    @Override
    public List<Customer> getCustomerSubset(int limit, int offset) {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email "
                    + "FROM customer "
                    + "LIMIT ? OFFSET ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("customer_id"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setCountry(resultSet.getString("country"));
                customer.setPostalCode(resultSet.getString("postal_code"));
                customer.setPhoneNumber(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                customers.add(customer);
            }
        }  catch (SQLException e) {
            e.printStackTrace();
                // Handle exception
        }
        return customers;
    }

    @Override
    public CustomerSpender findHighestSpender() {
        String sql = "SELECT c.customer_id, c.first_name, c.last_name, SUM(i.total) AS total_spent " +
                "FROM customer c " +
                "JOIN invoice i ON c.customer_id = i.customer_id " +
                "GROUP BY c.customer_id " +
                "ORDER BY total_spent DESC " +
                "LIMIT 1";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                Long customerId = resultSet.getLong("customer_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                double totalSpent = resultSet.getDouble("total_spent");

                return new CustomerSpender(customerId, firstName, lastName, totalSpent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
