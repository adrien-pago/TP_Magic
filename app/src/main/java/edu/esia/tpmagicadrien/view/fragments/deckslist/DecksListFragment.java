package edu.esia.tpmagicadrien.view.fragments.deckslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.esia.tpmagicadrien.R;
import edu.esia.tpmagicadrien.model.Deck;
import edu.esia.tpmagicadrien.viewmodel.MagicViewModel;

// Fragment pour afficher la liste des decks
public class DecksListFragment extends Fragment {

    // RecyclerView pour afficher les decks
    private RecyclerView rv;
    // ViewModel pour accéder aux données
    private MagicViewModel model;

    // Constructeur public requis
    public DecksListFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflater le layout du fragment
        final View v = inflater.inflate(R.layout.fragment_decks_list, container, false);
        // Initialiser le RecyclerView
        this.rv = v.findViewById(R.id.rv_main_decks);
        // Obtenir le ViewModel
        this.model = new ViewModelProvider(getActivity()).get(MagicViewModel.class);
        // Observer les changements dans la liste des decks
        this.model.getAllDecks().observe(getActivity(), this.decksObserver);
        return v;
    }

    // Observateur pour mettre à jour la liste des decks
    private final Observer<List<Deck>> decksObserver = new Observer<List<Deck>>() {
        @Override
        public void onChanged(final List<Deck> decks) {
            // Mettre à jour le RecyclerView avec les nouveaux decks
            DecksListFragment.this.rv.post(new Runnable() {
                @Override
                public void run() {
                    final DeckAdapter adapter = new DeckAdapter(decks, DecksListFragment.this.model);
                    DecksListFragment.this.rv.setAdapter(adapter);
                    DecksListFragment.this.rv.setHasFixedSize(false);
                    DecksListFragment.this.rv.setLayoutManager(new LinearLayoutManager(DecksListFragment.this.getContext()));
                }
            });
        }
    };

}