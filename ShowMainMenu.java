import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.event.*;

public class ShowMainMenu {
    private static Timer idleTimer;

    public ShowMainMenu(User user, ArrayList<Transaction> transactions) {
        SmartAtmDatabase database = new SmartAtmDatabase();

        JFrame mainMenuFrame = new JFrame("SmartATM - Menu Utama");
        System.out.println(user.getUsername() + " " + user.getPassword() + " " + user.getAccountNumber() + " " + user.getBankName() + " " + user.getBalance());

        // Mendapatkan ukuran layar
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainMenuFrame.setSize(screenSize.width, screenSize.height);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setLayout(null);

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
        // headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
            new BalanceInfoScreen(user);
        });

        withdrawButton.addActionListener(e -> {
            idleTimer.cancel();
            new ShowWithdrawScreen(user, database, mainMenuFrame);
        });        

        depositButton.addActionListener(e -> {
            idleTimer.cancel();
            new DepositScreen(user, database, mainMenuFrame);
        });      
        
        transactionHistoryButton.addActionListener(e -> {
            idleTimer.cancel();
            new ShowTransactionHistory(user, transactions);
        });        

        transferButton.addActionListener(e -> {
            idleTimer.cancel();
            new ShowTransferScreen(user, database, mainMenuFrame); // Memanggil GUI transfer
        });        

        changePinButton.addActionListener(e -> {
            idleTimer.cancel();
            new UbahPinScreen(user, database);
        });

        exitButton.addActionListener(e -> {
            mainMenuFrame.dispose();
            System.exit(0);
        });

        // Timer untuk mendeteksi idle
        idleTimer = new Timer();

        // Reset timer ketika mouse digerakkan
        mainMenuFrame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                resetIdleTimer(mainMenuFrame);
            }
        });

        // Reset timer ketika mouse diklik
        mainMenuFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resetIdleTimer(mainMenuFrame);
            }
        });

        // Mulai timer idle
        startIdleTimer(mainMenuFrame);

        mainMenuFrame.add(mainMenuPanel);
        mainMenuFrame.setVisible(true);
    }    

    private static void startIdleTimer(JFrame frame) {
        idleTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Menampilkan notifikasi setelah 5 detik idle
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(frame, "Mouse tidak digerakkan selama 5 detik!", "Idle Notification", JOptionPane.INFORMATION_MESSAGE));
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
