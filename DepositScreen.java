package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Transaction;
import model.User;
import repository.TransactionDB;
import repository.UserDB;

import java.awt.*;
import java.util.ArrayList;

public class DepositScreen extends JFrame {
    DepositScreen(User user, JFrame mainMenuFrame) {
        // Mendapatkan ukuran layar
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setTitle("Setor Tunai");
        setSize(screenSize.width, screenSize.height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JPanel depositPanel = new JPanel();
        depositPanel.setLayout(null);
        depositPanel.setSize(screenSize.width, screenSize.height);
        depositPanel.setBackground(new Color(107, 163, 189)); // Warna sesuai gambar

        // Label header "SETOR TUNAI"
        JLabel headerLabel = new JLabel("SETOR TUNAI");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 36));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBounds(screenSize.width / 2 - 150, 50, 300, 50);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        depositPanel.add(headerLabel);

        // Label "MASUKKAN JUMLAH UANG YANG ANDA INGINKAN"
        JLabel amountLabel = new JLabel("MASUKKAN JUMLAH UANG YANG ANDA INGINKAN");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setBounds(screenSize.width / 2 - 300, 150, 600, 30);
        amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        depositPanel.add(amountLabel);

        // Field input jumlah uang
        JTextField amountField = new JTextField("");
        amountField.setFont(new Font("Arial", Font.PLAIN, 18));
        amountField.setBounds(screenSize.width / 2 - 150, 200, 300, 50);
        amountField.setHorizontalAlignment(SwingConstants.CENTER);
        depositPanel.add(amountField);

        // Tombol "KEMBALI"
        JButton backButton = new JButton("KEMBALI");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBounds(screenSize.width / 2 - 160, 300, 140, 40);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        depositPanel.add(backButton);

        // Tombol "ENTER"
        JButton enterButton = new JButton("ENTER");
        enterButton.setFont(new Font("Arial", Font.BOLD, 16));
        enterButton.setBounds(screenSize.width / 2 + 20, 300, 140, 40);
        enterButton.setBackground(Color.WHITE);
        enterButton.setForeground(Color.BLACK);
        depositPanel.add(enterButton);

        // Tambahkan event listener untuk tombol
        backButton.addActionListener(e -> DepositScreen.this.dispose()); // Kembali ke menu utama

        enterButton.addActionListener(e -> {
            int amount = Integer.parseInt(amountField.getText());
            user.setBalance(amount + user.getBalance());

            UserDB.updateUser(user);

            TransactionDB.insertTransaction("Setor", amount,  java.time.LocalDateTime.now().toString(), user.getBankName(), user.getAccountNumber(), user.getAccountNumber());

            dispose();
            JOptionPane.showMessageDialog(DepositScreen.this, "Jumlah uang disetor: " + amount);

            ArrayList<Transaction> transactions = TransactionDB.getTransactions();

            mainMenuFrame.dispose();
            new MenuScreen(user, transactions);
        });

        add(depositPanel);
        setVisible(true);
    }
}

