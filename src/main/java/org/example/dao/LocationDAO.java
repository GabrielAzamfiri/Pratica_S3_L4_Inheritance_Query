package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.entities.Location;
import org.example.exceptions.NotFoundException;

import java.util.UUID;

public class LocationDAO {
    private final EntityManager em;


    public LocationDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Location location) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(location);

        transaction.commit();

        System.out.println("La location " + location.getNome() + " è stata salvata correttamente!");
    }

    public Location findById(String locationId) {
        Location found = em.find(Location.class, UUID.fromString(locationId)); // Primo parametro è la classe dell'entità, secondo è l'id da cercare
        if (found == null) throw new NotFoundException(locationId);
        return found;
    }

    public void findByIdAndDelete(String locationId) {
        Location found = this.findById(locationId);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("La location " + found.getNome() + " è stata eliminata correttamente!");
    }
}
