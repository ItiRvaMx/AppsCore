package core.data.session;

/**
 * Utility class that provides a wrapping for executing data manipulation in the
 * context of a transaction.
 * @author Rene Vera Apale
 */
public abstract class AbstractTransactionOperation {
    /**
     * This method will be called within the context of the transaction. Every and all
     * data manipulation must be done in this method.
     * @param params Optional parameters that can be passed to the method
     * @return A value resulted from the operations. It can vary from {@code null} to indicate
     * that no returned value should be expected, to an entity instance, to a list
     * of instances, etc
     */
    public abstract Object execute(Object... params);
}