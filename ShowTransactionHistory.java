import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class ShowTransactionHistory {
    public String formatDate(String inputDate) {
        try {
            // Memisahkan tanggal dan waktu berdasarkan "T"
            String[] dateTimeParts = inputDate.split("T");
            String datePart = dateTimeParts[0]; // Bagian tanggal: yyyy-MM-dd
            String timePart = dateTimeParts[1].split("\\.")[0]; // Bagian waktu: HH:mm:ss (tanpa nanodetik)
    
            // Memecah bagian tanggal (yyyy-MM-dd)
            String[] dateComponents = datePart.split("-");
            int year = Integer.parseInt(dateComponents[0]);
            int month = Integer.parseInt(dateComponents[1]);
            int day = Integer.parseInt(dateComponents[2]);
    
            // Konversi bulan ke nama (Bahasa Indonesia)
            String[] monthNames = {
                "Januari", "Februari", "Maret", "April", "Mei", "Juni",
                "Juli", "Agustus", "September", "Oktober", "November", "Desember"
            };
            String monthName = monthNames[month - 1];
    
            // Ambil jam dan menit dari timePart
            String hourMinute = timePart.substring(0, 5); // HH:mm
    
            // Menggabungkan hasil format
            return hourMinute + ", " + day + " " + monthName + " " + year;
        } catch (Exception e) {
            e.printStackTrace();
            return "Invalid date format";
        }
    }    

    public ShowTransactionHistory(User user, ArrayList<Transaction> transactions) {
        // Membuat frame utama
        JFrame frame = new JFrame("Riwayat Transaksi");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Mendapatkan ukuran layar
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLayout(null);

        // Panel utama
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setSize(screenSize.width, screenSize.height);
        mainPanel.setBackground(new Color(70, 130, 180));

        // Header
        JLabel headerLabel = new JLabel("RIWAYAT TRANSAKSI");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBounds(screenSize.width / 2 - 200, 20, 400, 50);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(headerLabel);

        // Nomor rekening
        JLabel accountLabel = new JLabel("REKENING : " + user.getAccountNumber());
        accountLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        accountLabel.setForeground(Color.WHITE);
        accountLabel.setBounds(screenSize.width / 2 - 200, 100, 400, 30);
        accountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(accountLabel);

        // Tabel transaksi
        JLabel tanggalLabel = new JLabel("Tanggal");
        tanggalLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        tanggalLabel.setForeground(Color.WHITE);
        tanggalLabel.setBounds(screenSize.width / 2 - 350, 200, 150, 30);
        tanggalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(tanggalLabel);

        JLabel jenisTransaksiLabel = new JLabel("Jenis Transaksi");
        jenisTransaksiLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        jenisTransaksiLabel.setForeground(Color.WHITE);
        jenisTransaksiLabel.setBounds(screenSize.width / 2 - 100, 200, 200, 30);
        jenisTransaksiLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(jenisTransaksiLabel);

        JLabel jumlahLabel = new JLabel("Jumlah (Rp)");
        jumlahLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        jumlahLabel.setForeground(Color.WHITE);
        jumlahLabel.setBounds(screenSize.width / 2 + 150, 200, 200, 30);
        jumlahLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(jumlahLabel);

        ArrayList<Transaction> transactionsByUser = new ArrayList<>();
        for (Transaction t : transactions) {
            if(t.getAccountNumber() == user.getAccountNumber()) {
                transactionsByUser.add(t);
            }
        }

        for (int i = 0; i < transactionsByUser.size(); i++) {
            Transaction transaction = transactionsByUser.get(i);
            
            // Hitung posisi vertikal dengan jarak antar elemen
            int yPosition = 250 + (i * 40); // 40 adalah jarak antar elemen
        
            // Data tanggal
            JLabel tanggalData = new JLabel(this.formatDate(transaction.getDate()));
            tanggalData.setFont(new Font("Arial", Font.PLAIN, 18));
            tanggalData.setForeground(Color.WHITE);
            tanggalData.setBounds(screenSize.width / 2 - 350, yPosition, 150, 30);
            tanggalData.setHorizontalAlignment(SwingConstants.CENTER);
            mainPanel.add(tanggalData);
        
            // Data jenis transaksi
            JLabel jenisTransaksiData = new JLabel(transaction.getType());
            jenisTransaksiData.setFont(new Font("Arial", Font.PLAIN, 18));
            jenisTransaksiData.setForeground(Color.WHITE);
            jenisTransaksiData.setBounds(screenSize.width / 2 - 100, yPosition, 200, 30);
            jenisTransaksiData.setHorizontalAlignment(SwingConstants.CENTER);
            mainPanel.add(jenisTransaksiData);
        
            // Data jumlah transaksi
            JLabel jumlahData = new JLabel("" + transaction.getAmount());
            jumlahData.setFont(new Font("Arial", Font.PLAIN, 18));
            jumlahData.setForeground(Color.WHITE);
            jumlahData.setBounds(screenSize.width / 2 + 150, yPosition, 200, 30);
            jumlahData.setHorizontalAlignment(SwingConstants.CENTER);
            mainPanel.add(jumlahData);
        }        

        // Tombol Simpan
        JButton backButton = new JButton("Kembali");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setBounds(screenSize.width / 2 - 620, 100, 120, 40);
        mainPanel.add(backButton);

        backButton.addActionListener(e -> {
            frame.dispose();
        });

        // Menambahkan panel ke frame
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
