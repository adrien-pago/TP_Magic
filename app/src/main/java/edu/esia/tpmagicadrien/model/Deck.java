package edu.esia.tpmagicadrien.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

import edu.esia.tpmagicadrien.model.enums.Color;

// Entité représentant un deck dans la base de données Room
@Entity
public class Deck {

    // Identifiant unique du deck
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "Id")
    private long id;

    // Indicateur si le deck est une réserve
    @ColumnInfo(name = "Reserve")
    private boolean reserve;

    // Nom du deck
    @ColumnInfo(name = "Name")
    private String name;

    // Liste des couleurs du deck
    @ColumnInfo(name = "Colors")
    private ArrayList<Color> colors;

    // Liste des cartes du deck (ignorée par Room)
    @Ignore
    private ArrayList<AbstractCard> cards;

    // Constructeur du deck avec son nom
    public Deck(final String name) {
        this.colors = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.name = name;
        this.reserve = false;
    }

    // Getter pour l'identifiant du deck
    public long getId() {
        return this.id;
    }

    // Setter pour l'identifiant du deck
    public void setId(final long id) {
        this.id = id;
    }

    // Getter pour l'indicateur de réserve
    public boolean isReserve() {
        return this.reserve;
    }

    // Setter pour l'indicateur de réserve
    public void setReserve(final boolean reserve) {
        this.reserve = reserve;
    }

    // Getter pour le nom du deck
    public String getName() {
        return this.name;
    }

    // Setter pour le nom du deck
    public void setName(final String name) {
        this.name = name;
    }

    // Getter pour la liste des couleurs du deck
    public ArrayList<Color> getColors() {
        return this.colors;
    }

    // Setter pour la liste des couleurs du deck
    public void setColors(final ArrayList<Color> colors) {
        this.colors = colors;
    }

    // Getter pour la liste des cartes du deck
    private ArrayList<AbstractCard> getCards() {
        if (this.cards == null) {
            this.cards = new ArrayList<>();
        }
        return this.cards;
    }

    // Setter pour la liste des cartes du deck
    public void setCards(final ArrayList<AbstractCard> cards) {
        this.cards = cards;
    }

    // Méthode pour ajouter une carte au deck
    public void addCard(final AbstractCard c) {
        getCards().add(c);
        c.setDeckId(this.id);
        if (!this.colors.contains(c.getColor())) {
            this.colors.add(c.getColor());
        }
    }

    // Méthode pour supprimer une carte du deck
    public void removeCard(final AbstractCard c) {
        getCards().remove(c);
        c.setDeckId(-1);
        final Color colorToTest = c.getColor();
        boolean found = false;
        for (final AbstractCard curCard : this.cards) {
            if (curCard.getColor().equals(colorToTest)) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.colors.remove(colorToTest);
        }
    }
}