package core.data.access.utils;

/**
 *
 * @author theReno
 */
public class QueryFilter {
    /**
     * Constant value to perform "equal" comparison.
     */
    public final static int EQ = 0;
    /**
     * Constant value to perform "like" comparison with "ANYWHERE" mode set.
     */
    public final static int LK = 1;
    /**
     * Constant value to perform "greater than" comparison.
     */
    public final static int GT = 2;
    public final static int GTP = 3;
    /**
     * Constant value to perform "greater or equal than" comparison.
     */
    public final static int GE = 4;
    /**
     * Constant value to perform "lower than" comparison.
     */
    public final static int LT = 5;
    /**
     * Constant value to perform "lower or equal than" comparison.
     */
    public final static int LE = 6;
    /**
     * Constant value to perform "equals null" comparison.
     */
    public final static int IS_NULL = 7;
    /**
     * Constant value to perform "is not null" comparison.
     */
    public final static int INN = 8;
    /**
     * Constant value to represent an "AND" comparison, also known as "conjunction".
     */
    public final static int CONJ = 9;
    /**
     * Constant value to represent an "OR" comparison, also known as "disjunction".
     */
    public final static int DISJ = 10;
    /**
     * Constant value to represent an "IN" comparison, used to compare a field against a collection of values.
     */
    public final static int IN = 11;
    /**
     * Constant value to represent "lower than property" comparison.
     */
    public final static int LTP = 12;
    /**
     * Constant value to represent "not equal" comparison.
     */
    public final static int NEQ = 13;
    

    private String property, name;
    private Object value;
    private int queryType;

    public QueryFilter(String property, int searchType, Object value) {
        this.property = property;
        this.value = value;
        this.queryType = searchType;
        name = "genericFilter";
    }
    
    public QueryFilter(String guiName, String property, int searchType, Object value) {
        this.property = property;
        this.value = value;
        this.queryType = searchType;
        name = guiName;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public int getQueryType() {
        return queryType;
    }

    public void setQueryType(int type) {
        this.queryType = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    public String getName() {
        return name;
    }

    /**
     * @param name the _name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof QueryFilter)) return false;
        QueryFilter cast = (QueryFilter)o;
        return cast.getProperty() != null && this.property != null && cast.getProperty().equals(property) &&
               cast.getQueryType() == this.queryType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + property.hashCode();
        hash = 89 * hash + queryType;
        return hash;
    }
}