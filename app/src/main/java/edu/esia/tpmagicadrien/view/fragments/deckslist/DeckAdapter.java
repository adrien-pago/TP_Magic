package edu.esia.tpmagicadrien.view.fragments.deckslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.esia.tpmagicadrien.R;
import edu.esia.tpmagicadrien.model.Deck;
import edu.esia.tpmagicadrien.model.enums.Color;
import edu.esia.tpmagicadrien.view.utils.UiUtils;
import edu.esia.tpmagicadrien.viewmodel.MagicViewModel;

// Adapter pour afficher les decks dans un RecyclerView
public class DeckAdapter extends RecyclerView.Adapter<DeckHolder> {
    // Liste de tous les decks
    private final List<Deck> allDecks;
    // ViewModel pour accéder aux données
    private final MagicViewModel model;

    // Constructeur de DeckAdapter
    public DeckAdapter(final List<Deck> decks, final MagicViewModel model) {
        this.allDecks = decks;
        this.model = model;
    }

    @NonNull
    @Override
    public DeckHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        // Inflater le layout pour chaque élément de la liste
        final LayoutInflater inflater = LayoutInflater.from(this.model.getContext());
        final View view = inflater.inflate(R.layout.deck_inlist_layout, parent, false);
        view.setPadding(15, 15, 15, 15);
        return new DeckHolder(view, this.model);
    }

    @Override
    public void onBindViewHolder(@NonNull final DeckHolder holder, final int position) {
        // Obtenir le deck à la position donnée
        final Deck deck = this.allDecks.get(position);
        // Définir le deck dans le holder
        holder.setDeck(deck);
        // Définir le nom du deck
        holder.getName().setText(deck.getName());
        // Obtenir les couleurs du deck
        final List<Color> colors = deck.getColors();
        final Context ctx = this.model.getContext();
        if (!colors.isEmpty()) {
            final int[] colorsInt;
            if (colors.size() == 1) {
                colorsInt = new int[2];
                colorsInt[0] = ctx.getColor(colors.get(0).getColorId());
                colorsInt[1] = ctx.getColor(R.color.transparent);
            } else {
                colorsInt = new int[colors.size()];
                for (int i = 0; i < colors.size(); i++) {
                    colorsInt[i] = ctx.getColor(colors.get(i).getColorId());
                }
            }
            // Appliquer un gradient de couleurs au nom du deck
            UiUtils.fillCustomGradient(holder.getName(), colorsInt);
        }
    }

    @Override
    public int getItemCount() {
        // Retourner le nombre total de decks
        return this.allDecks.size();
    }

}