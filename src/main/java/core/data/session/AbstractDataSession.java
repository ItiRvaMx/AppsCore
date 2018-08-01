package core.data.session;

import core.config.AppConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Rene Vera Apale
 */
public abstract class AbstractDataSession {
    
    protected SessionFactory sessionFactory;
    protected Transaction transaction;
    
    public abstract void init(AppConfig config);
    
    public abstract void close() throws Exception;
    
    public abstract void beginTransaction() throws Exception;
    
    public abstract void commit() throws Exception;
    
    public abstract void rollback() throws Exception;
    
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}