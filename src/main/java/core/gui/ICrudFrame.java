package core.gui;

/**
 *
 * @author Rene Vera Apale
 */
public interface ICrudFrame<T> {

    public void saveChanges(T record);
    
    public void findRecords();
    
    public void delete(T record);
    
    public void prepareEdition(T record);
    
    public void cancelAction();
    
    public T getDataForProcessing();
}