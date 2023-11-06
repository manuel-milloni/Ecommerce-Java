CREATE DATABASE  IF NOT EXISTS `sistema_ventas_java` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sistema_ventas_java`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sistema_ventas_java
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idCategoria` int unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(30) NOT NULL,
  PRIMARY KEY (`idCategoria`),
  UNIQUE KEY `idCategoria_UNIQUE` (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Minorista'),(2,'Mayorista');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idCliente` int unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `telefono` varchar(30) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `direccion` varchar(30) DEFAULT NULL,
  `cuit` varchar(30) NOT NULL,
  `idCategoria` int unsigned NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE KEY `idCliente_UNIQUE` (`idCliente`),
  UNIQUE KEY `CUIT_UNIQUE` (`cuit`),
  KEY `FK_Cliente_Categoria` (`idCategoria`),
  CONSTRAINT `FK_Cliente_Categoria` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (7,'John','Doe','341 1111111','johndoe@gmail.com','Salta 1500','20374482311',1,'C/DZ9XrfdBvdCVvUXDSnZg=='),(8,'Armando','Banquitos','341 2222222','armandobanquitos@gmail.com','Mitre 3000','20364422111',2,'C/DZ9XrfdBvdCVvUXDSnZg=='),(9,'Armando','Paredes','341 3333333','armandoparedes@gmail.com','Tucuman 2000','20353212641',1,'C/DZ9XrfdBvdCVvUXDSnZg=='),(10,'Aquiles','Bailo','341 4444444','aquilesbailo@gmail.com','Urquiza 1750','20313245641',2,'C/DZ9XrfdBvdCVvUXDSnZg=='),(11,'Aquiles','Canto','341 5555555','aquilescanto@gmail.com','Rioja 3000','20303136741',1,'C/DZ9XrfdBvdCVvUXDSnZg=='),(12,'Benito','Lopez','341 6666666','benito@gmail.com','Moreno 750','20392357841',2,'C/DZ9XrfdBvdCVvUXDSnZg==');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `idEmpleado` int unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `telefono` varchar(30) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `direccion` varchar(30) DEFAULT NULL,
  `dni` varchar(10) NOT NULL,
  `idRol` int unsigned NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`idEmpleado`),
  UNIQUE KEY `dni_UNIQUE` (`dni`),
  KEY `FK_Empleado_Rol` (`idRol`),
  CONSTRAINT `FK_Empleado_Rol` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (1,'Manuel','Milloni','3413567882','manuel-milloni@hotmail.com.ar','Mendoza 3456','37456298',1,'C/DZ9XrfdBvdCVvUXDSnZg=='),(2,'Feliciano','Mozzi','3329405244','felicianomozzi02@gmail.com','Moreno 1358','44114394',1,'C/DZ9XrfdBvdCVvUXDSnZg=='),(3,'Matías','Ferullo','3482539522','matias.ferullo1@gmail.com','Rioja 1234','43214321',1,'C/DZ9XrfdBvdCVvUXDSnZg=='),(4,'Claudio','Mozzi','3329581427','claudiomozzi@gmail.com','Saavedra 473','16301960',2,'C/DZ9XrfdBvdCVvUXDSnZg=='),(5,'Sergio','Rochetti','3329151515','sergiorochetti@gmail.com','Castro 1145','16444185',2,'C/DZ9XrfdBvdCVvUXDSnZg==');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `linea_de_pedido`
--

DROP TABLE IF EXISTS `linea_de_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `linea_de_pedido` (
  `idLineaPedido` int unsigned NOT NULL AUTO_INCREMENT,
  `cantProducto` int unsigned NOT NULL,
  `nroPedido` int unsigned NOT NULL,
  `idProducto` int unsigned NOT NULL,
  PRIMARY KEY (`idLineaPedido`),
  UNIQUE KEY `idLineaPedido_UNIQUE` (`idLineaPedido`),
  KEY `FK_LineaDePedido_Producto` (`idProducto`),
  KEY `FK_LineaDePedido_Pedido` (`nroPedido`),
  CONSTRAINT `FK_LineaDePedido_Pedido` FOREIGN KEY (`nroPedido`) REFERENCES `pedido` (`nroPedido`),
  CONSTRAINT `FK_LineaDePedido_Producto` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `linea_de_pedido`
--

LOCK TABLES `linea_de_pedido` WRITE;
/*!40000 ALTER TABLE `linea_de_pedido` DISABLE KEYS */;
INSERT INTO `linea_de_pedido` VALUES (1,1,1,1),(2,2,2,2),(3,1,3,3),(4,3,4,4),(5,2,5,5);
/*!40000 ALTER TABLE `linea_de_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `linea_de_venta`
--

DROP TABLE IF EXISTS `linea_de_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `linea_de_venta` (
  `idLineaVenta` int unsigned NOT NULL AUTO_INCREMENT,
  `cantProducto` int unsigned NOT NULL,
  `nroVenta` int unsigned NOT NULL,
  `idProducto` int unsigned NOT NULL,
  `precio` decimal(10,2) unsigned NOT NULL,
  PRIMARY KEY (`idLineaVenta`),
  KEY `FK_LineaDeVenta_Producto` (`idProducto`),
  KEY `FK_LineaDeVenta_Venta` (`nroVenta`),
  CONSTRAINT `FK_LineaDeVenta_Producto` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`),
  CONSTRAINT `FK_LineaDeVenta_Venta` FOREIGN KEY (`nroVenta`) REFERENCES `venta` (`nroVenta`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `linea_de_venta`
--

LOCK TABLES `linea_de_venta` WRITE;
/*!40000 ALTER TABLE `linea_de_venta` DISABLE KEYS */;
INSERT INTO `linea_de_venta` VALUES (1,2,1,1,84900.00),(2,3,2,2,123500.00),(3,1,3,3,49900.00),(4,4,4,4,129999.00),(5,2,5,5,68950.00),(6,3,6,6,21650.00);
/*!40000 ALTER TABLE `linea_de_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `idPedido` int unsigned NOT NULL AUTO_INCREMENT,
  `fechaPedido` date NOT NULL,
  `idEmpleado` int unsigned NOT NULL,
  `nroPedido` int unsigned NOT NULL,
  `idProveedor` int unsigned NOT NULL,
  PRIMARY KEY (`idPedido`),
  UNIQUE KEY `idPedido_UNIQUE` (`idPedido`),
  UNIQUE KEY `nroPedido_UNIQUE` (`nroPedido`),
  KEY `FK_Pedido_Empleado` (`idEmpleado`),
  KEY `FK_Pedido_Proveedor_idx` (`idProveedor`),
  CONSTRAINT `FK_Pedido_Empleado` FOREIGN KEY (`idEmpleado`) REFERENCES `empleado` (`idEmpleado`),
  CONSTRAINT `FK_Pedido_Proveedor` FOREIGN KEY (`idProveedor`) REFERENCES `proveedor` (`idProveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,'2023-10-31',1,1,1),(2,'2023-11-01',2,2,2),(3,'2023-11-02',3,3,3),(4,'2023-11-03',1,4,4),(5,'2023-11-04',2,5,5),(6,'2023-11-05',3,6,1);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `idProducto` int unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(90) NOT NULL,
  `precio` decimal(10,2) unsigned NOT NULL,
  `idTipo` int unsigned NOT NULL,
  `stock` int unsigned DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idProducto`),
  UNIQUE KEY `idProducto_UNIQUE` (`idProducto`),
  KEY `FK_Producto_TipoProducto` (`idTipo`),
  CONSTRAINT `FK_Producto_TipoProducto` FOREIGN KEY (`idTipo`) REFERENCES `tipo_producto` (`idTipo`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Procesador AMD RYZEN 3 3200G 4.0GHz Turbo + Radeon Vega 8 AM4 Wraith Stealth Cooler',84900.00,4,10,'https://imagenes.compragamer.com/productos/compragamer_Imganen_general_16752_Procesador_AMD_RYZEN_3_3200G_4.0GHz_Turbo___Radeon_Vega_8_AM4_Wraith_Stealth_Cooler_f414a507-grn.jpg'),(2,'Procesador AMD RYZEN 5 3600 4.2GHz Turbo AM4 Wraith Stealth Cooler',123500.00,4,10,'https://imagenes.compragamer.com/productos/compragamer_Imganen_general_16749_Procesador_AMD_RYZEN_5_3600_4.2GHz_Turbo_AM4_Wraith_Stealth_Cooler_f8ab4915-grn.jpg'),(3,'Gabinete Antec NX292 MESH RGB Vidrio Templado',49900.00,6,10,'https://imagenes.compragamer.com/productos/compragamer_Imganen_general_35714_Gabinete_Antec_NX292_MESH_RGB_Vidrio_Templado_a017a79f-grn.jpg'),(4,'Monitor Gamer Samsung 24\" G50 Curvo 144Hz Full HD VA FreeSync',129999.00,7,10,'https://imagenes.compragamer.com/productos/compragamer_Imganen_general_37305_Monitor_Gamer_Samsung_24__G50_Curvo_144Hz_Full_HD_VA_FreeSync_2fa95cdc-grn.jpg'),(5,'Disco Sólido SSD M.2 ADATA 1TB XPG Spectrix S40G RGB 3500MB/s NVMe PCI-E X4',68950.00,8,10,'https://imagenes.compragamer.com/productos/compragamer_Imganen_general_16533_Disco_S__lido_SSD_M.2_ADATA_1TB_XPG_Spectrix_S40G_RGB_3500MB_s_NVMe_PCI-E_X4_7622f5c4-grn.jpg'),(6,'Disco Solido SSD Team 512GB GX2 530MB/s',21650.00,8,10,'https://imagenes.compragamer.com/productos/compragamer_Imganen_general_33802_Disco_Solido_SSD_Team_512GB_GX2_530MB_s_7d116a15-grn.jpg'),(7,'Memoria Adata DDR4 16GB (2x8GB) 4133MHz XPG Spectrix D60G RGB',65030.00,5,10,'https://imagenes.compragamer.com/productos/compragamer_Imganen_general_36913_Memoria_Adata_DDR4_16GB__2x8GB__4133MHz_XPG_Spectrix_D60G_RGB_73bb84d3-grn.jpg'),(8,'Teclado Mecanico Game PRO GK70M RGB Black TKL',10190.00,1,10,'https://imagenes.compragamer.com/productos/compragamer_Imganen_general_36450_Teclado_Mecanico_Game_PRO_GK70M_RGB_Black_TKL_142da50f-grn.jpg'),(9,'Teclado Redragon Shiva K512 RGB Español Black',24480.00,1,10,'https://imagenes.compragamer.com/productos/compragamer_Imganen_general_24184_Teclado_Redragon_Shiva_K512_RGB_Espa__ol_Black_84a250d3-grn.jpg'),(10,'Mouse Wesdar Cerberus x4 White Rainbow 2400DPI',2100.00,2,10,'https://imagenes.compragamer.com/productos/compragamer_Imganen_general_36413_Mouse_Wesdar_Cerberus_x4_White_Rainbow_2400DPI_6656a722-grn.jpg'),(11,'Combo Mouse Gaming y Mousepad Wesdar Black Green X2-BG',2650.00,2,10,'https://imagenes.compragamer.com/productos/compragamer_Imganen_general_22553_Combo_Mouse_Gaming_y_Mousepad_Wesdar_Black_Green_X2-BG_fbe90499-grn.jpg'),(12,'Placa de Video Zotac GeForce RTX 4090 24GB GDDR6X Trinity OC White Edition',1672900.00,3,10,'https://imagenes.compragamer.com/productos/compragamer_Imganen_general_37223_Placa_de_Video_Zotac_GeForce_RTX_4090_24GB_GDDR6X_Trinity_OC_White_Edition_b9d7fda3-grn.jpg'),(13,'Placa de Video Zotac GeForce RTX 4080 16GB GGDR6X Trinity OC',1225850.00,3,10,'https://imagenes.compragamer.com/productos/compragamer_Imganen_general_37210_Placa_de_Video_Zotac_GeForce_RTX_4080_16GB_GGDR6X_Trinity_OC_1ed2043b-grn.jpg');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `idProveedor` int unsigned NOT NULL AUTO_INCREMENT,
  `cuit` char(30) NOT NULL,
  `razonSocial` char(30) NOT NULL,
  `telefono` char(30) NOT NULL,
  `email` char(50) DEFAULT NULL,
  PRIMARY KEY (`idProveedor`),
  UNIQUE KEY `idProveedor_UNIQUE` (`idProveedor`),
  UNIQUE KEY `cuit_UNIQUE` (`cuit`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'20182657419','Hardcore Computacion','3413582341','hardcore@computacion.com.ar'),(2,'20351293784','Todo computacion','3413345341','tcomputacion@gmail.com'),(3,'20236748921','Compumundo','3415673441','compumundo@gmail.com'),(4,'20893156428','Mayorista Informatica SRL','3414563344','informatica@gmail.com'),(5,'20564192735','Fox Insumos','3414567891','fox@gmail.com');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `idRol` int unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` char(30) NOT NULL,
  PRIMARY KEY (`idRol`),
  UNIQUE KEY `idRol_UNIQUE` (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Administrador'),(2,'Empleado');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_producto`
--

DROP TABLE IF EXISTS `tipo_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_producto` (
  `idTipo` int unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(30) NOT NULL,
  PRIMARY KEY (`idTipo`),
  UNIQUE KEY `idTipo_UNIQUE` (`idTipo`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_producto`
--

LOCK TABLES `tipo_producto` WRITE;
/*!40000 ALTER TABLE `tipo_producto` DISABLE KEYS */;
INSERT INTO `tipo_producto` VALUES (1,'Teclado'),(2,'Mouse'),(3,'Tarjeta Gráfica'),(4,'CPU'),(5,'RAM'),(6,'Gabinete'),(7,'Monitor'),(8,'SDD'),(9,'HDD');
/*!40000 ALTER TABLE `tipo_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `idVenta` int unsigned NOT NULL AUTO_INCREMENT,
  `fechaVenta` datetime NOT NULL,
  `idCliente` int unsigned NOT NULL,
  `nroVenta` int unsigned NOT NULL,
  PRIMARY KEY (`idVenta`),
  UNIQUE KEY `nroVenta_UNIQUE` (`nroVenta`),
  KEY `FK_Venta_Cliente` (`idCliente`),
  CONSTRAINT `FK_Venta_Cliente` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` VALUES (1,'2023-10-31 00:00:00',7,1),(2,'2023-10-31 00:00:00',8,2),(3,'2023-11-01 00:00:00',9,3),(4,'2023-11-01 00:00:00',10,4),(5,'2023-11-02 00:00:00',11,5),(6,'2023-11-03 00:00:00',12,6),(7,'2023-11-03 00:00:00',7,7);
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-06 20:56:45
