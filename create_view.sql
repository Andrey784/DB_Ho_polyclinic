DROP VIEW IF EXISTS myview;

CREATE VIEW myview AS
	SELECT i.id_doctor, j.date_record, COUNT(*) as count_patient
	FROM doctor as i
	LEFT JOIN record as j ON i.id_doctor=j.id_doctor
	GROUP BY i.id_doctor, j.date_record
	ORDER BY j.date_record;
	
SELECT *
FROM myview