package edu.esia.tpmagicadrien.view.fragments.deckslist;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import edu.esia.tpmagicadrien.R;
import edu.esia.tpmagicadrien.model.Deck;
import edu.esia.tpmagicadrien.view.MainActivity;
import edu.esia.tpmagicadrien.view.utils.UiUtils;
import edu.esia.tpmagicadrien.viewmodel.MagicViewModel;

// Classe DeckHolder qui étend RecyclerView.ViewHolder
public class DeckHolder extends RecyclerView.ViewHolder {

    // Référence au ViewModel
    private final MagicViewModel model;
    // Référence au deck
    private Deck deck;
    // TextView pour afficher le nom du deck
    private final TextView name;
    // Bouton pour éditer le deck
    private final ImageButton btEdit;
    // Bouton pour supprimer le deck
    private final ImageButton btDelete;

    // Constructeur de DeckHolder
    public DeckHolder(@NonNull final View itemView, final MagicViewModel model) {
        super(itemView);
        this.model = model;
        this.name = itemView.findViewById(R.id.tv_card_name);
        this.btEdit = itemView.findViewById(R.id.bt_deck_edit);
        // Définir l'action du bouton d'édition
        this.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                btEditClick(v);
            }
        });
        this.btDelete = itemView.findViewById(R.id.bt_deck_delete);
        // Définir l'action du bouton de suppression
        this.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                btDeleteClick(v);
            }
        });
    }

    // Action lors du clic sur le bouton de suppression
    private void btDeleteClick(final View v) {
        Toast.makeText(this.itemView.getContext(), "Btdelete", Toast.LENGTH_SHORT).show();
    }

    // Action lors du clic sur le bouton d'édition
    private void btEditClick(final View v) {
        this.model.setCurDeck(this.deck);
        Navigation.findNavController(v).navigate(R.id.action_decksListFragment_to_deckEditFragment);
        final MainActivity mainActivity = (MainActivity) UiUtils.getActivityContaining(v);
        if (mainActivity != null) {
            mainActivity.setAddDeckButtonEnabled(false);
        }
    }

    // Obtenir le TextView du nom
    public TextView getName() {
        return this.name;
    }

    // Obtenir le deck
    public Deck getDeck() {
        return this.deck;
    }

    // Définir le deck
    public void setDeck(final Deck deck) {
        this.deck = deck;
        if (this.deck.isReserve()) {
            this.btDelete.setVisibility(View.INVISIBLE);
            this.btDelete.setEnabled(false);
        }
    }
}