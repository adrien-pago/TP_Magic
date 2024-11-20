package edu.esia.tpmagicadrien.model.enums;

import edu.esia.tpmagicadrien.R;

public enum Color {
    // Définition des couleurs avec leurs identifiants de chaîne, de couleur et d'icône
    BLACK(R.string.black_label, R.color.black, R.drawable.mana_noir_actif, R.drawable.mana_noir_inactif, R.drawable.card_black, R.drawable.creature_black),
    BLUE(R.string.blue_label, R.color.blue, R.drawable.mana_bleu_actif, R.drawable.mana_bleu_inactif, R.drawable.card_blue, R.drawable.creature_blue),
    COLORLESS(R.string.colorless_label, R.color.colorless, R.drawable.mana_incolore_actif, R.drawable.mana_incolore_inactif, R.drawable.card_colorless, R.drawable.creature_colorless),
    GREEN(R.string.green_label, R.color.green, R.drawable.mana_vert_actif, R.drawable.mana_vert_inactif, R.drawable.card_green, R.drawable.creature_green),
    RED(R.string.red_label, R.color.red, R.drawable.mana_rouge_actif, R.drawable.mana_rouge_inactif, R.drawable.card_red, R.drawable.creature_red),
    WHITE(R.string.white_label, R.color.white, R.drawable.mana_blanc_actif, R.drawable.mana_blanc_inactif, R.drawable.card_white, R.drawable.creature_white);

    // Identifiant de la chaîne de caractères associée à la couleur
    private final int labelId;

    // Identifiant de la couleur associée
    private final int colorId;

    // Identifiant de l'icône drawable active associée à la couleur
    private final int activeDrawableId;

    // Identifiant de l'icône drawable inactive associée à la couleur
    private final int inactiveDrawableId;

    // Identifiant de l'arrière-plan de la carte associée à la couleur
    private final int bgCardId;

    // Identifiant de l'arrière-plan de la créature associée à la couleur
    private final int bgCreatureId;

    // Constructeur de l'énumération
    private Color(final int label, final int color, final int activeDrawable, final int inactiveDrawable, final int bgCardId, final int bgCreatureId) {
        this.labelId = label;
        this.colorId = color;
        this.activeDrawableId = activeDrawable;
        this.inactiveDrawableId = inactiveDrawable;
        this.bgCardId = bgCardId;
        this.bgCreatureId = bgCreatureId;
    }

    /**
     * @return l'identifiant de la chaîne de caractères de cette couleur à utiliser avec {@link android.content.res.Resources#getString(int)}
     */
    public int getLabelId() {
        return this.labelId;
    }

    /**
     * @return l'identifiant de la couleur de cette couleur à utiliser avec {@link android.content.res.Resources#getColor(int)}
     */
    public int getColorId() {
        return this.colorId;
    }

    // Méthode pour obtenir l'identifiant de l'icône drawable active
    public int getActiveDrawableId() {
        return this.activeDrawableId;
    }

    // Méthode pour obtenir l'identifiant de l'icône drawable inactive
    public int getInactiveDrawableId() {
        return this.inactiveDrawableId;
    }

    // Méthode pour obtenir l'identifiant de l'arrière-plan de la carte
    public int getBgCardId() {
        return this.bgCardId;
    }

    // Méthode pour obtenir l'identifiant de l'arrière-plan de la créature
    public int getBgCreatureId() {
        return this.bgCreatureId;
    }
}