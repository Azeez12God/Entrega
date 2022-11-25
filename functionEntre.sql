CREATE OR REPLACE FUNCTION public.cuentafutbolistas()
RETURNS int
	LANGUAGE plpgsql
AS $$
declare
   contador integer;
	BEGIN
		select count(*) into contador from futbolistas;
        return contador;
	END;
$$;