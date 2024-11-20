package edu.esia.tpmagicadrien.model;

import edu.esia.tpmagicadrien.model.enums.Color;

// Classe représentant le coût d'une carte
public class Cost {
    // Montant du coût
    private int amount;

    // Couleur associée au coût
    private Color color;

    // Constructeur de la classe Cost
    public Cost(final int amount, final Color color) {
        this.amount = amount;
        this.color = color;
    }

    // Getter pour le montant du coût
    public int getAmount() {
        return this.amount;
    }

    // Setter pour le montant du coût
    public void setAmount(final int amount) {
        this.amount = amount;
    }

    // Getter pour la couleur associée au coût
    public Color getColor() {
        return this.color;
    }

    // Setter pour la couleur associée au coût
    public void setColor(final Color color) {
        this.color = color;
    }
}