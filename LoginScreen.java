package view;

import javax.swing.*;

import config.CustomLib;
import model.Transaction;
import model.User;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LoginScreen extends JFrame {
    public LoginScreen(ArrayList<User> users, ArrayList<Transaction> transactions) {
        // Mendapatkan ukuran layar
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setTitle("Smart ATM - Login");
        setSize(screenSize.width, screenSize.height); // Ukuran frame sesuai layar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Manual layout

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(screenSize.width, screenSize.height); // Menyesuaikan ukuran dengan frame utama
        panel.setBackground(new Color(70, 130, 180)); // Warna biru lembut

        // Label utama
        JLabel welcomeLabel = new JLabel("<html>Welcome to<br><b>SmartATM</b></html>");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Ukuran font besar
        welcomeLabel.setBounds(60, 20, 300, 60); // Posisi label
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setForeground(Color.WHITE); // Warna teks putih
        panel.add(welcomeLabel);

        // Label dan field untuk nomor rekening
        JLabel accountLabel = new JLabel("No Rekening");
        accountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        accountLabel.setBounds(40, 80, 120, 20);
        accountLabel.setForeground(Color.WHITE); // Warna teks putih
        panel.add(accountLabel);

        JTextField accountField = new JTextField();
        accountField.setFont(new Font("Arial", Font.PLAIN, 14));
        accountField.setBackground(Color.WHITE);
        accountField.setForeground(Color.GRAY);
        accountField.setBounds(40, 110, 320, 40);
        accountField.setMargin(new Insets(10, 10, 10, 10)); // Padding dalam
        accountField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Border putih
        panel.add(accountField);

        // Label dan field untuk password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setBounds(40, 170, 120, 20);
        passwordLabel.setForeground(Color.WHITE); 
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.GRAY);
        passwordField.setBounds(40, 200, 320, 40);
        passwordField.setMargin(new Insets(10, 10, 10, 10)); 
        passwordField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); 
        panel.add(passwordField);

        // Tombol masuk
        JButton loginButton = new JButton("Masuk");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(30, 144, 255)); 
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(150, 270, 100, 30);
        panel.add(loginButton);

        // Label untuk registrasi
        JLabel registerLabel = new JLabel("<html><span style='color:darkgray;'>Belum punya akun? </span>" + "<span style='color:#0000FF;'>Daftar disini.</span></html>");
        registerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        registerLabel.setBounds(110, 320, 200, 25);
        panel.add(registerLabel);

        add(panel);

        // Listener untuk resize frame
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                int frameWidth = getWidth();
                int frameHeight = getHeight();

                // Update ukuran dan posisi elemen berdasarkan ukuran frame
                welcomeLabel.setBounds(frameWidth / 2 - 150, frameHeight / 10, 300, 60);
                accountLabel.setBounds(frameWidth / 2 - 160, frameHeight / 4, 120, 20);
                accountField.setBounds(frameWidth / 2 - 160, frameHeight / 4 + 30, 320, 40);
                passwordLabel.setBounds(frameWidth / 2 - 160, frameHeight / 4 + 90, 120, 20);
                passwordField.setBounds(frameWidth / 2 - 160, frameHeight / 4 + 120, 320, 40);
                loginButton.setBounds(frameWidth / 2 - 50, frameHeight / 4 + 180, 100, 30);
                registerLabel.setBounds(frameWidth / 2 - 100, frameHeight / 4 + 230, 200, 25);
            }
        });

        // ActionListener untuk tombol masuk
        loginButton.addActionListener(e -> {
            String accountNumber = accountField.getText();
            String password = new String(passwordField.getPassword());
            boolean isLoggedIn = false;

            // System.out.println(accountNumber + " " + password);

            if (!(accountNumber.isEmpty() || password.isEmpty())) {
                for (User user : users) {
                    if (user.getAccountNumber() == Integer.parseInt(accountNumber) && user.getPassword().equals(password)) {
                        CustomLib.playSound("assets/success.wav");
                        JOptionPane.showMessageDialog(LoginScreen.this, "Login Berhasil!");
                        isLoggedIn = true; // Login berhasil
                        dispose();
                        // Tampilkan menu utama setelah login berhasil
                        new MenuScreen(user, transactions);
                        break; // Keluar dari loop karena login berhasil
                    }
                }
            }

            if (!isLoggedIn) {
                CustomLib.playSound("assets/fail.wav");
                JOptionPane.showMessageDialog(LoginScreen.this, "No Rekening atau Password salah!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Event untuk klik pada "Daftar disini"
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new RegisterScreen(users, LoginScreen.this); // Menampilkan frame pendaftaran
            }
        });
        
        setVisible(true);
    }
}
