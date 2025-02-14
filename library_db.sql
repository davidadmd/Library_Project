-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 14 fév. 2025 à 18:49
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `library_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `books`
--

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `isbn` varchar(255) NOT NULL,
  `status` varchar(50) NOT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `edition` varchar(255) DEFAULT NULL,
  `loanDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `books`
--

INSERT INTO `books` (`id`, `title`, `author`, `isbn`, `status`, `genre`, `edition`, `loanDate`) VALUES
(1, 'Harry Potter', 'JK Potter', 'ISBN1737119838243', 'Available', NULL, NULL, NULL),
(3, 'La femme de ménage qui voit tout', 'Freida McFadden', 'ISBN1737123661240', 'Available', 'Roman', 'City', NULL),
(4, 'hh', 'hh', 'ISBN1738338857021', 'Available', 'hh', 'hh', NULL),
(5, '.21', '21', 'ISBN1738338874985', 'Available', '12', '12', NULL),
(6, 'klkl', 'klklk', 'ISBN1738338891687', 'Available', 'lklklk', ',kl,klk,', NULL),
(8, 'olo', 'ollk', 'ISBN1739442933563', 'Loaned', 'olo', 'mlo', '2025-02-14');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `isbn` (`isbn`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `books`
--
ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
