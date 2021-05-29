-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 23 mai 2021 à 22:45
-- Version du serveur :  10.4.18-MariaDB
-- Version de PHP : 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `happy`
--

-- --------------------------------------------------------

--
-- Structure de la table `abonne`
--

CREATE TABLE `abonne` (
  `id` int(11) NOT NULL,
  `nomabonne` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenomabonne` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mailabonne` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `datenaissabonne` date NOT NULL,
  `telephoneabonne` int(11) NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `roles` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `stripeid` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nomEvent` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `abonne`
--

INSERT INTO `abonne` (`id`, `nomabonne`, `prenomabonne`, `mailabonne`, `datenaissabonne`, `telephoneabonne`, `password`, `roles`, `stripeid`, `nomEvent`) VALUES
(5, 'maha', 'sg', 'maha@gmail.com', '1999-07-28', 20000000, '$2a$12$yN7xbq1ZXrA6tJ6ekii/3elgPcQdxs3k.luP24oUA66CviDlsPAou', 'abonne', NULL, 'happy hour'),
(47, 'Karma', 'Zeineb', 'zeineb.karma@esprit.tn', '1999-12-18', 55337315, '$2y$13$S7OhQuAaJi2qHTFOZdn0.ePQpIirr.Z6qviSZYYKQRr9H1elMltI.', NULL, NULL, ''),
(59, 'Samia', 'Abdelmoula', 'Samia.abedlmoula@gmailcom', '1998-04-14', 99999878, 'sdsd787  ', NULL, NULL, ''),
(60, 'Samiaaa', 'Abdelmoulaaaa', 'Samia.abedlmzoula@gmailcom', '1998-04-14', 99999878, 'sdsd787  ', NULL, NULL, ''),
(61, 'Samiaaa', 'Abdelmoulaaaa', 'Samia.abedlmzoula@gmailcom', '1998-04-14', 99999878, 'sdsd787  ', NULL, NULL, ''),
(62, 'dzeineb', 'karma', 'zeinebkarma@esprot?Tn', '1999-04-14', 9878987, 'sdsd78  ', NULL, NULL, ''),
(63, 'Mo', 'mi', 'momi@gmail.com', '1987-04-22', 9987887, 'sdqsd4  ', NULL, NULL, ''),
(64, 'Mods', 'misq', 'modmi@gmail.com', '1987-04-22', 9987888, 'sdqsd4  ', NULL, NULL, ''),
(65, 'meeed', 'midou', 'medmidou@gmail.com', '1987-04-14', 98785478, 'sdsd789  ', NULL, NULL, ''),
(66, 'semi', 'midou', 'meddmidou@gmail.com', '1989-04-14', 98785478, 'sdsd789  ', NULL, NULL, ''),
(67, 'fdfdddd', 'ddddddd', 'zeinebbb.karmaa@gmal.com', '1965-04-12', 9878987, 'qsdqsd8  ', NULL, NULL, ''),
(68, 'aaaaaaaa', 'dddddddfdfff', 'zeinebbb.karmaa@gmal.com', '1965-04-12', 9878987, 'qsdqsd8  ', NULL, NULL, ''),
(69, 'zizou', 'zezezez', 'zeazeaze@gmail.com', '2021-04-14', 9856895, 'edzed78  ', NULL, NULL, ''),
(70, 'john', 'johnny', 'john.johnny@gmail.com', '1999-04-15', 99854787, 'qsdqsd  ', NULL, NULL, ''),
(71, 'john', 'johnny', 'john.johnny@gmail.com', '1999-04-15', 99854787, 'qsdqsd  ', NULL, NULL, ''),
(72, 'zeinebdd', 'qsdqsd', 'qsdqsdqsd', '1986-04-14', 8558585, 'sdfsdf  ', NULL, NULL, ''),
(73, 'zeinebdd', 'qsdqsd', 'qsdqsdqsd', '1986-04-14', 8558585, 'sdfsdf  ', NULL, NULL, ''),
(74, 'zeinebdddfdfsdfsdf', 'qsdqsd', 'qsdqsdqsd', '1986-04-14', 8558585, 'sdfsdf  ', NULL, NULL, ''),
(75, 'Karma', 'Zeinebb', 'zeinebkarma@gmaill.com', '1889-04-14', 99856895, 'sdfsdfsdf  ', NULL, NULL, ''),
(76, 'k', 'zegtgr', 'ggr@gmail.com', '2021-04-07', 798798, 'sdfsf  ', NULL, NULL, ''),
(77, 'Zeineeeb', 'karma', 'zeineb.karma@gmaill.com', '2021-04-27', 99856985, 'qsdqsd  ', NULL, NULL, ''),
(78, 'hqgjshdgqjshdg', 'jhdgjhdgjfshdg', 'jhdsjhdgs', '2021-04-07', 99878687, 'sdfsdfsdf  ', NULL, NULL, ''),
(79, 'aa', 'aa', 'aa', '2021-04-16', 998236685, 'aaa  ', NULL, NULL, ''),
(80, 'Karma', 'med', 'medaa@a', '2021-04-17', 65655655, '9595944  ', NULL, NULL, ''),
(81, 'zeineb', 'karma', 'zeinebkarma90@gmail.com', '1999-12-18', 99823667, 'zeinebzeineb  ', NULL, NULL, ''),
(82, 'Karma', 'Med', 'bcrypt@yes.com', '2013-04-05', 99823669, '$2a$12$5EIJIw831cPNOTyZ4R5.7eHb017obSpkDtlzrQDJZv0bSEYb4vP6i  ', NULL, NULL, ''),
(83, 'ww', 'ww', 'ww@ww.com', '2021-04-17', 88888888, '$2y$12$IwMwyZHRXt49k9EYwEA6gemfp9L033CH/bYMguKCNFoO2qhSvjWjW  ', NULL, NULL, ''),
(84, 'kk', 'kk', 'bcrypt@yes.coml', '2021-04-17', 99823668, '$2y$12$CXDd.9fDwEKqCg.TlP6EveeD8Sa2qFgdKW1FxEiKxEvz35vvK8XPy  ', NULL, NULL, ''),
(85, 'Zeineb ', 'Karma ', 'zeineb@gmail.com', '2021-04-17', 99823668, '$2y$12$xLjc4SFiYaF.691VU0nORe8F1Ft98ur10Fm3OcbB9b9N/StnGuu72  ', NULL, NULL, ''),
(86, 'Chebbi', 'Rawend', 'rawendchebbi@gmail.com', '2021-04-19', 99823668, '$2y$12$j04wG/SGukaOjVc4e8SzFu0bfrTcWxHLGe1z9uGHtEQfdl7pFQpZO  ', NULL, NULL, ''),
(87, 'karmaaze', 'zeineb', 'qsdqsdqd@gmail.com', '2021-04-20', 99856987, '$2y$12$9YxcwLl4boY/.TM3His24eZ4/gYRY/8A.RYYaUJMzXT8fK4asSY.W  ', NULL, NULL, ''),
(89, 'Mondher', 'Karma', 'mondher@mail.com', '2021-04-20', 98789878, '$2y$12$//Yuh6dBhnxwxI1ePz7p1.Sxtfqpb6Qv4ZGNbFigfiwRoNXVOvX5m  ', NULL, NULL, ''),
(90, 'Mondherrr', 'Karma', 'mondher@gmail.com', '2021-04-20', 98789878, '$2y$12$5aY9ZQtWiwJ.butx8kztH.88bxRoU4VHqC2fvIpLYwZGPwygqeAdG  ', NULL, NULL, ''),
(91, 'Bouchnek', 'Abdelhamid', 'Abdelhamid@gmail.com', '2021-04-20', 98789878, '$2y$12$PyPPi3aLxrUu/KOCQ0bSo.TiDL.D.91hY3RPQysQIgUgWmHLiC2n6  ', NULL, NULL, ''),
(92, 'adee', 'dzdfsd', 'zeinebk@gmail.com', '2021-04-20', 98989898, '$2y$12$XvYm26TeV13XpTLjkZhTzuPY5F09NbTklofdAdQeUxQtjgcPQxFNS  ', NULL, NULL, ''),
(93, 'aaaaaaaaaaaaaaaaa', 'aaaaaaaaaa', 'am@am.am', '2021-04-20', 55555555, '$2y$12$3zTvxQqz16I94SHAs.Ka3.leNvfKp6kVMa/beuZLD/K.wQ.gX2pOu  ', NULL, NULL, ''),
(94, 'aa', 'aa', 'aaa@aaa.aaa', '2021-04-20', 22222222, 'aaaa', NULL, NULL, ''),
(95, 'hh', 'hh', 'hh@hh.hh', '2021-04-20', 99823668, '$2y$13$LtFABqKc03D7njbN66s26O2tDLtzVX4GCZKofWMdLXDvV4P.fI6k6', NULL, NULL, ''),
(96, 'med', 'med', 'med@med.med', '2021-04-20', 77777777, '$2y$13$Jk9AO/.yD20JvoZgnMBaO.FAEZuOLbdHqXzYKSQMNkBz3d26T0EiO  ', NULL, NULL, ''),
(97, 'zz', 'zz', 'medkarma099@gmail.tn', '2021-04-20', 55555555, '$2y$13$QYUsR4JsxHFKJFs5WD//guBAHf1tvfJUzJdFIoyoD1jVIotqJ8Afy  ', NULL, NULL, ''),
(98, 'Karmaa', 'Zeineb', 'Zeineb@gmail.com', '2021-04-20', 99856985, '$2a$12$i3pekdIPIr5XZ6UNYQd0huxpJI44Axvc7hJ6vT6WxHdQ3Jr3VWYli  ', NULL, NULL, ''),
(99, 'yassine', 'abdelmoula', 'y.a@gmail.com', '2021-04-20', 12345678, '$2a$12$NwbxxkxfpRt1vdtGfizJXOKseQcCOEGDCmIZA1pGq0Gk9rrj5cIeq  ', NULL, NULL, ''),
(100, 'Zeineb', 'Karma', 'f.k@gmail.com', '2021-04-20', 54999999, '$2a$12$3mRT8qdlhWVMUYynTyy.FegI0AcX.7BtzKXKTLMFirpzWYEPEYT22', 'abonne', 'cus_JMJWK1dT91X8ue', ''),
(101, 'Ramadan', 'Kareem', 'ramadan@gmail.com', '2021-04-22', 99823668, '$2a$12$Zg5ghul1DhEDNR791stuj.yP0o5NECzuOj9oXoYXAwd3rWCuej68a', 'abonne', NULL, ''),
(102, 'Admin', 'Admin', 'admin@gmail.com', '2021-04-24', 11111111, '$2a$12$pyyUOZNcI8MW0LJOX4esJOZGCgaWi6JTGk8cpoy0EsVhkhg81iZ.u', 'admin', NULL, ''),
(103, 'Karmaaa', 'zEINEB', 'z.k@gmail.Com', '1999-04-24', 54888888, '$2a$12$a5pJp9kjBPbyZYMhT6c1Yur22DdmPOWWNt1y8EWrJm9ttD6Xg.JNW', 'abonne', 'cus_JWAbbugHAgLKw6', ''),
(104, 'maha', 'segni', 'm.s@gmail.com', '2021-04-24', 99878878, '$2a$12$.XZt44zcp/nV.R4gthbXy.Ia5AKjmWqRgtnJK8YhMzPXdm69ciTiK', 'abonne', NULL, ''),
(105, 'zeineb', 'karma', 'k.z@gmail.com', '2021-04-24', 99856985, '$2a$12$BjrTzGJH7C2gem3VaJnq2.7037p88Jlzo9zVyz418ouOC66h6FEcq', 'abonne', 'cus_JMWjmWMoyDnhUB', ''),
(107, 'TRY', 'TRY', 'try@gmail.Com', '2021-04-25', 99888777, '$2a$12$QV2AvUSxdh0brM/y4DYe5u8agedjtOyo571PJuNdXvPla7v0fTIRi', 'abonne', 'cus_JMuwTZSxhSftG6', NULL),
(108, 'Hanine', 'Djebbi', 'Hanine.Djebbi@gmail.com', '2021-04-26', 99823668, '$2a$12$UAXjyBGpc8CzeKuDOS7i0.ZcxA.BlHUMcov1cV9xGF1Y2Lylli1L.', 'abonne', 'cus_JN9TTm4atttaj7', NULL),
(109, 'mahaaaa', 'segni', 'ma.s@gmail.com', '2021-05-19', 99999998, '$2y$13$0hdBrUeQ6C77HVYKF/00I.UKusXCTO0gZnd0L/pgPDuEhi2v2XBK2', NULL, NULL, NULL),
(110, 'mmm', 'm', 'm.m@gmail.com', '2021-05-19', 99999999, '$2y$13$kNVKR7UAay6nHNnTOWt6E.xE6em8up61jSponNXhu7rZzvr1s2RCC', 'abonne', NULL, NULL),
(111, 'z.a', 'de', 'z.a@gmail.Com', '2021-05-19', 87987987, '$2y$13$HHBc7W2OoxdHi8SaJpwGB.Pnn0h/Bx0u0fTJXGc063cAx5EUlHkvO', 'abonne', NULL, NULL),
(112, 'h', 'hh', 'h.h@gmail.com', '2021-05-19', 99888777, '$2y$13$OtdkuwrPe/eot1p3/fTIEOC9JyFYOGyyAHpX/ht/kK.7Bjilewo2y', 'abonne', NULL, NULL),
(113, 'tttt', 'tt', 't.t@gmail.com', '2021-05-19', 99999999, '$2y$13$CwZ7Zo5iVI9IMrdntyVLbuaMKmA7effiWhFpdGIwbiD05h7qB7RGi', 'abonne', 'cus_JVvNdFIrTKbTm3', NULL),
(114, 'maha', 'maha', 'ma@gmail.com', '2021-05-19', 20000000, '$2y$13$dNI/pU60Dj5y6e9e1tj0v.JEvXcDSiAOo9b7JaZZKmWlbS9b8/GgC', 'abonne', NULL, NULL),
(115, 'aa', 'aa', 'aa.a@gmail.com', '2021-05-20', 99999999, '$2y$13$YLNEsQzpasNEc3A1bxPEVOYWXGBOsucDDXyCCZi6GAqZdZoat3zN.', 'abonne', NULL, NULL),
(116, 'e', 'e', 'e.e@gmail.Com', '2021-05-20', 99999999, '$2y$13$aUBZ4L57.F42lVvvC.YkmekoZjcIinxJ9jsMkgI7k0zXBLo45YGSu', 'abonne', 'cus_JWAuZvVQbk6VVj', NULL),
(117, 'rawend', 'chebbbi', 'r.c@gmail.com', '2021-05-21', 99823668, '$2y$13$mNF9KsTkOiydIDIXtjFjWuySS01wl.13lmr9sZLrmxqMISSsJVF9O', 'abonne', 'cus_JWWUp9r7Id77x7', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `abonnement`
--

CREATE TABLE `abonnement` (
  `id` int(11) NOT NULL,
  `abonne_id` int(11) NOT NULL,
  `typeabonnement_id` int(11) NOT NULL,
  `datedebut` date NOT NULL,
  `datefin` date NOT NULL,
  `valide` tinyint(1) NOT NULL,
  `datedemande` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `abonnement`
--

INSERT INTO `abonnement` (`id`, `abonne_id`, `typeabonnement_id`, `datedebut`, `datefin`, `valide`, `datedemande`) VALUES
(1, 47, 33, '2021-03-29', '2021-03-29', 1, '2021-03-29'),
(3, 47, 33, '2021-03-29', '2021-03-29', 1, '2021-03-29'),
(4, 47, 33, '2021-03-30', '2021-04-30', 1, '2021-03-01'),
(5, 47, 33, '2021-04-30', '2021-04-30', 1, '2021-03-30'),
(6, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(7, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(10, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(11, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(12, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(13, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(14, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(15, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(16, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(17, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(18, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(19, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(20, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(21, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(22, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(23, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(24, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(25, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(26, 47, 33, '2021-03-30', '2021-03-30', 0, '2021-03-30'),
(76, 100, 33, '2021-04-23', '2021-05-23', 1, '2021-04-22'),
(77, 100, 33, '2021-04-23', '2021-05-23', 1, '2021-04-22'),
(79, 100, 33, '2021-04-22', '2021-04-24', 1, '2021-04-22'),
(80, 101, 33, '2021-04-22', '2021-04-22', 1, '2021-04-22'),
(81, 101, 33, '2021-04-23', '2021-05-23', 1, '2021-04-22'),
(82, 101, 33, '2021-04-23', '2021-05-23', 1, '2021-04-22'),
(83, 101, 33, '2021-04-23', '2021-05-23', 1, '2021-04-22'),
(84, 101, 33, '2021-04-23', '2021-05-23', 1, '2021-04-22'),
(85, 101, 33, '2021-04-23', '2021-05-23', 1, '2021-04-22'),
(97, 105, 33, '2021-04-24', '2021-05-24', 1, '2021-04-24'),
(98, 105, 33, '2021-04-24', '2021-05-24', 1, '2021-04-24'),
(99, 103, 33, '2021-04-25', '2021-05-25', 1, '2021-04-25'),
(100, 103, 33, '2021-04-25', '2021-05-25', 1, '2021-04-25'),
(101, 103, 33, '2021-04-25', '2021-05-25', 1, '2021-04-25'),
(102, 103, 33, '2021-04-25', '2021-05-25', 1, '2021-04-25'),
(103, 103, 33, '2021-04-25', '2021-05-25', 1, '2021-04-25'),
(104, 103, 33, '2021-04-25', '2021-05-25', 1, '2021-04-25'),
(105, 103, 33, '2021-04-25', '2021-05-25', 1, '2021-04-25'),
(107, 107, 33, '2021-04-25', '2021-05-25', 1, '2021-04-25'),
(108, 107, 33, '2021-04-25', '2021-05-25', 1, '2021-04-25'),
(109, 107, 33, '2021-04-25', '2021-05-25', 1, '2021-04-25'),
(110, 107, 33, '2021-04-25', '2021-05-25', 1, '2021-04-25'),
(111, 107, 33, '2021-04-25', '2021-05-25', 1, '2021-04-25'),
(112, 107, 33, '2021-04-25', '2021-05-25', 1, '2021-04-25'),
(113, 108, 33, '2021-04-26', '2021-05-26', 1, '2021-04-26'),
(114, 108, 33, '2021-04-26', '2021-05-26', 1, '2021-04-26'),
(115, 108, 33, '2021-04-26', '2021-05-26', 1, '2021-04-26'),
(116, 108, 33, '2021-04-26', '2021-05-26', 1, '2021-04-26'),
(117, 108, 33, '2021-04-26', '2021-05-26', 1, '2021-04-26'),
(118, 103, 33, '2021-05-19', '2021-05-19', 0, '2021-05-19'),
(119, 103, 33, '2021-05-19', '2021-05-19', 0, '2021-05-19'),
(120, 103, 33, '2021-05-19', '2021-05-19', 0, '2021-05-19'),
(122, 109, 33, '2021-05-19', '2021-05-19', 0, '2021-05-19'),
(123, 109, 33, '2021-05-19', '2021-05-19', 0, '2021-05-19'),
(124, 103, 34, '2021-05-19', '2021-05-19', 0, '2021-05-19'),
(125, 103, 33, '2021-05-19', '2021-05-19', 0, '2021-05-19'),
(126, 103, 34, '2021-05-19', '2021-05-19', 0, '2021-05-19'),
(127, 103, 33, '2021-05-19', '2021-05-19', 0, '2021-05-19'),
(128, 103, 33, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(129, 103, 33, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(130, 103, 33, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(131, 103, 33, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(132, 103, 33, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(133, 103, 33, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(134, 103, 34, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(135, 103, 34, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(136, 103, 34, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(137, 103, 34, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(138, 103, 34, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(139, 103, 34, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(141, 103, 33, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(142, 103, 33, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(144, 103, 33, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(147, 113, 34, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(149, 113, 34, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(150, 113, 35, '2021-05-19', '2021-05-19', 0, '2021-05-19'),
(151, 113, 34, '2021-05-19', '2021-05-19', 0, '2021-05-19'),
(152, 113, 33, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(153, 113, 35, '2021-05-19', '2021-06-19', 1, '2021-05-19'),
(154, 103, 33, '2021-05-20', '2021-06-20', 1, '2021-05-20'),
(155, 103, 34, '2021-05-20', '2021-06-20', 1, '2021-05-20'),
(156, 103, 33, '2021-05-20', '2021-06-20', 1, '2021-05-20'),
(157, 103, 33, '2021-05-20', '2021-06-20', 1, '2021-05-20'),
(158, 103, 34, '2021-05-20', '2021-06-20', 1, '2021-05-20'),
(159, 103, 33, '2021-05-20', '2021-06-20', 1, '2021-05-20'),
(160, 116, 33, '2021-05-20', '2021-06-20', 1, '2021-05-20'),
(161, 116, 34, '2021-05-20', '2021-06-20', 1, '2021-05-20'),
(162, 117, 34, '2021-05-21', '2021-06-21', 1, '2021-05-21'),
(163, 117, 33, '2021-05-21', '2021-06-21', 1, '2021-05-21');

-- --------------------------------------------------------

--
-- Structure de la table `avisclient`
--

CREATE TABLE `avisclient` (
  `id` int(11) NOT NULL,
  `rating` double DEFAULT NULL,
  `descR` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `avis_client`
--

CREATE TABLE `avis_client` (
  `id` int(11) NOT NULL,
  `rating` double DEFAULT NULL,
  `desc_r` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `avis_client`
--

INSERT INTO `avis_client` (`id`, `rating`, `desc_r`) VALUES
(1, 2, 'tttt'),
(2, 5, 'test'),
(3, 5, 'ee'),
(4, 3, 'aaa'),
(5, 2, 'desc'),
(6, 5, 'test'),
(7, 5, 'tres bien'),
(8, 5, 'tres bien');

-- --------------------------------------------------------

--
-- Structure de la table `calendar`
--

CREATE TABLE `calendar` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `description` text NOT NULL,
  `background_color` varchar(7) DEFAULT NULL,
  `text_color` varchar(7) DEFAULT NULL,
  `border_color` varchar(7) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `calendar`
--

INSERT INTO `calendar` (`id`, `title`, `start`, `end`, `description`, `background_color`, `text_color`, `border_color`) VALUES
(4, 'rdv2', '2021-03-28 06:59:00', '2021-03-28 13:59:00', 'avec Mr Sami', '#a34ca4', '#605310', '#000000'),
(6, 'rdv 3', '2021-03-13 13:31:00', '2021-03-13 16:31:00', 'avec mr fares', '#bd5656', '#000000', '#000000'),
(8, 'rdv6', '2021-04-01 08:13:00', '2021-04-01 11:13:00', 'traitement d\'image', '#b82e97', '#000000', '#d3a1a1'),
(9, 'rdv7', '2021-04-05 06:46:00', '2021-04-05 09:46:00', 'Mr ali', '#6b3333', '#000000', '#b00c0c'),
(13, 'rdv6', '2021-05-20 23:57:00', '2021-05-20 01:02:00', 'hhhh', '#000000', '#000000', '#000000');

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `idc` int(11) NOT NULL,
  `nomc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`idc`, `nomc`) VALUES
(4, 'sushi'),
(6, 'drinks'),
(7, 'berguerr'),
(14, 'cheesecake'),
(15, 'burger');

-- --------------------------------------------------------

--
-- Structure de la table `categorief`
--

CREATE TABLE `categorief` (
  `id` int(11) NOT NULL,
  `desc_c` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `categorief`
--

INSERT INTO `categorief` (`id`, `desc_c`) VALUES
(4, 'animation'),
(5, 'aventure'),
(6, 'comédie'),
(7, 'documentaire'),
(8, 'drame'),
(9, 'guerre'),
(10, 'histoire'),
(11, 'policier'),
(12, 'fantastique'),
(13, ' science-fction '),
(14, 'horreur'),
(15, 'action');

-- --------------------------------------------------------

--
-- Structure de la table `cellule`
--

CREATE TABLE `cellule` (
  `idCellule` varchar(100) NOT NULL,
  `dispo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `cellule`
--

INSERT INTO `cellule` (`idCellule`, `dispo`) VALUES
('null', 1),
('place 10', 0),
('place 2', 1),
('place 3', 0),
('place 5', 1),
('place 6', 1),
('place 60', 0),
('place 7', 0),
('place 8', 1),
('place50', 1);

-- --------------------------------------------------------

--
-- Structure de la table `chat`
--

CREATE TABLE `chat` (
  `id` int(11) NOT NULL,
  `id_chat` int(11) NOT NULL,
  `idsender` int(11) NOT NULL,
  `idreceiver` int(11) NOT NULL,
  `msg` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `cinema`
--

CREATE TABLE `cinema` (
  `num` int(11) NOT NULL,
  `date` date NOT NULL,
  `heurep` time NOT NULL,
  `nbr` int(11) NOT NULL,
  `film` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `cinema`
--

INSERT INTO `cinema` (`num`, `date`, `heurep`, `nbr`, `film`) VALUES
(56, '2021-05-19', '11:35:10', 25, 87),
(57, '2021-05-19', '13:36:10', 30, 88),
(58, '2021-05-19', '14:36:52', 45, 89),
(59, '2021-05-21', '00:23:00', 32, 89),
(61, '2021-05-21', '00:22:00', 40, 88);

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `idevent` int(11) DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `idUser` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `comment`
--

INSERT INTO `comment` (`id`, `idevent`, `content`, `idUser`) VALUES
(7, 5, 'aaaaaaaa', 5);

-- --------------------------------------------------------

--
-- Structure de la table `conge`
--

CREATE TABLE `conge` (
  `idconge` int(11) NOT NULL,
  `dateconge` date NOT NULL,
  `motifconge` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nbjourconge` int(11) NOT NULL,
  `employe_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `conge`
--

INSERT INTO `conge` (`idconge`, `dateconge`, `motifconge`, `nbjourconge`, `employe_id`) VALUES
(10, '2021-06-05', 'je veux un conge annuel', 11, 9),
(11, '2021-08-20', 'je veux un conge de maternité', 3, 12);

-- --------------------------------------------------------

--
-- Structure de la table `demande_res`
--

CREATE TABLE `demande_res` (
  `id` int(11) NOT NULL,
  `reference` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `matricule` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nom_client` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenom_client` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `duree` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `doctrine_migration_versions`
--

CREATE TABLE `doctrine_migration_versions` (
  `version` varchar(191) COLLATE utf8_unicode_ci NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20210325234113', '2021-03-26 00:41:49', 21),
('DoctrineMigrations\\Version20210329171006', '2021-03-29 19:10:51', 22);

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

CREATE TABLE `employe` (
  `idemp` int(11) NOT NULL,
  `nomemp` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenomemp` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `numtelemp` int(11) NOT NULL,
  `adresseemp` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`idemp`, `nomemp`, `prenomemp`, `numtelemp`, `adresseemp`) VALUES
(1, 'djebbi', 'hanine', 25366447, 'hanine@mail.com'),
(4, 'segni', 'maha', 25144578, 'maha@esprit.tn'),
(5, 'chebbi', 'rawend', 95514742, 'chebbi@mail.com'),
(6, 'sarra', 'slimi', 21477863, 'sarra@mail.fr'),
(7, 'salma', 'bakkar', 25265369, 'mahasegni00@gmail.com'),
(9, 'farah', 'segni', 25588855, 'hanine@gmail.com'),
(10, 'fatma', 'yahiawi', 25265368, 'fatma@mail.com'),
(11, 'anissa', 'othmani', 25265369, 'anis@mail.com'),
(12, 'fatma', 'saidane', 98563214, 'faty@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `equipement`
--

CREATE TABLE `equipement` (
  `id` int(11) NOT NULL,
  `ref` int(11) NOT NULL,
  `nome` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `resto_id` int(11) DEFAULT NULL,
  `prix` double NOT NULL,
  `dates` datetime NOT NULL DEFAULT current_timestamp(),
  `cinema_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `equipement`
--

INSERT INTO `equipement` (`id`, `ref`, `nome`, `resto_id`, `prix`, `dates`, `cinema_id`) VALUES
(13, 111, 'tele', NULL, 222, '2021-04-06 00:00:00', NULL),
(19, 1, 'four', 4, 33, '2021-01-05 00:00:00', 57),
(20, 1, 'panne', 4, 58000, '2021-05-20 00:00:00', 58);

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `id` int(11) NOT NULL,
  `nomEvent` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dateEvent` datetime DEFAULT NULL,
  `dureeEvent` int(11) DEFAULT NULL,
  `dateEventFin` datetime DEFAULT NULL,
  `emplacement` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `place` int(11) DEFAULT NULL,
  `capacite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id`, `nomEvent`, `dateEvent`, `dureeEvent`, `dateEventFin`, `emplacement`, `etat`, `place`, `capacite`) VALUES
(5, 'happy happy', '2021-04-06 08:10:00', 2, '2021-05-10 00:00:00', 'Lounge', '1', 4, 10),
(9, 'cinema', '2021-04-01 00:00:00', NULL, '2021-04-03 00:00:00', 'Cinema', NULL, 12, 15),
(12, 'park disco', NULL, NULL, NULL, 'parking', NULL, NULL, 50),
(13, 'happyl', NULL, NULL, NULL, 'cinema', NULL, NULL, 55);

-- --------------------------------------------------------

--
-- Structure de la table `film`
--

CREATE TABLE `film` (
  `categorie` int(11) DEFAULT NULL,
  `nomfilm` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `datesortie` date NOT NULL,
  `descriptionf` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `filename` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_film` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `film`
--

INSERT INTO `film` (`categorie`, `nomfilm`, `datesortie`, `descriptionf`, `filename`, `id_film`) VALUES
(5, 'boss level', '2021-05-15', '110min', 'bosslevel.jpg', 87),
(8, 'Soul', '2021-05-17', '110 min', '1608569732080_Poster.jpg', 88),
(NULL, 'Lion King', '2021-05-17', '160 min', 'lion.jpg', 89),
(NULL, 'L\'HOMME QUI A VENDU SA PEAU', '2021-05-20', '104 mins ', '1610136115754_Poster.jpg', 92);

-- --------------------------------------------------------

--
-- Structure de la table `guide`
--

CREATE TABLE `guide` (
  `id` int(11) NOT NULL,
  `id_guide` int(11) NOT NULL,
  `nom_guide` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenom_guide` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `activite` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `agenda` date NOT NULL,
  `agenda_time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `parking`
--

CREATE TABLE `parking` (
  `idParking` int(11) NOT NULL,
  `nom` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `plat`
--

CREATE TABLE `plat` (
  `idp` int(11) NOT NULL,
  `nomp` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prixp` int(11) NOT NULL,
  `categorie_id` int(11) DEFAULT NULL,
  `imagep` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `plat`
--

INSERT INTO `plat` (`idp`, `nomp`, `prixp`, `categorie_id`, `imagep`, `description`) VALUES
(10005, 'berger1', 20, 7, 'b1.jpg', 'jgduyg'),
(10006, 'california roll', 15, 4, 'roll.jpg', 'xguygug'),
(10009, 'rice', 14, 4, 'rice.jpg', 'sjhgdugu'),
(10012, 'sushi', 20, 4, 'salmon-ef19b0158f02a937a89a9028f5911eab.jpeg', 'sushi');

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `idrec` int(11) NOT NULL,
  `abonne_id` int(11) NOT NULL,
  `description` varchar(600) COLLATE utf8mb4_unicode_ci NOT NULL,
  `field` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `datee` datetime NOT NULL DEFAULT current_timestamp(),
  `rate` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`idrec`, `abonne_id`, `description`, `field`, `datee`, `rate`) VALUES
(81, 5, 'hellowin', 'Cinema', '2021-05-21 10:19:43', 0),
(84, 5, 'bonjour', 'Parking', '2021-04-26 09:06:33', 3),
(93, 5, 'workes', 'Cinema', '2021-05-20 15:26:52', 2.3),
(94, 114, '5idmot', 'autre', '2021-05-20 15:49:04', 1.6),
(95, 114, 'foule', 'Parking', '2021-05-20 20:24:01', 3),
(100, 47, 'haya', 'autre', '2021-05-20 20:50:01', 1.3);

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `idReservation` int(11) NOT NULL,
  `dateD` datetime NOT NULL,
  `dateF` datetime NOT NULL,
  `matricule` varchar(100) NOT NULL,
  `idCell` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`idReservation`, `dateD`, `dateF`, `matricule`, `idCell`) VALUES
(32, '2021-04-02 00:00:00', '2021-04-04 00:00:00', 'jjjj', 'place 7'),
(33, '2021-04-02 00:00:00', '2021-04-03 00:00:00', 'tun jjjj', 'place 10'),
(35, '2021-05-07 00:00:00', '2021-05-08 00:00:00', 'jjjjj', 'place 60');

-- --------------------------------------------------------

--
-- Structure de la table `type_abonnement`
--

CREATE TABLE `type_abonnement` (
  `id` int(11) NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prix` double NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `placesdispo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `type_abonnement`
--

INSERT INTO `type_abonnement` (`id`, `type`, `description`, `prix`, `image`, `placesdispo`) VALUES
(33, 'Resto', 'ce type est ', 50, 'C7FB0A4858F506B3A9775908C4322184.jpeg', 8),
(34, 'Parking', 'dccd', 80, 'B50FBD949FEBC9409B373835E4370970.jpeg', 9),
(35, 'test', 'test', 20, 'e.jpg', 20);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(180) COLLATE utf8mb4_unicode_ci NOT NULL,
  `roles` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '(DC2Type:json)',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_verified` tinyint(1) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `prenom` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mail` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mdp` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nomEvent` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `roles`, `password`, `lastname`, `is_verified`, `nom`, `prenom`, `mail`, `mdp`, `nomEvent`) VALUES
(2, 'maha2@gmail.com', '', 'mahamaha', 'maha2', 0, '', '', '', '', ''),
(5, 'maha.segni@esprit.tn', '', '123', 'fares', 1, 'aissa', 'fares', '', '', 'Box Office'),
(6, 'mahasg2@gmail.com', '', 'maha', 'maha', 0, '', '', '', '', ''),
(7, 'maha@gmail.com', '', '$argon2id$v=19$m=65536,t=4,p=1$ejJZV2dRdnhoZDRBcmVNTQ$/4iwu1R55emK6eKc5UNL5WVN59g2hY7GwwxkXYfrHcA', 'mahaa', 0, '', '', '', '', ''),
(8, 'maha23@gmail.com', '', '$argon2id$v=19$m=65536,t=4,p=1$NXBzYWQvUkJaTy91NUlZZA$nb5PHwZaaIGIlIopX6MoE4bKiRi11VilwYjbzFrtZ9U', 'mahamaha', 0, NULL, NULL, NULL, NULL, NULL),
(9, 'mah@gmail.com', 'user', 'mahamaha', 'mahamaha', 0, NULL, NULL, NULL, NULL, NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `abonne`
--
ALTER TABLE `abonne`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `abonnement`
--
ALTER TABLE `abonnement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_351268BBC325A696` (`abonne_id`),
  ADD KEY `IDX_351268BB2CCF9CBF` (`typeabonnement_id`);

--
-- Index pour la table `avisclient`
--
ALTER TABLE `avisclient`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `avis_client`
--
ALTER TABLE `avis_client`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `calendar`
--
ALTER TABLE `calendar`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`idc`);

--
-- Index pour la table `categorief`
--
ALTER TABLE `categorief`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `cellule`
--
ALTER TABLE `cellule`
  ADD PRIMARY KEY (`idCellule`);

--
-- Index pour la table `chat`
--
ALTER TABLE `chat`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `cinema`
--
ALTER TABLE `cinema`
  ADD PRIMARY KEY (`num`),
  ADD KEY `FK_D48304B48244BE22` (`film`);

--
-- Index pour la table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_9474526CFE6E88D7` (`idUser`),
  ADD KEY `IDX_9474526CEDAB66BE` (`idevent`);

--
-- Index pour la table `conge`
--
ALTER TABLE `conge`
  ADD PRIMARY KEY (`idconge`),
  ADD KEY `IDX_2ED893481B65292` (`employe_id`);

--
-- Index pour la table `demande_res`
--
ALTER TABLE `demande_res`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `doctrine_migration_versions`
--
ALTER TABLE `doctrine_migration_versions`
  ADD PRIMARY KEY (`version`);

--
-- Index pour la table `employe`
--
ALTER TABLE `employe`
  ADD PRIMARY KEY (`idemp`);

--
-- Index pour la table `equipement`
--
ALTER TABLE `equipement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_B8B4C6F3B4CB84B6` (`cinema_id`),
  ADD KEY `IDX_B8B4C6F32978E8D1` (`resto_id`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `film`
--
ALTER TABLE `film`
  ADD PRIMARY KEY (`id_film`);

--
-- Index pour la table `guide`
--
ALTER TABLE `guide`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `parking`
--
ALTER TABLE `parking`
  ADD PRIMARY KEY (`idParking`);

--
-- Index pour la table `plat`
--
ALTER TABLE `plat`
  ADD PRIMARY KEY (`idp`),
  ADD KEY `cle_cat123` (`categorie_id`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`idrec`),
  ADD KEY `cle_ab1` (`abonne_id`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`idReservation`),
  ADD KEY `IDX_42C84955B87AD97C` (`idCell`);

--
-- Index pour la table `type_abonnement`
--
ALTER TABLE `type_abonnement`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `abonne`
--
ALTER TABLE `abonne`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=118;

--
-- AUTO_INCREMENT pour la table `abonnement`
--
ALTER TABLE `abonnement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=164;

--
-- AUTO_INCREMENT pour la table `avisclient`
--
ALTER TABLE `avisclient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `avis_client`
--
ALTER TABLE `avis_client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `calendar`
--
ALTER TABLE `calendar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `idc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `categorief`
--
ALTER TABLE `categorief`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `chat`
--
ALTER TABLE `chat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `cinema`
--
ALTER TABLE `cinema`
  MODIFY `num` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT pour la table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `conge`
--
ALTER TABLE `conge`
  MODIFY `idconge` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `demande_res`
--
ALTER TABLE `demande_res`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `employe`
--
ALTER TABLE `employe`
  MODIFY `idemp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `equipement`
--
ALTER TABLE `equipement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `film`
--
ALTER TABLE `film`
  MODIFY `id_film` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=95;

--
-- AUTO_INCREMENT pour la table `guide`
--
ALTER TABLE `guide`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `parking`
--
ALTER TABLE `parking`
  MODIFY `idParking` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `plat`
--
ALTER TABLE `plat`
  MODIFY `idp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10016;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `idrec` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `idReservation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT pour la table `type_abonnement`
--
ALTER TABLE `type_abonnement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `cinema`
--
ALTER TABLE `cinema`
  ADD CONSTRAINT `FK_D48304B48244BE22` FOREIGN KEY (`film`) REFERENCES `film` (`id_film`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_9474526CEDAB66BE` FOREIGN KEY (`idevent`) REFERENCES `evenement` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_9474526CFE6E88D7` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `conge`
--
ALTER TABLE `conge`
  ADD CONSTRAINT `FK_2ED893481B65292` FOREIGN KEY (`employe_id`) REFERENCES `employe` (`idemp`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `equipement`
--
ALTER TABLE `equipement`
  ADD CONSTRAINT `cd_resto1236` FOREIGN KEY (`resto_id`) REFERENCES `categorie` (`idc`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cle_cin123` FOREIGN KEY (`cinema_id`) REFERENCES `cinema` (`num`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `film`
--
ALTER TABLE `film`
  ADD CONSTRAINT `FK_8244BE22497DD634` FOREIGN KEY (`categorie`) REFERENCES `categorief` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `plat`
--
ALTER TABLE `plat`
  ADD CONSTRAINT `cle_cat123` FOREIGN KEY (`categorie_id`) REFERENCES `categorie` (`idc`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `cle_ab1` FOREIGN KEY (`abonne_id`) REFERENCES `abonne` (`id`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_42C84955B87AD97C` FOREIGN KEY (`idCell`) REFERENCES `cellule` (`idCellule`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
