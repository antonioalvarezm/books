-- MySQL dump 10.16  Distrib 10.1.37-MariaDB, for osx10.10 (x86_64)
--
-- Host: localhost    Database: bd_books
-- ------------------------------------------------------
-- Server version	10.1.37-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `paises`
--

DROP TABLE IF EXISTS `paises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paises` (
  `pai_id` varchar(2) NOT NULL DEFAULT '',
  `pai_nombre` varchar(100) DEFAULT NULL,
  `pai_moneda` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`pai_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estados` (
  `est_id_pais` varchar(2) NOT NULL,
  `est_id_estado` varchar(2) NOT NULL,
  `est_nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`est_id_estado`),
  KEY `fk_pais_estados` (`est_id_pais`),
  CONSTRAINT `fk_pais_estados` FOREIGN KEY (`est_id_pais`) REFERENCES `paises` (`pai_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipos_asentamiento`
--

DROP TABLE IF EXISTS `tipos_asentamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_asentamiento` (
  `tas_id` int(11) NOT NULL AUTO_INCREMENT,
  `tas_nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`tas_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `municipios`
--

DROP TABLE IF EXISTS `municipios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `municipios` (
  `mun_id_municipio` int(11) NOT NULL AUTO_INCREMENT,
  `mun_id_estado` varchar(2) NOT NULL,
  `mun_nombre` varchar(50) NOT NULL,
  `mun_codigo` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`mun_id_municipio`),
  KEY `idx_mun_est` (`mun_id_estado`),
  CONSTRAINT `fk_mun_est` FOREIGN KEY (`mun_id_estado`) REFERENCES `estados` (`est_id_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=2459 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entidades`
--

DROP TABLE IF EXISTS `entidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entidades` (
  `ent_id` int(11) NOT NULL AUTO_INCREMENT,
  `ent_estatus` int(11) NOT NULL DEFAULT '1',
  `ent_rfc` varchar(13) NOT NULL,
  `ent_razon_social` varchar(180) NOT NULL,
  `ent_calle` varchar(120) DEFAULT NULL,
  `ent_num_ext` varchar(50) DEFAULT NULL,
  `ent_num_int` varchar(50) DEFAULT NULL,
  `ent_tipo_asentamiento` int(11) DEFAULT '9',
  `ent_colonia` varchar(120) DEFAULT NULL,
  `ent_pais` varchar(2) DEFAULT 'MX',
  `ent_estado` varchar(2) DEFAULT 'DF',
  `ent_municipio` int(11) DEFAULT NULL,
  `ent_cp` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`ent_id`),
  UNIQUE KEY `unq_rfc` (`ent_rfc`),
  KEY `fk_entidad_estado_idx` (`ent_estado`),
  KEY `fk_entidad_municipio_idx` (`ent_municipio`),
  KEY `fk_entidad_pais_idx` (`ent_pais`),
  CONSTRAINT `fk_entidad_estado` FOREIGN KEY (`ent_estado`) REFERENCES `estados` (`est_id_estado`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_entidad_municipio` FOREIGN KEY (`ent_municipio`) REFERENCES `municipios` (`mun_id_municipio`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_entidad_pais` FOREIGN KEY (`ent_pais`) REFERENCES `paises` (`pai_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=727 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `metodos_pago`
--

DROP TABLE IF EXISTS `metodos_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `metodos_pago` (
  `mpg_id` char(3) NOT NULL,
  `mpg_descripcion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`mpg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `formas_pago`
--

DROP TABLE IF EXISTS `formas_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formas_pago` (
  `fpg_id` int(11) NOT NULL AUTO_INCREMENT,
  `fpg_descripcion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`fpg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tasa_cuota`
--

DROP TABLE IF EXISTS `tasa_cuota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasa_cuota` (
  `tsc_id` int(11) NOT NULL AUTO_INCREMENT,
  `tsc_id_impuesto` int(11) DEFAULT NULL,
  `tsc_factor` char(1) DEFAULT 'T',
  `tsc_rango_fijo` char(1) DEFAULT 'F',
  `tsc_valor_min` double DEFAULT NULL,
  `tsc_valor_max` double DEFAULT NULL,
  `tsc_traslado` smallint(1) DEFAULT NULL,
  `tsc_retencion` smallint(1) DEFAULT NULL,
  `tsc_fecha_inicio` date DEFAULT NULL,
  `tsc_fecha_final` date DEFAULT NULL,
  PRIMARY KEY (`tsc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipos_comprobante`
--

DROP TABLE IF EXISTS `tipos_comprobante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_comprobante` (
  `tcm_id` char(1) NOT NULL,
  `tcm_descripcion` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`tcm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipos_unidad`
--

DROP TABLE IF EXISTS `tipos_unidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_unidad` (
  `tun_id` varchar(3) NOT NULL,
  `tun_descripcion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`tun_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipos_uso_cfdi`
--

DROP TABLE IF EXISTS `tipos_uso_cfdi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_uso_cfdi` (
  `tuc_id` varchar(3) NOT NULL,
  `tuc_descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`tuc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `productos_servicios`
--

DROP TABLE IF EXISTS `productos_servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos_servicios` (
  `prd_id` int(11) NOT NULL AUTO_INCREMENT,
  `prd_descripcion` varchar(180) DEFAULT NULL,
  `prd_fecha_inicio_vig` date DEFAULT NULL,
  `prd_fecha_fin_vig` date DEFAULT NULL,
  PRIMARY KEY (`prd_id`)
) ENGINE=InnoDB AUTO_INCREMENT=95141905 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cfdis`
--

DROP TABLE IF EXISTS `cfdis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cfdis` (
  `cfdi_id` int(11) NOT NULL AUTO_INCREMENT,
  `cfdi_emisor` varchar(13) NOT NULL,
  `cfdi_regimen_fiscal` int(11) DEFAULT NULL,
  `cfdi_receptor` varchar(13) NOT NULL,
  `cfdi_uso_cfdi` varchar(3) DEFAULT NULL,
  `cfdi_tipo_comprobante` char(1) NOT NULL DEFAULT 'I',
  `cfdi_fecha` date NOT NULL,
  `cfdi_uuid` varchar(40) DEFAULT NULL,
  `cfdi_estatus` int(11) NOT NULL DEFAULT '1',
  `cfdi_serie` varchar(20) DEFAULT NULL,
  `cfdi_folio` varchar(40) DEFAULT NULL,
  `cfdi_metodo_pago` char(3) NOT NULL DEFAULT 'PUE',
  `cfdi_forma_pago` int(11) NOT NULL DEFAULT '99',
  `cfdi_moneda` varchar(3) DEFAULT 'MXN',
  `cfdi_subtotal` double DEFAULT '0',
  `cfdi_descuento` double DEFAULT '0',
  `cfdi_impuestos_retenidos` double DEFAULT '0',
  `cfdi_impuestos_trasladados` double DEFAULT '0',
  `cfdi_total` double DEFAULT '0',
  `cfdi_tc` double DEFAULT '1',
  `cfdi_tipo_declaracion` char(1) DEFAULT 'M',
  PRIMARY KEY (`cfdi_id`),
  UNIQUE KEY `unq_cfdi_uuid` (`cfdi_uuid`),
  KEY `fk_cfdi_emisor_idx` (`cfdi_emisor`),
  KEY `fk_cfdi_forma_pago_idx` (`cfdi_forma_pago`),
  KEY `fk_cfdi_estatus_idx` (`cfdi_estatus`),
  KEY `fk_cfdi_metodo_pago_idx` (`cfdi_metodo_pago`),
  KEY `fk_cfdi_tipos_comprobante_idx` (`cfdi_tipo_comprobante`),
  KEY `fk_cfdi_regfiscal_idx` (`cfdi_regimen_fiscal`),
  KEY `fk_cfdi_uso_idx` (`cfdi_uso_cfdi`),
  CONSTRAINT `fk_cfdi_estatus` FOREIGN KEY (`cfdi_estatus`) REFERENCES `estatus` (`est_id`),
  CONSTRAINT `fk_cfdi_formas_pago` FOREIGN KEY (`cfdi_forma_pago`) REFERENCES `formas_pago` (`fpg_id`),
  CONSTRAINT `fk_cfdi_regfiscal` FOREIGN KEY (`cfdi_regimen_fiscal`) REFERENCES `regimen_fiscal` (`ref_id`),
  CONSTRAINT `fk_cfdi_uso` FOREIGN KEY (`cfdi_uso_cfdi`) REFERENCES `tipos_uso_cfdi` (`tuc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4392 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cfdis_impuestos`
--

DROP TABLE IF EXISTS `cfdis_impuestos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cfdis_impuestos` (
  `cfi_id` int(11) NOT NULL AUTO_INCREMENT,
  `cfi_id_cfdi` int(11) NOT NULL,
  `cfi_impuesto` int(11) NOT NULL,
  `cfi_factor` char(1) NOT NULL,
  `cfi_tasa_cuota` double DEFAULT NULL,
  `cfi_importe` double DEFAULT NULL,
  PRIMARY KEY (`cfi_id`),
  KEY `fk_cfdi_comprobante_idx` (`cfi_id_cfdi`),
  KEY `fk_cfdi_impuesto_idx` (`cfi_impuesto`),
  KEY `fk_concepto_factor_idx` (`cfi_factor`),
  CONSTRAINT `fk_cfdi_comprobante` FOREIGN KEY (`cfi_id_cfdi`) REFERENCES `cfdis` (`cfdi_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_concepto_impuesto` FOREIGN KEY (`cfi_impuesto`) REFERENCES `impuestos` (`imp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3129 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cfdis_conceptos`
--

DROP TABLE IF EXISTS `cfdis_conceptos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cfdis_conceptos` (
  `cfc_id` int(11) NOT NULL AUTO_INCREMENT,
  `cfc_id_cfdi` int(11) NOT NULL,
  `cfc_clave_prod_serv` int(11) NOT NULL,
  `cfc_no_identificacion` varchar(50) DEFAULT NULL,
  `cfc_cantidad` double DEFAULT NULL,
  `cfc_unidad` varchar(10) DEFAULT NULL,
  `cfc_descripcion` varchar(255) DEFAULT NULL,
  `cfc_valor_unitario` double DEFAULT NULL,
  `cfc_importe` double DEFAULT NULL,
  `cfc_deducible` smallint(1) DEFAULT '1',
  PRIMARY KEY (`cfc_id`),
  KEY `fk_cfdi_det_idx` (`cfc_id_cfdi`),
  KEY `fk_cve_prodserv_idx` (`cfc_clave_prod_serv`),
  CONSTRAINT `fk_cfdi_det` FOREIGN KEY (`cfc_id_cfdi`) REFERENCES `cfdis` (`cfdi_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cfdi_prodserv` FOREIGN KEY (`cfc_clave_prod_serv`) REFERENCES `productos_servicios` (`prd_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6187 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cfdis_conceptos_impuestos`
--

DROP TABLE IF EXISTS `cfdis_conceptos_impuestos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cfdis_conceptos_impuestos` (
  `cci_id` int(11) NOT NULL AUTO_INCREMENT,
  `cci_id_cfdi` int(11) NOT NULL,
  `cci_id_concepto` int(11) NOT NULL,
  `cci_impuesto` int(11) NOT NULL,
  `cci_factor` char(1) DEFAULT NULL,
  `cci_base` double DEFAULT NULL,
  `cci_tasa_cuota` double DEFAULT NULL,
  `cci_importe` double DEFAULT NULL,
  PRIMARY KEY (`cci_id`),
  KEY `fk_impuesto_by_concepto` (`cci_id_concepto`),
  CONSTRAINT `fk_impuesto_by_concepto` FOREIGN KEY (`cci_id_concepto`) REFERENCES `cfdis_conceptos` (`cfc_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4282 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nomina`
--

DROP TABLE IF EXISTS `nomina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nomina` (
  `nom_id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_id_cfdi` int(11) DEFAULT NULL,
  `nom_patron_rfc` varchar(13) DEFAULT NULL,
  `nom_registro_patronal` varchar(50) DEFAULT NULL,
  `nom_curp` varchar(18) DEFAULT NULL,
  `nom_nss` varchar(20) DEFAULT NULL,
  `nom_fecha_inicio_laboral` date DEFAULT NULL,
  `nom_antiguedad` varchar(10) DEFAULT NULL,
  `nom_tipo_contrato` int(11) DEFAULT NULL,
  `nom_tipo_jornada` int(11) DEFAULT NULL,
  `nom_tipo_regimen` int(11) DEFAULT NULL,
  `nom_num_empleado` varchar(20) DEFAULT NULL,
  `nom_departamento` varchar(100) DEFAULT NULL,
  `nom_puesto` varchar(100) DEFAULT NULL,
  `nom_riesgo_puesto` int(11) DEFAULT NULL,
  `nom_periodicidad_pago` int(11) DEFAULT NULL,
  `nom_banco` int(11) DEFAULT NULL,
  `nom_cuenta_bancaria` varchar(20) DEFAULT NULL,
  `nom_salario_diario_integrado` double DEFAULT NULL,
  `nom_tipo_nomina` varchar(2) DEFAULT NULL,
  `nom_fecha_pago` date DEFAULT NULL,
  `nom_fecha_inicial` date DEFAULT NULL,
  `nom_fecha_final` date DEFAULT NULL,
  `nom_num_dias` double DEFAULT NULL,
  `nom_total_percepciones` double DEFAULT NULL,
  `nom_total_deducciones` double DEFAULT NULL,
  `nom_total_otros_pagos` double DEFAULT NULL,
  `nom_total_sueldos` double DEFAULT NULL,
  `nom_total_gravado` double DEFAULT NULL,
  `nom_total_exento` double DEFAULT NULL,
  `nom_total_otras_deducciones` double DEFAULT NULL,
  `nom_total_impuestos_retenidos` double DEFAULT NULL,
  PRIMARY KEY (`nom_id`),
  KEY `fk_nom_cfdi_idx` (`nom_id_cfdi`),
  CONSTRAINT `fk_cfdi_nomina` FOREIGN KEY (`nom_id_cfdi`) REFERENCES `cfdis` (`cfdi_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nomina_detalle`
--

DROP TABLE IF EXISTS `nomina_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nomina_detalle` (
  `ndt_id` int(11) NOT NULL AUTO_INCREMENT,
  `ndt_id_nomina` int(11) NOT NULL,
  `ndt_clase` varchar(2) DEFAULT NULL,
  `ndt_tipo` varchar(3) DEFAULT NULL,
  `ndt_clave` varchar(10) DEFAULT NULL,
  `ndt_concepto` varchar(120) DEFAULT NULL,
  `ndt_importe_gravado` double DEFAULT NULL,
  `ndt_importe_exento` double DEFAULT NULL,
  `ndt_importe` double DEFAULT NULL,
  PRIMARY KEY (`ndt_id`),
  KEY `fk_nom_detalle_idx` (`ndt_id_nomina`),
  CONSTRAINT `fk_nom_det` FOREIGN KEY (`ndt_id_nomina`) REFERENCES `nomina` (`nom_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1472 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pagos`
--

DROP TABLE IF EXISTS `pagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagos` (
  `pag_id` int(11) NOT NULL AUTO_INCREMENT,
  `pag_id_cfdi` int(11) DEFAULT NULL,
  `pag_fecha_pago` date DEFAULT NULL,
  `pag_id_forma_pago` int(11) DEFAULT NULL,
  `pag_moneda` varchar(3) DEFAULT 'MXN',
  `pag_monto` double DEFAULT NULL,
  PRIMARY KEY (`pag_id`),
  KEY `fk_cfdi_pago_idx` (`pag_id_cfdi`),
  CONSTRAINT `fk_cfdi_pago` FOREIGN KEY (`pag_id_cfdi`) REFERENCES `cfdis` (`cfdi_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=261 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pagos_docs_relacionados`
--

DROP TABLE IF EXISTS `pagos_docs_relacionados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagos_docs_relacionados` (
  `pdr_id` int(11) NOT NULL AUTO_INCREMENT,
  `pdr_id_pago` int(11) DEFAULT NULL,
  `pdr_uuid_relacionado` varchar(40) DEFAULT NULL,
  `pdr_moneda` varchar(3) DEFAULT 'MXN',
  `pdr_metodo_pago` varchar(3) DEFAULT 'PPD',
  `pdr_parcialidad` int(11) DEFAULT NULL,
  `pdr_saldo_anterior` double DEFAULT NULL,
  `pdr_importe_pagado` double DEFAULT NULL,
  `pdr_saldo_insoluto` double DEFAULT NULL,
  PRIMARY KEY (`pdr_id`),
  KEY `fk_pago_doc_rel_idx` (`pdr_id_pago`),
  CONSTRAINT `fk_pago_doc_rel` FOREIGN KEY (`pdr_id_pago`) REFERENCES `pagos` (`pag_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=286 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-15 20:39:58
