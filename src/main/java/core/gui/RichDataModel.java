package core.gui;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Table model implementation to handle underlying data via a list of type <i>T</i>
 * and a set of {@link RichDataColumn} instances.
 * @author Rene Vera Apale
 * @param <T> Type of the data that will be displayed by this model
 */
public class RichDataModel<T> extends AbstractTableModel {
    
    private final ArrayList<T> data;
    private final ArrayList<RichDataColumn> columns;
    private final ArrayList<String> supportedMethodNames;
    /**
     * Initializes an instance with an empty data list, an empty column list and an empty method names list.
     */
    public RichDataModel() {
        data = new ArrayList<>();
        columns = new ArrayList<>();
        supportedMethodNames = new ArrayList<>();
    }
    /**
     * Sets the underlying data. Data currently held by the model is
     * cleared before setting the new info.
     * @param newData The data that will replace the current info
     */
    public void setData(List<T> newData) {
        data.clear();
        data.addAll(newData);
        fireTableDataChanged();
    }
    /**
     * Clears the current data held by this model.
     */
    public void clearData() {
        data.clear();
        fireTableDataChanged();
    }
    /**
     * Adds a new column to the existing list.
     * @param col Column to be added
     */
    public void addColumn(RichDataColumn col) {
        columns.add(col);
    }
    /**
     * Scans the names for all the public methods of the specified class and
     * adds them to a list. The methods are obtained via {@link Class#getMethods() Class.getMethods()}
     * @param type The class which methods will be scanned
     */
    public void loadAvailableMethods(Class type) {
        for (Method method : type.getMethods())
            supportedMethodNames.add(method.getName());
    }
    /**
     * Appends a new row to the current underlying data.
     * @param newRow The new object to append
     */
    public void addRow(T newRow) {
        data.add(newRow);
        fireTableRowsInserted(data.size(), data.size() + 1);
    }
    /**
     * Removes a row from the underlying data.
     * @param row Object that should be removed
     */
    public void removeRow(T row) {
        int removedIdx = data.indexOf(row);
        data.remove(row);
        fireTableRowsDeleted(removedIdx, removedIdx);
    }
    /**
     * Removes a row from the underlying data.
     * @param index Index of the row that should be removed.
     */
    public void removeRow(int index) {
        data.remove(index);
        fireTableRowsDeleted(index, index);
    }
    /**
     * Returns the data object pointed by the specified index.
     * @param index Position of the object within the underlying data collection
     * @return The object represented by the specified index
     */
    public T getRow(int index) {
        return data.get(index);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public String getColumnName(int i) {
        return columns.get(i).getHeader();
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return columns.get(i).getDataType();
    }

    @Override
    public Object getValueAt(int rowIdx, int colIdx) {
        Object row = data.get(rowIdx);
        String requestedMethod;
        if (columns.get(colIdx).getDataType() == boolean.class | columns.get(colIdx).getDataType() == Boolean.class)
            requestedMethod = "is";
        else
            requestedMethod = "get";
        requestedMethod += columns.get(colIdx).getProperty();
        Method method = null;
        try {
            for (String methodName : supportedMethodNames) {
                if (methodName.equalsIgnoreCase(requestedMethod)) {
                    method = row.getClass().getMethod(methodName);
                    break;
                }
            }
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
        if (method == null)
            throw new RuntimeException("Method " + requestedMethod + " was not found in type " + row.getClass().getName());
        try {
            return method.invoke(row);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}