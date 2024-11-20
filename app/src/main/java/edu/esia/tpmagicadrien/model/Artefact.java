package edu.esia.tpmagicadrien.model;

import androidx.room.Entity;

import edu.esia.tpmagicadrien.model.enums.CardType;
import edu.esia.tpmagicadrien.model.enums.Color;

// Entité représentant un artefact dans la base de données Room
@Entity
public class Artefact extends AbstractCardWithCost {

    // Constructeur de l'artefact avec son nom et sa couleur
    public Artefact(final String name, final Color color) {
        super(name, color, CardType.ARTEFACT);
    }

}