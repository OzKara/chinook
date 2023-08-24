package no.accelerate.chinook.repositories;

import java.util.List;

/**
 * A repository interface providing basic CRUD operations for entities.
 *
 * @param <T> The type of the entity.
 * @param <U> The type of the entity's ID.
 */
public interface CRUDRepository<T, U> {

    /**
     * Retrieves all customers.
     *
     * @return A list containing all customers.
     */
    List<T> findAll();

    /**
     * Retrieves a customer by ID.
     *
     * @param id The ID of the customer
     * @return The retrieved customer, or null if not found.
     */
    T findById(U id);

    /**
     * Inserts a new customer into the repository.
     *
     * @param object The customer to insert.
     * @return The number of affected rows.
     */
    int insert(T object);

    /**
     * Updates an existing customer in the repository.
     *
     * @param object The entity to update.
     * @return The number of affected rows.
     */
    int update(T object);

    /**
     * Deletes a customer.
     *
     * @param object The customer to delete.
     * @return The number of affected rows.
     */
    int delete(T object);

    /**
     * Deletes a customer by its ID.
     *
     * @param id The ID of the customer to delete.
     * @return The number of affected rows.
     */
    int deleteById(U id);
}
