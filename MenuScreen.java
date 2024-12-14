package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import model.User;
import repository.TransactionDB;
import repository.UserDB;
import model.Transaction;

public class MenuScreen extends JFrame {
    private static Timer idleTimer;

    public MenuScreen(User user, ArrayList<Transaction> transactions) {
        // Mendapatkan ukuran layar
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setTitle("SmartATM - Menu Utama");
        setSize(screenSize.width, screenSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Panel utama
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(null);
        mainMenuPanel.setSize(screenSize.width, screenSize.height);
        mainMenuPanel.setBackground(new Color(70, 130, 180)); // Warna biru

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, screenSize.width, 75); // Lebar penuh, tinggi 75px
        headerPanel.setBackground(new Color(60, 120, 180)); // Warna biru lebih gelap

        // Label untuk Header
        JLabel headerLabel = new JLabel("SmartATM");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 36));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        mainMenuPanel.add(headerPanel);

        // Tombol Menu
        JButton withdrawButton = new JButton("TARIK TUNAI");
        JButton depositButton = new JButton("SETOR TUNAI");
        JButton checkBalanceButton = new JButton("INFORMASI SALDO");
        JButton transactionHistoryButton = new JButton("RIWAYAT TRANSAKSI");
        JButton transferButton = new JButton("TRANSFER");
        JButton changePinButton = new JButton("UBAH PIN");
        JButton exitButton = new JButton("EXIT");

        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Color buttonColor = Color.WHITE;

        JButton[] buttons = {withdrawButton, depositButton, checkBalanceButton,
                transactionHistoryButton, transferButton, changePinButton, exitButton};

        int buttonStartY = 150;
        int buttonSpacingY = 80;

        for (int i = 0; i < buttons.length; i++) {
            JButton button = buttons[i];
            button.setFont(buttonFont);
            button.setForeground(Color.BLACK);
            button.setBackground(buttonColor);
            button.setFocusPainted(false);
            button.setBounds(screenSize.width / 2 - 120, buttonStartY + i * buttonSpacingY, 240, 50);
            mainMenuPanel.add(button);
        }

        checkBalanceButton.addActionListener(e -> {
            idleTimer.cancel();
            new BalanceScreen(user);
        });

        withdrawButton.addActionListener(e -> {
            idleTimer.cancel();
            new WithdrawScreen(user, MenuScreen.this);
        });

        depositButton.addActionListener(e -> {
            idleTimer.cancel();
            new DepositScreen(user, MenuScreen.this);
        });

        transactionHistoryButton.addActionListener(e -> {
            idleTimer.cancel();
            new HistoryScreen(user, transactions);
        });

        transferButton.addActionListener(e -> {
            idleTimer.cancel();
            new TransferScreen(user, MenuScreen.this); // Memanggil GUI transfer
        });

        changePinButton.addActionListener(e -> {
            idleTimer.cancel();
            new ChangePINScreen(user);
        });

        exitButton.addActionListener(e -> {
            MenuScreen.this.dispose();
            idleTimer.cancel();
            new LoginScreen(UserDB.getUsers(), TransactionDB.getTransactions());
        });

        // Timer untuk mendeteksi idle
        idleTimer = new Timer();

        // Reset timer ketika mouse digerakkan
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                resetIdleTimer(MenuScreen.this);
            }
        });

        // Reset timer ketika mouse diklik
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resetIdleTimer(MenuScreen.this);
            }
        });

        // Mulai timer idle
        startIdleTimer(this);

        add(mainMenuPanel);
        setVisible(true);
    }

    private static void startIdleTimer(JFrame frame) {
        idleTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    int response = JOptionPane.showConfirmDialog(frame,
                            "Mouse tidak digerakkan selama 5 detik! Apakah Anda ingin melanjutkan?",
                            "Idle Notification",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (response == JOptionPane.NO_OPTION) {
                        frame.dispose();
                        System.exit(0);
                    } else {
                        resetIdleTimer(frame);
                    }
                });
            }
        }, 5000); // 5000 ms = 5 detik
    }

    private static void resetIdleTimer(JFrame frame) {
        // Batalkan timer lama
        idleTimer.cancel();

        // Buat timer baru
        idleTimer = new Timer();
        startIdleTimer(frame);
    }
}
