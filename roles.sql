
DO
$$BEGIN
IF EXISTS (SELECT FROM pg_roles WHERE rolname = 'doctor') THEN
    EXECUTE 'REVOKE ALL PRIVILEGES ON ALL TABLES IN SCHEMA public FROM doctor';
END IF;
END$$;


DROP ROLE IF EXISTS administrator;
DROP ROLE IF EXISTS doctor;

DROP USER IF EXISTS irina_nicolaeva;
DROP USER IF EXISTS pavel_lebedev;
DROP USER IF EXISTS tatyana_kuznecova;

DROP USER IF EXISTS administrator_user;

CREATE ROLE administrator;
GRANT pg_read_all_data TO administrator;
GRANT pg_write_all_data TO administrator;

CREATE ROLE doctor;

GRANT SELECT, UPDATE ON TABLE public.record TO doctor;
GRANT SELECT, UPDATE ON TABLE public.diagnosis TO doctor;
GRANT SELECT, UPDATE ON TABLE public.prescription TO doctor;
GRANT SELECT, UPDATE ON TABLE public.drug_prescription TO doctor;
GRANT SELECT(first_name, middle_name, last_name, date_birth, SNILS, gender), UPDATE ON TABLE public.patient TO doctor;

CREATE USER irina_nicolaeva WITH PASSWORD 'J9#pA5cDeZ';
CREATE USER pavel_lebedev WITH PASSWORD 'Gh7&kL3pQw';
CREATE USER tatyana_kuznecova WITH PASSWORD 'XyZ2$abC8@';

GRANT doctor TO irina_nicolaeva;
GRANT doctor TO pavel_lebedev;
GRANT doctor TO tatyana_kuznecova;

CREATE USER administrator_user WITH PASSWORD '6423#$j987^&';

GRANT administrator TO administrator_user;

