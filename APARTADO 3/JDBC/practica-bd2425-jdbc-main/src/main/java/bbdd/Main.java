package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    // @TODO Sistituye xxx por los parámetros de tu conexión
    private final static String DB_SERVER = "localhost";
    private final static int DB_PORT = 3306;
    private final static String DB_NAME = "titanic_spaceship";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "";
    private static Connection conn;

    public static void main (String [] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://" + DB_SERVER + ":" + DB_PORT + "/" + DB_NAME;
        conn = DriverManager.getConnection(url, DB_USER, DB_PASS);
    
        // @TODO Prueba sus funciones
        // 1. Añade los planetas a la base de datos
        nuevoPlaneta("Kepler-186f", 3.3E24, 8800, "Copernico");
        nuevoPlaneta("HD 209458 b (Osiris)", 1.4E27, 100000, "Beta Pictoris");
        nuevoPlaneta("LHS 1140 b", 8.3E24, 8800, "Copernico");

        // 2. Muestra por pantalla la lista de pasajeros de la cabina A-60-S
        listaPasajerosCabina("A", 60, "S");

        // 3. Muestra por pantalla una lista de sistemas, planetas y número de pasajeros con origen en ellos
        listaOrigenes();

        conn.close();
    }

    private static void nuevoPlaneta (String nombre, double masa, int radio, String sistema) throws SQLException {
        // @TODO Añade planetas a la base de datos
        PreparedStatement statement;

        statement = conn.prepareStatement("INSERT INTO planetas VALUES (?, ?, ?, ?)");
        statement.setString(1, nombre);
        statement.setDouble(2, masa);
        statement.setInt(3, radio);
        statement.setString(4, sistema);

        System.out.println("Planeta " + nombre + " insertado.");

        statement.executeUpdate();

        statement.close();
    }

    private static void listaPasajerosCabina (String cubierta, int cabina, String lado) throws SQLException {
        // @TODO Muestra por pantalla una lista de pasajeros de una cabina
        PreparedStatement statement = conn.prepareStatement("SELECT nombre, edad FROM pasajeros NATURAL JOIN cabinas WHERE numero = ? AND lado = ? AND cubierta = ?");
        statement.setInt(1, cabina);
        statement.setString(2, lado);
        statement.setString(3, cubierta);

        ResultSet res = statement.executeQuery();
        while(res.next()) {
            String nombre = res.getString("nombre");
            String edad = res.getString("edad");
            System.out.println("Nombre: " + nombre + "; Edad: " + edad);
        }

        res.close();
        statement.close();
    }

    private static void listaOrigenes() throws SQLException {
        // @TODO Muestra por pantalla una lista de planetas, sistemas y número de pasajeros provinientes de ellos
        PreparedStatement statement = conn.prepareStatement("SELECT p.sistema, p.nombre, COUNT(pa.id) FROM planetas p LEFT JOIN pasajeros pa ON p.nombre = pa.planeta_natal  GROUP BY p.sistema, p.nombre");

        ResultSet res = statement.executeQuery();
        while(res.next()) {
            String sistema = res.getString("p.sistema");
            String planeta = res.getString("p.nombre");
            String numNatales = res.getString("COUNT(pa.id)");
            System.out.println("Sistema: " + sistema + "; Planeta: " + planeta + "; Número de provenientes: " + numNatales);
        }

        res.close();
        statement.close();
    }
}