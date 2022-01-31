-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Sep 05, 2020 at 03:01 PM
-- Server version: 10.3.23-MariaDB-log-cll-lve
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `posyand6_posyandu_ns`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_bayi`
--

CREATE TABLE `tb_bayi` (
  `id_bayi` tinyint(2) NOT NULL,
  `nama_bayi` varchar(50) NOT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `jk_bayi` varchar(10) NOT NULL,
  `nama_ibu` varchar(50) NOT NULL,
  `nama_ayah` varchar(50) NOT NULL,
  `kk_bayi` varchar(16) NOT NULL,
  `alamat_bayi` text NOT NULL,
  `anak_ke` varchar(1) NOT NULL,
  `berat_lahir` varchar(10) NOT NULL,
  `sasaran` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_bayi`
--

INSERT INTO `tb_bayi` (`id_bayi`, `nama_bayi`, `tgl_lahir`, `jk_bayi`, `nama_ibu`, `nama_ayah`, `kk_bayi`, `alamat_bayi`, `anak_ke`, `berat_lahir`, `sasaran`) VALUES
(1, 'Dhania Zahrotuzakiyah', '2019-08-31', 'Perempuan', 'Dahliana', 'Hubuhus', '6371044402200003', 'Anggrek IV No. 45 RT. 24', '1', '3,7 Kg', 'Sasaran'),
(2, 'Alesya Nur Naila', '2019-10-16', 'Perempuan', 'Antung Murniah', 'Nurhikmatullah', '6371041701080143', 'Taman Pesona No. 30', '3', '2,7 Kg', 'Bukan Sasaran'),
(7, 'M. Rizkon Habibi', '2018-10-20', 'Laki-Laki', 'Raudhatul Janah', 'Gt. Elfinur', '-', 'Jl. Padat Karua No.50 Rt. 37', '1', '-', 'Sasaran'),
(9, 'Anandito', '2018-03-29', 'Laki-Laki', 'Harsi Tuti', 'Singgih', '6372016904750022', 'Nusa Indah No. 22 Rt. 23 Rw. 02', '3', '3 Kg', 'Sasaran'),
(10, 'Raffa Al Farizi', '2017-06-23', 'Laki-Laki', 'Faridah Ariyani', 'Rafii Hamli', '6371071910810004', 'Komp. Nusa Indah No.1', '3', '2,4 Kg', 'Sasaran'),
(11, 'Ashadia Keysa Nafisa', '2018-10-12', 'Perempuan', 'Sonia Anisa', 'M. Dayan', '-', 'Komp. Nusa Indah No. 59', '1', '3 Kg', 'Sasaran'),
(12, 'Dinar', '2017-01-07', 'Perempuan', 'Maulida', 'M. Rizqi', '-', 'Komp. Nusa Indah No. 60', '2', '2,7 Kg', 'Sasaran'),
(13, 'Arjuna Tri Putra', '2016-02-11', 'Laki-Laki', 'Atiah Kirana Perdanasari', 'Ariyadi Beni', '6371042001080896', 'Anggrek IV No. 51 Rt. 24', '3', '3,7 Kg', 'Sasaran'),
(14, 'Abizar M. Ghifari', '2018-06-25', 'Laki-Laki', 'Siti Fatimah', 'Supian Hadi', '6371040804130008', 'Melati No. 48 Rt. 22', '3', '2,8 Kg', 'Sasaran'),
(15, 'M. Zaini Ghoni Febrian L.', '2020-02-26', 'Laki-Laki', 'Rusmini', 'M. Maydi J. Lampus', '6371040809090016', 'Anggrek 2 Rt. 24 No. 30', '5', '-', 'Sasaran'),
(16, 'M. Ibnu Miladri L.', '2018-08-17', 'Laki-Laki', 'Rusmini', 'M. Mahdi J. Lampus', '6371040809090016', 'Anggrek 2 Rt.24 No. 30', '4', '3,2 Kg', 'Sasaran'),
(17, 'Haura Azkia', '2018-10-11', 'Perempuan', 'Jubaidah', 'M. Taufiq', '6371041601080086', 'Anggrek 10 No.46 Rt. 24', '3', '3,5 Kg', 'Sasaran'),
(18, 'Maryam Camilla Puspita', '2019-08-20', 'Perempuan', 'Ani Puspita', 'Handoyo Budiarto', '6371041507780011', 'Anggrek Raya No.83', '3', '3 Kg', 'Sasaran'),
(19, 'ade', '2020-07-16', 'Perempuan', 'Ita', 'Herly', '-', 'r', '2', '6', 'Bukan Sasaran');

-- --------------------------------------------------------

--
-- Table structure for table `tb_covid19`
--

CREATE TABLE `tb_covid19` (
  `id_covid` tinyint(2) NOT NULL,
  `id_bayi` tinyint(2) NOT NULL,
  `tanggal_covid` date NOT NULL,
  `masker` varchar(5) NOT NULL,
  `cuci_tangan` varchar(5) NOT NULL,
  `jaga_jarak` varchar(5) NOT NULL,
  `demam` varchar(10) NOT NULL,
  `sesak_napas` varchar(10) NOT NULL,
  `batuk` varchar(10) NOT NULL,
  `sakit_tenggorokan` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_covid19`
--

INSERT INTO `tb_covid19` (`id_covid`, `id_bayi`, `tanggal_covid`, `masker`, `cuci_tangan`, `jaga_jarak`, `demam`, `sesak_napas`, `batuk`, `sakit_tenggorokan`) VALUES
(21, 1, '2020-08-13', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Ada', 'Tidak Ada'),
(24, 10, '2020-08-13', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(26, 7, '2020-08-13', 'Ya', 'Ya', 'Tidak', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(27, 12, '2020-08-13', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Ada', 'Tidak Ada'),
(28, 11, '2020-08-13', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Ada'),
(29, 9, '2020-08-13', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(30, 13, '2020-08-13', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(31, 13, '2020-07-08', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(32, 13, '2020-06-11', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(33, 1, '2020-07-08', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(34, 1, '2020-06-11', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(35, 14, '2020-08-19', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(36, 15, '2020-08-13', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Ada', 'Tidak Ada'),
(37, 15, '2020-07-08', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(38, 15, '2020-06-11', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(39, 16, '2020-08-13', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(40, 16, '2020-07-08', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(41, 16, '2020-06-11', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(42, 17, '2020-08-13', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(43, 17, '2020-07-08', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(44, 17, '2020-06-11', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(45, 18, '2020-08-13', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(46, 18, '2020-07-08', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(47, 18, '2020-06-11', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Ada', 'Tidak Ada'),
(48, 2, '2020-08-13', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(49, 2, '2020-07-08', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(50, 2, '2020-06-11', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(52, 14, '2020-08-03', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada'),
(53, 1, '2020-08-21', 'Ya', 'Ya', 'Ya', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada', 'Tidak Ada');

-- --------------------------------------------------------

--
-- Table structure for table `tb_ibu_hamil`
--

CREATE TABLE `tb_ibu_hamil` (
  `id_ibu_hamil` tinyint(2) NOT NULL,
  `nama_ibu_hamil` varchar(50) NOT NULL,
  `nama_suami` varchar(50) NOT NULL,
  `hamil_ke` varchar(1) NOT NULL,
  `tanggal_pendaftaran` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_ibu_hamil`
--

INSERT INTO `tb_ibu_hamil` (`id_ibu_hamil`, `nama_ibu_hamil`, `nama_suami`, `hamil_ke`, `tanggal_pendaftaran`) VALUES
(3, 'Nourma Aninda Sari', 'Muhammad Madani', '2', '2020-01-03'),
(4, 'Sinta Mayang', 'Dimas Anggara', '2', '2020-08-20'),
(5, 'Kumala Sari', 'Benny Sujono', '1', '2020-04-09'),
(8, 'Shinta Maria', 'Sutedjo', '1', '2019-12-11'),
(9, 'Puspita Ningsih Sari', 'Alamsyah', '2', '2020-02-06'),
(10, 'Humayra Bilqis Ananda', 'Maulana Akbar', '1', '2020-02-06'),
(14, 'Rusmini', 'M. Maydi L', '5', '2020-01-03'),
(15, 'Dahlia', 'Hubihus', '1', '2020-01-03'),
(16, 'Heni', 'A. Ripani', '3', '2020-01-03'),
(17, 'Anisha Putri', 'Norhadi Putra', '2', '2020-02-06');

-- --------------------------------------------------------

--
-- Table structure for table `tb_imunisasi_bayi`
--

CREATE TABLE `tb_imunisasi_bayi` (
  `id_imunisasi` tinyint(2) NOT NULL,
  `id_bayi` tinyint(2) NOT NULL,
  `tanggal_imunisasi` date NOT NULL,
  `jenis_imunisasi` varchar(50) NOT NULL,
  `usia_pemberian` tinyint(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_imunisasi_bayi`
--

INSERT INTO `tb_imunisasi_bayi` (`id_imunisasi`, `id_bayi`, `tanggal_imunisasi`, `jenis_imunisasi`, `usia_pemberian`) VALUES
(10, 1, '2020-02-05', 'HB0', 0),
(13, 2, '2019-10-16', 'HB0', 0),
(14, 2, '2019-12-03', 'BCG', 2),
(17, 13, '2016-04-06', 'POLIO 1', 1),
(18, 13, '2016-12-07', 'Campak', 4),
(19, 13, '2017-09-07', 'DPT-HB-Hib Lanjutan', 18),
(20, 13, '2016-05-10', 'DPT-HB-Hib 1', 2),
(22, 2, '2019-12-03', 'POLIO 1', 2),
(23, 2, '2020-02-08', 'DPT-HB-Hib 1', 3),
(24, 14, '2020-08-27', 'HB0', 5),
(25, 14, '2018-07-08', 'BCG', 0),
(26, 14, '2018-07-08', 'POLIO 1', 0),
(27, 14, '2018-09-10', 'DPT-HB-Hib 1', 2),
(28, 14, '2019-06-23', 'Campak', 23),
(29, 15, '2020-02-26', 'HB0', 0),
(30, 15, '2020-03-05', 'BCG', 0),
(31, 15, '2020-03-05', 'POLIO 1', 0),
(32, 15, '2020-07-17', 'DPT-HB-Hib 1', 5),
(33, 16, '2018-08-17', 'HB0', 0),
(34, 16, '2018-10-02', 'BCG', 2),
(35, 16, '2018-10-02', 'POLIO 1', 2),
(36, 16, '2018-11-10', 'DPT-HB-Hib 1', 3),
(37, 16, '2019-12-10', 'Campak', 23),
(38, 16, '2020-03-05', 'DPT-HB-Hib Lanjutan', 24),
(39, 9, '2020-08-15', 'POLIO 1', 1),
(43, 9, '2020-08-25', 'Campak', 1),
(44, 9, '2020-08-14', 'Campak Lanjutan', 3),
(47, 19, '2022-08-20', 'DPT-HB-Hib 2, Polio 3', 4),
(49, 1, '2020-08-15', 'BCG, Polio 1', 3),
(50, 1, '2020-08-30', 'Campak', 8);

-- --------------------------------------------------------

--
-- Table structure for table `tb_jadwal_posyandu`
--

CREATE TABLE `tb_jadwal_posyandu` (
  `id_jadwal` tinyint(2) NOT NULL,
  `tgl_jadwal` date NOT NULL,
  `waktu_jadwal` varchar(25) NOT NULL,
  `acara_jadwal` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_jadwal_posyandu`
--

INSERT INTO `tb_jadwal_posyandu` (`id_jadwal`, `tgl_jadwal`, `waktu_jadwal`, `acara_jadwal`) VALUES
(2, '2020-02-06', '09.00 - Selesai', 'Penimbangan'),
(3, '2020-03-05', '09.00 - Selesai', 'Penimbangan'),
(4, '2020-04-09', '09.00 - Selesai', 'Penimbangan'),
(5, '2020-05-08', '09.00 - Selesai', 'Penimbangan'),
(6, '2020-06-11', '09.00 - Selesai', 'Penimbangan'),
(7, '2020-07-08', '09.00 - Selesai', 'Penimbangan'),
(8, '2020-08-13', '09.00 - Selesai', 'Penimbangan, Vitamin A'),
(9, '2020-09-10', '09.00 - Selesai', 'Penimbangan'),
(10, '2020-10-08', '09.00 - Selesai', 'Penimbangan'),
(11, '2020-11-05', '09.00 - Selesai', 'Penimbangan'),
(12, '2020-12-12', '09.00 - Selesai', 'Penimbangan'),
(18, '2020-01-03', '09.00 - Selesai', 'Penimbangan, Vitamin A');

-- --------------------------------------------------------

--
-- Table structure for table `tb_layanan_bayi`
--

CREATE TABLE `tb_layanan_bayi` (
  `id_layanan_bayi` tinyint(2) NOT NULL,
  `id_bayi` tinyint(2) NOT NULL,
  `berat_badan` decimal(2,1) NOT NULL,
  `tinggi_badan` decimal(2,1) NOT NULL,
  `lingkar_kepala` decimal(2,1) NOT NULL,
  `umur_sekarang` tinyint(3) NOT NULL,
  `vit_a` varchar(5) NOT NULL,
  `oralit` varchar(5) NOT NULL,
  `tanggal_layanan_bayi` date NOT NULL,
  `status_gizi` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_layanan_bayi`
--

INSERT INTO `tb_layanan_bayi` (`id_layanan_bayi`, `id_bayi`, `berat_badan`, `tinggi_badan`, `lingkar_kepala`, `umur_sekarang`, `vit_a`, `oralit`, `tanggal_layanan_bayi`, `status_gizi`) VALUES
(1, 1, 7.0, 8.0, 8.0, 10, 'Tidak', 'Tidak', '2020-07-08', 'Baik'),
(3, 1, 7.0, 8.0, 8.0, 10, 'Ya', 'Tidak', '2020-08-13', 'Baik'),
(5, 2, 4.0, 8.0, 8.0, 2, 'Ya', 'Tidak', '2020-01-03', 'Baik'),
(6, 2, 5.0, 8.0, 8.0, 3, 'Ya', 'Tidak', '2020-02-06', 'Kurang'),
(9, 9, 3.0, 8.0, 8.0, 10, 'Ya', 'Tidak', '2020-08-13', 'Baik'),
(10, 7, 9.9, 8.0, 8.0, 10, 'Ya', 'Tidak', '2020-08-13', 'Baik'),
(11, 10, 9.9, 8.0, 8.0, 10, 'Ya', 'Tidak', '2020-08-13', 'Baik'),
(12, 11, 9.9, 8.0, 8.0, 10, 'Ya', 'Tidak', '2020-08-13', 'Baik'),
(13, 12, 9.9, 8.0, 8.0, 10, 'Ya', 'Tidak', '2020-08-13', 'Baik'),
(14, 13, 9.9, 9.9, 8.0, 10, 'Ya', 'Tidak', '2020-08-13', 'Baik'),
(15, 13, 9.9, 9.9, 8.0, 10, 'Tidak', 'Tidak', '2020-07-08', 'Baik'),
(17, 13, 9.9, 9.9, 8.0, 10, 'Tidak', 'Tidak', '2020-06-11', 'Baik'),
(18, 1, 6.0, 8.0, 8.0, 10, 'Tidak', 'Tidak', '2020-06-11', 'Baik'),
(19, 14, 9.9, 8.0, 8.0, 9, 'Ya', 'Tidak', '2020-08-13', 'Baik'),
(20, 15, 7.0, 8.0, 8.0, 5, 'Ya', 'Tidak', '2020-08-13', 'Baik'),
(21, 15, 6.0, 8.0, 8.0, 4, 'Tidak', 'Tidak', '2020-07-08', 'Baik'),
(22, 15, 6.0, 8.0, 8.0, 3, 'Tidak', 'Tidak', '2020-06-11', 'Baik'),
(23, 16, 9.9, 9.9, 8.0, 10, 'Ya', 'Tidak', '2020-08-13', 'Baik'),
(24, 16, 9.0, 8.0, 8.0, 10, 'Tidak', 'Tidak', '2020-06-11', 'Baik'),
(25, 16, 9.9, 8.0, 8.0, 10, 'Tidak', 'Tidak', '2020-07-08', 'Baik'),
(26, 17, 9.0, 8.0, 8.0, 10, 'Ya', 'Tidak', '2020-08-13', 'Baik'),
(27, 17, 8.0, 8.0, 8.0, 10, 'Tidak', 'Tidak', '2020-06-11', 'Baik'),
(28, 17, 9.0, 8.0, 8.0, 10, 'Tidak', 'Tidak', '2020-07-08', 'Baik'),
(29, 18, 8.0, 9.9, 8.0, 10, 'Ya', 'Tidak', '2020-08-13', 'Baik'),
(30, 18, 7.0, 9.9, 8.0, 10, 'Tidak', 'Tidak', '2020-07-08', 'Baik'),
(31, 18, 7.0, 9.9, 8.0, 9, 'Tidak', 'Tidak', '2020-06-11', 'Baik'),
(32, 2, 8.0, 9.9, 8.0, 10, 'Ya', 'Tidak', '2020-08-13', 'Baik'),
(33, 2, 7.0, 9.9, 8.0, 10, 'Tidak', 'Tidak', '2020-07-08', 'Baik'),
(34, 2, 7.0, 9.9, 8.0, 10, 'Tidak', 'Tidak', '2020-06-11', 'Baik'),
(36, 1, 7.0, 9.9, 8.0, 2, 'Ya', 'Tidak', '2020-08-25', 'Baik'),
(38, 1, 7.3, 5.6, 2.6, 5, 'Tidak', 'Tidak', '2020-08-14', 'Buruk'),
(39, 1, 3.4, 5.6, 3.6, 3, 'Tidak', 'Ya', '2017-08-01', 'Baik'),
(41, 1, 5.9, 6.9, 9.9, 5, 'Tidak', 'Ya', '2016-08-30', 'Baik');

-- --------------------------------------------------------

--
-- Table structure for table `tb_layanan_ibu_hamil`
--

CREATE TABLE `tb_layanan_ibu_hamil` (
  `id_layanan_ibu_hamil` tinyint(3) NOT NULL,
  `hasil_penimbangan` varchar(10) NOT NULL,
  `pmt_pemulihan` varchar(50) NOT NULL,
  `tanggal_layanan` date NOT NULL,
  `id_ibu_hamil` tinyint(2) NOT NULL,
  `umur_kehamilan` varchar(10) NOT NULL,
  `lingkar_lengan` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_layanan_ibu_hamil`
--

INSERT INTO `tb_layanan_ibu_hamil` (`id_layanan_ibu_hamil`, `hasil_penimbangan`, `pmt_pemulihan`, `tanggal_layanan`, `id_ibu_hamil`, `umur_kehamilan`, `lingkar_lengan`) VALUES
(21, '57 Kg', 'Snack Ibu Hamil', '2020-02-06', 3, '6 Minggu', '26 cm'),
(24, '60 Kg', 'Bubur', '2020-01-03', 8, '5 Minggu', '23,8 cm'),
(25, '55 Kg', 'Snack Ibu Hamil', '2020-02-06', 9, '3 Minggu', '22,4 cm'),
(26, '60 Kg', 'Bubur', '2020-02-06', 10, '4 Minggu', '24 cm'),
(30, '53', 'Snack Ibu Hamil', '2019-04-09', 4, '5', '21'),
(31, '54 Kg', 'Snack Ibu Hamil', '2020-01-03', 5, '3 Minggu', '22 cm'),
(32, '63 Kg', 'Bubur, Snack Ibu Hamil', '2020-02-06', 8, '9 Minggu', '24 cm'),
(41, '50 Kg', 'Snack, Bubur ', '2020-01-03', 3, '2 Minggu ', '23,5 cm'),
(42, '57 Kg', 'Bubur', '2019-05-15', 4, '10 Minggu', '23 cm'),
(43, '60 Kg', 'Bubur', '2019-06-05', 4, '12 Minggu', '23,6 cm'),
(44, '59 Kg', 'Snack Ibu Hamil', '2019-07-04', 4, '16 Minggu', '22,6 cm'),
(45, '62 Kg', 'Bubur ', '2019-08-06', 4, '20 Minggu', '23,8 cm'),
(46, '60 Kg', 'Bubur', '2020-02-06', 5, '7 Minggu', '23 cm'),
(47, '68 Kg', 'Snack Ibu Hamil', '2020-01-03', 14, '32 Minggu', '23,5 cm'),
(48, '65 Kg', 'Snack Ibu Hamil', '2020-02-06', 14, '36 Minggu', '22 cm'),
(49, '70 Kg', 'Bubur', '2020-01-03', 15, '34 Minggu', '25 cm'),
(50, '57 Kg', 'Snack Ibu Hamil, Bubur', '2020-01-03', 16, '16 Minggu', '22 cm'),
(51, '59 Kg', 'Bubur', '2020-02-06', 16, '20 Minggu', '22,5 cm'),
(52, '63 Kg', 'Bubur', '2020-02-06', 17, '32 Minggu', '23,3 cm'),
(54, '3', 'snack', '2018-08-30', 4, '5', '65');

-- --------------------------------------------------------

--
-- Table structure for table `tb_login_pengunjung`
--

CREATE TABLE `tb_login_pengunjung` (
  `id_login_pengunjung` tinyint(2) NOT NULL,
  `id_bayi` tinyint(2) NOT NULL,
  `username_pengunjung` varchar(25) CHARACTER SET latin1 COLLATE latin1_bin NOT NULL,
  `password_pengunjung` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_login_pengunjung`
--

INSERT INTO `tb_login_pengunjung` (`id_login_pengunjung`, `id_bayi`, `username_pengunjung`, `password_pengunjung`) VALUES
(5, 13, 'Arjuna', 'e10adc3949ba59abbe56e057f20f883e'),
(6, 14, 'Abizar', 'e10adc3949ba59abbe56e057f20f883e'),
(7, 16, 'Ibnu', 'e10adc3949ba59abbe56e057f20f883e'),
(8, 15, 'Zaini', 'e10adc3949ba59abbe56e057f20f883e'),
(14, 1, 'Dhania', 'e10adc3949ba59abbe56e057f20f883e');

-- --------------------------------------------------------

--
-- Table structure for table `tb_login_petugas`
--

CREATE TABLE `tb_login_petugas` (
  `id_login_petugas` tinyint(2) NOT NULL,
  `username_petugas` varchar(25) CHARACTER SET latin1 COLLATE latin1_bin NOT NULL,
  `password_petugas` varchar(255) NOT NULL,
  `id_petugas` tinyint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_login_petugas`
--

INSERT INTO `tb_login_petugas` (`id_login_petugas`, `username_petugas`, `password_petugas`, `id_petugas`) VALUES
(1, 'Ita', 'e10adc3949ba59abbe56e057f20f883e', 1),
(2, 'Ermin', 'e10adc3949ba59abbe56e057f20f883e', 2);

-- --------------------------------------------------------

--
-- Table structure for table `tb_petugas`
--

CREATE TABLE `tb_petugas` (
  `id_petugas` tinyint(2) NOT NULL,
  `nama_petugas` varchar(25) NOT NULL,
  `jabatan` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_petugas`
--

INSERT INTO `tb_petugas` (`id_petugas`, `nama_petugas`, `jabatan`) VALUES
(1, 'Ita Susilowati', 'Kader'),
(2, 'Hj. Ermin', 'Gizi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_bayi`
--
ALTER TABLE `tb_bayi`
  ADD PRIMARY KEY (`id_bayi`);

--
-- Indexes for table `tb_covid19`
--
ALTER TABLE `tb_covid19`
  ADD PRIMARY KEY (`id_covid`),
  ADD KEY `id_bayi` (`id_bayi`);

--
-- Indexes for table `tb_ibu_hamil`
--
ALTER TABLE `tb_ibu_hamil`
  ADD PRIMARY KEY (`id_ibu_hamil`);

--
-- Indexes for table `tb_imunisasi_bayi`
--
ALTER TABLE `tb_imunisasi_bayi`
  ADD PRIMARY KEY (`id_imunisasi`),
  ADD KEY `id_bayi` (`id_bayi`);

--
-- Indexes for table `tb_jadwal_posyandu`
--
ALTER TABLE `tb_jadwal_posyandu`
  ADD PRIMARY KEY (`id_jadwal`);

--
-- Indexes for table `tb_layanan_bayi`
--
ALTER TABLE `tb_layanan_bayi`
  ADD PRIMARY KEY (`id_layanan_bayi`),
  ADD KEY `id_bayi` (`id_bayi`),
  ADD KEY `id_bayi_2` (`id_bayi`);

--
-- Indexes for table `tb_layanan_ibu_hamil`
--
ALTER TABLE `tb_layanan_ibu_hamil`
  ADD PRIMARY KEY (`id_layanan_ibu_hamil`),
  ADD KEY `id_ibu_hamil` (`id_ibu_hamil`);

--
-- Indexes for table `tb_login_pengunjung`
--
ALTER TABLE `tb_login_pengunjung`
  ADD PRIMARY KEY (`id_login_pengunjung`),
  ADD KEY `id_bayi` (`id_bayi`),
  ADD KEY `id_bayi_2` (`id_bayi`);

--
-- Indexes for table `tb_login_petugas`
--
ALTER TABLE `tb_login_petugas`
  ADD PRIMARY KEY (`id_login_petugas`),
  ADD KEY `id_petugas` (`id_petugas`);

--
-- Indexes for table `tb_petugas`
--
ALTER TABLE `tb_petugas`
  ADD PRIMARY KEY (`id_petugas`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_bayi`
--
ALTER TABLE `tb_bayi`
  MODIFY `id_bayi` tinyint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `tb_covid19`
--
ALTER TABLE `tb_covid19`
  MODIFY `id_covid` tinyint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `tb_ibu_hamil`
--
ALTER TABLE `tb_ibu_hamil`
  MODIFY `id_ibu_hamil` tinyint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `tb_imunisasi_bayi`
--
ALTER TABLE `tb_imunisasi_bayi`
  MODIFY `id_imunisasi` tinyint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `tb_jadwal_posyandu`
--
ALTER TABLE `tb_jadwal_posyandu`
  MODIFY `id_jadwal` tinyint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `tb_layanan_bayi`
--
ALTER TABLE `tb_layanan_bayi`
  MODIFY `id_layanan_bayi` tinyint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `tb_layanan_ibu_hamil`
--
ALTER TABLE `tb_layanan_ibu_hamil`
  MODIFY `id_layanan_ibu_hamil` tinyint(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT for table `tb_login_pengunjung`
--
ALTER TABLE `tb_login_pengunjung`
  MODIFY `id_login_pengunjung` tinyint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `tb_login_petugas`
--
ALTER TABLE `tb_login_petugas`
  MODIFY `id_login_petugas` tinyint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tb_petugas`
--
ALTER TABLE `tb_petugas`
  MODIFY `id_petugas` tinyint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_covid19`
--
ALTER TABLE `tb_covid19`
  ADD CONSTRAINT `tb_covid19_ibfk_1` FOREIGN KEY (`id_bayi`) REFERENCES `tb_bayi` (`id_bayi`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tb_imunisasi_bayi`
--
ALTER TABLE `tb_imunisasi_bayi`
  ADD CONSTRAINT `tb_imunisasi_bayi_ibfk_1` FOREIGN KEY (`id_bayi`) REFERENCES `tb_bayi` (`id_bayi`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tb_layanan_bayi`
--
ALTER TABLE `tb_layanan_bayi`
  ADD CONSTRAINT `tb_layanan_bayi_ibfk_1` FOREIGN KEY (`id_bayi`) REFERENCES `tb_bayi` (`id_bayi`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tb_layanan_ibu_hamil`
--
ALTER TABLE `tb_layanan_ibu_hamil`
  ADD CONSTRAINT `tb_layanan_ibu_hamil_ibfk_1` FOREIGN KEY (`id_ibu_hamil`) REFERENCES `tb_ibu_hamil` (`id_ibu_hamil`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tb_login_pengunjung`
--
ALTER TABLE `tb_login_pengunjung`
  ADD CONSTRAINT `tb_login_pengunjung_ibfk_1` FOREIGN KEY (`id_bayi`) REFERENCES `tb_bayi` (`id_bayi`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tb_login_petugas`
--
ALTER TABLE `tb_login_petugas`
  ADD CONSTRAINT `tb_login_petugas_ibfk_1` FOREIGN KEY (`id_petugas`) REFERENCES `tb_petugas` (`id_petugas`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
