-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 30, 2017 at 01:26 PM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `member_info`
--

CREATE TABLE `member_info` (
  `username` varchar(30) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `id` varchar(20) NOT NULL,
  `dep` varchar(5) DEFAULT NULL,
  `sec` varchar(3) DEFAULT NULL,
  `year` varchar(5) DEFAULT NULL,
  `sem` varchar(5) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member_info`
--

INSERT INTO `member_info` (`username`, `name`, `id`, `dep`, `sec`, `year`, `sem`, `email`, `mobile`) VALUES
('eaf', 'dsf', 'fd', 'a', 'a', 'a', 'ad', 'd', 'd'),
('Emu', 'MD . Sajedul ', '123456', 'CSE', 'A', '1ST', '2ND', 'emo@gmail.com', '01700949490'),
('rafiqreephat', 'Md. Rofiqul Islam', '160104011', 'CSE', 'A', '2ND', '1ST', 'rafiqreefat@gmail.com', '018xxxxxxxxx'),
('reasad', 'Md. Reasad Zaman', '16010404', 'CSE', 'A', '2ND', '1ST', 'reasadzaman@gmail.com', '017xxxxxxxxx'),
('rezahasan', 'Md. Ali hasan Bhuiyan', 'helloali', 'CSE', 'A', '2ND', '1ST', 'alihasanbhuiyan@gmail.com', '017ewgtewtew'),
('shibbir', 'Md. Shibbir Ahmed', 'tahsan123', 'CSE', 'A', '2ND', '1ST', 'shibbirtahsan@gmail.com', '017mithila123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `member_info`
--
ALTER TABLE `member_info`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `id` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
