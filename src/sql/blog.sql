-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: 11-Set-2020 às 14:08
-- Versão do servidor: 5.7.21
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `blog`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `artigo`
--

DROP TABLE IF EXISTS `artigo`;
CREATE TABLE IF NOT EXISTS `artigo` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_usuario` int(10) UNSIGNED NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `conteudo` text,
  `liberar` char(1) NOT NULL DEFAULT 'N',
  `aprovado` char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `fk_articles_users1_idx` (`id_usuario`) USING BTREE,
  KEY `fk_articles_category1` (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `artigo`
--

INSERT INTO `artigo` (`id`, `id_usuario`, `id_categoria`, `titulo`, `conteudo`, `liberar`, `aprovado`) VALUES
(1, 1, 1, 'Article premier', '<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>\r\n<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>', 'N', 'N'),
(2, 1, 1, 'Article 2', '<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>\r\n<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>', 'N', 'N'),
(3, 1, 1, 'Article 3', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla varius sem vitae tempus ullamcorper. Morbi lorem nunc, efficitur vehicula lectus pretium, commodo pellentesque nibh. Curabitur finibus semper ultricies. Praesent a viverra diam, in rutrum diam. Sed sed pretium mauris. Vivamus sed mi vitae odio vulputate malesuada. Sed quis turpis condimentum massa aliquet vulputate. Vivamus neque lacus, vestibulum lobortis sagittis ut, dictum sit amet nulla. Curabitur vel facilisis libero, a fringilla est. Mauris lobortis mi dui, sit amet tempor nisl sagittis ut. Morbi fermentum ex ipsum, sit amet iaculis nisi lobortis non. Ut sit amet neque risus. Aliquam non molestie tellus, quis euismod turpis. Donec vitae dignissim nisi.</p>\r\n<p>Curabitur gravida libero at scelerisque vulputate. Sed vestibulum suscipit metus quis dignissim. In hac habitasse platea dictumst. Proin consequat lobortis libero, ac elementum nunc fermentum sed. Phasellus euismod massa nisi, vel imperdiet massa ultrices ut. Morbi eu eleifend ligula. Mauris nisl nulla, dapibus vel feugiat a, consequat id augue. Fusce quis nibh sapien. Phasellus ut libero sit amet purus blandit faucibus. Donec semper nulla a fermentum ullamcorper. Proin dignissim, nunc vitae tincidunt suscipit, est neque iaculis augue, et aliquet libero ligula nec justo. Praesent scelerisque semper auctor. Quisque magna turpis, lobortis a sem ut, rutrum tempor lacus. Duis venenatis non enim sed congue. Nulla efficitur ante arcu, quis pellentesque elit hendrerit non. Sed vel tincidunt diam.</p>\r\n<p>Donec nisl eros, pellentesque eu laoreet et, volutpat ut odio. Nunc facilisis, nunc vitae tristique blandit, eros augue placerat felis, ultrices sollicitudin mauris risus fringilla massa. Morbi cursus ante ac viverra posuere. Aliquam erat volutpat. Donec semper enim vel velit sodales elementum. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Proin non hendrerit eros, et condimentum sapien. Donec porttitor, velit a ornare tincidunt, massa est vulputate velit, et rhoncus tellus libero in lectus. Morbi et lobortis quam. Nullam faucibus est imperdiet eros varius auctor.</p>', 'N', 'N'),
(4, 1, 1, 'Article 4x6', '<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>\r\n<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>', 'N', 'N'),
(5, 1, 1, 'Et le surf ?', '<p>Le <strong>surf</strong> (abr&eacute;viation fran&ccedil;aise de l\'anglais <em>surf-riding</em>, o&ugrave; <em>riding</em> signifie &laquo;&nbsp;chevaucher&nbsp;&raquo; et <em>surf</em> &laquo;&nbsp;(vagues) d&eacute;ferlantes&nbsp;&raquo;) est un <a title=\"Sport\" href=\"https://fr.wikipedia.org/wiki/Sport\">sport</a> qui consiste &agrave; glisser sur les <a title=\"Vague\" href=\"https://fr.wikipedia.org/wiki/Vague\">vagues</a>, <a title=\"Onde de gravit&eacute;\" href=\"https://fr.wikipedia.org/wiki/Onde_de_gravit%C3%A9\">ondes de surface</a>, au bord de l\'oc&eacute;an, debout sur une planche. Le surf se pratique sur des <em><a title=\"Spot de surf\" href=\"https://fr.wikipedia.org/wiki/Spot_de_surf\">sites de surf</a></em>, appel&eacute;s spots (&laquo;&nbsp;bons coins&nbsp;&raquo;), plages qui sont baign&eacute;es par des vagues plus ou moins grandes et propices &agrave; la glisse.</p>\r\n<p>Les adeptes de ce sport sont les surfeurs ou aquaplanchistes !<sup id=\"cite_ref-OQLF_1-0\" class=\"reference\"></sup></p>', 'N', 'N');

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE IF NOT EXISTS `categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `categoria`
--

INSERT INTO `categoria` (`id`, `descricao`) VALUES
(1, 'politica'),
(2, 'atualidade'),
(3, 'artes');

-- --------------------------------------------------------

--
-- Estrutura da tabela `comentario`
--

DROP TABLE IF EXISTS `comentario`;
CREATE TABLE IF NOT EXISTS `comentario` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `comentario` text NOT NULL,
  `id_artigo` int(10) UNSIGNED NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_article1_idx` (`id_artigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `cpf` varchar(14) NOT NULL,
  `papel` tinyint(1) NOT NULL DEFAULT '2',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `email`, `senha`, `nome`, `cpf`, `papel`) VALUES
(1, 'login@usuario.com', '111', 'admin', '249.252.810-38', 0);

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `artigo`
--
ALTER TABLE `artigo`
  ADD CONSTRAINT `fk_articles_category1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`),
  ADD CONSTRAINT `fk_articles_users1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `comentario`
--
ALTER TABLE `comentario`
  ADD CONSTRAINT `comentario_fk` FOREIGN KEY (`id`) REFERENCES `artigo` (`id`),
  ADD CONSTRAINT `fk_usuario` FOREIGN KEY (`id_artigo`) REFERENCES `usuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
