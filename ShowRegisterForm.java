import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class ShowRegisterForm {
    public ShowRegisterForm(ArrayList<User> users, SmartAtmDatabase database, JFrame parentFrame){
        JFrame registerFrame = new JFrame("Form Pendaftaran");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        registerFrame.setSize(screenSize.width, screenSize.height);
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setLayout(null);

        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(null);
        registerPanel.setSize(screenSize.width, screenSize.height);
        registerPanel.setBackground(new Color(70, 130, 180));

        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, screenSize.width, 75);
        headerPanel.setBackground(new Color(60, 120, 180));

        JLabel headerLabel = new JLabel("DAFTAR");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 36));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(headerLabel);

        registerPanel.add(headerPanel);

        JLabel nameLabel = new JLabel("Nama Lengkap");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setBounds(screenSize.width / 2 - 160, 140, 120, 20);
        nameLabel.setForeground(Color.WHITE);
        registerPanel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(screenSize.width / 2 - 160, 170, 320, 40);
        registerPanel.add(nameField);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usernameLabel.setBounds(screenSize.width / 2 - 160, 230, 120, 20);
        usernameLabel.setForeground(Color.WHITE);
        registerPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(screenSize.width / 2 - 160, 260, 320, 40);
        registerPanel.add(usernameField);

        JLabel rekeningLabel = new JLabel("No Rekening");
        rekeningLabel.setFont(new Font("Arial", Font.BOLD, 14));
        rekeningLabel.setBounds(screenSize.width / 2 - 160, 320, 120, 20);
        rekeningLabel.setForeground(Color.WHITE);
        registerPanel.add(rekeningLabel);

        JTextField rekeningField = new JTextField();
        rekeningField.setBounds(screenSize.width / 2 - 160, 350, 320, 40);
        registerPanel.add(rekeningField);

        JLabel namaBankLabel = new JLabel("Nama Bank");
        namaBankLabel.setFont(new Font("Arial", Font.BOLD, 14));
        namaBankLabel.setBounds(screenSize.width / 2 - 160, 420, 120, 20);
        namaBankLabel.setForeground(Color.WHITE);
        registerPanel.add(namaBankLabel);

        JTextField namaBankField = new JTextField();
        namaBankField.setBounds(screenSize.width / 2 - 160, 450, 320, 40);
        registerPanel.add(namaBankField);

        JLabel passwordLabel = new JLabel("PIN");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setBounds(screenSize.width / 2 - 160, 520, 120, 20);
        passwordLabel.setForeground(Color.WHITE);
        registerPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(screenSize.width / 2 - 160, 550, 320, 40);
        registerPanel.add(passwordField);

        JButton registerButton = new JButton("Daftar");
        registerButton.setBounds(screenSize.width / 2 - 60, 630, 120, 40);
        registerButton.setBackground(new Color(30, 144, 255));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerPanel.add(registerButton);

        registerButton.addActionListener(e -> {
            String namaLengkap = nameField.getText();
            String username = usernameField.getText();
            String noRekening = rekeningField.getText();
            String namaBank = namaBankField.getText();
            String password = new String(passwordField.getPassword());

            if (namaLengkap.isEmpty() || username.isEmpty() || noRekening.isEmpty() || password.isEmpty() || namaBank.isEmpty()) {
                JOptionPane.showMessageDialog(registerFrame, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                for (User user : users) {
                    if(user.getAccountNumber() == Integer.parseInt(noRekening)) {
                        JOptionPane.showMessageDialog(registerFrame, "Nomor rekening sudah terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                database.insertUser(Integer.parseInt(noRekening), namaLengkap, username, password, namaBank, 0);

                parentFrame.dispose();
                new App();

                registerFrame.dispose();
                JOptionPane.showMessageDialog(registerFrame, "Pendaftaran berhasil!");
            }
        });

        registerFrame.add(registerPanel);
        registerFrame.setVisible(true);
    }
}
