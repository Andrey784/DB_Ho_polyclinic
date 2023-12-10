ALTER TABLE public.patient ENABLE ROW LEVEL SECURITY;
CREATE POLICY deny_passport_access ON public.patient
  FOR SELECT
  USING (NOT (current_user = 'doctor'));
