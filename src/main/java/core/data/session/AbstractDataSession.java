package core.data.session;

import core.config.AppConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Provides a basic implementation to initialize a Hibernate session factory, as
 * well as the basic unimplemented methods for a common data session.
 * @author Rene Vera Apale
 */
public abstract class AbstractDataSession {
    
    protected SessionFactory sessionFactory;
    protected Transaction transaction;
    
    /**
     * Provides the basic initialization process to create a Hibernate session
     * factory. This method can be override in order to apply custom configuration
     * values through the {@code config} parameter.
     * @param config Custom values that can be applied to the Hibernate configuration
     */
    public void init(AppConfig config) {
        Configuration hibernateCfg = new Configuration();
        hibernateCfg.configure();
        sessionFactory = hibernateCfg.buildSessionFactory();
    }
    /**
     * Closes the active session factory.
     * @throws Exception When an error closing the session factory occurs
     */
    public abstract void close() throws Exception;
    /**
     * Begins a new transaction associated with the current session. The resulting
     * transaction object is referenced by {@link AbstractDataSession#transaction AbstractDataSession.transaction}
     * protected field
     * @throws Exception When an error occurs while beginning a transaction
     */
    public abstract void beginTransaction() throws Exception;
    /**
     * Commits the active transaction, if there is one.
     * @throws Exception When an error occurs while committing the transaction
     */
    public abstract void commit() throws Exception;
    /**
     * Rollback the active transaction, if there is one.
     * @throws Exception When an error occurs while rolling back the transaction
     */
    public abstract void rollback() throws Exception;
    /**
     * Returns the current session, as per the {@link SessionFactory#getCurrentSession() SessionFactory.getCurrentSession()}
     * method spec. This method is useful in the construction of very specific Hibernate
     * operations, such as custom queries execution.
     * @return A {@link Session} instance
     */
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}