package edu.esia.tpmagicadrien.view.utils;

import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import edu.esia.tpmagicadrien.R;
import edu.esia.tpmagicadrien.model.enums.CardType;

// Adapter pour le Spinner des types de cartes
public class CardTypeSpinnerAdapter implements SpinnerAdapter {

    // Tableau des valeurs des types de cartes
    private final CardType[] values = CardType.values();

    @Override
    public View getDropDownView(final int position, final View convertView, final ViewGroup parent) {
        TextView ret = null;
        if (convertView instanceof TextView) {
            ret = (TextView) convertView;
        } else {
            ret = new TextView(parent.getContext());
        }
        // Définir le texte de l'élément du Spinner
        ret.setText(this.values[position].getLabelId());
        // Définir la taille du texte
        ret.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20.0f);
        // Définir la police du texte
        final Typeface typeface = ResourcesCompat.getFont(parent.getContext(), R.font.aladin);
        ret.setTypeface(typeface);
        return ret;
    }

    @Override
    public void registerDataSetObserver(final DataSetObserver observer) {
        // Méthode vide, pas d'observateur à enregistrer
    }

    @Override
    public void unregisterDataSetObserver(final DataSetObserver observer) {
        // Méthode vide, pas d'observateur à désenregistrer
    }

    @Override
    public int getCount() {
        // Retourner le nombre de types de cartes
        return this.values.length;
    }

    @Override
    public Object getItem(final int position) {
        // Retourner l'élément à la position donnée
        return this.values[position];
    }

    @Override
    public long getItemId(final int position) {
        // Retourner l'ID de l'élément (ici, la position)
        return position;
    }

    @Override
    public boolean hasStableIds() {
        // Indiquer que les IDs sont stables
        return true;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        // Retourner la vue de l'élément sélectionné
        return getDropDownView(position, convertView, parent);
    }

    @Override
    public int getItemViewType(final int position) {
        // Retourner le type de vue de l'élément (ici, ignoré)
        return Adapter.IGNORE_ITEM_VIEW_TYPE;
    }

    @Override
    public int getViewTypeCount() {
        // Retourner le nombre de types de vues (ici, 1)
        return 1;
    }

    @Override
    public boolean isEmpty() {
        // Indiquer si l'adapter est vide (ici, non)
        return false;
    }
}