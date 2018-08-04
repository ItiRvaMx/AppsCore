package core.gui;

/**
 * Representation of a column part of a {@link RichDataModel}
 * @author Rene Vera Apale
 */
public class RichDataColumn {
    
    private String header, property;
    private Class dataType;
    /**
     * Initializes a new column with the specified header, pointing to the specified
     * property and representing the specified data type.
     * @param header The header that will be display for this column
     * @param property Publicly accessible property from which the data will be taken
     * @param dataType Type of the value return by the property. Is used to render
     * the cell containing data.
     */
    public RichDataColumn(String header, String property, Class dataType) {
        this.header = header;
        this.property = property;
        this.dataType = dataType;
    }
    /**
     * @return the header that will be displayed for this column
     */
    public String getHeader() {
        return header;
    }
    /**
     * Sets the header for the column
     * @param header Text to display as header
     */
    public void setHeader(String header) {
        this.header = header;
    }
    /**
     * Returns the name of the method from which data will be obtained. It must
     * be publicly accessible in the class
     * @return The name of the method that represents data for this column
     */
    public String getProperty() {
        return property;
    }
    /**
     * Sets the name of the method from which data will be obtained. It must
     * be publicly accessible in the class
     * @param property The name of the method that represents data for this column
     */
    public void setProperty(String property) {
        this.property = property;
    }
    /**
     * @return The class that represents the data shown in this column
     */
    public Class getDataType() {
        return dataType;
    }
    /**
     * Sets the class that represents the data shown in this column
     * @param dataType Class that represents the data shown in this column
     */
    public void setDataType(Class dataType) {
        this.dataType = dataType;
    }

}