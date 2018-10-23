-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 23, 2018 at 06:15 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fakeapples`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `account_ID` int(11) NOT NULL,
  `username` varchar(15) COLLATE utf8_bin NOT NULL,
  `password_hash` varchar(100) COLLATE utf8_bin NOT NULL,
  `password_salt` char(32) COLLATE utf8_bin NOT NULL,
  `email` varchar(30) COLLATE utf8_bin NOT NULL,
  `firstName` varchar(15) COLLATE utf8_bin NOT NULL DEFAULT '',
  `lastName` varchar(15) COLLATE utf8_bin NOT NULL DEFAULT '',
  `isBanned` tinyint(1) NOT NULL DEFAULT '0',
  `displayName` varchar(25) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`account_ID`, `username`, `password_hash`, `password_salt`, `email`, `firstName`, `lastName`, `isBanned`, `displayName`) VALUES
(1, 'pouchbunny', '2828d2205a8de6b982fbb3f18b26eaecfc0e8558349f3eb4c9daec1cd6755b36', '[C@2d0756da', 'pouchbunny@timbrfawks.com', '', '', 0, 'timbrfawks'),
(2, 'spgiff2651', '026492a56caaac48e2139204a4db88fc7278264efc491ec2627e2ee8d7c33c0a', '[C@5af76ef4', 'stephen@ung.edu', '', '', 0, 'stephen');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `product_ID` varchar(20) COLLATE utf8_bin NOT NULL,
  `pName` varchar(25) COLLATE utf8_bin NOT NULL,
  `pModel` varchar(15) COLLATE utf8_bin NOT NULL,
  `pDescription` varchar(500) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `purchase`
--

CREATE TABLE `purchase` (
  `purchase_ID` varchar(20) COLLATE utf8_bin NOT NULL,
  `product_ID` varchar(20) COLLATE utf8_bin NOT NULL,
  `account_ID` varchar(20) COLLATE utf8_bin NOT NULL,
  `pDate` datetime NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `review_ID` varchar(20) COLLATE utf8_bin NOT NULL,
  `account_ID` varchar(20) COLLATE utf8_bin NOT NULL,
  `product_ID` varchar(20) COLLATE utf8_bin NOT NULL,
  `rDate` datetime NOT NULL,
  `rDescription` varchar(1000) COLLATE utf8_bin NOT NULL,
  `stars` int(11) NOT NULL,
  `postingIP` varchar(15) COLLATE utf8_bin NOT NULL DEFAULT '000.000.000.000',
  `isValid` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_ID`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_ID`);

--
-- Indexes for table `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`purchase_ID`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`review_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `account_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
