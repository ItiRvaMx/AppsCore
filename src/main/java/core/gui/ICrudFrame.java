package core.gui;

/**
 * Describes the basic actions for any frame that must expose CRUD functionality.
 * Every class that implements this interface must have a <i>edition mode</i> and
 * a <i>list mode</i>.
 * @author Rene Vera Apale
 * @param <T> Type of the entities that will be managed by this frame
 */
public interface ICrudFrame<T> {

    /**
     * Action fired to persist the changes done since the last
     * Prepare Edition action.
     * @param record Object which should be persisted to the database
     */
    public void saveChanges(T record);
    /**
     * Action fired to perform a search to the database. The exact fields
     * allowed to be queried are left to the business rules of the application.
     */
    public void findRecords();
    /**
     * Action fired to delete a record. This action can be fired
     * from the table itself or from from the edition form.
     * @param record Object which should be delete from the database
     */
    public void delete(T record);
    /**
     * Action fired to enable the edition form, either for an existing record or
     * a new one.
     * @param record The record which will be edited, or {@code null} if it's a
     * new one.
     */
    public void prepareEdition(T record);
    /**
     * Action fired to discard the changes done to the editing record.
     */
    public void cancelAction();
    /**
     * Method invoke when Save, Delete or Edit actions are triggered. If the frame
     * it's in edition mode, then the returned data is gather from the components;
     * otherwise the returned object is taken directly from the table rows.
     * @return 
     */
    public T getDataForProcessing();
}