-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 12 Des 2024 pada 11.24
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smartatm`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaction`
--

CREATE TABLE `transaction` (
  `transaction_id` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `amount` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  `bank_name` varchar(255) NOT NULL,
  `account_number` int(11) NOT NULL,
  `source` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaction`
--

INSERT INTO `transaction` (`transaction_id`, `type`, `amount`, `date`, `bank_name`, `account_number`, `source`) VALUES
(26, 'Tarik', 1000, '2024-12-11T09:52:39.192303500', 'bni', 111, 111),
(27, 'Transfer', 4000, '2024-12-11T09:53:11.059036200', 'mandiri', 333, 111),
(28, 'Transfer', 500, '2024-12-11T10:02:12.042588400', 'mandiri', 333, 111);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `account_number` int(11) NOT NULL,
  `balance` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `bank_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`username`, `fullname`, `account_number`, `balance`, `password`, `bank_name`) VALUES
('udin', 'Udin', 111, 4500, '111', 'bni'),
('ajun', 'Ajun', 123, 78000, '111', 'mandiri'),
('alea', 'Alea', 333, 5000, '333', 'mandiri'),
('ayu', 'Ayu', 456, 85000, '456', 'bri'),
('raka', 'Raka', 789, 60000, '789', 'bni'),
('tes8', 'tes 8', 888, 0, '888', 'bni'),
('tes', 'Tess', 999, 0, '999', 'bri'),
('tes', 'Tes', 123456, 0, '12345678', 'bni');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `FK_Transaction_User` (`account_number`),
  ADD KEY `FK_Transaction_Bank` (`bank_name`),
  ADD KEY `FK_Transaction_Source` (`source`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`account_number`),
  ADD KEY `FK_User_Bank` (`bank_name`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FK_Transaction_Source` FOREIGN KEY (`source`) REFERENCES `user` (`account_number`),
  ADD CONSTRAINT `FK_Transaction_User` FOREIGN KEY (`account_number`) REFERENCES `user` (`account_number`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
