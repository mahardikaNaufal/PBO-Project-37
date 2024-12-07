import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.*;

import java.awt.event.*;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DepositScreen {
    private static Timer idleTimer;

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

    DepositScreen(User user, SmartAtmDatabase database, JFrame mainMenuFrame) {
        JFrame depositFrame = new JFrame("Setor Tunai");

        // Mendapatkan ukuran layar
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        depositFrame.setSize(screenSize.width, screenSize.height);
        depositFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        depositFrame.setLayout(null);

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
        backButton.addActionListener(e -> depositFrame.dispose()); // Kembali ke menu utama

        enterButton.addActionListener(e -> {
            int amount = Integer.parseInt(amountField.getText());
            user.setBalance(amount + user.getBalance());

            database.updateUser(user);

            database.insertTransaction("Setor", amount,  java.time.LocalDateTime.now().toString(), user.getBankName(), user.getAccountNumber());

            depositFrame.dispose();
            JOptionPane.showMessageDialog(depositFrame, "Jumlah uang disetor: " + amount);

            ArrayList<Transaction> transactions = database.getTransactions();

            mainMenuFrame.dispose();
            new ShowMainMenu(user, transactions);
            // String amount = amountField.getText().trim();
            // if (amount.isEmpty() || amount.equals("RP.")) {
            //     JOptionPane.showMessageDialog(depositFrame, "Jumlah uang tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            // } else {
            //     JOptionPane.showMessageDialog(depositFrame, "Jumlah uang diterima: " + amount);
            //     depositFrame.dispose();

            //     // Panggil layar konfirmasi PIN
            //     KonfirmasiPIN.showPINConfirmationScreen(depositFrame);
            // }
        });

        // // Timer untuk mendeteksi idle
        // idleTimer = new Timer();

        // // Reset timer ketika mouse digerakkan
        // depositFrame.addMouseMotionListener(new MouseMotionAdapter() {
        //     @Override
        //     public void mouseMoved(MouseEvent e) {
        //         resetIdleTimer(depositFrame);
        //     }
        // });

        // // Reset timer ketika mouse diklik
        // depositFrame.addMouseListener(new MouseAdapter() {
        //     @Override
        //     public void mouseClicked(MouseEvent e) {
        //         resetIdleTimer(depositFrame);
        //     }
        // });

        // // Mulai timer idle
        // startIdleTimer(depositFrame);

        depositFrame.add(depositPanel);
        depositFrame.setVisible(true);
    }
}
