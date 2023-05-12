CREATE DATABASE consultoria_medica;
USE consultoria_medica;

CREATE TABLE `consultoria_medica`.`administrador` (
  `idadmin` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `usuario` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(100) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `saldo` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`idadmin`));

CREATE TABLE `consultoria_medica`.`especialidad` (
  `idespecialidad` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(200) NOT NULL,
  `idadmin` INT NOT NULL,
  `idmedico` INT NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idespecialidad`),
  INDEX `fk_especialidad_ad_idx` (`idadmin` ASC) VISIBLE,
  CONSTRAINT `fk_especialidad_ad`
    FOREIGN KEY (`idadmin`)
    REFERENCES `consultoria_medica`.`administrador` (`idadmin`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
CREATE TABLE `consultoria_medica`.`tipo_examen` (
  `idtipo_examen` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(200) NOT NULL,
  `idadmin` INT NOT NULL,
  `idlaboratorio` INT NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idtipo_examen`),
  INDEX `fk_tipo_examen_ad_idx` (`idadmin` ASC) VISIBLE,
  CONSTRAINT `fk_tipo_examen_ad`
    FOREIGN KEY (`idadmin`)
    REFERENCES `consultoria_medica`.`administrador` (`idadmin`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
CREATE TABLE `consultoria_medica`.`porcentaje_cobro` (
  `idporcentaje` INT NOT NULL AUTO_INCREMENT,
  `porcentaje` DECIMAL(3,3) NOT NULL,
  `fecha_activacion` DATETIME NOT NULL,
  `fecha_desativacion` DATETIME NULL,
  `idadmin` INT NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idporcentaje`),
  INDEX `fk_porcentaje_ad_idx` (`idadmin` ASC) VISIBLE,
  CONSTRAINT `fk_porcentaje_ad`
    FOREIGN KEY (`idadmin`)
    REFERENCES `consultoria_medica`.`administrador` (`idadmin`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
CREATE TABLE `consultoria_medica`.`medico` (
  `idmedico` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `usuario` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(100) NOT NULL,
  `direccion` VARCHAR(100) NOT NULL,
  `cui` INT NOT NULL,
  `telefono` INT NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `saldo` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`idmedico`));
  
  CREATE TABLE `consultoria_medica`.`especialidad_medico` (
  `idespecialidad_medico` INT NOT NULL AUTO_INCREMENT,
  `idmedico` INT NOT NULL,
  `idespecialidad` INT NOT NULL,
  `precio` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`idespecialidad_medico`),
  INDEX `fk_em_medico_idx` (`idmedico` ASC) VISIBLE,
  INDEX `fk_em_especialidad_idx` (`idespecialidad` ASC) VISIBLE,
  CONSTRAINT `fk_em_medico`
    FOREIGN KEY (`idmedico`)
    REFERENCES `consultoria_medica`.`medico` (`idmedico`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_em_especialidad`
    FOREIGN KEY (`idespecialidad`)
    REFERENCES `consultoria_medica`.`especialidad` (`idespecialidad`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `consultoria_medica`.`horario_atencion` (
  `idhorario` INT NOT NULL AUTO_INCREMENT,
  `idmedico` INT NOT NULL,
  `hora_inicial` TIME NOT NULL,
  `hora_final` TIME NOT NULL,
  PRIMARY KEY (`idhorario`),
  INDEX `fk_horario_me_idx` (`idmedico` ASC) VISIBLE,
  CONSTRAINT `fk_horario_me`
    FOREIGN KEY (`idmedico`)
    REFERENCES `consultoria_medica`.`medico` (`idmedico`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `consultoria_medica`.`laboratorio` (
  `idlaboratorio` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `usuario` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(100) NOT NULL,
  `direccion` VARCHAR(100) NOT NULL,
  `cui` INT NOT NULL,
  `telefono` INT NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `fecha_fundacion` DATE NOT NULL,
  `saldo` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`idlaboratorio`));
  
CREATE TABLE `consultoria_medica`.`examen_laboratorio` (
  `idexamenlab` INT NOT NULL AUTO_INCREMENT,
  `idlaboratorio` INT NOT NULL,
  `idtipo` INT NOT NULL,
  `precio` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`idexamenlab`),
  INDEX `fk_el_lab_idx` (`idlaboratorio` ASC) VISIBLE,
  INDEX `fk_el_examen_idx` (`idtipo` ASC) VISIBLE,
  CONSTRAINT `fk_el_lab`
    FOREIGN KEY (`idlaboratorio`)
    REFERENCES `consultoria_medica`.`laboratorio` (`idlaboratorio`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_el_examen`
    FOREIGN KEY (`idtipo`)
    REFERENCES `consultoria_medica`.`tipo_examen` (`idtipo_examen`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
 
 CREATE TABLE `consultoria_medica`.`paciente` (
  `idpaciente` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `usuario` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(100) NOT NULL,
  `direccion` VARCHAR(100) NOT NULL,
  `cui` INT NOT NULL,
  `telefono` INT NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `saldo` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`idpaciente`));
  
CREATE TABLE `consultoria_medica`.`recarga` (
  `idrecarga` INT NOT NULL AUTO_INCREMENT,
  `idpaciente` INT NOT NULL,
  `fecha` DATETIME NOT NULL,
  `monto` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`idrecarga`),
  INDEX `fk_recarga_p_idx` (`idpaciente` ASC) VISIBLE,
  CONSTRAINT `fk_recarga_p`
    FOREIGN KEY (`idpaciente`)
    REFERENCES `consultoria_medica`.`paciente` (`idpaciente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `consultoria_medica`.`consulta` (
  `idconsulta` INT NOT NULL AUTO_INCREMENT,
  `idpaciente` INT NOT NULL,
  `idmedico` INT NOT NULL,
  `idespecialidad` INT NOT NULL,
  `porcentaje_app` DECIMAL(3,3) NOT NULL,
  `fecha_creacion` DATE NOT NULL,
  `precio` DECIMAL(10,2) NOT NULL,
  `informe` VARCHAR(500) NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idconsulta`),
  INDEX `fk_consulta_p_idx` (`idpaciente` ASC) VISIBLE,
  INDEX `fk_consulta_m_idx` (`idmedico` ASC) VISIBLE,
  CONSTRAINT `fk_consulta_p`
    FOREIGN KEY (`idpaciente`)
    REFERENCES `consultoria_medica`.`paciente` (`idpaciente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_consulta_m`
    FOREIGN KEY (`idmedico`)
    REFERENCES `consultoria_medica`.`medico` (`idmedico`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
CREATE TABLE `consultoria_medica`.`fecha_agendada` (
  `idfecha_agendada` INT NOT NULL AUTO_INCREMENT,
  `idconsulta` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `hora_inicial` TIME NOT NULL,
  `hora_final` TIME NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idfecha_agendada`),
  INDEX `fk_fecha_agendada_idx` (`idconsulta` ASC) VISIBLE,
  CONSTRAINT `fk_fecha_agendada`
    FOREIGN KEY (`idconsulta`)
    REFERENCES `consultoria_medica`.`consulta` (`idconsulta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
CREATE TABLE `consultoria_medica`.`examen_consulta` (
  `idexamen_consulta` INT NOT NULL AUTO_INCREMENT,
  `idconsulta` INT NOT NULL,
  `idexamen` INT NOT NULL,
  `precio` DECIMAL(10,2) NOT NULL,
  `resultado` BLOB NOT NULL,
  PRIMARY KEY (`idexamen_consulta`),
  INDEX `fk_examen_consulta_idx` (`idconsulta` ASC) VISIBLE,
  INDEX `fk_exc_tipo_idx` (`idexamen` ASC) VISIBLE,
  CONSTRAINT `fk_examen_consulta`
    FOREIGN KEY (`idconsulta`)
    REFERENCES `consultoria_medica`.`consulta` (`idconsulta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_exc_tipo`
    FOREIGN KEY (`idexamen`)
    REFERENCES `consultoria_medica`.`tipo_examen` (`idtipo_examen`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
CREATE TABLE `consultoria_medica`.`solicitud_examen` (
  `idsolicitud` INT NOT NULL AUTO_INCREMENT,
  `idpaciente` INT NOT NULL,
  `idlaboratorio` INT NOT NULL,
  `porcentaje` DECIMAL(3,3) NOT NULL,
  `fecha_solicitado` DATE NOT NULL,
  `fecha_finalizado` DATE NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idsolicitud`),
  INDEX `fk_solicitud_examen_idx` (`idpaciente` ASC) VISIBLE,
  INDEX `fk_solicitud_examen_idx1` (`idlaboratorio` ASC) VISIBLE,
  CONSTRAINT `fk_solicitud_examen`
    FOREIGN KEY (`idpaciente`)
    REFERENCES `consultoria_medica`.`paciente` (`idpaciente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_solicitudex_lab`
    FOREIGN KEY (`idlaboratorio`)
    REFERENCES `consultoria_medica`.`laboratorio` (`idlaboratorio`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `consultoria_medica`.`examenes_solicitados` (
  `idexamenes_solicitados` INT NOT NULL AUTO_INCREMENT,
  `idsolicitud` INT NOT NULL,
  `idexamen` INT NOT NULL,
  `precio` DECIMAL(10,2) NOT NULL,
  `resultado` BLOB NULL,
  PRIMARY KEY (`idexamenes_solicitados`),
  INDEX `fk_examenes_solicitud_idx` (`idsolicitud` ASC) VISIBLE,
  INDEX `fk_examenessol_tipo_idx` (`idexamen` ASC) VISIBLE,
  CONSTRAINT `fk_examenes_solicitud`
    FOREIGN KEY (`idsolicitud`)
    REFERENCES `consultoria_medica`.`solicitud_examen` (`idsolicitud`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_examenessol_tipo`
    FOREIGN KEY (`idexamen`)
    REFERENCES `consultoria_medica`.`tipo_examen` (`idtipo_examen`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);    