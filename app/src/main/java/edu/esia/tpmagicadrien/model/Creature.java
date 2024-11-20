package edu.esia.tpmagicadrien.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.util.ArrayList;

import edu.esia.tpmagicadrien.model.enums.CardType;
import edu.esia.tpmagicadrien.model.enums.Color;
import edu.esia.tpmagicadrien.model.enums.CreatureCharacteristic;

// Entité représentant une créature dans la base de données Room
@Entity
public class Creature extends AbstractCardWithCost {

    // Points d'attaque de la créature
    @ColumnInfo(name = "Atk")
    private final int attack;

    // Points de défense de la créature
    @ColumnInfo(name = "Def")
    private final int defense;

    // Liste des caractéristiques de la créature
    @ColumnInfo(name = "Characteristics")
    private ArrayList<CreatureCharacteristic> characteristics;

    // Constructeur de la créature avec son nom, sa couleur, ses points d'attaque et de défense
    public Creature(final String name, final Color color, final int attack, final int defense) {
        super(name, color, CardType.CREATURE);
        this.attack = attack;
        this.defense = defense;
    }

    // Getter pour les points d'attaque
    public int getAttack() {
        return this.attack;
    }

    // Getter pour les points de défense
    public int getDefense() {
        return this.defense;
    }

    // Getter pour la liste des caractéristiques
    public ArrayList<CreatureCharacteristic> getCharacteristics() {
        if (this.characteristics == null) {
            this.characteristics = new ArrayList<>();
        }
        return this.characteristics;
    }

    // Setter pour la liste des caractéristiques
    public void setCharacteristics(final ArrayList<CreatureCharacteristic> characteristics) {
        this.characteristics = characteristics;
    }

    // Méthode pour ajouter une caractéristique à la liste
    public void addCharacteristic(final CreatureCharacteristic c) {
        getCharacteristics().add(c);
    }

    // Méthode pour supprimer une caractéristique de la liste
    public void removeCharacteristic(final CreatureCharacteristic c) {
        getCharacteristics().remove(c);
    }
}