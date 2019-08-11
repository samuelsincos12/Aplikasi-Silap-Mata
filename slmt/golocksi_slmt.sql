-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 29, 2019 at 04:10 AM
-- Server version: 5.7.26-log-cll-lve
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `golocksi_slmt`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_aksi`
--

CREATE TABLE `tbl_aksi` (
  `id_aksi` int(11) NOT NULL,
  `id_petugas` int(11) NOT NULL,
  `id_laporan` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `waktu` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_aksi`
--

INSERT INTO `tbl_aksi` (`id_aksi`, `id_petugas`, `id_laporan`, `status`, `tanggal`, `waktu`) VALUES
(1, 1, 1, 2, '2019-05-01', '08:07:00'),
(3, 2, 22, 3, '2019-06-26', '15:30:44'),
(8, 1, 27, 3, '2019-07-18', '09:18:04'),
(9, 0, 28, 1, '2019-07-05', '12:59:23'),
(10, 0, 29, 1, '2019-07-05', '13:21:50'),
(11, 0, 30, 1, '2019-07-18', '09:15:52');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_laporan`
--

CREATE TABLE `tbl_laporan` (
  `id_laporan` int(11) NOT NULL,
  `gambar` varchar(500) NOT NULL,
  `jns_pelanggaran` varchar(50) NOT NULL,
  `lat` double NOT NULL,
  `lon` double NOT NULL,
  `alamat` varchar(300) NOT NULL,
  `keterangan` varchar(200) NOT NULL,
  `tanggal` date NOT NULL,
  `waktu` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_laporan`
--

INSERT INTO `tbl_laporan` (`id_laporan`, `gambar`, `jns_pelanggaran`, `lat`, `lon`, `alamat`, `keterangan`, `tanggal`, `waktu`) VALUES
(1, 'Bioma.png', 'Dilarang Masuk', 0.532184, 101.420121, 'Jl. Soekarno - Hatta No.7a, Air Hitam, Kec. Payung Sekaki, Kota Pekanbaru, Riau 28291, Indonesia', 'zzzz', '2019-05-01', '08:05:00'),
(22, 'sm_19350.jpg', 'Dilarang Masuk', 0.4220943, 101.4173825, 'Unnamed Road, Kubang Jaya, Siak Hulu, Kampar, Riau 28293, Indonesia', 'bla..bla..bla', '2019-06-20', '10:20:40'),
(27, 'sm_15962.jpg', 'Dilarang Parkir', 0.4718588, 101.3821353, 'Jl. Binakrida UNRI, Simpang Baru, Kec. Tampan, Kota Pekanbaru, Riau 28292, Indonesia', 'terima kasih', '2019-07-05', '09:48:45'),
(28, 'sm_18272.jpg', 'Dilarang Belok', 0.4699569, 101.417658, 'Jl. Soekarno - Hatta No.39d, Tengkerang Bar., Kec. Marpoyan Damai, Kota Pekanbaru, Riau 28292, Indonesia', 'spbu', '2019-07-05', '12:59:23'),
(29, 'sm_19398.jpg', 'Dilarang Parkir', 0.475409, 101.4189797, 'Jl. Soekarno - Hatta No.234, Sidomulyo Tim., Kec. Marpoyan Damai, Kota Pekanbaru, Riau 28289, Indonesia', 'Dendeng Batokok', '2019-07-05', '13:21:50'),
(30, 'sm_15294.jpg', 'Dilarang Masuk', 0.4775567, 101.3767186, 'Unnamed Road, Simpang Baru, Kec. Tampan, Kota Pekanbaru, Riau 28292, Indonesia', 'bm 6331 ys', '2019-07-18', '09:15:52');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_pelapor`
--

CREATE TABLE `tbl_pelapor` (
  `id_pelapor` int(11) NOT NULL,
  `nohp` varchar(12) NOT NULL,
  `kode` varchar(8) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_pelapor`
--

INSERT INTO `tbl_pelapor` (`id_pelapor`, `nohp`, `kode`, `status`) VALUES
(8, '082165339172', 'G4CHJRP', 2),
(9, '085264686080', 'HWGT1F3', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_petugas`
--

CREATE TABLE `tbl_petugas` (
  `id_petugas` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `user` varchar(50) NOT NULL,
  `password` varchar(300) NOT NULL,
  `foto` varchar(300) NOT NULL,
  `level` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_petugas`
--

INSERT INTO `tbl_petugas` (`id_petugas`, `name`, `user`, `password`, `foto`, `level`) VALUES
(1, 'samuel', 'samuel', '8cb2237d0679ca88db6464eac60da96345513964', 'avatar-31.jpg', 2),
(2, 'Rudi', 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'avatar.jpg', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_tugas`
--

CREATE TABLE `tbl_tugas` (
  `id_tugas` int(11) NOT NULL,
  `id_petugas` int(11) NOT NULL,
  `waktu_on` time NOT NULL,
  `waktu_off` time NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_tugas`
--

INSERT INTO `tbl_tugas` (`id_tugas`, `id_petugas`, `waktu_on`, `waktu_off`, `tanggal`) VALUES
(1, 1, '03:26:15', '03:27:41', '2019-05-28'),
(80, 0, '17:06:43', '08:58:53', '2019-07-02'),
(81, 0, '06:23:04', '08:58:53', '2019-07-03'),
(82, 0, '15:44:55', '08:58:53', '2019-07-03'),
(83, 0, '16:46:13', '08:58:53', '2019-07-07'),
(84, 0, '12:24:17', '08:58:53', '2019-07-08'),
(85, 0, '12:37:29', '08:58:53', '2019-07-08'),
(86, 0, '15:07:00', '08:58:53', '2019-07-08'),
(87, 0, '15:07:55', '08:58:53', '2019-07-08'),
(88, 0, '15:26:44', '08:58:53', '2019-07-08'),
(89, 0, '21:28:44', '08:58:53', '2019-07-10'),
(90, 0, '21:29:26', '08:58:53', '2019-07-10'),
(91, 0, '22:12:58', '08:58:53', '2019-07-10'),
(92, 0, '22:18:35', '08:58:53', '2019-07-10'),
(93, 0, '22:19:44', '08:58:53', '2019-07-10'),
(94, 0, '13:53:17', '08:58:53', '2019-07-15'),
(95, 0, '18:32:54', '08:58:53', '2019-07-17'),
(96, 0, '06:52:56', '08:58:53', '2019-07-18'),
(97, 0, '08:57:11', '08:58:53', '2019-07-18'),
(98, 0, '08:58:36', '08:58:53', '2019-07-18'),
(99, 0, '09:16:55', '09:16:55', '2019-07-18'),
(100, 0, '19:43:31', '19:43:31', '2019-07-20'),
(101, 0, '20:05:08', '20:05:08', '2019-07-20');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_aksi`
--
ALTER TABLE `tbl_aksi`
  ADD PRIMARY KEY (`id_aksi`),
  ADD KEY `tbl_aksi_ibfk_1` (`id_laporan`);

--
-- Indexes for table `tbl_laporan`
--
ALTER TABLE `tbl_laporan`
  ADD PRIMARY KEY (`id_laporan`);

--
-- Indexes for table `tbl_pelapor`
--
ALTER TABLE `tbl_pelapor`
  ADD PRIMARY KEY (`id_pelapor`);

--
-- Indexes for table `tbl_petugas`
--
ALTER TABLE `tbl_petugas`
  ADD PRIMARY KEY (`id_petugas`);

--
-- Indexes for table `tbl_tugas`
--
ALTER TABLE `tbl_tugas`
  ADD PRIMARY KEY (`id_tugas`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_aksi`
--
ALTER TABLE `tbl_aksi`
  MODIFY `id_aksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `tbl_laporan`
--
ALTER TABLE `tbl_laporan`
  MODIFY `id_laporan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `tbl_pelapor`
--
ALTER TABLE `tbl_pelapor`
  MODIFY `id_pelapor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tbl_petugas`
--
ALTER TABLE `tbl_petugas`
  MODIFY `id_petugas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_tugas`
--
ALTER TABLE `tbl_tugas`
  MODIFY `id_tugas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_aksi`
--
ALTER TABLE `tbl_aksi`
  ADD CONSTRAINT `tbl_aksi_ibfk_1` FOREIGN KEY (`id_laporan`) REFERENCES `tbl_laporan` (`id_laporan`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
