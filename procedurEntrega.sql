CREATE OR REPLACE PROCEDURE public.borrafutbolistas()
	LANGUAGE plpgsql
AS $procedure$
	BEGIN
		 DELETE FROM futbolistas WHERE futbolistaid = 2;
	END;
$procedure$
;

