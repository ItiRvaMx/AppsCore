package core.data.service;

import core.data.access.utils.QueryFilter;
import java.util.List;

/**
 * Describes the basic methods that are available for data interaction
 * @author Rene Vera Apale
 */
public interface IGeneralDataService<T> {

    /**
     * Creates a new record or updates an existing one.
     * @param record The object to persist/update
     * @return The same instance that was persisted/updated
     * @throws Exception When an error occurs
     */
    public T createOrUpdate(T record) throws Exception;
    /**
     * Deletes a record from the database.
     * @param record The entity to remove
     * @throws Exception When an error occurs
     */
    public void delete(T record) throws Exception;
    /**
     * Finds a record by its id
     * @param id Database ID for the record
     * @return The object represented by the specified ID
     * @throws Exception When an error occurs
     */
    public T getRecord(int id) throws Exception;
    /**
     * Finds the records that match the specified conditions
     * @param filters List of {@link QueryFilter} objects that represent the restrictions for the search
     * @param orderBy Name of the property to order the results over, pass {@code null} if no explicit
     * ordering is required
     * @param ascending {@code true} for ascending ordering, {@code false} otherwise
     * @return A list of objects of type {@code T}
     * @throws Exception When an error occurs
     */
    public List<T> getRecordsList(List<QueryFilter> filters, String orderBy, boolean ascending) throws Exception;
}