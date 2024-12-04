import java.awt.*;
import javax.swing.*;

public class UbahPinPage {

    public UbahPinPage() {
        JFrame frame = new JFrame("Ubah PIN");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height); 
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Menutup hanya halaman ini
        frame.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(screenSize.width, screenSize.height);
        panel.setBackground(new Color(70, 130, 180)); // Warna biru lembut

        // Header Label
        JLabel headerLabel = new JLabel("UBAH PIN");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 36));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBounds((screenSize.width - 200) / 2, 50, 200, 50);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(headerLabel);

        // Field No Rekening
        JLabel noRekeningLabel = new JLabel("Masukkan Email");
        noRekeningLabel.setFont(new Font("Arial", Font.BOLD, 14));
        noRekeningLabel.setBounds(screenSize.width / 2 - 160, 150, 200, 20);
        noRekeningLabel.setForeground(Color.WHITE);
        panel.add(noRekeningLabel);

        JTextField noRekeningField = new JTextField();
        noRekeningField.setFont(new Font("Arial", Font.PLAIN, 14));
        noRekeningField.setBounds(screenSize.width / 2 - 160, 180, 320, 40);
        panel.add(noRekeningField);

        // Field PIN Baru
        JLabel pinBaruLabel = new JLabel("Masukkan PIN Baru");
        pinBaruLabel.setFont(new Font("Arial", Font.BOLD, 14));
        pinBaruLabel.setBounds(screenSize.width / 2 - 160, 250, 200, 20);
        pinBaruLabel.setForeground(Color.WHITE);
        panel.add(pinBaruLabel);

        JPasswordField pinBaruField = new JPasswordField();
        pinBaruField.setFont(new Font("Arial", Font.PLAIN, 14));
        pinBaruField.setBounds(screenSize.width / 2 - 160, 280, 320, 40);
        panel.add(pinBaruField);

        // Field Konfirmasi PIN
        JLabel konfirmasiPinLabel = new JLabel("Masukkan kembali PIN");
        konfirmasiPinLabel.setFont(new Font("Arial", Font.BOLD, 14));
        konfirmasiPinLabel.setBounds(screenSize.width / 2 - 160, 350, 200, 20);
        konfirmasiPinLabel.setForeground(Color.WHITE);
        panel.add(konfirmasiPinLabel);

        JPasswordField konfirmasiPinField = new JPasswordField();
        konfirmasiPinField.setFont(new Font("Arial", Font.PLAIN, 14));
        konfirmasiPinField.setBounds(screenSize.width / 2 - 160, 380, 320, 40);
        panel.add(konfirmasiPinField);

        // Tombol Simpan
        JButton simpanButton = new JButton("Simpan");
        simpanButton.setFont(new Font("Arial", Font.BOLD, 16));
        simpanButton.setBackground(new Color(30, 144, 255));
        simpanButton.setForeground(Color.WHITE);
        simpanButton.setBounds(screenSize.width / 2 - 60, 450, 120, 40);
        panel.add(simpanButton);

        // Action Listener untuk tombol simpan
        simpanButton.addActionListener(e -> {
            String noRekening = noRekeningField.getText();
            String pinBaru = new String(pinBaruField.getPassword());
            String konfirmasiPin = new String(konfirmasiPinField.getPassword());

            if (noRekening.isEmpty() || pinBaru.isEmpty() || konfirmasiPin.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!pinBaru.equals(konfirmasiPin)) {
                JOptionPane.showMessageDialog(frame, "PIN tidak cocok!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "PIN berhasil diubah!");
                frame.dispose();
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
