SELECT nombre, concat(cubierta, '-', numero_cabina, '-', lado_cabina) AS 'cubiertas',planeta_destino, sistema_destino
FROM pasajeros p JOIN cubiertas ON p.cubierta = cubiertas.letra
WHERE p.cubierta = 'A' OR p.cubierta = 'B' OR
	  p.cubierta = 'C' OR p.cubierta = 'D' AND p.criosueño = 1 AND cubiertas.clase = 3;