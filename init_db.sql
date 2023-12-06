
/* Drop Tables */

DROP TABLE IF EXISTS Diagnosis;
DROP TABLE IF EXISTS Drug_Prescription;
DROP TABLE IF EXISTS Prescription;
DROP TABLE IF EXISTS Record;
DROP TABLE IF EXISTS Doctor;
DROP TABLE IF EXISTS Drug;
DROP TABLE IF EXISTS Medcard;
DROP TABLE IF EXISTS MKB_10;
DROP TABLE IF EXISTS Patient;
DROP TABLE IF EXISTS OMS;
DROP TABLE IF EXISTS Speciality_doctor;
DROP TYPE IF EXISTS gender_type;



/* Create Tables */

-- Таблица, хранит данные о поставленных диагнозах
CREATE TABLE Diagnosis
(
	ID_record int NOT NULL UNIQUE,
	-- Код диагноза по МКБ-10
	Code_diagnosis text NOT NULL UNIQUE,
	-- Первичное или повторное заболевание 
	-- False или True
	Is_first boolean NOT NULL,
	PRIMARY KEY (ID_record, Code_diagnosis)
) WITHOUT OIDS;


-- Таблица, хранящая данные о врачах в поликлинике
CREATE TABLE Doctor
(
	ID_doctor serial NOT NULL UNIQUE,
	-- Имя
	first_name varchar(20) NOT NULL,
	-- Отчество
	-- 
	middle_name varchar(20),
	-- Фамилия
	-- 
	last_name varchar(20) NOT NULL,
	login varchar(20),
	password_doctor TEXT,
	ID_speciality  int NOT NULL,
	PRIMARY KEY (ID_doctor )
) WITHOUT OIDS;


-- Таблица хранит данные о лекартсвенных препаратов, назначаемые врачом
CREATE TABLE Drug
(
	ID_drug serial NOT NULL UNIQUE,
	-- Наименование препората
	Name_drug varchar(50) NOT NULL,
	-- Дозировка
	dosage text NOT NULL,
	-- Форма выпуска
	Release_form text NOT NULL,
	-- Количество
	Quantity text NOT NULL,
	PRIMARY KEY (ID_drug)
) WITHOUT OIDS;


CREATE TABLE Drug_Prescription
(
	ID_drug int NOT NULL UNIQUE,
	ID_prescription int NOT NULL UNIQUE
) WITHOUT OIDS;


-- Хранит данные о мед. картах
CREATE TABLE Medcard
(
	ID_medcard serial NOT NULL UNIQUE,
	-- Дата заведения мед. карты
	-- 
	Date_create date NOT NULL,
	ID_patient int NOT NULL UNIQUE,
	PRIMARY KEY (ID_medcard)
) WITHOUT OIDS;


-- Классификация диагнозов по МКБ-10
CREATE TABLE MKB_10
(
	-- Код диагноза по МКБ-10
	Code_diagnosis text NOT NULL UNIQUE,
	Name_diagnosis varchar(100) NOT NULL,
	PRIMARY KEY (Code_diagnosis)
) WITHOUT OIDS;


-- Данные о ОМС пациентов
CREATE TABLE OMS
(
	ID_OMS serial NOT NULL UNIQUE,
	-- Серия ОМС
	Seria char(16) NOT NULL,
	-- Номер ОМС
	Number_OMS char(16) NOT NULL,
	Organization_name  text NOT NULL,
	PRIMARY KEY (ID_OMS)
) WITHOUT OIDS;

--Создание пользовательского типа данных - пол человека
CREATE TYPE gender_type AS enum('Mужской', 'Женский');

-- Данные о пациентах
CREATE TABLE Patient
(
	ID_patient serial NOT NULL UNIQUE,
	-- Имя
	first_name varchar(20) NOT NULL,
	-- Отчество
	-- 
	middle_name varchar(20),
	-- Фамилия
	-- 
	last_name varchar(20) NOT NULL,
	date_birth date NOT NULL,
	-- номер снилса 
	SNILS char(11) NOT NULL,
	-- вбдхранится только (YYY) XXX XX XX 
	phone_number char(10) NOT NULL,
	-- м или ж
	gender gender_type NOT NULL,
	-- Cерия и номер через пробел
	Passport_data varchar(11) NOT NULL UNIQUE,
	ID_OMS int NOT NULL UNIQUE,
	PRIMARY KEY (ID_patient)
) WITHOUT OIDS;


-- Таблица, хранящая данныо о выписанных рецептах
CREATE TABLE Prescription
(
	ID_prescription serial NOT NULL UNIQUE,
	Date_prescription date NOT NULL,
	-- Сколько месяцев действует рецепт
	Period int NOT NULL,
	Name_drug varchar(50) NOT NULL,
	ID_record int NOT NULL UNIQUE,
	PRIMARY KEY (ID_prescription)
) WITHOUT OIDS;


-- Таблица, хранящая информацию о записях врачей в мед. карте
CREATE TABLE Record
(
	ID_record serial NOT NULL UNIQUE,
	-- Дата записи
	-- 
	Date_record date NOT NULL,
	-- Жалобы пациента
	Complaints text,
	ID_medcard int NOT NULL UNIQUE,
	ID_doctor  int NOT NULL UNIQUE,
	PRIMARY KEY (ID_record)
) WITHOUT OIDS;


CREATE TABLE Speciality_doctor
(
	ID_speciality  serial NOT NULL UNIQUE,
	-- Наименование специальности врача
	-- 
	Name_speciality varchar(50) NOT NULL,
	PRIMARY KEY (ID_speciality )
) WITHOUT OIDS;



/* Create Foreign Keys */

ALTER TABLE Record
	ADD FOREIGN KEY (ID_doctor )
	REFERENCES Doctor (ID_doctor )
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Drug_Prescription
	ADD FOREIGN KEY (ID_drug)
	REFERENCES Drug (ID_drug)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Record
	ADD FOREIGN KEY (ID_medcard)
	REFERENCES Medcard (ID_medcard)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Diagnosis
	ADD FOREIGN KEY (Code_diagnosis)
	REFERENCES MKB_10 (Code_diagnosis)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Patient
	ADD FOREIGN KEY (ID_OMS)
	REFERENCES OMS (ID_OMS)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Medcard
	ADD FOREIGN KEY (ID_patient)
	REFERENCES Patient (ID_patient)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Drug_Prescription
	ADD FOREIGN KEY (ID_prescription)
	REFERENCES Prescription (ID_prescription)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Diagnosis
	ADD FOREIGN KEY (ID_record)
	REFERENCES Record (ID_record)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Prescription
	ADD FOREIGN KEY (ID_record)
	REFERENCES Record (ID_record)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Doctor
	ADD FOREIGN KEY (ID_speciality )
	REFERENCES Speciality_doctor (ID_speciality )
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



/* Comments */

COMMENT ON TABLE Diagnosis IS 'Таблица, хранит данные о поставленных диагнозах';
COMMENT ON COLUMN Diagnosis.Code_diagnosis IS 'Код диагноза по МКБ-10';
COMMENT ON COLUMN Diagnosis.Is_first IS 'Первичное или повторное заболевание 
False или True';
COMMENT ON TABLE Doctor IS 'Таблица, хранящая данные о врачах в поликлинике';
COMMENT ON COLUMN Doctor.first_name IS 'Имя';
COMMENT ON COLUMN Doctor.middle_name IS 'Отчество
';
COMMENT ON COLUMN Doctor.last_name IS 'Фамилия
';
COMMENT ON TABLE Drug IS 'Таблица хранит данные о лекартсвенных препаратов, назначаемые врачом';
COMMENT ON COLUMN Drug.Name_drug IS 'Наименование препората';
COMMENT ON COLUMN Drug.dosage IS 'Дозировка';
COMMENT ON COLUMN Drug.Release_form IS 'Форма выпуска';
COMMENT ON COLUMN Drug.Quantity IS 'Количество';
COMMENT ON TABLE Medcard IS 'Хранит данные о мед. картах';
COMMENT ON COLUMN Medcard.Date_create IS 'Дата заведения мед. карты
';
COMMENT ON TABLE MKB_10 IS 'Классификация диагнозов по МКБ-10';
COMMENT ON COLUMN MKB_10.Code_diagnosis IS 'Код диагноза по МКБ-10';
COMMENT ON TABLE OMS IS 'Данные о ОМС пациентов';
COMMENT ON COLUMN OMS.Seria IS 'Серия ОМС';
COMMENT ON COLUMN OMS.Number_OMS IS 'Номер ОМС';
COMMENT ON TABLE Patient IS 'Данные о пациентах';
COMMENT ON COLUMN Patient.first_name IS 'Имя';
COMMENT ON COLUMN Patient.middle_name IS 'Отчество
';
COMMENT ON COLUMN Patient.last_name IS 'Фамилия
';
COMMENT ON COLUMN Patient.SNILS IS 'номер снилса ';
COMMENT ON COLUMN Patient.phone_number IS 'вбдхранится только (YYY) XXX XX XX ';
COMMENT ON COLUMN Patient.gender IS 'м или ж';
COMMENT ON COLUMN Patient.Passport_data IS 'Cерия и номер через пробел';
COMMENT ON TABLE Prescription IS 'Таблица, хранящая данныо о выписанных рецептах';
COMMENT ON COLUMN Prescription.Period IS 'Сколько месяцев действует рецепт';
COMMENT ON TABLE Record IS 'Таблица, хранящая информацию о записях врачей в мед. карте';
COMMENT ON COLUMN Record.Date_record IS 'Дата записи
';
COMMENT ON COLUMN Record.Complaints IS 'Жалобы пациента';
COMMENT ON COLUMN Speciality_doctor.Name_speciality IS 'Наименование специальности врача
';



