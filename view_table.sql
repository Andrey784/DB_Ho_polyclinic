--временная таблица
DROP TABLE IF EXISTS drug_patient;
CREATE TABLE drug_patient(id_medcard integer, name_drug character varying (50), date_prescription date);
INSERT INTO drug_patient (id_medcard, name_drug, date_prescription)
SELECT id_medcard, a.name_drug, a.date_prescription
FROM (SELECT *
	FROM(SELECT * FROM drug_prescription as i 
		 		JOIN drug as j on i.id_drug=j.id_drug) as k
	JOIN prescription as l ON k.id_prescription=l.id_prescription) as a
JOIN record as b ON a.id_record = b.id_record;

SELECT * 
FROM count_doctors;

--представление
DROP VIEW IF EXISTS myview;

CREATE VIEW myview AS
	SELECT i.id_doctor, j.date_record, COUNT(*) as count_patient
	FROM doctor as i
	LEFT JOIN record as j ON i.id_doctor=j.id_doctor
	GROUP BY i.id_doctor, j.date_record
	ORDER BY j.date_record;
	
SELECT *
FROM myview
