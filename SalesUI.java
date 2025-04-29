package surenatalaga;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalesUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField startDateField, endDateField;
    private JLabel dateLabel, timeLabel, totalRevenueLabel, totalOrdersLabel;

    public SalesUI() {
        setTitle("Sales System");
        setSize(1150, 541); // New size as you requested
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Date and Time Labels
        dateLabel = new JLabel("Date " + new SimpleDateFormat("M/d/yyyy").format(new Date()));
        dateLabel.setBounds(20, 10, 150, 20);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        dateLabel.setForeground(new Color(0, 200, 0)); // Green date

        timeLabel = new JLabel("Time " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        timeLabel.setBounds(180, 10, 150, 20);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        timeLabel.setForeground(new Color(220, 20, 60)); // Red time

        add(dateLabel);
        add(timeLabel);

        // Start Date & End Date Fields
        startDateField = new JTextField();
        startDateField.setBounds(620, 10, 120, 25);
        add(startDateField);

        endDateField = new JTextField();
        endDateField.setBounds(760, 10, 120, 25);
        add(endDateField);

        JLabel startDateLabel = new JLabel("Start Date");
        startDateLabel.setBounds(560, 10, 60, 25);
        startDateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        add(startDateLabel);

        JLabel endDateLabel = new JLabel("End Date");
        endDateLabel.setBounds(700, 10, 60, 25);
        endDateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        add(endDateLabel);

        // Search Sale Button
        JButton searchSaleBtn = new JButton("Search Sale");
        searchSaleBtn.setBounds(900, 10, 110, 25);
        styleButton(searchSaleBtn);
        add(searchSaleBtn);

        // Generate Excel Button
        JButton generateExcelBtn = new JButton("Generate Excel");
        generateExcelBtn.setBounds(1020, 10, 110, 25);
        styleButton(generateExcelBtn);
        add(generateExcelBtn);

        // Revenue and Orders Panels
        JPanel revenuePanel = new JPanel(null);
        revenuePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        revenuePanel.setBounds(20, 60, 200, 100);
        add(revenuePanel);

        JLabel revenueTitle = new JLabel("Total Revenue");
        revenueTitle.setBounds(50, 10, 120, 20);
        revenueTitle.setFont(new Font("Arial", Font.BOLD, 14));
        revenuePanel.add(revenueTitle);

        totalRevenueLabel = new JLabel("₱ 100.50");
        totalRevenueLabel.setBounds(70, 40, 100, 20);
        totalRevenueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        revenuePanel.add(totalRevenueLabel);

        JPanel ordersPanel = new JPanel(null);
        ordersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ordersPanel.setBounds(240, 60, 200, 100);
        add(ordersPanel);

        JLabel ordersTitle = new JLabel("Total Orders");
        ordersTitle.setBounds(60, 10, 120, 20);
        ordersTitle.setFont(new Font("Arial", Font.BOLD, 14));
        ordersPanel.add(ordersTitle);

        totalOrdersLabel = new JLabel("300");
        totalOrdersLabel.setBounds(85, 40, 100, 20);
        totalOrdersLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ordersPanel.add(totalOrdersLabel);

        // 2x2 Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttonPanel.setBounds(20, 180, 420, 130);
        add(buttonPanel);

        JButton addSaleBtn = new JButton("Add Sale");
        styleButton(addSaleBtn);
        buttonPanel.add(addSaleBtn);

        JButton clearBtn1 = new JButton("Clear");
        styleButton(clearBtn1);
        buttonPanel.add(clearBtn1);

        JButton clearBtn2 = new JButton("Clear");
        styleButton(clearBtn2);
        buttonPanel.add(clearBtn2);

        JButton backBtn = new JButton("Back");
        styleButton(backBtn);
        buttonPanel.add(backBtn);

        // Table
        String[] columns = {"ID", "Item Name", "Quantity", "Date", "Price"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBounds(460, 60, 660, 430);
        add(tableScroll);

        // Timer to update time every second
        new Timer(1000, e -> {
            timeLabel.setText("Time " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }).start();

        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
    }

    public static void main(String[] args) {
        new SalesUI();
    }
}
