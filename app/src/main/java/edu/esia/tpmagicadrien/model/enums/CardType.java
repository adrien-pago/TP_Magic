package edu.esia.tpmagicadrien.model.enums;

import edu.esia.tpmagicadrien.R;

// Enumération représentant les différents types de cartes
public enum CardType {
    // Définition des types de cartes avec leurs identifiants de chaîne et d'icône
    LAND(R.string.cardtype_land, R.drawable.terrain),
    CREATURE(R.string.cardtype_creature, R.drawable.creature),
    ARTEFACT(R.string.cardtype_artefact, R.drawable.artefact),
    INTERRUPTION(R.string.cardtype_interruption, R.drawable.interruption),
    RITUAL(R.string.cardtype_ritual, R.drawable.rituel);

    // Identifiant de l'icône drawable associé au type de carte
    private final int drawableIconId;

    // Identifiant de la chaîne de caractères associée au type de carte
    private final int labelId;

    // Constructeur de l'énumération
    private CardType(final int label, final int drawableIcon) {
        this.drawableIconId = drawableIcon;
        this.labelId = label;
    }

    // Méthode pour obtenir l'identifiant de la chaîne de caractères
    public int getLabelId() {
        return this.labelId;
    }

    // Méthode pour obtenir l'identifiant de l'icône drawable
    public int getDrawableIconId() {
        return this.drawableIconId;
    }
}