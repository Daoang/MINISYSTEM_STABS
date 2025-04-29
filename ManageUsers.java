package surenatalaga;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class ManageUsers extends JFrame {
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> DECLARATIONS <<<<<<<<<<<<<<<<<<<<<<<< //
    private JTextField usernameField, fullNameField, passwordField, emailField;
    private JComboBox<String> accessComboBox;
    private JButton createUserButton, updateUserButton, deleteUserButton, backButton;
    private JTable userTable;
    private DefaultTableModel tableModel;
    private Color darkBackground = new Color(40, 40, 40);
    private Color lightGray = new Color(180, 180, 180);
    private Color inputBackground = new Color(140, 140, 140);
    private JFrame parentFrame; 

    public ManageUsers(JFrame parentFrame) {
        this.parentFrame = parentFrame; 

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Frame <<<<<<<<<<<<<<<<<<<<<<<< //
        setTitle("Manage Users");
        setSize(1100, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLayout(null);
        getContentPane().setBackground(darkBackground);

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Panels with borders <<<<<<<<<<<<<<<<<<<<<<<< //
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBounds(10, 10, 440, 400);
        leftPanel.setBackground(darkBackground);
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBounds(460, 10, 620, 400);
        rightPanel.setBackground(darkBackground);
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Left panel <<<<<<<<<<<<<<<<<<<<<<<< //
        JLabel titleLabel = new JLabel("MANAGE USERS");
        titleLabel.setBounds(20, 10, 150, 20);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        leftPanel.add(titleLabel);

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Username <<<<<<<<<<<<<<<<<<<<<<<< //
        JLabel usernameLabel = new JLabel("USERNAME:");
        usernameLabel.setBounds(30, 60, 150, 25);
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        leftPanel.add(usernameLabel);

        usernameField = createStyledTextField();
        usernameField.setBounds(195, 60, 220, 25);
        leftPanel.add(usernameField);

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Full Name <<<<<<<<<<<<<<<<<<<<<<<< //
        JLabel fullNameLabel = new JLabel("FULL NAME:");
        fullNameLabel.setBounds(30, 100, 150, 25);
        fullNameLabel.setForeground(Color.WHITE);
        fullNameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        leftPanel.add(fullNameLabel);

        fullNameField = createStyledTextField();
        fullNameField.setBounds(195, 100, 220, 25);
        leftPanel.add(fullNameField);

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Password <<<<<<<<<<<<<<<<<<<<<<<< //
        JLabel passwordLabel = new JLabel("PASSWORD:");
        passwordLabel.setBounds(30, 140, 150, 25);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        leftPanel.add(passwordLabel);

        passwordField = createStyledTextField();
        passwordField.setBounds(195, 140, 220, 25);
        leftPanel.add(passwordField);

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Email <<<<<<<<<<<<<<<<<<<<<<<< //
        JLabel emailLabel = new JLabel("E-MAIL:");
        emailLabel.setBounds(30, 180, 150, 25);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 12));
        leftPanel.add(emailLabel);

        emailField = createStyledTextField();
        emailField.setBounds(195, 180, 220, 25);
        leftPanel.add(emailField);

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Access Level <<<<<<<<<<<<<<<<<<<<<<<< //
        JLabel accessLabel = new JLabel("ACCESS:");
        accessLabel.setBounds(30, 220, 150, 25);
        accessLabel.setForeground(Color.WHITE);
        accessLabel.setFont(new Font("Arial", Font.BOLD, 12));
        leftPanel.add(accessLabel);

        accessComboBox = new JComboBox<>(new String[]{"Dieser", "Admin", "User"});
        accessComboBox.setBounds(195, 220, 220, 25);
        accessComboBox.setBackground(inputBackground);
        accessComboBox.setForeground(Color.WHITE);
        accessComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
        leftPanel.add(accessComboBox);

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Buttons <<<<<<<<<<<<<<<<<<<<<<<< //
        createUserButton = createStyledButton("ADD USER", 30, 280, 140, 30, false);
        leftPanel.add(createUserButton);

        updateUserButton = createStyledButton("UPDATE USER", 195, 280, 140, 30, false);
        leftPanel.add(updateUserButton);

        JLabel deleteLabel = new JLabel("Select from table to delete");
        deleteLabel.setBounds(30, 330, 180, 25);
        deleteLabel.setForeground(Color.WHITE);
        leftPanel.add(deleteLabel);

        deleteUserButton = createStyledButton("DELETE", 195, 330, 140, 30, true);
        leftPanel.add(deleteUserButton);

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> JTable <<<<<<<<<<<<<<<<<<<<<<<< //
        String[] columnNames = {"Username", "Full Name", "Password", "E-mail", "Access Level"};
        tableModel = new DefaultTableModel(columnNames, 0);
        userTable = new JTable(tableModel);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userTable.setBackground(Color.WHITE);
        userTable.setGridColor(Color.LIGHT_GRAY);
        userTable.getTableHeader().setBackground(Color.WHITE);
        userTable.getTableHeader().setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Back button <<<<<<<<<<<<<<<<<<<<<<<< //
        backButton = createStyledButton("BACK", 940, 430, 120, 45, false);

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Add components <<<<<<<<<<<<<<<<<<<<<<<< //
        add(leftPanel);
        add(rightPanel);
        add(backButton);

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Listeners <<<<<<<<<<<<<<<<<<<<<<<< //
        createUserButton.addActionListener(e -> addUser());

        updateUserButton.addActionListener(e -> showUpdatePopup());

        deleteUserButton.addActionListener(e -> deleteUser());

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Back button <<<<<<<<<<<<<<<<<<<<<<<< //
        backButton.addActionListener(e -> {
            dispose();
            if (parentFrame != null) {
                parentFrame.setVisible(true); 
            }
        });

        userTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && userTable.getSelectedRow() != -1) {
                int selectedRow = userTable.getSelectedRow();
                usernameField.setText((String) tableModel.getValueAt(selectedRow, 0));
                fullNameField.setText((String) tableModel.getValueAt(selectedRow, 1));
                passwordField.setText((String) tableModel.getValueAt(selectedRow, 2));
                emailField.setText((String) tableModel.getValueAt(selectedRow, 3));
                accessComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 4));
            }
        });

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Final setup <<<<<<<<<<<<<<<<<<<<<<<< //
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Create styled text field <<<<<<<<<<<<<<<<<<<<<<<< //
    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setBackground(inputBackground);
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        textField.setFont(new Font("Arial", Font.PLAIN, 12));
        return textField;
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Create styled button <<<<<<<<<<<<<<<<<<<<<<<< //
    private JButton createStyledButton(String text, int x, int y, int width, int height, boolean isDelete) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(isDelete ? Color.RED : Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        button.setOpaque(true);
        button.setBorderPainted(true);
        return button;
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Add new user <<<<<<<<<<<<<<<<<<<<<<<< //
    private void addUser() {
        String username = usernameField.getText();
        String fullName = fullNameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        String access = (String) accessComboBox.getSelectedItem();

        if (!username.isEmpty() && !fullName.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
            Object[] row = {username, fullName, password, email, access};
            tableModel.addRow(row);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Delete user <<<<<<<<<<<<<<<<<<<<<<<< //
    private void deleteUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Clear input fields <<<<<<<<<<<<<<<<<<<<<<<< //
    private void clearFields() {
        usernameField.setText("");
        fullNameField.setText("");
        passwordField.setText("");
        emailField.setText("");
        accessComboBox.setSelectedIndex(0);
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Popup for updating selected user <<<<<<<<<<<<<<<<<<<<<<<< //
    private void showUpdatePopup() {
        int selectedRow = userTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to update", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String currentUsername = (String) tableModel.getValueAt(selectedRow, 0);
        String currentFullName = (String) tableModel.getValueAt(selectedRow, 1);
        String currentPassword = (String) tableModel.getValueAt(selectedRow, 2);
        String currentEmail = (String) tableModel.getValueAt(selectedRow, 3);
        String currentAccess = (String) tableModel.getValueAt(selectedRow, 4);

        JTextField usernameField = new JTextField(currentUsername);
        JTextField fullNameField = new JTextField(currentFullName);
        JTextField passwordField = new JTextField(currentPassword);
        JTextField emailField = new JTextField(currentEmail);
        JComboBox<String> accessBox = new JComboBox<>(new String[]{"Dieser", "Admin", "User"});
        accessBox.setSelectedItem(currentAccess);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Full Name:"));
        panel.add(fullNameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Access Level:"));
        panel.add(accessBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Edit User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            tableModel.setValueAt(usernameField.getText(), selectedRow, 0);
            tableModel.setValueAt(fullNameField.getText(), selectedRow, 1);
            tableModel.setValueAt(passwordField.getText(), selectedRow, 2);
            tableModel.setValueAt(emailField.getText(), selectedRow, 3);
            tableModel.setValueAt(accessBox.getSelectedItem(), selectedRow, 4);
            JOptionPane.showMessageDialog(this, "User updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
