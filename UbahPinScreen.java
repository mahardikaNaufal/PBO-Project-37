import javax.swing.*;
import java.awt.*;

public class UbahPinScreen {
    public UbahPinScreen(User user, SmartAtmDatabase database) {
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
        JLabel oldPinLabel = new JLabel("Masukkan PIN Lama");
        oldPinLabel.setFont(new Font("Arial", Font.BOLD, 14));
        oldPinLabel.setBounds(screenSize.width / 2 - 160, 150, 200, 20);
        oldPinLabel.setForeground(Color.WHITE);
        panel.add(oldPinLabel);

        JTextField oldPinField = new JTextField();
        oldPinField.setFont(new Font("Arial", Font.PLAIN, 14));
        oldPinField.setBounds(screenSize.width / 2 - 160, 180, 320, 40);
        panel.add(oldPinField);

        // Field PIN Baru
        JLabel newPinLabel = new JLabel("Masukkan PIN Baru");
        newPinLabel.setFont(new Font("Arial", Font.BOLD, 14));
        newPinLabel.setBounds(screenSize.width / 2 - 160, 250, 200, 20);
        newPinLabel.setForeground(Color.WHITE);
        panel.add(newPinLabel);

        JPasswordField newPinField = new JPasswordField();
        newPinField.setFont(new Font("Arial", Font.PLAIN, 14));
        newPinField.setBounds(screenSize.width / 2 - 160, 280, 320, 40);
        panel.add(newPinField);

        // Field Konfirmasi PIN
        JLabel newPinLabel2 = new JLabel("Masukkan Kembali PIN Baru");
        newPinLabel2.setFont(new Font("Arial", Font.BOLD, 14));
        newPinLabel2.setBounds(screenSize.width / 2 - 160, 350, 200, 20);
        newPinLabel2.setForeground(Color.WHITE);
        panel.add(newPinLabel2);

        JPasswordField newPinField2 = new JPasswordField();
        newPinField2.setFont(new Font("Arial", Font.PLAIN, 14));
        newPinField2.setBounds(screenSize.width / 2 - 160, 380, 320, 40);
        panel.add(newPinField2);

        // Tombol Simpan
        JButton simpanButton = new JButton("Simpan");
        simpanButton.setFont(new Font("Arial", Font.BOLD, 16));
        simpanButton.setBackground(new Color(30, 144, 255));
        simpanButton.setForeground(Color.WHITE);
        simpanButton.setBounds(screenSize.width / 2 + 20, 450, 120, 40);
        panel.add(simpanButton);

        // Tombol Simpan
        JButton backButton = new JButton("Kembali");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setBounds(screenSize.width / 2 - 120, 450, 120, 40);
        panel.add(backButton);

        backButton.addActionListener(e -> {
            frame.dispose();
        });

        // Action Listener untuk tombol simpan
        simpanButton.addActionListener(e -> {
            String oldPin = oldPinField.getText();
            String newPin = new String(newPinField.getPassword());
            String newPin2 = new String(newPinField2.getPassword());

            if (!(oldPin.isEmpty() || newPin.isEmpty() || newPin2.isEmpty())) {
                if(Integer.parseInt(oldPin) == Integer.parseInt(user.getPassword())) {
                    if(newPin.equals(newPin2)) {
                        user.setPassword(newPin);
                        database.updateUser(user);
                        
                        JOptionPane.showMessageDialog(frame, "Ubah PIN berhasil!");

                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Periksa PIN Baru Kembali!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "PIN lama salah!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            });

        frame.add(panel);
        frame.setVisible(true);
    }
}