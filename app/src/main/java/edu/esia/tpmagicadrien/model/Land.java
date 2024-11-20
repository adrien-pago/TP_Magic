package edu.esia.tpmagicadrien.model;

import androidx.room.Entity;

import edu.esia.tpmagicadrien.model.enums.CardType;
import edu.esia.tpmagicadrien.model.enums.Color;

// Entité représentant un terrain dans la base de données Room
@Entity
public class Land extends AbstractCard {

    // Constructeur du terrain avec son nom et sa couleur
    public Land(final String name, final Color color) {
        super(name, color, CardType.LAND);
    }
}