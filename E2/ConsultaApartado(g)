SELECT c.cubierta, c.numero, c.lado
FROM cabinas c
JOIN pasajeros p ON c.numero = p.numero_cabina
JOIN gastos g ON p.id = g.pasajero
JOIN entretenimientos e ON g.entretenimiento = e.id
WHERE g.entretenimiento = (
    SELECT g.entretenimiento
    FROM gastos g
    GROUP BY g.entretenimiento
    ORDER BY COUNT(*) DESC
    LIMIT 1
)
GROUP BY c.cubierta, c.numero, c.lado
ORDER BY c.cubierta, c.numero;
