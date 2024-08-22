package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.entities.Persona;
import org.example.exceptions.NotFoundException;

import java.util.UUID;

public class PersonDAO {

    private final EntityManager em;


    public PersonDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Persona person) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(person);

        transaction.commit();

        System.out.println("La persona " + person.getCognome() + " è stato salvato correttamente!");
    }

    public Persona findById(String personId) {
        Persona found = em.find(Persona.class, UUID.fromString(personId)); // Primo parametro è la classe dell'entità, secondo è l'id da cercare
        if (found == null) throw new NotFoundException(personId);
        return found;
    }

    public void findByIdAndDelete(String personId) {
        Persona found = this.findById(personId);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("La persona ' " + found.getCognome() + " è stata eliminata correttamente!");
    }
}
