-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 28, 2018 at 01:14 AM
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
-- Table structure for table `blacklist`
--

CREATE TABLE `blacklist` (
  `ipListID` int(11) NOT NULL,
  `ipAddress` varchar(15) COLLATE utf8_bin NOT NULL DEFAULT '000.000.000.000'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `blacklist`
--

INSERT INTO `blacklist` (`ipListID`, `ipAddress`) VALUES
(18, '1.1.1.1'),
(9, '11.103.138.200'),
(13, '141.51.239.53'),
(10, '149.2.140.101'),
(4, '168.192.0.0'),
(12, '18.91.79.175'),
(14, '181.181.181.113'),
(7, '19.49.190.136'),
(3, '192.168.1.1'),
(1, '239.32.002.0'),
(15, '24.154.236.1'),
(6, '247.208.12.197'),
(8, '25.201.179.240'),
(5, '255.255.255.255'),
(2, '3.3.3.3'),
(16, '7.7.7.7'),
(11, '82.235.234.220');

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `reviewID` int(11) NOT NULL,
  `postingIP` varchar(15) COLLATE utf8_bin NOT NULL DEFAULT '000.000.000.000',
  `reviewText` varchar(1000) COLLATE utf8_bin NOT NULL,
  `rating` int(11) DEFAULT NULL,
  `isValid` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`reviewID`, `postingIP`, `reviewText`, `rating`, `isValid`) VALUES
(1, '23.229.226.12', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 3, 1),
(2, '126.196.176.42', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 3, 1),
(3, '21.159.147.21', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 3, 1),
(4, '144.126.122.249', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 3, 1),
(5, '35.252.209.134', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 5, 1),
(6, '82.214.196.11', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 4, 1),
(7, '7.7.7.7', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 5, 0),
(8, '149.2.140.101', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 1, 1),
(9, '254.93.209.4', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 5, 1),
(10, '179.2.162.113', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 4, 1),
(11, '59.205.185.29', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 4, 1),
(12, '150.188.129.82', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 3, 1),
(13, '32.83.84.69', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 2, 1),
(14, '4.243.62.201', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 4, 1),
(15, '20.99.37.227', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 4, 1),
(16, '92.221.68.120', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 3, 1),
(17, '7.7.7.7', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 5, 0),
(18, '209.2.241.2', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 2, 1),
(19, '243.235.136.220', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 4, 1),
(20, '211.12.217.54', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 3, 1),
(21, '24.154.236.1', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 1, 1),
(22, '81.236.109.123', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 3, 1),
(23, '120.205.199.190', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 2, 1),
(24, '24.154.236.1', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 1, 1),
(25, '49.247.88.151', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 2, 1),
(26, '163.27.35.75', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 3, 1),
(27, '7.7.7.7', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 2, 0),
(28, '59.33.202.5', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 4, 1),
(29, '222.184.145.46', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 1, 1),
(30, '222.184.145.46', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 1, 1),
(31, '222.184.145.46', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 1, 1),
(32, '5.5.5.5', 'test test test', 5, 1),
(33, '1.2.3.4', 'Example Review Text', 5, 1),
(55, '1.1.1.1', 'Example New Reivew Text', 5, 1),
(56, '9.9.9.9', 'Example Flagged Text', 5, 0),
(57, '67.120.27.90', 'Example New Reivew Text', 5, 1),
(58, '147.90.85.182', 'Example New Reivew Text', 5, 1),
(59, '112.158.80.153', 'Example New Reivew Text', 5, 1),
(60, '238.50.159.223', 'Example New Reivew Text', 5, 1),
(61, '109.96.6.100', 'Example New Reivew Text', 5, 1),
(62, '5.65.191.163', 'Example New Reivew Text', 5, 1),
(63, '70.189.102.76', 'Example New Reivew Text', 5, 1),
(64, '7.21.47.82', 'Example New Reivew Text', 5, 1),
(65, '202.163.157.60', 'Example New Reivew Text', 5, 1),
(66, '103.164.144.17', 'Example New Reivew Text', 5, 1),
(67, '213.170.26.90', 'Example New Reivew Text', 5, 1),
(68, '3.93.147.79', 'Example New Reivew Text', 5, 1),
(69, '56.135.7.133', 'Example New Reivew Text', 5, 1),
(70, '9.3.166.34', 'Example New Reivew Text', 5, 1),
(71, '114.48.35.103', 'Example New Reivew Text', 5, 1),
(72, '51.236.172.4', 'Example New Reivew Text', 5, 1),
(73, '130.5.169.125', 'Example New Reivew Text', 5, 1),
(74, '110.29.184.21', 'Example New Reivew Text', 5, 1),
(75, '112.13.29.193', 'Example New Reivew Text', 5, 1),
(76, '143.154.123.106', 'Example New Reivew Text', 5, 1),
(77, '151.234.2.129', 'Example New Reivew Text', 5, 1),
(78, '141.51.239.53', 'Example Flagged Text', 5, 0),
(79, '147.169.50.27', 'Example New Reivew Text', 5, 1),
(80, '198.14.196.27', 'Example New Reivew Text', 5, 1),
(81, '1.1.1.1', 'Example Flagged Text', 5, 0),
(82, '1.1.1.1', 'Example New Reivew Text', 5, 1),
(83, '1.1.1.1', 'Example New Reivew Text', 5, 1),
(85, '105.27.154.85', 'test', 2, 1),
(86, '212.2.55.239', 'asdfad', 4, 1),
(87, '163.117.156.148', 'ilfghop;sverr65h 6j765', 5, 1),
(88, '51.72.231.91', 'lshr6 8j9l  ertpp\'[\'\'', 5, 1),
(89, '103.110.49.67', 'asdf asdf sadf', 1, 1),
(90, '201.48.188.203', 'asdfASE FEWF E', 1, 1),
(91, '244.70.76.170', 'a sdfawfwefwe', 1, 1),
(93, '3.3.3.3', 'This is a review.', 3, 1),
(94, '55.92.173.176', 'drop table where 1=1', 3, 1),
(95, '248.92.246.147', 'test test test', 5, 1),
(96, '239.32.002.0', 'This is a fake review.', 4, 1),
(97, '149.2.140.101', 'THIS is now a fake review.', 2, 1),
(98, '7.7.7.7', 'lol', 3, 1),
(99, '255.255.255.255', 'Finally a fake review!', 5, 0),
(100, '19.49.190.136', 'Yet another fake review.', 1, 0),
(101, '255.255.255.255', 'What is this all about?', 2, 0),
(102, '35.43.77.160', 'asdfasdfasdfasdfasdfasdfasdf', 4, 1),
(103, '223.47.2.221', 'asdfadsf', 1, 1),
(104, '202.142.137.151', 'asdfadsf', 4, 1),
(105, '24.154.236.1', 'FAKE review', 4, 0),
(106, '141.51.239.53', 'pu h', 3, 0),
(107, '81.145.224.162', 'ug 087tr08 tyufjg9-8y pghjgp ', 3, 1),
(108, '19.49.190.136', 'ug 087tr08 tyufjg9-8y pghjgp ', 3, 0),
(109, '168.192.0.0', 'This review is in a jar, NOT intellij. Blacklist is 50% chance.', 3, 0),
(110, '25.201.179.240', 'This review is in a jar, NOT intellij. Blacklist is 50% chance.', 3, 0),
(111, '77.57.36.10', 'This review is in a jar, NOT intellij. Blacklist is 50% chance.', 3, 1),
(112, '141.51.239.53', 'asdfsadf 4tj576 kkimuntrsfa D32', 4, 0),
(113, '25.201.179.240', 'asdfsadf 4tj576 kkimuntrsfa D32', 4, 0),
(114, '168.192.0.0', 'asdfsadf 4tj576 kkimuntrsfa D32', 4, 0),
(115, '91.191.10.50', 'asdfsadf 4tj576 kkimuntrsfa D32', 4, 1),
(116, '27.20.214.144', 'fja ;ldkj[qrew\npojqw;lkdjf poea\nj4\"Pwe', 4, 1),
(117, '30.202.161.57', 'fja ;ldkj[qrew\npojqw;lkdjf poea\nj4\"Pwe', 4, 1),
(118, '249.62.135.226', 'fja ;ldkj[qrew\npojqw;lkdjf poea\nj4\"Pwe', 4, 1),
(119, '154.63.133.195', 'fja ;ldkj[qrew\npojqw;lkdjf poea\nj4\"Pwe', 4, 1),
(120, '159.96.158.101', 'fja ;ldkj[qrew\npojqw;lkdjf poea\nj4\"Pwe', 4, 1),
(121, '5.193.108.95', 'fja ;ldkj[qrew\npojqw;lkdjf poea\nj4\"Pwe', 4, 1),
(122, '80.42.240.40', 'fja ;ldkj[qrew\npojqw;lkdjf poea\nj4\"Pwe', 4, 1),
(123, '19.49.190.136', 'fja ;ldkj[qrew\npojqw;lkdjf poea\nj4\"Pwe', 4, 0),
(124, '247.208.12.197', 'test', 3, 0),
(125, '4.4.31.138', 'I promised to look after a friends cat for the week. My place has a glass atrium that goes through two levels, I have put the cat in there with enough food and water to last the week. I am looking forward to the end of the week. It is just sitting there glaring at me, it doesn\'t do anything else. I can tell it would like to kill me. If I knew I could get a perfect replacement cat, I would kill this one now and replace it Friday afternoon. As we sit here glaring at each other I have already worked out several ways to kill it.\nThe simplest would be to drop heavy items on it from the upstairs bedroom though I have enough basic engineering knowledge to assume that I could build some form of \'spear like\' projectile device from parts in the downstairs shed. If the atrium was waterproof, the most entertaining would be to flood it with water. It wouldn\'t have to be that deep, just deeper than the cat.', 3, 1),
(126, '25.201.179.240', 'This is a review for an imaginary product. We wanted to allow more for a log in and product page but ended up going with this. The back end is via SQL database. And only allows for a limit of around 1000 characters.', 5, 0),
(127, '168.192.0.0', 'This is a review for an imaginary product. We wanted to allow more for a log in and product page but ended up going with this. The back end is via SQL database. And only allows for a limit of around 1000 characters.', 5, 0),
(128, '78.226.52.90', 'This is a review for an imaginary product. We wanted to allow more for a log in and product page but ended up going with this. The back end is via SQL database. And only allows for a limit of around 1000 characters.', 5, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `blacklist`
--
ALTER TABLE `blacklist`
  ADD PRIMARY KEY (`ipListID`),
  ADD UNIQUE KEY `ipAddress` (`ipAddress`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`reviewID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `blacklist`
--
ALTER TABLE `blacklist`
  MODIFY `ipListID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `reviewID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=129;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
