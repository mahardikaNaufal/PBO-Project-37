import javax.swing.*;

public class KonfirmasiPIN {
    public static boolean showPINConfirmationScreen(JFrame parentFrame) {
        final boolean[] isPinCorrect = {false}; // Array boolean untuk menyimpan hasil

        // Dialog untuk konfirmasi PIN
        JDialog pinDialog = new JDialog(parentFrame, "SmartATM - Konfirmasi PIN", true);
        pinDialog.setSize(400, 300);
        pinDialog.setLayout(null);
        pinDialog.setLocationRelativeTo(parentFrame);

        // Label instruksi
        JLabel pinLabel = new JLabel("MASUKKAN PIN ANDA");
        pinLabel.setBounds(100, 30, 200, 30);
        pinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pinDialog.add(pinLabel);

        // Input PIN
        JPasswordField pinField = new JPasswordField();
        pinField.setBounds(100, 80, 200, 30);
        pinDialog.add(pinField);

        // Tombol "Enter"
        JButton enterPinButton = new JButton("ENTER");
        enterPinButton.setBounds(150, 150, 100, 30);
        pinDialog.add(enterPinButton);

        // Event tombol Enter
        enterPinButton.addActionListener(e -> {
            char[] pin = pinField.getPassword();
            if (new String(pin).equals("1")) { // Validasi PIN
                isPinCorrect[0] = true; // Set boolean menjadi true
                pinDialog.dispose();   // Tutup dialog
            } else {
                JOptionPane.showMessageDialog(pinDialog, "PIN Salah, Coba Lagi!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Tampilkan dialog
        pinDialog.setVisible(true);

        return isPinCorrect[0]; // Kembalikan nilai
    }

}
