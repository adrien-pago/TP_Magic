package edu.esia.tpmagicadrien.model.enums;

import edu.esia.tpmagicadrien.R;

// Enumération représentant les différentes caractéristiques des créatures
public enum CreatureCharacteristic {
    // Définition des caractéristiques avec leurs identifiants de chaîne
    FLY(R.string.creature_carac_fly),
    WALL(R.string.creature_carac_wall),
    TRAMPLING(R.string.creature_carac_trampling),
    INITIATIVE(R.string.creature_carac_initiative);

    // Identifiant de la chaîne de caractères associée à la caractéristique
    private final int labelId;

    // Constructeur de l'énumération
    private CreatureCharacteristic(final int label) {
        this.labelId = label;
    }

    // Méthode pour obtenir l'identifiant de la chaîne de caractères
    public int getLabelId() {
        return this.labelId;
    }
}