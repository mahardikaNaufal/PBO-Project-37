import java.awt.*;
import javax.swing.*;

public class BalanceInformation {

    private JFrame frame;

    public BalanceInformation(ATM atm) {
        // Membuat frame baru untuk halaman informasi saldo
        frame = new JFrame("Informasi Saldo");

        // Mendapatkan ukuran layar
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Menutup halaman ini saja saat di-close

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(screenSize.width, screenSize.height);
        panel.setBackground(new Color(70, 130, 180)); // Warna biru lembut

        // Label judul
        JLabel titleLabel = new JLabel("Informasi Saldo");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setBounds((screenSize.width - 300) / 2, 50, 300, 50);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel);

        // Label untuk menampilkan saldo
        JLabel balanceLabel = new JLabel("Saldo Anda: Rp " + atm.getBalance());
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        balanceLabel.setBounds((screenSize.width - 400) / 2, 150, 400, 40);
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(balanceLabel);

        // Tombol kembali ke menu utama
        JButton backButton = new JButton("Kembali");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(30, 144, 255));
        backButton.setForeground(Color.WHITE);
        backButton.setBounds((screenSize.width - 150) / 2, 250, 150, 40);
        panel.add(backButton);

        // ActionListener untuk tombol kembali
        backButton.addActionListener(e -> {
            frame.dispose(); // Menutup halaman informasi saldo
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
