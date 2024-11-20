package edu.esia.tpmagicadrien.model;

import androidx.room.Entity;

import edu.esia.tpmagicadrien.model.enums.CardType;
import edu.esia.tpmagicadrien.model.enums.Color;

// Entité représentant une interruption dans la base de données Room
@Entity
public class Interruption extends AbstractCardWithCost {

    // Constructeur de l'interruption avec son nom et sa couleur
    public Interruption(final String name, final Color color) {
        super(name, color, CardType.INTERRUPTION);
    }

}