package edu.esia.tpmagicadrien.view.fragments.deckedit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.esia.tpmagicadrien.R;
import edu.esia.tpmagicadrien.model.AbstractCard;
import edu.esia.tpmagicadrien.viewmodel.MagicViewModel;

// Fragment pour éditer un deck
public class DeckEditFragment extends Fragment {

    // RecyclerView pour afficher les cartes du deck
    private RecyclerView rv;

    // ViewModel pour accéder aux données
    private MagicViewModel model;
    // Indique si c'est un nouveau deck
    private boolean isNewDeck = false;

    // Constructeur public requis
    public DeckEditFragment() {
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.model = new ViewModelProvider(getActivity()).get(MagicViewModel.class);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        this.model = new ViewModelProvider(getActivity()).get(MagicViewModel.class);
        this.isNewDeck = this.model.getCurDeck() == null;
        final View v = inflater.inflate(R.layout.fragment_deck_edit, container, false);
        this.rv = v.findViewById(R.id.rv_cards_list);
        this.model.getAllCardsFromDeck(this.model.getCurDeck().getId()).observe(getActivity(), this.cardsObserver);

        final EditText etDeckName = v.findViewById(R.id.et_deck_name);
        if (this.isNewDeck) {
            etDeckName.setFocusedByDefault(true);
        } else {
            etDeckName.setText(this.model.getCurDeck().getName());
        }
        final Button btSaveDeck = v.findViewById(R.id.bt_saveDeck);
        // btSaveDeck.setBackgroundResource(R.drawable.bg_mtg_background);
        return v;
    }

    // Observateur pour mettre à jour la liste des cartes
    private final Observer<List<AbstractCard>> cardsObserver = new Observer<List<AbstractCard>>() {
        @Override
        public void onChanged(final List<AbstractCard> cards) {
            DeckEditFragment.this.rv.post(new Runnable() {
                @Override
                public void run() {
                    final CardAdapter adapter = new CardAdapter(cards, DeckEditFragment.this.model);
                    DeckEditFragment.this.rv.setAdapter(adapter);
                    DeckEditFragment.this.rv.setHasFixedSize(false);
                    DeckEditFragment.this.rv.setLayoutManager(new LinearLayoutManager(DeckEditFragment.this.getContext()));
                }
            });
        }
    };

}