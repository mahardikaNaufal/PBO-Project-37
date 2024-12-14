package view;

import javax.swing.*;

import model.Transaction;
import model.User;
import repository.TransactionDB;
import repository.UserDB;

import java.awt.*;
import java.util.ArrayList;

public class TransferScreen extends JFrame {
    public TransferScreen(User user, JFrame mainMenuFrame) {
        // Mendapatkan ukuran layar
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setTitle("Transfer");
        setSize(screenSize.width, screenSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel transferPanel = new JPanel();
        transferPanel.setLayout(null);
        transferPanel.setSize(screenSize.width, screenSize.height);
        transferPanel.setBackground(new Color(70, 130, 180)); // Warna biru lembut

        // Label header "TRANSFER"
        JLabel headerLabel = new JLabel("TRANSFER");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 36));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBounds(screenSize.width / 2 - 150, 50, 300, 50);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        transferPanel.add(headerLabel);

        // Label sub-header "Masukkan Nama Bank..."
        JLabel subHeaderLabel = new JLabel("<html>Pilih Nama Bank Tujuan, Masukkan No.Rek Tujuan, dan Nominal yang ingin Anda Transfer</html>");
        subHeaderLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        subHeaderLabel.setForeground(Color.WHITE);
        subHeaderLabel.setBounds(screenSize.width / 2 - 350, 120, 700, 50);
        subHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        transferPanel.add(subHeaderLabel);

        // Radio button untuk bank
        JLabel bankLabel = new JLabel("Pilih Bank Tujuan:");
        bankLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bankLabel.setForeground(Color.WHITE);
        bankLabel.setBounds(screenSize.width / 2 - 200, 200, 400, 30);
        transferPanel.add(bankLabel);

        JRadioButton mandiriButton = new JRadioButton("Mandiri");
        mandiriButton.setBounds(screenSize.width / 2 - 200, 230, 100, 30);
        mandiriButton.setBackground(new Color(70, 130, 180));
        mandiriButton.setForeground(Color.WHITE);
        mandiriButton.setFont(new Font("Arial", Font.BOLD, 16));

        JRadioButton bniButton = new JRadioButton("BNI");
        bniButton.setBounds(screenSize.width / 2 - 80, 230, 100, 30);
        bniButton.setBackground(new Color(70, 130, 180));
        bniButton.setForeground(Color.WHITE);
        bniButton.setFont(new Font("Arial", Font.BOLD, 16));

        JRadioButton briButton = new JRadioButton("BRI");
        briButton.setBounds(screenSize.width / 2 + 40, 230, 100, 30);
        briButton.setBackground(new Color(70, 130, 180));
        briButton.setForeground(Color.WHITE);
        briButton.setFont(new Font("Arial", Font.BOLD, 16));

        ButtonGroup bankGroup = new ButtonGroup();
        bankGroup.add(mandiriButton);
        bankGroup.add(bniButton);
        bankGroup.add(briButton);

        transferPanel.add(mandiriButton);
        transferPanel.add(bniButton);
        transferPanel.add(briButton);

        // Label dan field untuk Nomor Rekening Tujuan
        JLabel accountLabel = new JLabel("No.Rek Tujuan:");
        accountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        accountLabel.setForeground(Color.WHITE);
        accountLabel.setBounds(screenSize.width / 2 - 200, 290, 400, 30);
        transferPanel.add(accountLabel);

        JTextField accountField = new JTextField();
        accountField.setFont(new Font("Arial", Font.PLAIN, 16));
        accountField.setBounds(screenSize.width / 2 - 200, 320, 400, 40);
        transferPanel.add(accountField);

        // Label dan field untuk Nominal
        JLabel amountLabel = new JLabel("Nominal:");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setBounds(screenSize.width / 2 - 200, 380, 400, 30);
        transferPanel.add(amountLabel);

        JTextField amountField = new JTextField("");
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        amountField.setBounds(screenSize.width / 2 - 200, 410, 400, 40);
        transferPanel.add(amountField);

        // Tombol "Kembali"
        JButton backButton = new JButton("KEMBALI");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBounds(screenSize.width / 2 - 220, 500, 180, 50);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        transferPanel.add(backButton);

        // Tombol "Lanjutkan"
        JButton proceedButton = new JButton("LANJUTKAN");
        proceedButton.setFont(new Font("Arial", Font.BOLD, 16));
        proceedButton.setBounds(screenSize.width / 2 + 40, 500, 180, 50);
        proceedButton.setBackground(Color.WHITE);
        proceedButton.setForeground(Color.BLACK);
        transferPanel.add(proceedButton);

        // Event Listener untuk tombol
        backButton.addActionListener(e -> {
            TransferScreen.this.dispose(); // Kembali ke menu utama
        });

        proceedButton.addActionListener(e -> {
            String bankName = null;

            if (mandiriButton.isSelected()) {
                bankName = "mandiri";
            } else if (bniButton.isSelected()) {
                bankName = "bni";
            } else if (briButton.isSelected()) {
                bankName = "bri";
            }

            String accountNumber = accountField.getText();
            String amountText = amountField.getText();

            if (bankName == null || accountNumber.isEmpty() || amountText.isEmpty()) {
                JOptionPane.showMessageDialog(TransferScreen.this, "Harap lengkapi semua data!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (Integer.parseInt(amountText) > user.getBalance()) {
                JOptionPane.showMessageDialog(TransferScreen.this, "Saldo tidak mencukupi!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            user.setBalance(user.getBalance() - Integer.parseInt(amountText));

            User targetUser = UserDB.getUser(Integer.parseInt(accountNumber));
            targetUser.setBalance(targetUser.getBalance() + Integer.parseInt(amountText));

            UserDB.updateUser(user);
            UserDB.updateUser(targetUser);

            TransactionDB.insertTransaction("Transfer", Integer.parseInt(amountText), java.time.LocalDateTime.now().toString(), bankName, targetUser.getAccountNumber(), user.getAccountNumber());

            JOptionPane.showMessageDialog(TransferScreen.this, "Transfer berhasil!");
            TransferScreen.this.dispose(); // Tutup layar transfer

            ArrayList<Transaction> transactions = TransactionDB.getTransactions();

            mainMenuFrame.dispose();
            new MenuScreen(user, transactions);
        });

        add(transferPanel);
        setVisible(true);
    }
}
