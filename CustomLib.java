package config;

import javax.sound.sampled.*;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class CustomLib {
    public static void playSound(String filePath) {
        try {
            // Mendapatkan file dari resources
            InputStream audioSrc = CustomLib.class.getClassLoader().getResourceAsStream(filePath);
            if (audioSrc == null) {
                System.err.println("File " + filePath + " tidak ditemukan!");
                return;
            }

            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            
            // Format audio
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String formatDate(String inputDate) {
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

    
}
