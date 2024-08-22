package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dao.EventoDAO;
import org.example.dao.LocationDAO;
import org.example.dao.PartecipazioneDAO;
import org.example.dao.PersonDAO;
import org.example.entities.Enum.Sesso;
import org.example.entities.Enum.StatoPartecipazione;
import org.example.entities.Enum.TipoEvento;
import org.example.entities.Evento;
import org.example.entities.Location;
import org.example.entities.Partecipazione;
import org.example.entities.Persona;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        EventoDAO eddy = new EventoDAO(em);
        LocationDAO locd = new LocationDAO(em);
        PartecipazioneDAO pard = new PartecipazioneDAO(em);
        PersonDAO perd = new PersonDAO(em);

        Location location1 = new Location("Carroponte", "Milano");
        //locd.save(location1);
        Location loc1FromDB = locd.findById("159af1fe-bb76-49c7-93ed-0de59fa0e559");
        //  Evento evento1 = new Evento("PoolParty", LocalDate.of(2024, 8, 20), "Facciamo baldoria tutta la notte!", TipoEvento.PRIVATO, 10);
        Evento evento2 = new Evento("RaveParty", LocalDate.of(2024, 9, 18), "baldoria tutta notte", TipoEvento.PUBBLICO, 50, loc1FromDB);
        //  Evento evento3 = new Evento("NinnaNanna", LocalDate.of(2025, 1, 1), "Si va a dormire alle 20:00!", TipoEvento.PRIVATO, 2);
        // eddy.save(evento2);
        Evento evento2FromDB = eddy.findById("9cf459ff-75a9-48cc-a47e-b2758f891760");

        Persona diego = new Persona("Diego", "Basili", "diegobasili@gmail.com", LocalDate.of(1994, 9, 14), Sesso.MASCHILE);
        //  perd.save(diego);
        Persona diegoFromDB = perd.findById("72a18276-1a3f-4042-abba-e28e920c7579");

        Partecipazione diegoPartecipa = new Partecipazione(diegoFromDB, evento2FromDB, StatoPartecipazione.CONFERMATA);
        pard.save(diegoPartecipa);
    }

}
