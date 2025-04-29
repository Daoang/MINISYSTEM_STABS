package surenatalaga;

/*  Reeeeey Prject

*/

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

class CustomTable extends JTable {
    private List<List<Object>> tableData; 
    public CustomTable() {
        
        tableData = new ArrayList<>();

        // >>>>>>>>>>>>>>>>>>>>> TABLE MODEL <<<<<<<<<<<<<<<<<<<<<<<< //
        setModel(new DefaultTableModel(
            new Object[]{"Item Name", "Quantity", "ID Number", "Price"}, 
            0 
        ));
    }

    
    public void addRow(Object[] rowData) {
        
        List<Object> row = new ArrayList<>();
        for (Object cell : rowData) {
            row.add(cell);
        }
        tableData.add(row);

        // >>>>>>>>>>>>>>>>>>>>>>>> JTABLE <<<<<<<<<<<<<<<<<<<<<<<<<<< //
        ((DefaultTableModel) getModel()).addRow(rowData);
    }

    
    public List<Object> getRowData(int rowIndex) {
        return tableData.get(rowIndex);
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);

        if (column == 1) {
            try {
                int quantity = Integer.parseInt(getValueAt(row, column).toString());
                if (quantity >= 100) {
                    c.setBackground(Color.GREEN);
                    c.setForeground(Color.BLACK);
                } else if (quantity >= 50) {
                    c.setBackground(Color.YELLOW);
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(Color.RED);
                    c.setForeground(Color.BLACK);
                }
            } catch (NumberFormatException e) {
                c.setBackground(Color.WHITE);
                c.setForeground(Color.BLACK);
            }
        } else {
            c.setBackground(getBackground());
            c.setForeground(getForeground());
        }

        return c;
    }

    
    public void printTableData() {
        for (List<Object> row : tableData) {
            System.out.println(row);
        }
    }
}
