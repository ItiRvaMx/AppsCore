package core.gui;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rene Vera Apale
 */
public class RichDataModel<T> extends AbstractTableModel {
    
    private final ArrayList<T> data;
    private final ArrayList<RichDataColumn> columns;
    private final ArrayList<String> supportedMethodNames;
    
    public RichDataModel() {
        data = new ArrayList<>();
        columns = new ArrayList<>();
        supportedMethodNames = new ArrayList<>();
    }
    
    public void setData(List<T> newData) {
        data.clear();
        data.addAll(newData);
        fireTableDataChanged();
    }
    
    public void clearData() {
        data.clear();
        fireTableDataChanged();
    }
    
    public void addColumn(RichDataColumn col) {
        columns.add(col);
    }
    
    public void loadAvailableMethods(Class type) {
        for (Method method : type.getMethods())
            supportedMethodNames.add(method.getName());
    }
    
    public void addRow(T newRow) {
        data.add(newRow);
        fireTableRowsInserted(data.size(), data.size() + 1);
    }
    
    public void removeRow(T row) {
        int removedIdx = data.indexOf(row);
        data.remove(row);
        fireTableRowsDeleted(removedIdx, removedIdx);
    }
    
    public void removeRow(int index) {
        data.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
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