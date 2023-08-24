package no.accelerate.chinook.repositories;

import no.accelerate.chinook.models.Customer;
import no.accelerate.chinook.models.CustomerGenre;
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
    public int insert(Customer customer) {
        String sql = "INSERT INTO customer (first_name, last_name, country, postal_code, phone, email) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getCountry());
            preparedStatement.setString(4, customer.getPostalCode());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setString(6, customer.getEmail());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
            return 0;
        }
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
    public int delete(Customer object) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public List<Customer> findByName(String name) {
        List<Customer> customers = new ArrayList<>();
        // SQL query to select customers matching the provided name
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email "
                + "FROM customer "
                + "WHERE first_name ILIKE ? OR last_name ILIKE ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set the name parameters with wildcard search (%name%) for first_name and last_name
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, "%" + name + "%");

            // Execute the query and retrieve the result set
            ResultSet resultSet = preparedStatement.executeQuery();
            // Iterate through the result set and create customer objects
            while (resultSet.next()) {
                Customer customer = new Customer();
                // Populate customer attributes from the result set
                customer.setId(resultSet.getLong("customer_id"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setCountry(resultSet.getString("country"));
                customer.setPostalCode(resultSet.getString("postal_code"));
                customer.setPhoneNumber(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                customers.add(customer); // Add customer to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
        return customers;
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

    @Override
    public List<CustomerGenre> findMostPopularGenres(Long customerId) {

        String sql = "SELECT g.genre_id, g.name AS genre, COUNT(t.track_id) AS count " +
                "FROM customer c " +
                "JOIN invoice i ON c.customer_id = i.customer_id " +
                "JOIN invoice_line il ON i.invoice_id = il.invoice_id " +
                "JOIN track t ON il.track_id = t.track_id " +
                "JOIN genre g ON t.genre_id = g.genre_id " +
                "WHERE c.customer_id = ? " +
                "GROUP BY g.genre_id, g.name " +
                "HAVING COUNT(t.track_id) = (" +
                "    SELECT MAX(count) " +
                "    FROM (" +
                "        SELECT g.genre_id, COUNT(t.track_id) AS count " +
                "        FROM customer c " +
                "        JOIN invoice i ON c.customer_id = i.customer_id " +
                "        JOIN invoice_line il ON i.invoice_id = il.invoice_id " +
                "        JOIN track t ON il.track_id = t.track_id " +
                "        JOIN genre g ON t.genre_id = g.genre_id " +
                "        WHERE c.customer_id = ? " +
                "        GROUP BY g.genre_id" +
                "    ) AS genre_counts" +
                ")";

        List<CustomerGenre> genres = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, customerId);
            preparedStatement.setLong(2, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long genreId = resultSet.getLong("genre_id");
                String genreName = resultSet.getString("genre");
                int count = resultSet.getInt("count");

                genres.add(new CustomerGenre(genreId, genreName, count));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
        return genres;
    }
}
