package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dao.EventoDAO;
import org.example.dao.LocationDAO;
import org.example.dao.PartecipazioneDAO;
import org.example.dao.PersonDAO;
import org.example.entities.Enum.Sesso;
import org.example.entities.Enum.TipoEvento;
import org.example.entities.GaraDiAtletica;
import org.example.entities.Location;
import org.example.entities.PartitaDiCalcio;
import org.example.entities.Persona;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("eventi_inheritance_queries");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        EventoDAO eddy = new EventoDAO(em);
        LocationDAO locd = new LocationDAO(em);
        PartecipazioneDAO pard = new PartecipazioneDAO(em);
        PersonDAO perd = new PersonDAO(em);

        Location location1 = new Location("Carroponte", "Milano");
        Location location2 = new Location("Ponte di Legno", "Bergamo");

        //locd.save(location2);
        Location loc1FromDB = locd.findById("1658df05-33a0-4e64-aba5-21deeac5e9a1");
        Location loc2FromDB = locd.findById("66c404f5-d6a5-45cb-85d2-2ca7eebfda35");

        //  Evento evento1 = new Evento("PoolParty", LocalDate.of(2024, 8, 20), "Facciamo baldoria tutta la notte!", TipoEvento.PRIVATO, 10);
        //Evento evento2 = new Evento("RaveParty", LocalDate.of(2024, 9, 18), "baldoria tutta notte", TipoEvento.PUBBLICO, 50, loc1FromDB);
        //  Evento evento3 = new Evento("NinnaNanna", LocalDate.of(2025, 1, 1), "Si va a dormire alle 20:00!", TipoEvento.PRIVATO, 2);
        // eddy.save(evento2);
        //Evento evento2FromDB = eddy.findById("f949d04f-4a9d-4b27-a28b-9d2f20ae2922");

        Persona diego = new Persona("Diego", "Basili", "diegobasili@gmail.com", LocalDate.of(1994, 9, 14), Sesso.MASCHILE);
        Persona arianna = new Persona("Arianna", "Loreti", "ariannaloreti@gmail.com", LocalDate.of(1999, 12, 10), Sesso.FEMMINILE);


        //perd.save(diego);
        //perd.save(arianna);

        Persona diegoFromDB = perd.findById("1e7f9a9f-c7d3-4879-ae5e-5cda8fe5d3df");
        Persona ariannaFromDB = perd.findById("d223ff41-194d-4965-9966-503be156f5ee");

        PartitaDiCalcio partitaDiCalcio1 = new PartitaDiCalcio("Coppa del mondo marcio", LocalDate.of(2024, 9, 18), "almeno 2 gambe rotte", TipoEvento.PUBBLICO, 1000, loc1FromDB, "Milan", "Inter", "Inter", 2, 4);
        //eddy.save(partitaDiCalcio1);
        GaraDiAtletica primaGara = new GaraDiAtletica("Nuoto stile libero", LocalDate.of(2024, 8, 23), "Diego senza mani", TipoEvento.PRIVATO, 2, loc2FromDB, ariannaFromDB);
        eddy.save(primaGara);
        //Partecipazione diegoPartecipa = new Partecipazione(diegoFromDB, evento2FromDB, StatoPartecipazione.CONFERMATA);
        // pard.save(diegoPartecipa);


    }

}
