-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  sam. 07 mars 2020 à 12:28
-- Version du serveur :  5.7.26
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gallery_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `album`
--

DROP TABLE IF EXISTS `album`;
CREATE TABLE IF NOT EXISTS `album` (
  `album_id` int(11) NOT NULL AUTO_INCREMENT,
  `album_name` varchar(255) DEFAULT NULL,
  `SHARED` tinyint(1) DEFAULT '0',
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`album_id`),
  KEY `FK_ALBUM_userid` (`userid`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `album`
--

INSERT INTO `album` (`album_id`, `album_name`, `SHARED`, `userid`) VALUES
(1, 'vacance', 1, 1),
(2, 'tabaski', 1, 1),
(3, 'Jagwar', 1, 1),
(4, 'tabaski', 1, 2),
(5, 'korite', 0, 2);

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `image`
--

DROP TABLE IF EXISTS `image`;
CREATE TABLE IF NOT EXISTS `image` (
  `IMAGEID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATED` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `HEIGTH` int(11) DEFAULT NULL,
  `image_path` longtext,
  `MODIFIED` datetime DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `WIDTH` int(11) DEFAULT NULL,
  `album_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`IMAGEID`),
  KEY `FK_IMAGE_category_id` (`category_id`),
  KEY `FK_IMAGE_userid` (`userid`),
  KEY `FK_IMAGE_album_id` (`album_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `image`
--

INSERT INTO `image` (`IMAGEID`, `CREATED`, `DESCRIPTION`, `HEIGTH`, `image_path`, `MODIFIED`, `TITLE`, `WIDTH`, `album_id`, `category_id`, `userid`) VALUES
(1, '2020-03-03 18:30:49', 'Nature', 12, '20823568_farcry4_nvidiatrailer_ign-1415645949950.jpg', NULL, 'Jagwar', 5, 3, NULL, 1),
(2, '2020-03-03 18:31:29', 'Ecole', 12, 'Milkzo.jpg', NULL, 'Malik', 7, 1, NULL, 1),
(3, '2020-03-03 18:32:12', 'Nature', 8, 'chituwa.jpg', NULL, 'Jagwar', 5, 3, NULL, 1),
(4, '2020-03-03 18:55:10', 'Fete', 12, 'Mane ak sama petite.jpg', NULL, 'Tabaski', 11, 2, NULL, 1),
(5, '2020-03-03 20:44:19', 'Ecole', 12, 'JavaDeveloperRolesandResponsibilities_Final.jpg', NULL, 'Java', 12, 4, NULL, 2),
(6, '2020-03-03 21:00:15', 'Le jour de la koritÃ©', 1, 'un grand.jpg', NULL, 'KoritÃ© Days', 2, 5, NULL, 2),
(7, '2020-03-03 21:00:47', 'Le jour de la koritÃ©', 1, 'un grand.jpg', NULL, 'KoritÃ© Days', 2, 4, NULL, 2);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `USERID` int(11) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PHOTOURL` varchar(255) DEFAULT NULL,
  `ROLE` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USERID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`USERID`, `EMAIL`, `PASSWORD`, `PHOTOURL`, `ROLE`, `USERNAME`) VALUES
(1, 'malickmbengue95@hotmail.fr', 'aa', NULL, 'admin', 'admin'),
(2, 'malickmbengue95@hotmail.fr', 'qq', NULL, 'user', 'Malik'),
(3, 'aicha@gmail.com', 'aa', NULL, 'user', 'aicha');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
