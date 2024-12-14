package view;

import javax.swing.*;

import model.Transaction;
import model.User;
import repository.TransactionDB;
import repository.UserDB;

import java.awt.*;

import java.util.ArrayList;


public class WithdrawScreen extends JFrame {
    public WithdrawScreen(User user, JFrame mainMenuFrame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setTitle("SmartATM - Tarik Tunai");
        setSize(screenSize.width, screenSize.height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JPanel withdrawPanel = new JPanel();
        withdrawPanel.setLayout(null);
        withdrawPanel.setSize(screenSize.width, screenSize.height);
        withdrawPanel.setBackground(new Color(100, 160, 180));

        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, screenSize.width, 75);
        headerPanel.setBackground(new Color(60, 120, 150));

        JLabel headerLabel = new JLabel("TARIK TUNAI");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        withdrawPanel.add(headerPanel);

        JLabel instructionLabel = new JLabel("MASUKKAN JUMLAH PENARIKAN TUNAI YANG ANDA INGINKAN");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        instructionLabel.setForeground(Color.WHITE);
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionLabel.setBounds(screenSize.width / 2 - 400, 150, 800, 30);
        withdrawPanel.add(instructionLabel);

        JTextField amountField;
        amountField = new JTextField("");
        amountField.setFont(new Font("Arial", Font.PLAIN, 18));
        amountField.setHorizontalAlignment(SwingConstants.CENTER);
        amountField.setForeground(Color.GRAY);
        amountField.setBackground(Color.WHITE);
        amountField.setBounds(screenSize.width / 2 - 200, 200, 400, 50);
        withdrawPanel.add(amountField);

        JButton backButton = new JButton("KEMBALI");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setBounds(screenSize.width / 2 - 250, 300, 200, 50);
        withdrawPanel.add(backButton);

        JButton enterButton = new JButton("ENTER");
        enterButton.setFont(new Font("Arial", Font.BOLD, 14));
        enterButton.setBackground(Color.WHITE);
        enterButton.setForeground(Color.BLACK);
        enterButton.setBounds(screenSize.width / 2 + 50, 300, 200, 50);
        withdrawPanel.add(enterButton);

        add(withdrawPanel);
        setVisible(true);

        backButton.addActionListener(e -> {
            WithdrawScreen.this.dispose();
        });

        enterButton.addActionListener(e -> {
            int amount = Integer.parseInt(amountField.getText());
            if(amount > user.getBalance()) {
                JOptionPane.showMessageDialog(WithdrawScreen.this, "Saldo tidak mencukupi!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                user.setBalance(user.getBalance() - amount);
                
                UserDB.updateUser(user);
    
                TransactionDB.insertTransaction("Tarik", amount,  java.time.LocalDateTime.now().toString(), user.getBankName(), user.getAccountNumber(), user.getAccountNumber());

                ArrayList<Transaction> transactions = TransactionDB.getTransactions();

                mainMenuFrame.dispose();
                new MenuScreen(user, transactions);
    
                JOptionPane.showMessageDialog(WithdrawScreen.this, "Jumlah uang ditarik: " + amount);
                WithdrawScreen.this.dispose();
            }
        });
    }
}
