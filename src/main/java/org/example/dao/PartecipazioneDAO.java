package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.entities.Partecipazione;
import org.example.exceptions.NotFoundException;

import java.util.UUID;

public class PartecipazioneDAO {
    private final EntityManager em;


    public PartecipazioneDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Partecipazione partecipazione) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(partecipazione);

        transaction.commit();

        System.out.println("La partecipazione " + partecipazione.getId() + " è stata salvata correttamente!");
    }

    public Partecipazione findById(String partecipazioneId) {
        Partecipazione found = em.find(Partecipazione.class, UUID.fromString(partecipazioneId)); // Primo parametro è la classe dell'entità, secondo è l'id da cercare
        if (found == null) throw new NotFoundException(partecipazioneId);
        return found;
    }

    public void findByIdAndDelete(String partecipazioneId) {
        Partecipazione found = this.findById(partecipazioneId);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("La partecipazione " + found.getId() + " è stata eliminata correttamente!");
    }
}
