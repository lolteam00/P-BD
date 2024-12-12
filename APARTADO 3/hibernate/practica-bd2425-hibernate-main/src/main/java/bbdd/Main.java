package bbdd;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import bbdd.model.Pasajero;
import bbdd.model.Entretenimiento;
import bbdd.model.Gasto;


public class Main 
{
    public static void main( String[] args ) throws IOException {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        // @TODO Crear un nuevo pasajero llamado "Din Djarin" y un nuevo entretenimiento
        // llamado "Bounty Hunting" y guardarlos en la base de datos. Añade un gasto de
        // 100 a "Din Djarin" para "Bounty Hunting".

        Pasajero dinDjarin = new Pasajero("Din Djarin");
        Entretenimiento BountyHunting = new Entretenimiento("Bounty Hunting");
        Gasto din100Bounty = new Gasto(dinDjarin, BountyHunting, 100);
        dinDjarin.getGastos().add(din100Bounty);

        session.beginTransaction();
        session.save(dinDjarin);
        session.save(BountyHunting);
        session.getTransaction().commit();
        

        // @TODO Leer el fichero CSV gastos.csv que se encuentra en el directorio "resources" y 
        // recorrerlo usando CSVParser para crear los pasajeros, entretenimientos y gastos que
        // en él se encuentran. Dichos gastos deberán ser asignados al pasajero/a y al entretenimiento 
        // correspondientes. Se deben guardar todos estos datos en la base de datos.

        Reader reader = Files.newBufferedReader(Paths.get("./src/main/resources/gastos.csv"));
        CSVFormat format = CSVFormat.DEFAULT.withHeader();
        CSVParser parser = new CSVParser(reader, format);

        for(CSVRecord record : parser) {
            String nombrePasajero = record.get(1);
            String nombreEntretenimiento = record.get(2);
            Integer cantidadGasto = Integer.parseInt(record.get(3));

            Pasajero pasajero = new Pasajero(nombrePasajero);
            Entretenimiento entretenimiento = new Entretenimiento(nombreEntretenimiento);
            Gasto gasto = new Gasto(pasajero, entretenimiento, cantidadGasto);

            session.beginTransaction();
            session.save(pasajero);
            session.save(entretenimiento);
            session.getTransaction().commit();
        }

        parser.close();
        reader.close();

        session.close();
    }
}





