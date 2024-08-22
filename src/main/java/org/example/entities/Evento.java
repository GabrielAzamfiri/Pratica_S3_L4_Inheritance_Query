package org.example.entities;

import jakarta.persistence.*;
import org.example.entities.Enum.TipoEvento;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "event")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_evento") // OPZIONALE. Serve per rinominare la colonna DTYPE (Colonna discriminante)
public abstract class Evento {
    @Id
    @GeneratedValue
    protected UUID id;

    @Column(name = "titolo", nullable = false)
    protected String titolo;

    @Column(name = "data_evento", nullable = true)
    protected LocalDate dataEvento;

    @Column(name = "descrizione", nullable = false)
    protected String descrizione;

    @Column(name = "tipoEvento", nullable = false)
    @Enumerated(EnumType.STRING)
    protected TipoEvento tipoEvento;

    @Column(name = "num_max_partecipanti", nullable = false)
    protected Integer numeroMassimoPartecipanti;


    @ManyToOne
    @JoinColumn(name = "location_fk", nullable = false)
    protected Location locationFk;

    protected Evento() {
        // OBBLIGATORIO avere un costruttore vuoto nelle entities. Serve a JPA per poter ricreare degli oggetti
        // quando andremo a leggere i record di questa tabella
    }

    public Evento(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento, Integer numeroMassimoPartecipanti, Location locationFk) {
        this.titolo = titolo;
        this.dataEvento = dataEvento;
        this.descrizione = descrizione;
        this.tipoEvento = tipoEvento;
        this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
        this.locationFk = locationFk;
    }


    public UUID getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Integer getNumeroMassimoPartecipanti() {
        return numeroMassimoPartecipanti;
    }

    public void setNumeroMassimoPartecipanti(Integer numeroMassimoPartecipanti) {
        this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
    }

    public Location getLocationFk() {
        return locationFk;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", dataEvento=" + dataEvento +
                ", descrizione='" + descrizione + '\'' +
                ", tipoEvento=" + tipoEvento +
                ", numeroMassimoPartecipanti=" + numeroMassimoPartecipanti +
                ", locationFk=" + locationFk.getNome() +
                '}';
    }
}
