import javax.swing.*;
import java.awt.*;

public class TransferGUI {
    public static void showTransferScreen() {
        JFrame transferFrame = new JFrame("Transfer");

        // Mendapatkan ukuran layar
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        transferFrame.setSize(screenSize.width, screenSize.height);
        transferFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        transferFrame.setLayout(null);

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
        JLabel subHeaderLabel = new JLabel("<html>Masukkan Nama Bank Tujuan, No.Rek Tujuan, dan Nominal yang ingin Anda Transfer</html>");
        subHeaderLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        subHeaderLabel.setForeground(Color.WHITE);
        subHeaderLabel.setBounds(screenSize.width / 2 - 350, 120, 700, 50);
        subHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        transferPanel.add(subHeaderLabel);

        // Label dan field untuk Nama Bank Tujuan
        JLabel bankLabel = new JLabel("Nama Bank Tujuan:");
        bankLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bankLabel.setForeground(Color.WHITE);
        bankLabel.setBounds(screenSize.width / 2 - 200, 200, 400, 30);
        transferPanel.add(bankLabel);

        JTextField bankField = new JTextField();
        bankField.setFont(new Font("Arial", Font.PLAIN, 16));
        bankField.setBounds(screenSize.width / 2 - 200, 230, 400, 40);
        transferPanel.add(bankField);

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

        JTextField amountField = new JTextField(" ");
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
            transferFrame.dispose(); //Kembali ke menu utama
        });

        proceedButton.addActionListener(e -> {
            String bankName = bankField.getText();
            String accountNumber = accountField.getText();
            String amountText = amountField.getText().replace(" ", "").trim();

            try {
                double amount = Double.parseDouble(amountText);
                if (bankName.isEmpty() || accountNumber.isEmpty() || amount <= 0) {
                    JOptionPane.showMessageDialog(transferFrame, "Semua field harus diisi dengan benar!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    transferFrame.dispose(); // Tutup layar transfer
                    KonfirmasiPIN.showPINConfirmationScreen(transferFrame); // Panggil GUI Konfirmasi PIN
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(transferFrame, "Masukkan nominal yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        transferFrame.add(transferPanel);
        transferFrame.setVisible(true);
    }
}
