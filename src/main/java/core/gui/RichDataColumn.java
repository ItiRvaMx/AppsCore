package core.gui;

/**
 *
 * @author Rene Vera Apale
 */
public class RichDataColumn {
    
    private String header, property;
    private Class dataType;
    
    public RichDataColumn(String header, String property, Class dataType) {
        this.header = header;
        this.property = property;
        this.dataType = dataType;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Class getDataType() {
        return dataType;
    }

    public void setDataType(Class dataType) {
        this.dataType = dataType;
    }

}