DROP ROLE administrator;
DROP ROLE doctor;

DROP USER irina_nicolaeva;
DROP USER pavel_lebedev;
DROP USER tatyana_kuznecova;

CREATE ROLE administrator;
GRANT ALL ON SCHEMA public TO administrator;

CREATE ROLE doctor;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO doctor;
REVOKE SELECT ON public.oms FROM doctor;


GRANT UPDATE ON TABLE public.record TO doctor;
GRANT UPDATE ON TABLE public.diagnosis TO doctor;
GRANT UPDATE ON TABLE public.prescription TO doctor;
GRANT UPDATE ON TABLE public.medcard TO doctor;


CREATE USER irina_nicolaeva WITH PASSWORD 'J9#pA5cDeZ';
CREATE USER pavel_lebedev WITH PASSWORD 'Gh7&kL3pQw';
CREATE USER tatyana_kuznecova WITH PASSWORD 'XyZ2$abC8@';

GRANT doctor TO irina_nicolaeva;
GRANT doctor TO pavel_lebedev;
GRANT doctor TO tatyana_kuznecova;

CREATE USER administrator_user WITH PASSWORD '6423#$j987^&';

GRANT administrator TO administrator_user;