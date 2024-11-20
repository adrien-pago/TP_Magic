package edu.esia.tpmagicadrien.view.fragments.deckedit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.esia.tpmagicadrien.R;
import edu.esia.tpmagicadrien.model.AbstractCard;
import edu.esia.tpmagicadrien.model.enums.Color;
import edu.esia.tpmagicadrien.viewmodel.MagicViewModel;

// Adaptateur pour afficher les cartes dans une RecyclerView
public class CardAdapter extends RecyclerView.Adapter<CardHolder> {
    // Liste de toutes les cartes
    private final List<AbstractCard> allCards;
    // ViewModel pour accéder aux données
    private final MagicViewModel model;

    // Constructeur de l'adaptateur
    public CardAdapter(final List<AbstractCard> cards, final MagicViewModel model) {
        this.allCards = cards;
        this.model = model;
    }

    // Crée un nouveau ViewHolder pour une carte
    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(this.model.getContext());
        final View view = inflater.inflate(R.layout.card_inlist_layout, parent, false);
        view.setPadding(15, 15, 15, 15);
        return new CardHolder(view, this.model);
    }

    // Lie les données d'une carte à un ViewHolder
    @Override
    public void onBindViewHolder(@NonNull final CardHolder holder, final int position) {
        final Context ctx = this.model.getContext();
        final AbstractCard card = this.allCards.get(position);
        holder.setCard(card);
        holder.getName().setText(card.getName());
        final Color colors = card.getColor();
        holder.getIvColorIcon().setImageDrawable(ctx.getDrawable(card.getColor().getInactiveDrawableId()));
        holder.getIvTypeIcon().setImageDrawable(ctx.getDrawable(card.getType().getDrawableIconId()));
    }

    // Retourne le nombre total de cartes
    @Override
    public int getItemCount() {
        return this.allCards.size();
    }
}