package edu.esia.tpmagicadrien.view.fragments.deckedit;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import edu.esia.tpmagicadrien.R;
import edu.esia.tpmagicadrien.model.AbstractCard;
import edu.esia.tpmagicadrien.view.MainActivity;
import edu.esia.tpmagicadrien.view.utils.UiUtils;
import edu.esia.tpmagicadrien.viewmodel.MagicViewModel;

// ViewHolder pour afficher les informations d'une carte dans une RecyclerView
public class CardHolder extends RecyclerView.ViewHolder {

    // ViewModel pour accéder aux données
    private final MagicViewModel model;
    private AbstractCard card;
    private final ImageView ivTypeIcon;
    private final ImageView ivColorIcon;
    private final TextView name;
    private final ImageButton btEdit;
    private final ImageButton btDelete;

    // Constructeur du ViewHolder
    public CardHolder(@NonNull final View itemView, final MagicViewModel model) {
        super(itemView);
        this.model = model;
        this.ivTypeIcon = itemView.findViewById(R.id.iv_card_type_icon);
        this.ivColorIcon = itemView.findViewById(R.id.iv_card_color_icon);
        this.name = itemView.findViewById(R.id.tv_card_name);
        this.btEdit = itemView.findViewById(R.id.bt_deck_edit);
        this.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                btEditClick(v);
            }
        });
        this.btDelete = itemView.findViewById(R.id.bt_deck_delete);
        this.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                btDeleteClick(v);
            }
        });
    }

    // Méthode appelée lors du clic sur le bouton de suppression
    private void btDeleteClick(final View v) {
        Toast.makeText(this.itemView.getContext(), "Btdelete", Toast.LENGTH_SHORT).show();
    }

    // Méthode appelée lors du clic sur le bouton d'édition
    private void btEditClick(final View v) {
        this.model.setCurCard(this.card);
        Navigation.findNavController(v).navigate(R.id.action_deckEditFragment_to_cardEditFragment);
        final MainActivity mainActivity = (MainActivity) UiUtils.getActivityContaining(v);
        if (mainActivity != null) {
            mainActivity.setAddDeckButtonEnabled(false);
        }
    }

    // Getter pour le nom de la carte
    public TextView getName() {
        return this.name;
    }

    // Getter pour l'icône du type de carte
    public ImageView getIvTypeIcon() {
        return this.ivTypeIcon;
    }

    // Getter pour l'icône de la couleur de la carte
    public ImageView getIvColorIcon() {
        return this.ivColorIcon;
    }

    // Getter pour la carte associée
    public AbstractCard getCard() {
        return this.card;
    }

    // Setter pour la carte associée
    public void setCard(final AbstractCard card) {
        this.card = card;
/*
        if (this.card.isReserve()) {
            this.btDelete.setVisibility(View.INVISIBLE);
            this.btDelete.setEnabled(false);
        }
*/
    }
}
