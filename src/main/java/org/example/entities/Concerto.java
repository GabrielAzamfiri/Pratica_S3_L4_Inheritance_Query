package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import org.example.entities.Enum.GenereConcerto;
import org.example.entities.Enum.TipoEvento;

import java.time.LocalDate;

@DiscriminatorValue("GaraDiAtletica")
public class Concerto extends Evento {

    @Column(name = "genere", nullable = false)
    protected GenereConcerto genere;

    @Column(name = "in_streaming", nullable = false)
    protected boolean inStreaming;

    public Concerto(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento, Integer numeroMassimoPartecipanti, Location locationFk, GenereConcerto genere, boolean inStreaming) {
        super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti, locationFk);
        this.genere = genere;
        this.inStreaming = inStreaming;
    }

    public GenereConcerto getGenere() {
        return genere;
    }

    public void setGenere(GenereConcerto genere) {
        this.genere = genere;
    }

    public boolean isInStreaming() {
        return inStreaming;
    }

    public void setInStreaming(boolean inStreaming) {
        this.inStreaming = inStreaming;
    }

    @Override
    public String toString() {
        return "Concerto{" +
                "genere=" + genere +
                ", inStreaming=" + inStreaming +
                ", id=" + id +
                ", titolo='" + titolo + '\'' +
                ", dataEvento=" + dataEvento +
                ", descrizione='" + descrizione + '\'' +
                ", tipoEvento=" + tipoEvento +
                ", locationFk=" + locationFk.getNome() +
                ", numeroMassimoPartecipanti=" + numeroMassimoPartecipanti +
                '}';
    }
}
