package core.data.session;

/**
 *
 * @author Rene Vera Apale
 */
public abstract class AbstractTransactionOperation {
    
    public abstract Object execute(Object... params);
}