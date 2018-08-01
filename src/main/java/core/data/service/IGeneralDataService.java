package core.data.service;

import core.data.access.utils.QueryFilter;
import java.util.List;

/**
 *
 * @author Rene Vera Apale
 */
public interface IGeneralDataService<T> {

    public T createOrUpdate(T record) throws Exception;
    
    public void delete(T record) throws Exception;
    
    public T getRecord(int id) throws Exception;
    
    public List<T> getRecordsList(List<QueryFilter> filters, String orderBy, boolean ascending) throws Exception;
}