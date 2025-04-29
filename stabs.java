package surenatalaga;

/*  Reeeeey Prject

*/

import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.table.*;

public class stabs extends JFrame {

    // >>>>>>>>>>>>>>>>>>>>>>>>>> Declaring UI  <<<<<<<<<<<<<<<<//
    private JFrame loginFrame;
    private JPanel jPanel1, jPanel2;
    private JLabel txtUser;
    private JTextField txtPrice, txtItemName, txtQuantity, txtIDNumber, txtSearch;
    private JTable jTable1;
    private JScrollPane jScrollPane2;
    private JButton btnSignOut, btnSearch, btnAddItem, btnDelete, btnClear, btnSales;

    // >>>>>>>>>>>>>>>>>>>>>>>>>> Constructor log in frame <<<<<<<<<<<<<<<<//
    public stabs(JFrame loginFrame, String username, String role) {
        this.loginFrame = loginFrame;
        initComponents(); // >>>>>>>>>>>>>>>>>>>>>>>>>> GUI  <<<<<<<<<<<<<<<<//
        txtUser.setText(username + " (" + role + ")");
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>> Clears all text  <<<<<<<<<<<<<<<<//
    private void clearFields() {
        txtItemName.setText("");
        txtQuantity.setText("");
        txtIDNumber.setText("");
        txtPrice.setText("");
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>> full UI layout <<<<<<<<<<<<<<<<//
    private void initComponents() {
        jPanel1 = new JPanel(null);
        jPanel1.setBackground(new Color(50, 50, 50));
        jPanel1.setForeground(Color.WHITE);

        // >>>>>>>>>>>>>>>>>>>>>>>>>> jtable  <<<<<<<<<<<<<<<<//
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new String[]{"Item Name", "Quantity", "ID Number", "Price"});
        jTable1 = new JTable(model);
        jTable1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTable1.setRowHeight(25);
        jTable1.setBackground(new Color(50, 50, 50));
        jTable1.setForeground(Color.WHITE);
        jTable1.setAutoCreateRowSorter(true); 
        jTable1.getColumnModel().getColumn(1).setCellRenderer(new QuantityCellRenderer());

        jScrollPane2 = new JScrollPane(jTable1);
        jScrollPane2.setBounds(20, 100, 350, 320);
        jPanel1.add(jScrollPane2);

        // >>>>>>>>>>>>>>>>>>>>>>>>>> User label <<<<<<<<<<<<<<<<//
        txtUser = new JLabel("User: Admin (ADMINISTRATOR)");
        txtUser.setBounds(450, 20, 300, 30);
        txtUser.setFont(new Font("Tahoma", Font.BOLD, 16));
        txtUser.setForeground(Color.WHITE);
        jPanel1.add(txtUser);

        // >>>>>>>>>>>>>>>>>>>>>>>>>> Sign out button <<<<<<<<<<<<<<<<//
        btnSignOut = new JButton("Sign Out");
        btnSignOut.setBounds(720, 20, 100, 30);
        btnSignOut.setFocusable(false);
        btnSignOut.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSignOut.setBackground(Color.RED);
        btnSignOut.setForeground(Color.WHITE);
        btnSignOut.addActionListener(evt -> {
            this.setVisible(false);
            if (loginFrame != null) {
                loginFrame.setVisible(true);
            }
            System.exit(0);
        });
        jPanel1.add(btnSignOut);

        // >>>>>>>>>>>>>>>>>>>>>>>>>> Item details panel <<<<<<<<<<<<<<<<//
        jPanel2 = new JPanel(null);
        jPanel2.setBounds(400, 100, 400, 320); 
        jPanel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Item Details", 0, 0, new Font("Arial", Font.BOLD, 14), Color.WHITE));
        jPanel2.setBackground(Color.DARK_GRAY);

        // >>>>>>>>>>>>>>>>>>>>>>>>>> input fields for item details <<<<<<<<<<<<<<<<//
        JLabel lblItemName = new JLabel("Item Name:");
        lblItemName.setBounds(20, 30, 100, 25);
        lblItemName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblItemName.setForeground(Color.WHITE);
        jPanel2.add(lblItemName);
        txtItemName = new JTextField();
        txtItemName.setBounds(120, 30, 230, 25);
        jPanel2.add(txtItemName);

        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(20, 70, 100, 25);
        lblQuantity.setForeground(Color.WHITE);
        jPanel2.add(lblQuantity);
        txtQuantity = new JTextField();
        txtQuantity.setBounds(120, 70, 230, 25);
        jPanel2.add(txtQuantity);

        JLabel lblIDNumber = new JLabel("ID Number:");
        lblIDNumber.setBounds(20, 110, 100, 25);
        lblIDNumber.setForeground(Color.WHITE);
        jPanel2.add(lblIDNumber);
        txtIDNumber = new JTextField();
        txtIDNumber.setBounds(120, 110, 230, 25);
        jPanel2.add(txtIDNumber);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(20, 150, 100, 25);
        lblPrice.setForeground(Color.WHITE);
        jPanel2.add(lblPrice);
        txtPrice = new JTextField();
        txtPrice.setBounds(120, 150, 230, 25);
        jPanel2.add(txtPrice);

        // >>>>>>>>>>>>>>>>>>>>>>>>>> Add item button  <<<<<<<<<<<<<<<<//
        btnAddItem = new JButton("Add Item");
        btnAddItem.setBounds(30, 190, 130, 30);
        btnAddItem.setBackground(Color.BLACK);
        btnAddItem.setForeground(Color.WHITE);
        btnAddItem.addActionListener(evt -> {
            String itemName = txtItemName.getText().trim();
            String quantity = txtQuantity.getText().trim();
            String idNumber = txtIDNumber.getText().trim();
            String price = txtPrice.getText().trim();

            if (itemName.isEmpty() || quantity.isEmpty() || idNumber.isEmpty() || price.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled out!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String formattedPrice = formatPrice(price);
            if (formattedPrice.equals("Invalid Price")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid price!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Integer.parseInt(quantity);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Quantity must be a number!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
            m.addRow(new Object[]{itemName, quantity, idNumber, formattedPrice});
            clearFields();

            TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) jTable1.getRowSorter();
            sorter.setSortKeys(java.util.Collections.singletonList(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
            sorter.sort();

            int option = JOptionPane.showConfirmDialog(this, "Do you want to add another item?", "Add Another Item", JOptionPane.YES_NO_OPTION);
        });
        jPanel2.add(btnAddItem);

        // >>>>>>>>>>>>>>>>>>>>>>>>>> Delete item button <<<<<<<<<<<<<<<<//
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(220, 190, 130, 30);
        btnDelete.setBackground(Color.RED);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.addActionListener(evt -> deleteItem());
        jPanel2.add(btnDelete);

        // >>>>>>>>>>>>>>>>>>>>>>>>>> Clear all button <<<<<<<<<<<<<<<<//
        btnClear = new JButton("Clear All");
        btnClear.setBounds(30, 230, 130, 30);
        btnClear.setBackground(Color.BLACK);
        btnClear.setForeground(Color.WHITE);
        btnClear.addActionListener(evt -> clearTable());
        jPanel2.add(btnClear);

        // >>>>>>>>>>>>>>>>>>>>>>>>>> Sales UI <<<<<<<<<<<<<<<<//
        btnSales = new JButton("Sales");
        btnSales.setBounds(220, 230, 130, 30);
        btnSales.setBackground(Color.BLACK);
        btnSales.setForeground(Color.WHITE);
        btnSales.addActionListener(evt -> {
            // Fixed code: Make SalesUI visible first, then hide this frame
            new SalesUI().setVisible(true);
            this.setVisible(false);
        });
        jPanel2.add(btnSales);

        // >>>>>>>>>>>>>>>>>>>>>>>>>> Edit button  <<<<<<<<<<<<<<<<//
        JButton btnEdit = new JButton("Edit");
        btnEdit.setBounds(30, 270, 130, 30);
        btnEdit.setBackground(Color.BLACK);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFocusable(false);
        jPanel2.add(btnEdit);

        btnEdit.addActionListener(evt -> {
            int selectedRow = jTable1.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an item to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }

            DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
            int modelRow = jTable1.convertRowIndexToModel(selectedRow);

            String itemName = m.getValueAt(modelRow, 0).toString();
            String quantity = m.getValueAt(modelRow, 1).toString();
            String idNumber = m.getValueAt(modelRow, 2).toString();
            String price = m.getValueAt(modelRow, 3).toString().replace("₱", "").replace(",", "");

            JTextField itemNameField = new JTextField(itemName);
            JTextField quantityField = new JTextField(quantity);
            JTextField idNumberField = new JTextField(idNumber);
            JTextField priceField = new JTextField(price);

            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Item Name:"));
            panel.add(itemNameField);
            panel.add(new JLabel("Quantity:"));
            panel.add(quantityField);
            panel.add(new JLabel("ID Number:"));
            panel.add(idNumberField);
            panel.add(new JLabel("Price:"));
            panel.add(priceField);

            int result = JOptionPane.showConfirmDialog(this, panel, "Edit Item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    String newItemName = itemNameField.getText().trim();
                    String newQuantity = quantityField.getText().trim();
                    String newIDNumber = idNumberField.getText().trim();
                    String newPrice = formatPrice(priceField.getText().trim());

                    if (newItemName.isEmpty() || newQuantity.isEmpty() || newIDNumber.isEmpty() || newPrice.equals("Invalid Price")) {
                        JOptionPane.showMessageDialog(this, "Invalid input. Please fill all fields correctly.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Integer.parseInt(newQuantity);

                    m.setValueAt(newItemName, modelRow, 0);
                    m.setValueAt(newQuantity, modelRow, 1);
                    m.setValueAt(newIDNumber, modelRow, 2);
                    m.setValueAt(newPrice, modelRow, 3);

                    JOptionPane.showMessageDialog(this, "Item updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Quantity must be a number!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // >>>>>>>>>>>>>>>>>>>>>>>>>> Manage User Button <<<<<<<<<<<<<<<<//
        JButton manageUser = new JButton("Manage User");
        manageUser.setBounds(220, 270, 130, 30);
        manageUser.setBackground(Color.BLACK);
        manageUser.setForeground(Color.WHITE);
        manageUser.setFocusable(false);
        manageUser.addActionListener(evt -> {
            this.setVisible(false);
            new ManageUsers(this).setVisible(true);
        });
        jPanel2.add(manageUser);

        // >>>>>>>>>>>>>>>>>>>>>>>>>> Search Section <<<<<<<<<<<<<<<<//
        JLabel lblSearch = new JLabel("Mart Ease");
        lblSearch.setBounds(20, 70, 300, 25);
        lblSearch.setFont(new Font("Rockwell", Font.BOLD, 22));
        lblSearch.setForeground(Color.WHITE);
        jPanel1.add(lblSearch);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(450, 70, 90, 25);
        btnSearch.setBackground(Color.BLACK);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.addActionListener(evt -> filterTable(txtSearch.getText()));
        jPanel1.add(btnSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(550, 70, 150, 25);
        jPanel1.add(txtSearch);

        JButton btnClearSearch = new JButton("Clear");
        btnClearSearch.setBounds(710, 70, 90, 25);
        btnClearSearch.setBackground(Color.BLACK);
        btnClearSearch.setForeground(Color.WHITE);
        btnClearSearch.addActionListener(evt -> {
            txtSearch.setText("");
            filterTable("");
        });
        jPanel1.add(btnClearSearch);

        jPanel1.add(jPanel2);

        // >>>>>>>>>>>>>>>>>>>>>>>>>> Final JFrame  <<<<<<<<<<<<<<<<//
        setTitle("Mart Ease System");
        setSize(850, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(jPanel1);
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>> Format price to currency format <<<<<<<<<<<<<<<<//
    private String formatPrice(String price) {
        try {
            price = price.replaceAll("[^\\d.]", "");
            double parsedPrice = Double.parseDouble(price);
            NumberFormat format = new DecimalFormat("#,###.00");
            return "₱" + format.format(parsedPrice);
        } catch (NumberFormatException e) {
            return "Invalid Price";
        }
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>> Delete item from table <<<<<<<<<<<<<<<<//
    private void deleteItem() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow != -1) {
            int confirmation = JOptionPane.showConfirmDialog(
                    this, "Are you sure you want to delete the selected item?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE
            );
            if (confirmation == JOptionPane.YES_OPTION) {
                model.removeRow(jTable1.convertRowIndexToModel(selectedRow));
                JOptionPane.showMessageDialog(this, "Item deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No item selected to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>> Clear all table <<<<<<<<<<<<<<<<//
    private void clearTable() {
        int option = JOptionPane.showConfirmDialog(this, "Clear all items from the table?", "Confirm Clear", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
        }
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>>  search input <<<<<<<<<<<<<<<<//
    private void filterTable(String searchText) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);

        if (searchText.trim().isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>> Quantity Color if low or high <<<<<<<<<<<<<<<<//
    class QuantityCellRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            try {
                int quantity = Integer.parseInt(value.toString());
                if (quantity >= 75) {
                    c.setBackground(Color.GREEN);
                } else if (quantity >= 51) {
                    c.setBackground(Color.YELLOW);
                } else {
                    c.setBackground(Color.RED);
                }
                c.setForeground(Color.BLACK);
            } catch (NumberFormatException e) {
                c.setBackground(Color.WHITE);
                c.setForeground(Color.BLACK);
            }
            return c;
        }
    }
}
