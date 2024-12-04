import javax.swing.*;

public class KonfirmasiPIN {
    public static void showPINConfirmationScreen(JFrame parentFrame) {
        // Frame baru untuk konfirmasi PIN
        JFrame pinFrame = new JFrame("SmartATM - Konfirmasi PIN");
        pinFrame.setSize(400, 300);
        pinFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pinFrame.setLayout(null);
        pinFrame.setLocationRelativeTo(parentFrame); // Lokasi di tengah parentFrame

        // Label instruksi
        JLabel pinLabel = new JLabel("MASUKKAN PIN ANDA");
        pinLabel.setBounds(100, 30, 200, 30);
        pinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pinFrame.add(pinLabel);

        // Input PIN
        JPasswordField pinField = new JPasswordField();
        pinField.setBounds(100, 80, 200, 30);
        pinFrame.add(pinField);

        // Tombol "Enter"
        JButton enterPinButton = new JButton("ENTER");
        enterPinButton.setBounds(150, 150, 100, 30);
        pinFrame.add(enterPinButton);

        pinFrame.setVisible(true);

        // Event tombol Enter
        enterPinButton.addActionListener(e -> {
            char[] pin = pinField.getPassword();
            if (new String(pin).equals("1234")) { // Contoh PIN hardcoded (sebaiknya gunakan metode validasi yang aman)
                pinFrame.dispose();
                JOptionPane.showMessageDialog(parentFrame, "Transaksi Berhasil!");
            } else {
                JOptionPane.showMessageDialog(pinFrame, "PIN Salah, Coba Lagi!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
