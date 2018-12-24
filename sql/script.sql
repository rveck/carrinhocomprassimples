create database db_carrinho;

use db_carrinho;

CREATE TABLE `produto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `valor` double(13,2) NOT NULL,
  `data_exclusao` datetime DEFAULT NULL,
  `data_criacao` datetime NOT NULL,
  PRIMARY KEY (`id`)
);