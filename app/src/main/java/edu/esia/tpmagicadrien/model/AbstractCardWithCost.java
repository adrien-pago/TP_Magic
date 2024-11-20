package edu.esia.tpmagicadrien.model;

import androidx.room.ColumnInfo;

import java.util.ArrayList;

import edu.esia.tpmagicadrien.model.enums.CardType;
import edu.esia.tpmagicadrien.model.enums.Color;

// Classe abstraite représentant une carte avec un coût
public abstract class AbstractCardWithCost extends AbstractCard {

    // Liste des coûts associés à la carte
    @ColumnInfo(name = "Costs")
    private ArrayList<Cost> costs;

    /**
     * <b><u>Ce constructeur est public et cette classe n'est plus abstraite en raison des limitations de Room. NE PAS INSTANCIER DIRECTEMENT</u></b>
     *
     * @param name Nom de la carte
     * @param color Couleur de la carte
     * @param type Type de la carte
     */
    public AbstractCardWithCost(final String name, final Color color, final CardType type) {
        super(name, color, type);
    }

    // Getter pour la liste des coûts de la carte
    public ArrayList<Cost> getCosts() {
        if (this.costs == null) {
            this.costs = new ArrayList<>();
        }
        return this.costs;
    }

    // Setter pour la liste des coûts de la carte
    public void setCosts(final ArrayList<Cost> costs) {
        this.costs = costs;
    }

    // Méthode pour ajouter un coût à la carte
    public void addCost(final Cost c) {
        getCosts().add(c);
    }

    // Méthode pour supprimer un coût de la carte
    public void removeCost(final Cost c) {
        getCosts().remove(c);
    }
}