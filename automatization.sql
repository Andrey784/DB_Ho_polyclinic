-- Процедура для создания "нвоого доктора" 
CREATE OR REPLACE PROCEDURE create_doctor(
    first_name character varying(20), 
    middle_name character varying(20), 
    last_name character varying(20), 
    login character varying(20), 
    password_doctor text, 
    id_speciality integer
) LANGUAGE SQL 
AS $$
    INSERT INTO public.doctor (first_name, middle_name, last_name, login, password_doctor, id_speciality) 
    VALUES (first_name, middle_name, last_name, login, password_doctor, id_speciality);
$$;

-- CALL create_doctor('Name2', 'MidName2', 'LastName2', 'Login', pgp_sym_encrypt('J9#pA5zDaZ', 'this_is_a_dummy_secret_key')::text, 80);
-- SELECT * FROM public.doctor;

-- функция для вычисления возраста пациента 
CREATE OR REPLACE FUNCTION calculate_age(date_of_birth date)
RETURNS INTEGER AS $$
DECLARE
    age_in_years INTEGER;
BEGIN
    SELECT EXTRACT(YEAR FROM age(current_date, date_of_birth)) INTO age_in_years;
    RETURN age_in_years;
END;
$$ LANGUAGE plpgsql;

-- SELECT calculate_age('1974-04-19');


-- Функция для получения записей по дате
CREATE OR REPLACE FUNCTION get_records_by_date(input_date date) 
RETURNS TABLE (
    record_id int,
    date_record date,
    complaints text,
    medcard_id int,
    doctor_id int
) AS $$
BEGIN
    RETURN QUERY 
    SELECT 
        r.ID_record, 
        r.Date_record, 
        r.Complaints, 
        r.ID_medcard, 
        r.ID_doctor
    FROM 
        public.record r
    WHERE 
        r.Date_record = input_date;
END;
$$ LANGUAGE plpgsql;

-- SELECT * FROM get_records_by_date('2023-10-26');



