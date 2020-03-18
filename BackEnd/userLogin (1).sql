-- phpMyAdmin SQL Dump
-- version 4.9.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Mar 18, 2020 at 08:21 PM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id11747915_timetable`
--

-- --------------------------------------------------------

--
-- Table structure for table `userLogin`
--

CREATE TABLE `userLogin` (
  `studentID` int(11) NOT NULL,
  `userName` text COLLATE utf8_unicode_ci NOT NULL,
  `userEmail` text COLLATE utf8_unicode_ci NOT NULL,
  `userPassword` text COLLATE utf8_unicode_ci NOT NULL,
  `userToken` varchar(5000) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `userLogin`
--

INSERT INTO `userLogin` (`studentID`, `userName`, `userEmail`, `userPassword`, `userToken`) VALUES
(11, '11', '11', '22', 'f-ogjA-Cg_0:APA91bHbuvJVxQ5gTNtx-klPtp-51y_GP4sQ3-xdCaFWCTih21ELG0Fc-cMujy-musmgk0dAjZaBlJ4Au5VcclYl8bvtbBkp3K7Y6wTl8Asfv3jtczyJw3cHkfh8xhXJy4V7Jky22uBK'),
(123, '123', '123', '214', 'ef9a6IFXaQU:APA91bGzB5a7ipHMrI3q8vjYM3Kif99F5MOwGIgTaJw77nAalbh4HbYsODMGpjFIuQB13HUQD9dPLsMHwWiqiaCBPv3XB_GKQxY95r0mSG4uheLmVv5cQXMNf0Svz4dpvwQrNjPEd6ne'),
(999, '999', '999', '000', 'dm3-ggAsS3Y:APA91bHi2khsZKuN0nAZLh4-jdNFnTeT2TCTARj0UVokOyYbE1Ux_RNZ85nQwJCwW-EV0JMgbGQ2jf-HUd1NXpZNYCE8lhXsoc4o5qnUTEvRcPCDJiU0MxSWbjZ_iMSlMZW67KZC7d2q'),
(1122, '1122', '1122', '2211', 'dm3-ggAsS3Y:APA91bHi2khsZKuN0nAZLh4-jdNFnTeT2TCTARj0UVokOyYbE1Ux_RNZ85nQwJCwW-EV0JMgbGQ2jf-HUd1NXpZNYCE8lhXsoc4o5qnUTEvRcPCDJiU0MxSWbjZ_iMSlMZW67KZC7d2q');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `userLogin`
--
ALTER TABLE `userLogin`
  ADD PRIMARY KEY (`studentID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
