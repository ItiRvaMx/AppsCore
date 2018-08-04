package core.data.access.utils;

import java.util.List;

/**
 * Abstraction for the some of the restrictions used in data queries.
 * @author Rene Vera Apale
 */
public class QueryFilter {
    /**
     * Indicates what kind of restriction will be added to the query criteria.
     */
    public static enum Type {
        /**
         * Perform "equal" comparison.
         */
        EQ,
        /**
         * Perform "like" comparison with "ANYWHERE" mode set.
         */
        LK,
        /**
         * Perform "greater than" comparison.
         */
        GT,
        /**
         * Perform "greater than property" comparison.
         */
        GTP,
        /**
         * Perform "greater or equal than" comparison.
         */
        GE,
        /**
         * Perform "lower than" comparison.
         */
        LT,
        /**
         * Perform "lower or equal than" comparison.
         */
        LE,
        /**
         * Perform "equals null" comparison.
         */
        IS_NULL,
        /**
         * Perform "is not null" comparison.
         */
        INN,
        /**
         * Constant value to represent an "AND" comparison, also known as "conjunction".
         */
        CONJ,
        /**
         * Constant value to represent an "OR" comparison, also known as "disjunction".
         */
        DISJ,
        /**
         * Constant value to represent an "IN" comparison, used to compare a field against a collection of values.
         */
        IN,
        /**
         * Constant value to represent "lower than property" comparison.
         */
        LTP,
        /**
         * Constant value to represent "not equal" comparison.
         */
        NEQ
    }

    private String property, name;
    private Object value;
    private Type queryType;
    
    private QueryFilter(String property, Type searchType, Object value) {
        this.property = property;
        this.value = value;
        this.queryType = searchType;
        name = "genericFilter";
    }
    /**
     * Create an {@code or} restriction
     * @param conditions List of filters that should act as a single condition
     * @return A {@link QueryFilter} instance representing the {@code or} condition
     */
    public static QueryFilter or(List<QueryFilter> conditions) {
        return new QueryFilter(null, Type.DISJ, conditions);
    }
    /**
     * Create an {@code and} restriction
     * @param conditions List of filters that should act as a single condition
     * @return An {@link QueryFilter} instance representing the {@code and} condition
     */
    public static QueryFilter and(List<QueryFilter> conditions) {
        return new QueryFilter(null, Type.CONJ, conditions);
    }
    /**
     * Create an {@code equal} restriction
     * @param property Name of the entity property that will be compared
     * @param value Value that should match
     * @return A {@link QueryFilter} instance representing the {@code equal} condition
     */
    public static QueryFilter equal(String property, Object value) {
        return new QueryFilter(property, Type.EQ, value);
    }
    /**
     * Create a {@code greateEqual} restriction
     * @param property Name of the entity property that will be compared
     * @param value Value that should be lower or equal to that of the property
     * @return A {@link QueryFilter} instance representing the {@code greaterEqual} condition
     */
    public static QueryFilter greaterEqual(String property, Object value) {
        return new QueryFilter(property, Type.GE, value);
    }
    /**
     * Create a {@code greater} restriction
     * @param property Name of the entity property that will be compared
     * @param value Value that should be lower to that of the property
     * @return A {@link QueryFilter} instance representing the {@code greater} condition
     */
    public static QueryFilter greater(String property, Object value) {
        return new QueryFilter(property, Type.GT, value);
    }
    /**
     * Create a {@code greaterThanProperty} restriction
     * @param property Name of the entity property that will be compared
     * @param otherProperty Name of the property which value must be lower than the first property
     * @return A {@link QueryFilter} instance representing the {@code greaterThanProperty} condition
     */
    public static QueryFilter greaterThanProperty(String property, String otherProperty) {
        return new QueryFilter(property, Type.GTP, otherProperty);
    }
    /**
     * Create an {@code in} restriction
     * @param property Name of the entity property that will be compared
     * @param values List of accepted values that can match to the property
     * @return A {@link QueryFilter} instance representing the {@code in} condition
     */
    public static QueryFilter in(String property, List values) {
        return new QueryFilter(property, Type.IN, values);
    }
    /**
     * Create an {@code isNotNull} restriction
     * @param property Name of the entity property that will test for non-nullity
     * @return A {@link QueryFilter} instance representing the {@code isNotNull} condition
     */
    public static QueryFilter isNotNull(String property) {
        return new QueryFilter(property, Type.INN, null);
    }
    /**
     * Create an {@code isNull} restriction
     * @param property Name of the entity property that will test for nullity
     * @return A {@link QueryFilter} instance representing the {@code isNull} condition
     */
    public static QueryFilter isNull(String property) {
        return new QueryFilter(property, Type.IS_NULL, null);
    }
    /**
     * Create a {@code lowerEqual} restriction
     * @param property Name of the entity property that will be compared
     * @param value Value that should be greater or equal to that of the property
     * @return A {@link QueryFilter} instance representing the {@code lowerEqual} condition
     */
    public static QueryFilter lowerEqual(String property, Object value) {
        return new QueryFilter(property, Type.LE, value);
    }
    /**
     * Create an {@code ilike} restriction
     * @param property Name of the entity property that will be compared
     * @param value Case insensitive string that must be contained anywhere in the property value.
     * @return A {@link QueryFilter} instance representing the {@code ilike} condition
     */
    public static QueryFilter ilike(String property, String value) {
        return new QueryFilter(property, Type.LK, value);
    }
    /**
     * Create a {@code lower} restriction
     * @param property Name of the entity property that will be compared
     * @param value Value that must ve greater than that of the property
     * @return A {@link QueryFilter} instance representing the {@code lower} condition
     */
    public static QueryFilter lower(String property, Object value) {
        return new QueryFilter(property, Type.LT, value);
    }
    /**
     * Create a {@code lowerThanProperty} restriction
     * @param property Name of the entity property that will be compared
     * @param otherProperty Name of the property which value must be greater than the first property
     * @return A {@link QueryFilter} instance representing the {@code lowerThanProperty} condition
     */
    public static QueryFilter lowerThanProperty(String property, String otherProperty) {
        return new QueryFilter(property, Type.LTP, property);
    }
    /**
     * Create a {@code notEqual} restriction
     * @param property Name of the entity property that will be compared
     * @param value Value that must be different to that of the property
     * @return A {@link QueryFilter} instance representing the {@code notEqual} condition
     */
    public static QueryFilter notEqual(String property, Object value) {
        return new QueryFilter(property, Type.NEQ, value);
    }
    /**
     * Returns the name of the property represented in this filter
     * @return the name of the property
     */
    public String getProperty() {
        return property;
    }
    /**
     * Returns the query type represented by this filter
     * @return the query type represented by this filter
     */
    public Type getQueryType() {
        return queryType;
    }
    /**
     * Gets the value that will be compared against the property, usually an {@link Object}
     * instance.
     * <ul><li>In the case of {@link Type#CONJ Type.CONJ} and {@link Type#DISJ Type.DISJ}
     * the return object will be a {@link List} of {@link QueryFilter} objects.</li>
     * <li>For {@link Type#INN Type.INN} and {@link Type#IS_NULL Type.IS_NULL}, the return
     * object will be {@code null}.</li>
     * <li>For {@link Type#IN Type.IN}, the return value will be a {@link List} of objects.</li></ul>
     * @return the value that will be compared against the property
     */
    public Object getValue() {
        return value;
    }
    /**
     * Returns the name that displays when this filter is used in a GUI component.
     * @return 
     */
    public String getName() {
        return name;
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
        hash = 89 * hash + queryType.hashCode();
        return hash;
    }
}