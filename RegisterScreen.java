package view;

import javax.swing.*;
import model.User;
import repository.TransactionDB;
import repository.UserDB;
import java.awt.*;
import java.util.ArrayList;

public class RegisterScreen extends JFrame {
    public RegisterScreen(ArrayList<User> users, JFrame parentFrame) {
        setTitle("Smart ATM - Daftar");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setSize(screenSize.width, screenSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

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

        // Radio Buttons for Bank Selection
        JRadioButton mandiriButton = new JRadioButton("Mandiri");
        mandiriButton.setBounds(screenSize.width / 2 - 160, 450, 100, 30);
        mandiriButton.setBackground(new Color(70, 130, 180));
        mandiriButton.setForeground(Color.WHITE);

        JRadioButton bniButton = new JRadioButton("BNI");
        bniButton.setBounds(screenSize.width / 2 - 50, 450, 100, 30);
        bniButton.setBackground(new Color(70, 130, 180));
        bniButton.setForeground(Color.WHITE);

        JRadioButton briButton = new JRadioButton("BRI");
        briButton.setBounds(screenSize.width / 2 + 60, 450, 100, 30);
        briButton.setBackground(new Color(70, 130, 180));
        briButton.setForeground(Color.WHITE);

        ButtonGroup bankGroup = new ButtonGroup();
        bankGroup.add(mandiriButton);
        bankGroup.add(bniButton);
        bankGroup.add(briButton);

        registerPanel.add(mandiriButton);
        registerPanel.add(bniButton);
        registerPanel.add(briButton);

        JLabel passwordLabel = new JLabel("PIN");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setBounds(screenSize.width / 2 - 160, 520, 120, 20);
        passwordLabel.setForeground(Color.WHITE);
        registerPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(screenSize.width / 2 - 160, 550, 320, 40);
        registerPanel.add(passwordField);

        JButton backButton = new JButton("Kembali");
        backButton.setBounds(screenSize.width / 2 - 140, 630, 120, 40);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerPanel.add(backButton);

        backButton.addActionListener(e -> dispose());

        JButton registerButton = new JButton("Daftar");
        registerButton.setBounds(screenSize.width / 2, 630, 120, 40);
        registerButton.setBackground(new Color(30, 144, 255));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerPanel.add(registerButton);

        registerButton.addActionListener(e -> {
            String namaLengkap = nameField.getText();
            String username = usernameField.getText();
            String noRekening = rekeningField.getText();
            String password = new String(passwordField.getPassword());
            String namaBank = "";

            if (mandiriButton.isSelected()) {
                namaBank = "mandiri";
            } else if (bniButton.isSelected()) {
                namaBank = "bni";
            } else if (briButton.isSelected()) {
                namaBank = "bri";
            }

            if (namaLengkap.isEmpty() || username.isEmpty() || noRekening.isEmpty() || password.isEmpty() || namaBank.isEmpty()) {
                JOptionPane.showMessageDialog(RegisterScreen.this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                for (User user : users) {
                    if (user.getAccountNumber() == Integer.parseInt(noRekening)) {
                        JOptionPane.showMessageDialog(RegisterScreen.this, "Nomor rekening sudah terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                UserDB.insertUser(Integer.parseInt(noRekening), namaLengkap, username, password, namaBank, 0);

                parentFrame.dispose();
                new LoginScreen(UserDB.getUsers(), TransactionDB.getTransactions());

                dispose();
                JOptionPane.showMessageDialog(RegisterScreen.this, "Pendaftaran berhasil!");
            }
        });

        add(registerPanel);
        setVisible(true);
    }
}
