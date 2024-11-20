package edu.esia.tpmagicadrien.model;

// Classe représentant un effet d'une carte
public class Effect {

    // Coût de l'effet
    private Cost cost;

    // Description de l'effet
    private String description;

    // Constructeur de la classe Effect
    public Effect(final Cost cost, final String description) {
        this.cost = cost;
        this.description = description;
    }

    // Getter pour le coût de l'effet
    public Cost getCost() {
        return this.cost;
    }

    // Setter pour le coût de l'effet
    public void setCost(final Cost cost) {
        this.cost = cost;
    }

    // Getter pour la description de l'effet
    public String getDescription() {
        return this.description;
    }

    // Setter pour la description de l'effet
    public void setDescription(final String description) {
        this.description = description;
    }
}