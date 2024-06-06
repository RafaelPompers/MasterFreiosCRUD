-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: masterfreios
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `IDcliente` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Telefone` char(11) NOT NULL,
  `CpfCnpj` char(18) NOT NULL,
  `Rg` char(12) DEFAULT NULL,
  `Endereco` varchar(50) DEFAULT NULL,
  `Cidade` varchar(30) DEFAULT NULL,
  `Estado` char(2) DEFAULT NULL,
  PRIMARY KEY (`IDcliente`),
  UNIQUE KEY `CpfCnpj` (`CpfCnpj`),
  UNIQUE KEY `Rg` (`Rg`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Alice Silva','alice.silva@example.com','11987654321','12345678901','MG1234567','Rua Um, 100','Cidade A','AA'),(2,'Bruno Lima','bruno.lima@example.com','21987654321','23456789012','SP2345678','Rua Dois, 200','Cidade B','BB'),(3,'Carla Souza','carla.souza@example.com','31987654321','34567890123','RJ3456789','Rua Três, 300','Cidade C','CC'),(4,'Daniel Costa','daniel.costa@example.com','41987654321','45678901234','ES4567890','Rua Quatro, 400','Cidade D','DD'),(5,'Eva Martins','eva.martins@example.com','51987654321','56789012345','BA5678901','Rua Cinco, 500','Cidade E','EE'),(6,'Felipe Rocha','felipe.rocha@example.com','61987654321','67890123456','PR6789012','Rua Seis, 600','Cidade F','FF'),(7,'Gabriela Melo','gabriela.melo@example.com','71987654321','78901234567','SC7890123','Rua Sete, 700','Cidade G','GG'),(8,'Henrique Alves','henrique.alves@example.com','81987654321','89012345678','RS8901234','Rua Oito, 800','Cidade H','HH'),(9,'Isabela Castro','isabela.castro@example.com','91987654321','90123456789','PA9012345','Rua Nove, 900','Cidade I','II'),(10,'João Pereira','joao.pereira@example.com','10198765432','01234567890','AM0123456','Rua Dez, 1000','Cidade J','JJ'),(11,'Renan','renan','11111','11111','1111','rena','renan','SP'),(13,'Renan','renan','1255555','12','12','renan','renanN','SP');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compra` (
  `IDcompra` int NOT NULL AUTO_INCREMENT,
  `ValorTotal` float(12,2) NOT NULL,
  `DataDaCompra` date NOT NULL,
  `TipoDePagamento` set('crédito','débito','pix') NOT NULL,
  `IDfornecedor` int DEFAULT NULL,
  PRIMARY KEY (`IDcompra`),
  KEY `IDfornecedor` (`IDfornecedor`),
  CONSTRAINT `compra_ibfk_1` FOREIGN KEY (`IDfornecedor`) REFERENCES `fornecedor` (`IDfornecedor`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
INSERT INTO `compra` VALUES (1,1500.00,'2024-05-21','crédito',1),(2,2500.00,'2024-05-22','débito',2),(3,2000.00,'2024-05-23','pix',3),(4,1200.00,'2024-05-24','crédito',4),(5,1500.00,'2024-05-25','débito',5),(6,1250.00,'2024-05-26','pix',1),(7,1000.00,'2024-05-27','crédito',2),(8,2000.00,'2024-05-30','débito',1),(9,1000.00,'2024-05-30','débito',1),(10,1000.00,'2024-05-30','débito',5),(11,1000.00,'2024-05-30','débito',1),(12,1000.00,'2024-05-30','débito',1),(13,2000.00,'2024-05-30','débito',1),(14,1500.00,'2024-06-01','débito',1),(15,1000.00,'2024-06-01','débito',1),(16,1600.00,'2024-06-01','débito',1),(17,100.00,'2024-06-01','débito',1),(18,2000.00,'2024-06-01','débito',1),(19,2000.00,'2024-06-01','débito',1),(20,2000.00,'2024-06-01','débito',2),(21,2000.00,'2024-06-01','débito',2),(22,1200.00,'2024-06-01','débito',1),(23,1200.00,'2024-06-01','débito',1),(24,1500.00,'2024-06-01','débito',1);
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornecedor` (
  `IDfornecedor` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Telefone` char(11) NOT NULL,
  `Endereco` varchar(50) DEFAULT NULL,
  `Cidade` varchar(30) NOT NULL,
  `Estado` char(2) NOT NULL,
  `Cnpj` char(18) NOT NULL,
  PRIMARY KEY (`IDfornecedor`),
  UNIQUE KEY `Cnpj` (`Cnpj`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` VALUES (1,'Fornecedor A','fornecedor_a@example.com','11223344556','Rua A, 123','Cidade A','AA','12345678901234'),(2,'Fornecedor B','fornecedor_b@example.com','22334455667','Rua B, 456','Cidade B','BB','23456789012345'),(3,'Fornecedor C','fornecedor_c@example.com','33445566778','Rua C, 789','Cidade C','CC','34567890123456'),(4,'Fornecedor D','fornecedor_d@example.com','44556677889','Rua D, 101','Cidade D','DD','45678901234567'),(5,'Fornecedor E','fornecedor_e@example.com','55667788990','Rua E, 202','Cidade E','EE','56789012345678'),(6,'19','19','19','198','19','19','19');
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itens_venda`
--

DROP TABLE IF EXISTS `itens_venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `itens_venda` (
  `IDprodutosVenda` int NOT NULL AUTO_INCREMENT,
  `Quantidade` int DEFAULT NULL,
  `IDProduto` int DEFAULT NULL,
  `IDVenda` int DEFAULT NULL,
  PRIMARY KEY (`IDprodutosVenda`),
  KEY `IDProduto` (`IDProduto`),
  KEY `IDVenda` (`IDVenda`),
  CONSTRAINT `itens_venda_ibfk_1` FOREIGN KEY (`IDProduto`) REFERENCES `produto` (`IDproduto`),
  CONSTRAINT `itens_venda_ibfk_2` FOREIGN KEY (`IDVenda`) REFERENCES `venda` (`IDvenda`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itens_venda`
--

LOCK TABLES `itens_venda` WRITE;
/*!40000 ALTER TABLE `itens_venda` DISABLE KEYS */;
INSERT INTO `itens_venda` VALUES (1,2,5,5),(2,1,5,6),(3,2,5,7),(4,3,5,8),(5,1,5,9),(6,1,5,10),(7,50,5,11),(8,1,8,12),(9,1,5,13),(10,1,5,14),(11,1,5,17),(12,1,5,18);
/*!40000 ALTER TABLE `itens_venda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `IDproduto` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(30) DEFAULT NULL,
  `Categoria` set('ABS','EBS','DISCO','LONA DE FREIO','AR','AUXILIAR','SUSPENSÃO') DEFAULT NULL,
  `ValorPago` float(12,2) DEFAULT NULL,
  `ValorVenda` float(12,2) DEFAULT NULL,
  `QuantidadeComprada` int NOT NULL,
  `QuantidadeVendida` int DEFAULT '0',
  `IDcompra` int DEFAULT NULL,
  PRIMARY KEY (`IDproduto`),
  KEY `IDcompra` (`IDcompra`),
  CONSTRAINT `produto_ibfk_1` FOREIGN KEY (`IDcompra`) REFERENCES `compra` (`IDcompra`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'Produto A','ABS',100.00,150.00,50,20,1),(2,'Produto B','EBS',200.00,250.00,30,30,2),(3,'Produto C','DISCO',150.00,200.00,40,10,3),(4,'Produto D','LONA DE FREIO',80.00,120.00,60,60,4),(5,'Produto A','ABS',100.00,150.00,5000,74,5),(6,'Produto B','EBS',200.00,250.00,15,5,6),(7,'Produto C','DISCO',150.00,200.00,10,5,7),(8,'Abacaxi','EBS',100.00,150.00,20,NULL,NULL),(9,'Laranja','ABS',100.00,150.00,20,0,8),(10,NULL,'ABS',100.00,150.00,10,0,9),(11,NULL,'ABS',100.00,150.00,10,0,10),(12,NULL,'DISCO',100.00,150.00,10,0,11),(13,NULL,'ABS',100.00,150.00,10,0,12),(14,NULL,'LONA DE FREIO',100.00,150.00,20,0,13),(15,NULL,'AUXILIAR',100.00,150.00,15,0,14),(16,NULL,'AR',100.00,150.00,10,0,15),(17,NULL,'LONA DE FREIO',100.00,150.00,16,0,16),(18,NULL,'LONA DE FREIO',100.00,150.00,1,0,17),(19,'Renan89','LONA DE FREIO',100.00,150.00,20,0,18),(20,'Renan89','LONA DE FREIO',100.00,150.00,20,0,19),(21,'Renan99','DISCO',100.00,150.00,20,0,20),(22,'Renan99','DISCO',100.00,150.00,20,0,21),(23,'Renan1','DISCO',100.00,150.00,12,0,22),(24,'Renan1','DISCO',100.00,150.00,12,0,23),(25,'vuto','ABS',100.00,150.00,15,0,24);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `produtosemestoque_v`
--

DROP TABLE IF EXISTS `produtosemestoque_v`;
/*!50001 DROP VIEW IF EXISTS `produtosemestoque_v`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `produtosemestoque_v` AS SELECT 
 1 AS `Nome`,
 1 AS `QuantidadeEmEstoque`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `venda`
--

DROP TABLE IF EXISTS `venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venda` (
  `IDvenda` int NOT NULL AUTO_INCREMENT,
  `CodVenda` varchar(50) NOT NULL,
  `ValorTotal` float(10,2) DEFAULT NULL,
  `DataDaVenda` date DEFAULT NULL,
  `TipoPagamento` set('débito','crédito','pix') DEFAULT NULL,
  `IDcliente` int DEFAULT NULL,
  `EstadoVenda` set('Pago','Cancelado','Em Andamento') DEFAULT 'Em Andamento',
  PRIMARY KEY (`IDvenda`),
  KEY `IDcliente` (`IDcliente`),
  CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`IDcliente`) REFERENCES `cliente` (`IDcliente`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
INSERT INTO `venda` VALUES (1,'1',1.00,NULL,'débito',1,'Pago'),(2,'2',20.00,NULL,'crédito',2,'Pago'),(3,'3',30.00,NULL,'débito',1,'Pago'),(4,'4',30.00,'2024-01-01','débito',1,'Pago'),(5,'10',300.00,'2024-05-29','débito',1,'Em Andamento'),(6,'50',150.00,'2024-05-29','débito',1,'Pago'),(7,'60',300.00,'2024-05-29','débito',1,'Pago'),(8,'80',450.00,'2024-05-29','débito',1,'Pago'),(9,'80',150.00,'2024-05-29','débito',1,'Pago'),(10,'65',150.00,'2024-05-29','débito',1,'Pago'),(11,'600',7500.00,'2024-05-29','débito',1,'Pago'),(12,'1',150.00,'2024-05-30','débito',1,'Pago'),(13,'1',150.00,'2024-05-30','débito',1,'Pago'),(14,'1',150.00,'2024-05-30','débito',1,'Pago'),(17,'20',150.00,'2024-05-30','débito',1,'Pago'),(18,'7000',150.00,'2024-05-31','pix',1,NULL);
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'masterfreios'
--

--
-- Dumping routines for database 'masterfreios'
--
/*!50003 DROP PROCEDURE IF EXISTS `AdicionarVenda` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AdicionarVenda`(
    IN p_IDProduto INT,
    IN p_IDCliente INT,
    IN p_CodVenda VARCHAR(50),
    IN p_Quantidade INT,
    IN p_TipoPagamento SET('débito','crédito','pix'),
	IN p_EstadoVenda SET('Pago', 'Cancelado', 'Em Andamento')
)
BEGIN
    DECLARE v_QuantidadeComprada INT;
    DECLARE v_QuantidadeVendidaAtual INT;
    DECLARE v_ValorVenda FLOAT(12, 2);
    DECLARE v_IDVenda INT;

    -- Recupera a quantidade comprada e vendida atual do produto
    SELECT QuantidadeComprada, QuantidadeVendida INTO v_QuantidadeComprada, v_QuantidadeVendidaAtual
    FROM Produto
    WHERE IDproduto = p_IDProduto;

    -- Verifica se a quantidade disponível é suficiente para venda
    IF v_QuantidadeVendidaAtual + p_Quantidade > v_QuantidadeComprada THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Erro: Quantidade vendida ultrapassa a quantidade comprada. Operação cancelada.';
    ELSE
        -- Recupera o valor de venda do produto
        SELECT ValorVenda INTO v_ValorVenda
        FROM Produto
        WHERE IDproduto = p_IDProduto;

        -- Insere um novo registro na tabela Venda
        INSERT INTO Venda (CodVenda, ValorTotal, DataDaVenda, TipoPagamento, IDcliente,EstadoVenda)
        VALUES (p_CodVenda, v_ValorVenda * p_Quantidade, CURDATE(), p_TipoPagamento, p_IDCliente,p_EstadoVenda);

        -- Recupera o ID da venda recém-inserida
        SET v_IDVenda = LAST_INSERT_ID();

        -- Insere o item na tabela Itens_Venda
        INSERT INTO Itens_Venda (Quantidade, IDProduto, IDVenda)
        VALUES (p_Quantidade, p_IDProduto, v_IDVenda);

        -- Incrementa a quantidade vendida no produto
        UPDATE Produto 
        SET QuantidadeVendida = QuantidadeVendida + p_Quantidade
        WHERE IDproduto = p_IDProduto;

    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AtualizarPrutodoInfo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AtualizarPrutodoInfo`(
	IN produtoNome VARCHAR(30), 
    IN novoValorPago FLOAT, 
    IN novoValorVenda FLOAT, 
    IN novoNomeFornecedor VARCHAR(30), 
    IN novaQuantidadeComprada INT, 
    IN novoTipoPagamento SET('crédito', 'débito', 'pix'),
    IN novaCategoria SET('ABS', 'EBS', 'DISCO', 'LONA DE FREIO', 'AR', 'AUXILIAR', 'SUSPENSÃO')
)
BEGIN
    DECLARE fornecedorId INT;
    DECLARE produtoId INT;
    DECLARE compraId INT;
    DECLARE produtoExiste INT DEFAULT 0;

    -- Verificar se o produto existe
    SELECT IDproduto, IDcompra INTO produtoId, compraId
    FROM produto
    WHERE Nome = produtoNome;

    SET produtoExiste = IFNULL(produtoId, 0);

    IF produtoExiste != 0 THEN
        -- Obter o ID do fornecedor
        SELECT IDfornecedor INTO fornecedorId
        FROM fornecedor
        WHERE Nome = novoNomeFornecedor;

        -- Atualizar o produto com as novas informações
        UPDATE produto
        SET 
            ValorPago = novoValorPago, 
            ValorVenda = novoValorVenda, 
            QuantidadeComprada = novaQuantidadeComprada,
            Categoria = novaCategoria
        WHERE 
            Nome = produtoNome;

        -- Atualizar a compra associada ao produto
        UPDATE compra
        SET 
            ValorTotal = novoValorPago * novaQuantidadeComprada,
            TipoDePagamento = novoTipoPagamento,
            IDfornecedor = fornecedorId
        WHERE 
            IDcompra = compraId;
    ELSE
        -- Produto não existe, retornar um erro
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Produto não existe';
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AtualizarStatusVenda` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AtualizarStatusVenda`(
	IN vendaId INT, 
    IN novoStatus Set('Pago', 'Cancelado', 'Em Andamento')
    )
BEGIN
    UPDATE venda
    SET EstadoVenda = novoStatus
    WHERE IDvenda = vendaId;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `InserirProdutoCompra` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `InserirProdutoCompra`(
    IN produto_nome VARCHAR(30),
    IN fornecedor_id INT,
    IN quantidade INT,
    IN valor_compra FLOAT(12,2),
    IN valor_venda FLOAT(12,2),
    IN produto_categoria VARCHAR(20),
    IN tipo_pagamento VARCHAR(10)
)
BEGIN
    DECLARE compra_id INT;

    -- Verificar se o valor de venda é maior que o valor de compra
    IF valor_venda <= valor_compra THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Erro: O valor de venda deve ser maior que o valor de compra.';
    END IF;

    -- Inserir na tabela Compra
    INSERT INTO Compra (ValorTotal, DataDaCompra, TipoDePagamento, IDfornecedor)
    VALUES (valor_compra * quantidade, CURDATE(), tipo_pagamento, fornecedor_id);

    -- Obter o ID da compra inserida
    SET compra_id = LAST_INSERT_ID();

    -- Inserir na tabela Produto
    
    INSERT INTO Produto (Nome, Categoria, ValorPago, ValorVenda, QuantidadeComprada, IDcompra)
    VALUES (produto_nome, produto_categoria, valor_compra, valor_venda, quantidade, compra_id);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PegarInfoProduto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PegarInfoProduto`(
	IN produtoNome VARCHAR(30),
    OUT ValorPago FLoat(12,2),
    OUT ValorVenda FLOAT (12,2),
    OUT Lucro FLOAT (12,2),
    OUT NomeFornecedor VARCHAR(30),
    OUT QuantidadeComprada INT,
    OUT TipoDePamaneto VARCHAR(10),
    OUT Categoria SET('ABS', 'EBS', 'DISCO', 'LONA DE FREIO', 'AR', 'AUXILIAR', 'SUSPENSÃO')
    )
BEGIN
    SELECT 
        p.ValorPago, 
        p.ValorVenda, 
        (p.ValorVenda - p.ValorPago) AS Lucro,
        f.Nome AS NomeFornecedor, 
        p.QuantidadeComprada, 
        c.TipoDePagamento,
        p.Categoria
	INTO
		ValorPago,
        ValorVenda,
        Lucro,
        NomeFornecedor,
        QuantidadeComprada,
        TipoDePamaneto,
        Categoria
    FROM 
        produto p
    LEFT JOIN 
        compra c ON p.IDcompra = c.IDcompra
    LEFT JOIN 
        fornecedor f ON c.IDfornecedor = f.IDfornecedor
    WHERE 
        p.Nome = produtoNome;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PegarProdutosVenda` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PegarProdutosVenda`(
	IN vendaId INT,
    OUT produtoName VARCHAR(30),
    OUT quantidade INT
    )
BEGIN
SELECT 
        p.Nome AS NomeProduto, 
        iv.Quantidade 
	INTO 
		produtoName,quantidade
    FROM 
        itens_venda iv
    INNER JOIN 
        produto p ON iv.IDProduto = p.IDproduto
    WHERE 
        iv.IDVenda = vendaId;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `RetornoNomeProduto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `RetornoNomeProduto`(
	IN p_IDVenda INT,
    OUT NomeProduto VARCHAR(50)
)
BEGIN
    declare ArmazenaIDProduto INT;
	Select IDProduto INTO ArmazenaIDProduto  from itens_venda where IDVenda = p_IDVenda;
    
    Select Nome INTO NomeProduto from produto where IDProduto = ArmazenaIDProduto ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `VerVenda` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `VerVenda`(IN p_CodVenda VARCHAR(50))
BEGIN
    SELECT Produto.Nome, Itens_Venda.Quantidade
    FROM Venda
    INNER JOIN Itens_Venda ON Venda.IDvenda = Itens_Venda.IDvenda
    INNER JOIN Produto ON Produto.IDproduto = Itens_Venda.IDproduto
    WHERE Venda.CodVenda = p_CodVenda;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `produtosemestoque_v`
--

/*!50001 DROP VIEW IF EXISTS `produtosemestoque_v`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `produtosemestoque_v` AS select `produto`.`Nome` AS `Nome`,sum((`produto`.`QuantidadeComprada` - `produto`.`QuantidadeVendida`)) AS `QuantidadeEmEstoque` from `produto` group by `produto`.`Nome` having (`QuantidadeEmEstoque` > 0) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-04 20:14:38
[0x7FFD71806470] ANOMALY: meaningless REX prefix used
