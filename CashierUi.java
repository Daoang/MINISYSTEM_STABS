package surenatalaga;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CashierUI extends JFrame {
    private JTable tableInventory, tableCart;
    private JTextField txtSearch, txtQuantity, txtDiscount, txtCash;
    private JLabel lblDate, lblTime, lblTotal, lblChange;
    private DefaultTableModel modelInventory, modelCart;
    private double totalAmount = 0;

    public CashierUI(String username, String role) {
        JLabel lblCashier = new JLabel(username + " (" + role + ")");
        setTitle("Cashier System");
        setSize(1209, 697);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));

        lblDate = new JLabel();
        lblDate.setFont(new Font("Arial", Font.BOLD, 24));
        lblDate.setForeground(Color.GREEN);
        lblDate.setBounds(80, 20, 200, 30);
        add(lblDate);

        lblTime = new JLabel();
        lblTime.setFont(new Font("Arial", Font.BOLD, 24));
        lblTime.setForeground(Color.RED);
        lblTime.setBounds(360, 20, 200, 30);
        add(lblTime);

        JLabel lblDateText = new JLabel("Date:");
        lblDateText.setFont(new Font("Arial", Font.BOLD, 24));
        lblDateText.setBounds(10, 20, 80, 30);
        add(lblDateText);

        JLabel lblTimeText = new JLabel("Time:");
        lblTimeText.setFont(new Font("Arial", Font.BOLD, 24));
        lblTimeText.setBounds(280, 20, 80, 30);
        add(lblTimeText);

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                lblDate.setText(new SimpleDateFormat("M/dd/yyyy").format(new Date()));
                lblTime.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }, 0, 1000);

        modelInventory = new DefaultTableModel(new String[]{"Item Name", "Quantity", "Price", "Type"}, 0);
        tableInventory = new JTable(modelInventory);
        JScrollPane spInventory = new JScrollPane(tableInventory);
        spInventory.setBounds(10, 110, 600, 400);
        add(spInventory);

        modelCart = new DefaultTableModel(new String[]{"Item Name", "Quantity", "Price", "Type"}, 0);
        tableCart = new JTable(modelCart);
        JScrollPane spCart = new JScrollPane(tableCart);
        spCart.setBounds(630, 110, 550, 400);
        add(spCart);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(350, 75, 90, 30);
        btnSearch.setBackground(Color.BLACK);
        btnSearch.setForeground(Color.WHITE);
        add(btnSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(450, 75, 160, 30);
        add(txtSearch);

        JLabel lblMartEase = new JLabel("MartEase");
        lblMartEase.setFont(new Font("Arial", Font.BOLD, 20));
        lblMartEase.setBounds(10, 75, 100, 30);
        add(lblMartEase);

        lblCashier.setFont(new Font("Arial", Font.BOLD, 20));
        lblCashier.setBounds(830, 75, 200, 30);
        add(lblCashier);

        JButton btnLogout = new JButton("Log Out");
        btnLogout.setBounds(1080, 60, 100, 40);
        btnLogout.setBackground(Color.RED);
        btnLogout.setForeground(Color.WHITE);
        add(btnLogout);

        JPanel quantityPanel = new JPanel(null);
        quantityPanel.setBounds(10, 520, 600, 130);
        quantityPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        quantityPanel.setBackground(new Color(245, 245, 245));
        add(quantityPanel);

        JLabel lblQty = new JLabel("Enter Quantity");
        lblQty.setFont(new Font("Arial", Font.BOLD, 16));
        lblQty.setBounds(20, 50, 150, 30);
        quantityPanel.add(lblQty);

        txtQuantity = new JTextField();
        txtQuantity.setBounds(160, 50, 150, 30);
        quantityPanel.add(txtQuantity);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(330, 45, 150, 40);
        btnAdd.setBackground(Color.BLACK);
        btnAdd.setForeground(Color.WHITE);
        quantityPanel.add(btnAdd);

        JPanel salePanel = new JPanel(null);
        salePanel.setBounds(630, 520, 550, 130);
        salePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        salePanel.setBackground(new Color(245, 245, 245));
        add(salePanel);

        JLabel lblTotalText = new JLabel("Total");
        lblTotalText.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotalText.setBounds(20, 10, 100, 30);
        salePanel.add(lblTotalText);

        lblTotal = new JLabel("Total");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotal.setForeground(Color.RED);
        lblTotal.setBounds(80, 10, 200, 30);
        salePanel.add(lblTotal);

        JLabel lblDiscount = new JLabel("Discount");
        lblDiscount.setFont(new Font("Arial", Font.BOLD, 16));
        lblDiscount.setBounds(20, 40, 100, 30);
        salePanel.add(lblDiscount);

        txtDiscount = new JTextField();
        txtDiscount.setBounds(130, 40, 150, 30);
        salePanel.add(txtDiscount);

        JLabel lblCash = new JLabel("Cash");
        lblCash.setFont(new Font("Arial", Font.BOLD, 16));
        lblCash.setBounds(20, 70, 100, 30);
        salePanel.add(lblCash);

        txtCash = new JTextField();
        txtCash.setBounds(130, 70, 150, 30);
        salePanel.add(txtCash);

        JButton btnSale = new JButton("Add Sale");
        btnSale.setBounds(300, 70, 130, 30);
        btnSale.setBackground(Color.BLACK);
        btnSale.setForeground(Color.WHITE);
        salePanel.add(btnSale);

        JButton btnNew = new JButton("Apply Discount");
        btnNew.setBounds(300, 38, 130, 30);
        btnNew.setBackground(Color.BLACK);
        btnNew.setForeground(Color.WHITE);
        salePanel.add(btnNew);

        lblChange = new JLabel("");
        lblChange.setFont(new Font("Arial", Font.BOLD, 16));
        lblChange.setBounds(20, 100, 500, 30);
        salePanel.add(lblChange);

        // >>>>>>>>>> NEW Buttons Added Here <<<<<<<<<<
        JButton btnEdit = new JButton("Edit Selected");
        btnEdit.setBounds(300, 5, 130, 30);
        btnEdit.setBackground(Color.BLACK);
        btnEdit.setForeground(Color.WHITE);
        salePanel.add(btnEdit);

        JButton btnDelete = new JButton("Delete Selected");
        btnDelete.setBounds(440, 5, 130, 30);
        btnDelete.setBackground(Color.BLACK);
        btnDelete.setForeground(Color.WHITE);
        salePanel.add(btnDelete);

        JButton btnClearCart = new JButton("Clear Cart");
        btnClearCart.setBounds(440, 70, 100, 30);
        btnClearCart.setBackground(Color.BLACK);
        btnClearCart.setForeground(Color.WHITE);
        salePanel.add(btnClearCart);

        addSampleInventory();

        tableInventory.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tableInventory.getSelectedRow();
                txtQuantity.setText("1");
            }
        });

        btnAdd.addActionListener(e -> {
            int row = tableInventory.getSelectedRow();
            if (row >= 0) {
                String name = modelInventory.getValueAt(row, 0).toString();
                int qty = Integer.parseInt(txtQuantity.getText());
                int availableQty = Integer.parseInt(modelInventory.getValueAt(row, 1).toString());
                if (qty > availableQty) {
                    JOptionPane.showMessageDialog(this, "Not enough stock.");
                    return;
                }
                double price = Double.parseDouble(modelInventory.getValueAt(row, 2).toString());
                String type = modelInventory.getValueAt(row, 3).toString();
                double subtotal = price * qty;
                totalAmount += subtotal;
                modelCart.addRow(new Object[]{name, qty, subtotal, type});
                lblTotal.setText("₱" + String.format("%.2f", totalAmount));
                modelInventory.setValueAt(availableQty - qty, row, 1);
            }
        });

        btnNew.addActionListener(e -> {
            double discount = 0;
            if (!txtDiscount.getText().isEmpty()) {
                String dis = txtDiscount.getText().trim();
                if (dis.endsWith("%")) {
                    double percent = Double.parseDouble(dis.replace("%", ""));
                    discount = (totalAmount * percent) / 100;
                } else {
                    discount = Double.parseDouble(dis);
                }
            }
            double finalTotal = totalAmount - discount;
            lblTotal.setText("₱" + String.format("%.2f", finalTotal));
        });

        btnSale.addActionListener(e -> {
            double discount = 0;
            if (!txtDiscount.getText().isEmpty()) {
                String dis = txtDiscount.getText().trim();
                if (dis.endsWith("%")) {
                    double percent = Double.parseDouble(dis.replace("%", ""));
                    discount = (totalAmount * percent) / 100;
                } else {
                    discount = Double.parseDouble(dis);
                }
            }
            double cash = txtCash.getText().isEmpty() ? 0 : Double.parseDouble(txtCash.getText());
            double finalTotal = totalAmount - discount;
            if (cash >= finalTotal) {
                double change = cash - finalTotal;
                String message = String.format(
                        "Sale Processed Successfully!\n\n" +
                                "Total: ₱%.2f\n" +
                                "Discount: ₱%.2f\n" +
                                "Final Total: ₱%.2f\n" +
                                "Cash: ₱%.2f\n" +
                                "Change: ₱%.2f",
                        totalAmount, discount, finalTotal, cash, change
                );
                JOptionPane.showMessageDialog(this, message, "Sale Complete", JOptionPane.INFORMATION_MESSAGE);
                generateReceipt();
                lblChange.setText("");
                totalAmount = 0;
                lblTotal.setText("Total");
                modelCart.setRowCount(0);
                txtCash.setText("");
                txtDiscount.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient Cash.");
            }
        });

        btnSearch.addActionListener(e -> {
            String keyword = txtSearch.getText().toLowerCase();
            filterInventory(keyword);
        });

        btnLogout.addActionListener(e -> {
            dispose();
            System.exit(0);
        });

        // >>>>>>> NEW Button Functions >>>>>>>
        btnEdit.addActionListener(e -> {
            int selectedRow = tableCart.getSelectedRow();
            if (selectedRow >= 0) {
                String newQtyStr = JOptionPane.showInputDialog(this, "Enter new quantity:");
                if (newQtyStr != null && !newQtyStr.isEmpty()) {
                    try {
                        int newQty = Integer.parseInt(newQtyStr);
                        if (newQty <= 0) {
                            JOptionPane.showMessageDialog(this, "Quantity must be greater than 0.");
                            return;
                        }
                        double pricePerItem = Double.parseDouble(modelCart.getValueAt(selectedRow, 2).toString()) / Integer.parseInt(modelCart.getValueAt(selectedRow, 1).toString());
                        double newSubtotal = pricePerItem * newQty;
                        totalAmount -= Double.parseDouble(modelCart.getValueAt(selectedRow, 2).toString());
                        totalAmount += newSubtotal;
                        modelCart.setValueAt(newQty, selectedRow, 1);
                        modelCart.setValueAt(newSubtotal, selectedRow, 2);
                        lblTotal.setText("₱" + String.format("%.2f", totalAmount));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid number format.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select an item to edit.");
            }
        });

        btnDelete.addActionListener(e -> {
            int selectedRow = tableCart.getSelectedRow();
            if (selectedRow >= 0) {
                double subtotal = Double.parseDouble(modelCart.getValueAt(selectedRow, 2).toString());
                totalAmount -= subtotal;
                modelCart.removeRow(selectedRow);
                lblTotal.setText("₱" + String.format("%.2f", totalAmount));
            } else {
                JOptionPane.showMessageDialog(this, "Please select an item to delete.");
            }
        });

        btnClearCart.addActionListener(e -> {
            modelCart.setRowCount(0);
            totalAmount = 0;
            lblTotal.setText("Total");
        });

        setVisible(true);
    }

    private void addSampleInventory() {
        modelInventory.setRowCount(0);
        modelInventory.addRow(new Object[]{"Pancit Canton Original", 500, 25.00, "Food"});
        modelInventory.addRow(new Object[]{"555 Tuna", 50, 30.00, "Food"});
        modelInventory.addRow(new Object[]{"Soap", 70, 40.00, "Hygiene products"});
        modelInventory.addRow(new Object[]{"Alfonso", 1000, 135.00, "Alcohol"});
    }

    private void filterInventory(String keyword) {
        addSampleInventory();
        if (!keyword.isEmpty()) {
            for (int i = modelInventory.getRowCount() - 1; i >= 0; i--) {
                String item = modelInventory.getValueAt(i, 0).toString().toLowerCase();
                if (!item.contains(keyword)) {
                    modelInventory.removeRow(i);
                }
            }
        }
    }

    private void generateReceipt() {
        System.out.println("Receipt generated...");
    }
}
