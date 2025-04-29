package surenatalaga;


/*  Reeeeey Prject

*/

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import javax.swing.RowFilter;

public class UserInterface extends JFrame {
    private JFrame loginFrame;
    private JPanel mainPanel;
    private JLabel userLabel, titleLabel;
    private JTextField searchField;
    private JTable itemTable;
    private JScrollPane scrollPane;
    private JButton signOutButton, searchButton, clearButton;
    
    
    public UserInterface(JFrame loginFrame, String username, String role) {
        this.loginFrame = loginFrame; 
        initComponents(); 
        userLabel.setText(username + " (" + role + ")"); // >>>>>>>>>>>>>>>>>>> DISPLAY THE ROLE <<<<<<<<<<<<<<<<< //
    }
    
    private void initComponents() {
        
        mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(80, 80, 80)); // >>>>>>>>>>>>>>>>>>>>>> BACKGROUND COLOR RGB <<<<<<<<<<<<<<<<<<<< //
        
        // >>>>>>>>>>>>>>>>>>>>> JFRAME PROPERTIES <<<<<<<<<<<<<<<<<<<< // 
        setTitle("Mart Ease");
        setSize(850, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        // >>>>>>>>>>>>>>>>>>>>>>>> CREATE THE TABLE <<<<<<<<<<<<<<<<<<<<< //
        String[] columnNames = {"Item Name", "Quantity", "ID Number", "Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        itemTable = new JTable(model);
        itemTable.setRowHeight(25);
        itemTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        itemTable.setBackground(new Color(50, 50, 50));
        itemTable.setForeground(Color.WHITE);
        
        // >>>>>>>>>>>>>>>>>>>> TABLE TO SCROLL PANE <<<<<<<<<<<<<<<<<<<<<< //
        scrollPane = new JScrollPane(itemTable);
        scrollPane.setBounds(20, 100, 810, 250);
        scrollPane.setBackground(Color.BLACK);
        mainPanel.add(scrollPane);
        
        // <>>>>>>>>>>>>>>>>>>>>>> USER LABEL >>>>>>>>>>>>>>>>>>>>>>>>>> //
        userLabel = new JLabel("User: Default (USER)");
        userLabel.setBounds(450, 20, 300, 30);
        userLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        userLabel.setForeground(Color.WHITE);
        mainPanel.add(userLabel);
        
        // >>>>>>>>>>>>>>>>>>>>>> SIGN OUT BUTTON >>>>>>>>>>>>>>>>>>>>>> //
        signOutButton = new JButton("Sign Out");
        signOutButton.setBounds(720, 20, 100, 30);
        signOutButton.setFocusable(false);
        signOutButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        signOutButton.setBackground(Color.RED);
        signOutButton.setForeground(Color.WHITE);
        signOutButton.addActionListener(evt -> {
            int option = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to sign out?", "Sign Out", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                this.setVisible(false); 
                if (loginFrame != null) {
                    loginFrame.setVisible(true); 
                } else {
                    System.exit(0); 
                }
            }
        });
        mainPanel.add(signOutButton);
        
        // >>>>>>>>>>>>>>>>>>>> TITLE LABEL <<<<<<<<<<<<<<<<<<<<<<<<< //
        titleLabel = new JLabel("Mart Ease");
        titleLabel.setBounds(20, 70, 300, 25);
        titleLabel.setFont(new Font("Rockwell", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel);
        
        // >>>>>>>>>>>>>>>>>> SEARCH BUTTON <<<<<<<<<<<<<<<<<<<<<< //
        searchButton = new JButton("Search");
        searchButton.setBounds(450, 70, 90, 25);
        searchButton.setFocusable(false);
        searchButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchButton.setBackground(Color.BLACK);
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(evt -> filterTable(searchField.getText()));
        mainPanel.add(searchButton);
        
        // >>>>>>>>>>>>>>>>>>>>>>>>>> SEARCH TEXT FIELD <<<<<<<<<<<<<<<< //
        searchField = new JTextField();
        searchField.setBounds(550, 70, 150, 25);
        searchField.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        searchField.setBackground(Color.LIGHT_GRAY);
        searchField.setForeground(Color.BLACK);
        mainPanel.add(searchField);
        
        // >>>>>>>>>>>>>>>>>> Clear Search Button <<<<<<<<<<<<<<<<<<<<<<<<<<
        clearButton = new JButton("Clear");
        clearButton.setBounds(710, 70, 90, 25);
        clearButton.setFocusable(false);
        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        clearButton.setBackground(Color.BLACK);
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(evt -> {
            searchField.setText("");
            filterTable(""); 
        });
        mainPanel.add(clearButton);
        
       
        add(mainPanel);
    }
    
    
    private void filterTable(String searchText) {
        DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        itemTable.setRowSorter(sorter);
        
        if (searchText.trim().isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }
        
       
        if (itemTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Item not found!", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
