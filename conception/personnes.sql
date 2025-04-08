-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 04 avr. 2025 à 07:56
-- Version du serveur : 9.1.0
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `parcinfo`
--

--
-- Déchargement des données de la table `personnes`
--

INSERT INTO `personnes` (`id_personne`, `adresse`, `date_naissance`, `nom`, `prenom`, `telephone`) VALUES
(1, '1 rue du pré Longeau', '1960-05-28', 'Condé', 'Geneviève', '0362742742'),
(2, '67b rue de la Cheneau', '1992-12-21', 'Kuntz', 'Lucas', '07788810469'),
(3, '4 rue de Sarre', '1978-10-24', 'Pierson', 'Hervé', '0387215487');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
