package surenatalaga;

/*  Reeeeey Prject

*/
import java.awt.*;
import javax.swing.*;
import surenatalaga.UserInterface;

public class loginnew {
    public static Object pesoFormat;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new loginnew(); 
            }
        });
    }

    public loginnew() {
        // >>>>>>>>>>>>>>>>>>>>>>> FRAME <<<<<<<<<<<<<<<<<<<<< //  
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridBagLayout()); 

       
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // >>>>>>>>>>>>>>>>>>>>>>> TITLE LABEL <<<<<<<<<<<<<<<<<<<<< //  
        JLabel titleLabel = new JLabel("Mart Ease", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20)); 
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.gridwidth = 2; 
        frame.add(titleLabel, gbc);

        // >>>>>>>>>>>>>>>>>>>>>>> USER NAME LABEL <<<<<<<<<<<<<<<<<<<<< //  
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        frame.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(usernameField, gbc);

        // >>>>>>>>>>>>>>>>>>>>>>> PASSOWRD LABEL <<<<<<<<<<<<<<<<<<<<< // 
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(passwordField, gbc);

        // <<<<<<<<<<<<<<<<<<<<<<<< COMBO BOX ROLE >>>>>>>>>>>>>>>>>>>>>>>>>>
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(roleLabel, gbc);

        String[] roles = {"ADMINISTRATOR", "USER"};
        JComboBox<String> roleDropdown = new JComboBox<>(roles);
        roleDropdown.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        frame.add(roleDropdown, gbc);

        // >>>>>>>>>>>>>>>>>>>>>>>>> LOG IN BUTTON <<<<<<<<<<<<<<<<<<<<<< //
        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; 
        frame.add(loginButton, gbc);

        // >>>>>>>>>>>>>>>>>>>> LOG IN BUTTON <<<<<<<<<<<<<<<<<<<<<<<<<<<< //
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String role = (String) roleDropdown.getSelectedItem();

            //  >>>>>>>>>>>>>>>>>>> LOG IN USER, PASS, AND ROLE  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< //
            if (username.equals("Admin") && password.equals("Admin") && role.equals("ADMINISTRATOR")) {
                frame.dispose(); 

                SwingUtilities.invokeLater(() -> {
                    JFrame loginFrame = new JFrame(); 
                    new stabs(loginFrame, username, role).setVisible(true);
                });
            } else if (username.equals("Rey") && password.equals("Pogi") && role.equals("USER")) {
                frame.dispose(); 
                
                SwingUtilities.invokeLater(() -> {
                    JFrame loginFrame = new JFrame();
                    new UserInterface(loginFrame, username, role).setVisible(true);
                });
            } else {
                JOptionPane.showMessageDialog(frame, "Password and Username is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> BACKGROUND COLOR <<<<<<<<<<<<<<<<<<<<<<<<<< // 
        frame.getContentPane().setBackground(Color.DARK_GRAY); 
        titleLabel.setForeground(Color.WHITE); 
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        roleLabel.setForeground(Color.WHITE);
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true); 
    }
}
