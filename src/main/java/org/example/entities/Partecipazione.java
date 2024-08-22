package org.example.entities;

import jakarta.persistence.*;
import org.example.entities.Enum.StatoPartecipazione;

import java.util.UUID;

@Entity
@Table(name = "partecipazioni")
public class Partecipazione {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona personaId;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento eventoId;

    @Column(name = "stato_partecipazione", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoPartecipazione statoPartecipazione;

    public Partecipazione() {
    }

    public Partecipazione(Persona personaId, Evento eventoId, StatoPartecipazione statoPartecipazione) {
        this.personaId = personaId;
        this.eventoId = eventoId;
        this.statoPartecipazione = statoPartecipazione;
    }

    public UUID getId() {
        return id;
    }

    public Persona getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Persona personaId) {
        this.personaId = personaId;
    }

    public Evento getEventoId() {
        return eventoId;
    }

    public void setEventoId(Evento eventoId) {
        this.eventoId = eventoId;
    }

    public StatoPartecipazione getStatoPartecipazione() {
        return statoPartecipazione;
    }

    public void setStatoPartecipazione(StatoPartecipazione statoPartecipazione) {
        this.statoPartecipazione = statoPartecipazione;
    }

    @Override
    public String toString() {
        return "Partecipazione{" +
                "id=" + id +
                ", personaId=" + personaId +
                ", eventoId=" + eventoId +
                ", statoPartecipazione=" + statoPartecipazione +
                '}';
    }
}
