package edu.esia.tpmagicadrien.model;

import androidx.room.Entity;

import edu.esia.tpmagicadrien.model.enums.CardType;
import edu.esia.tpmagicadrien.model.enums.Color;

// Entité représentant un rituel dans la base de données Room
@Entity
public class Ritual extends AbstractCardWithCost {

    // Constructeur du rituel avec son nom et sa couleur
    public Ritual(final String name, final Color color) {
        super(name, color, CardType.RITUAL);
    }

}