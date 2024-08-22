package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.entities.Concerto;
import org.example.entities.Enum.GenereConcerto;
import org.example.entities.Evento;
import org.example.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public class EventoDAO {
    // DAO (Data Access Object) è un Design Pattern. Questa classe ci serve per semplificare l'interazione con il database
    // Semplificare nel senso di nascondere dei dettagli implementativi che in alcuni casi potrebbero essere anche ben complessi
    // Nascondendoli forniamo a chi avrà bisogno di interagire con la tabella degli studenti, dei metodi pratici e semplici da utilizzare
    // magari anche dotandoli di nomi appropriati e "parlanti"
    private final EntityManager em;

    public EventoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Evento evento) {
        // 1. Chiediamo all'entity manager di fornirci una transazione
        EntityTransaction transaction = em.getTransaction();

        // 2. Facciamo partire la transazione
        transaction.begin();

        // 3. Aggiungiamo lo studente al Persistence Context (a questo step lo studente non è ancora salvato a db)
        em.persist(evento);

        // 4. Concludiamo la transazione (qua lo studente verrà effettivamente salvato)
        transaction.commit();

        System.out.println("L'Evento " + evento.getTitolo() + " è stato salvato correttamente!");
    }

    public Evento findById(String eventId) {
        Evento found = em.find(Evento.class, UUID.fromString(eventId)); // Primo parametro è la classe dell'entità, secondo è l'id da cercare
        if (found == null) throw new NotFoundException(eventId);
        return found;
    }

    public void findByIdAndDelete(String eventId) {
        // 0. Cerco l'evento nel db
        Evento found = this.findById(eventId);

        // 1. Chiediamo all'entity manager di fornirci una transazione
        EntityTransaction transaction = em.getTransaction();

        // 2. Facciamo partire la transazione
        transaction.begin();

        // 3. Rimuoviamo l'evento' dal Persistence Context (a questo step l'evento' non è ancora stato rimosso effettivamente dal db)
        em.remove(found);

        // 4. Concludiamo la transazione (qua l'evento verrà effettivamente rimosso)
        transaction.commit();

        System.out.println("L'evento' " + found.getTitolo() + " è stato eliminato correttamente!");
    }

    public List<Concerto> getConcertiInStreaming(Boolean scelta) {
        TypedQuery<Concerto> query = em.createQuery("SELECT a FROM Concerto a WHERE a.inStreaming = :scelta ", Concerto.class);
        query.setParameter("scelta", scelta);

        return query.getResultList();
    }

    public List<Concerto> getConcertiPerGenere(GenereConcerto genere) {
        TypedQuery<Concerto> query = em.createQuery("SELECT a FROM Concerto a WHERE a.genere = :genere ", Concerto.class);
        query.setParameter("genere", genere);

        return query.getResultList();
    }
}

