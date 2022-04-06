-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 08 Jun 2021 pada 16.43
-- Versi server: 10.4.14-MariaDB
-- Versi PHP: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rembol`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `adminrembol`
--

CREATE TABLE `adminrembol` (
  `id` int(11) NOT NULL,
  `fullname` text NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` text NOT NULL,
  `email` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `adminrembol`
--

INSERT INTO `adminrembol` (`id`, `fullname`, `username`, `password`, `email`) VALUES
(1, 'admin', 'admin', '$2y$10$qY3UulK1Cisp2CsqzDj0QOWZ0XunY5tDX36jHJajEd3ajQ54q5Hmi', 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `listmobil`
--

CREATE TABLE `listmobil` (
  `idList` int(11) NOT NULL,
  `idMobil` int(11) NOT NULL,
  `namamobil` varchar(100) NOT NULL,
  `hargasewa` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `listmobil`
--

INSERT INTO `listmobil` (`idList`, `idMobil`, `namamobil`, `hargasewa`, `status`) VALUES
(1, 1, 'Honda Jazz', '150000', 'Booked'),
(2, 1, 'Honda Brio', '145000', 'available'),
(3, 2, 'Toyota Innova', '200000', 'available'),
(4, 3, 'Suzuki New Carry', '175000', 'available'),
(5, 3, 'Mitsubishi L300', '180000', 'available'),
(6, 2, 'Toyota Fortuner', '300000', 'available'),
(7, 4, 'Toyota Alphard', '750000', 'available'),
(8, 4, 'Daihatsu Gran Max', '300000', 'available'),
(9, 2, 'Pajero sport', '250000', 'available');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mobil`
--

CREATE TABLE `mobil` (
  `idMobil` int(11) NOT NULL,
  `jenismobil` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `mobil`
--

INSERT INTO `mobil` (`idMobil`, `jenismobil`) VALUES
(1, 'Penumpang 5 Orang'),
(2, 'Penumpang 8 Orang'),
(3, 'Pickup'),
(4, 'Minibus');

-- --------------------------------------------------------

--
-- Struktur dari tabel `orders`
--

CREATE TABLE `orders` (
  `idorder` int(11) NOT NULL,
  `idList` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `NoTelp` varchar(50) NOT NULL,
  `Tanggal` varchar(50) NOT NULL,
  `statusrent` varchar(50) NOT NULL DEFAULT 'belum'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `orders`
--

INSERT INTO `orders` (`idorder`, `idList`, `id`, `alamat`, `NoTelp`, `Tanggal`, `statusrent`) VALUES
(1, 5, 2, 'Jl.Tanggerang', '06431588', '2021-06-05', 'selesai'),
(2, 4, 2, 'Jl.Tanggerang', '06431588', '2021-06-20', 'selesai'),
(3, 3, 5, 'Bogor', '045637845349', '2021-06-30', 'selesai'),
(4, 9, 2, 'Jonggol', '0540152688', '2021-06-30', 'selesai'),
(5, 8, 2, 'Bandung', '0857648886', '2021-06-27', 'selesai'),
(10, 1, 5, 'Depok', '08543498', '2021-06-13', 'selesai'),
(11, 7, 2, 'Jl.Jalanin aja dulu', '0543468469', '2021-06-20', 'selesai'),
(12, 3, 5, 'bujet', '05464885', '2021-06-23', 'selesai'),
(13, 3, 5, 'Cibubur', '0243464846', '2021-06-20', 'selesai'),
(14, 7, 5, 'Jatijajar', '024319876', '2021-06-27', 'selesai'),
(15, 9, 5, 'Rumah Albert', '0464348', '2021-06-27', 'selesai'),
(16, 1, 5, 'jl. testing ', '081312345678', '2021-06-05', 'selesai'),
(17, 2, 22, 'bogor', '0812837456', '2021-06-06', 'selesai'),
(18, 7, 22, 'depok', '0813398457', '2021-06-07', 'selesai'),
(19, 3, 22, 'jambi', '0821489567', '2021-06-08', 'selesai'),
(20, 4, 22, 'lampung', '088975234', '2021-06-10', 'selesai'),
(21, 3, 5, 'jl.test', '12345', '2021-06-07', 'selesai'),
(22, 1, 5, 'test', '234', '2021-06-07', 'belum'),
(23, 2, 5, 'test', '123', '2021-06-07', 'selesai'),
(24, 7, 2, 'rumah dodo', '081212345678', '2021-06-08', 'selesai');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `fullname` text NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` text NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `fullname`, `username`, `password`, `email`) VALUES
(1, 'kefas Bagastio', 'kefas', '$2y$10$8glP19PFKVUqD0XklI7Kn.1FWBqQKMyT8BaS/ad6iM0T1ktZzjege', 'kefas'),
(2, 'Ruddy kusuma', 'rudin', '$2y$10$ZkwCUNTzz0SDhm5SWnYd4ezReYS.wTeVx7nFfbHNW3yCY4O1rbDjW', 'Ruddy@gmail.com'),
(3, 'Robengtus Nangda', 'butter', '$2y$10$xfTpOaWCiewD5zdYLMCVB.Twi.eq2CEs1oxeV4exxMVr9Wvm3EIi2', 'robertus@gmail.com'),
(4, 'Albert Putra', 'simpenan', '$2y$10$.AdawmulUosFfI/VtSV6a.MGnEoyE.TAQF58clonDlu1ELXlPGH0S', 'albert@gmail.com'),
(5, 'testing', 'test', '$2y$10$xvUnEuwiYHOLQ8YhXTdrmuo8vLYr.A8p3uIOEo3LK5Hk4XTS6V1ae', 'test@gmail.com'),
(9, 'testing', 'tes', '$2y$10$S70rtNNRpXJUvFoL.oqq7u/r3cFMeYdVoXv9Op/KZ3Y9o5HEoF65u', 'test@gmail.co'),
(22, 'kelompok rembol', 'kelompok_rembol', '$2y$10$jdX2fRDCGVTYmD8CnFdwl.VC6CZJOT5H1462IguhO7KTVN1J3FL/y', 'kelompok.rembol@mail.com'),
(25, 'test', 'testing', '$2y$10$uIxgN8bJCh70pmuOlV5aOOpRZiXWcE1EV6z4F0DIDnkfHhLRQXZIG', 'test@mail.com,'),
(26, 'Ricardo Cuatanto', 'dodo', '$2y$10$ghS42R5Yoh8pEqEFPcbAIuQ0gQcXuD8AQBqdLHAlk0xH3lASDRJaC', 'dodo@mail.com');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `adminrembol`
--
ALTER TABLE `adminrembol`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indeks untuk tabel `listmobil`
--
ALTER TABLE `listmobil`
  ADD PRIMARY KEY (`idList`),
  ADD KEY `idMobil` (`idMobil`);

--
-- Indeks untuk tabel `mobil`
--
ALTER TABLE `mobil`
  ADD PRIMARY KEY (`idMobil`);

--
-- Indeks untuk tabel `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`idorder`),
  ADD KEY `idList` (`idList`),
  ADD KEY `id` (`id`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `adminrembol`
--
ALTER TABLE `adminrembol`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `listmobil`
--
ALTER TABLE `listmobil`
  MODIFY `idList` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT untuk tabel `mobil`
--
ALTER TABLE `mobil`
  MODIFY `idMobil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `orders`
--
ALTER TABLE `orders`
  MODIFY `idorder` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `listmobil`
--
ALTER TABLE `listmobil`
  ADD CONSTRAINT `listmobil_ibfk_1` FOREIGN KEY (`idMobil`) REFERENCES `mobil` (`idMobil`);

--
-- Ketidakleluasaan untuk tabel `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`idList`) REFERENCES `listmobil` (`idList`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
