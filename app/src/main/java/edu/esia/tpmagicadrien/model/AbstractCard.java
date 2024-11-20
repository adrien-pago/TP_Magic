package edu.esia.tpmagicadrien.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

import edu.esia.tpmagicadrien.model.enums.CardType;
import edu.esia.tpmagicadrien.model.enums.Color;

public abstract class AbstractCard {

    // Clé primaire auto-générée pour la base de données Room
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "Id")
    private long Id;

    // Identifiant du deck auquel cette carte appartient
    @ColumnInfo(name = "DeckId")
    private long deckId;

    // Nom de la carte
    @ColumnInfo(name = "Name")
    private String name;

    // Couleur de la carte (utilise l'énumération Color)
    @ColumnInfo(name = "Color")
    private Color color;

    // Type de la carte (utilise l'énumération CardType)
    @ColumnInfo(name = "Type")
    private CardType type;

    // Liste des effets associés à la carte
    @ColumnInfo(name = "Effects")
    private ArrayList<Effect> effects;

    /**
     * <b><u>Ce constructeur est public et cette classe n'est plus abstraite en raison des limitations de Room. NE PAS INSTANCIER DIRECTEMENT</u></b>
     *
     * @param name Nom de la carte
     * @param color Couleur de la carte
     * @param type Type de la carte
     */
    public AbstractCard(final String name, final Color color, final CardType type) {
        this.name = name;
        this.color = color;
        this.type = type;
    }

    // Getter pour l'identifiant de la carte
    public long getId() {
        return this.Id;
    }

    // Setter pour l'identifiant de la carte
    public void setId(final long id) {
        this.Id = id;
    }

    // Getter pour l'identifiant du deck
    public long getDeckId() {
        return this.deckId;
    }

    // Setter pour l'identifiant du deck
    public void setDeckId(final long deckId) {
        this.deckId = deckId;
    }

    // Getter pour le nom de la carte
    public String getName() {
        return this.name;
    }

    // Setter pour le nom de la carte
    public void setName(final String name) {
        this.name = name;
    }

    // Getter pour la couleur de la carte
    public Color getColor() {
        return this.color;
    }

    // Setter pour la couleur de la carte
    public void setColor(final Color color) {
        this.color = color;
    }

    // Getter pour le type de la carte
    public CardType getType() {
        return this.type;
    }

    // Setter pour le type de la carte
    public void setType(final CardType type) {
        this.type = type;
    }

    // Getter pour la liste des effets de la carte
    public ArrayList<Effect> getEffects() {
        if (this.effects == null) {
            this.effects = new ArrayList<>();
        }
        return this.effects;
    }

    // Setter pour la liste des effets de la carte
    public void setEffects(final ArrayList<Effect> effects) {
        this.effects = effects;
    }

    // Méthode pour ajouter un effet à la carte
    public void addEffect(final Effect ef) {
        getEffects().add(ef);
    }

    // Méthode pour supprimer un effet de la carte
    public void removeEffect(final Effect ef) {
        getEffects().remove(ef);
    }
}