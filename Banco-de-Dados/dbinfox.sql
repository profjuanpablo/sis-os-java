-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 28-Out-2020 às 23:53
-- Versão do servidor: 10.4.14-MariaDB
-- versão do PHP: 7.2.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `dbinfox`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbclientes`
--

CREATE TABLE `tbclientes` (
  `idCli` int(11) NOT NULL,
  `nomeCli` varchar(50) NOT NULL,
  `endCli` varchar(100) DEFAULT NULL,
  `fone` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `tbclientes`
--

INSERT INTO `tbclientes` (`idCli`, `nomeCli`, `endCli`, `fone`, `email`) VALUES
(1, 'usuario', 'rua dos usuarios', '4444-4444', 'usu_ario10@hotmail.com'),
(2, 'cliente', 'rua dos clientes', '5555-5555', 'cli_ente10@hotmail.com');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbos`
--

CREATE TABLE `tbos` (
  `os` int(11) NOT NULL,
  `data_os` timestamp NOT NULL DEFAULT current_timestamp(),
  `equipamento` varchar(150) NOT NULL,
  `defeito` varchar(150) NOT NULL,
  `servico` varchar(150) DEFAULT NULL,
  `tecnico` varchar(30) DEFAULT NULL,
  `valor` decimal(10,2) DEFAULT NULL,
  `idCli` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `tbos`
--

INSERT INTO `tbos` (`os`, `data_os`, `equipamento`, `defeito`, `servico`, `tecnico`, `valor`, `idCli`) VALUES
(1, '2020-10-26 16:33:30', 'placa mãe', 'não está ligando', 'soldar pino', 'henrique', '79.85', 1),
(2, '2020-10-26 16:48:59', 'notbook', 'não está ligando', 'troca de fonte', 'henrique', '80.55', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbusuarios`
--

CREATE TABLE `tbusuarios` (
  `idUser` int(11) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `fone` varchar(15) DEFAULT NULL,
  `login` varchar(15) NOT NULL,
  `senha` varchar(15) NOT NULL,
  `perfil` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `tbusuarios`
--

INSERT INTO `tbusuarios` (`idUser`, `usuario`, `fone`, `login`, `senha`, `perfil`) VALUES
(1, 'otavio', '990679262', 'rique', 'admin', 'admin'),
(2, 'Adminstrador', '9999-9999', 'admin', 'admin', 'admin'),
(4, 'Otávio Henrique', '40028922', 'tavin', 'admin', 'admin');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `tbclientes`
--
ALTER TABLE `tbclientes`
  ADD PRIMARY KEY (`idCli`);

--
-- Índices para tabela `tbos`
--
ALTER TABLE `tbos`
  ADD PRIMARY KEY (`os`),
  ADD KEY `idCli` (`idCli`);

--
-- Índices para tabela `tbusuarios`
--
ALTER TABLE `tbusuarios`
  ADD PRIMARY KEY (`idUser`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `tbclientes`
--
ALTER TABLE `tbclientes`
  MODIFY `idCli` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `tbos`
--
ALTER TABLE `tbos`
  MODIFY `os` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `tbusuarios`
--
ALTER TABLE `tbusuarios`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `tbos`
--
ALTER TABLE `tbos`
  ADD CONSTRAINT `tbos_ibfk_1` FOREIGN KEY (`idCli`) REFERENCES `tbclientes` (`idCli`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
